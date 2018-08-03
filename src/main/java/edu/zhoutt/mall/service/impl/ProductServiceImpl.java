package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.common.IsDown;
import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.dao.ICategoryMapper;
import edu.zhoutt.mall.dao.IProductMapper;
import edu.zhoutt.mall.helper.FTPHelper;
import edu.zhoutt.mall.pojo.Category;
import edu.zhoutt.mall.pojo.Product;
import edu.zhoutt.mall.service.IProductService;
import edu.zhoutt.mall.util.FileUtil;
import edu.zhoutt.mall.util.StringUtil;
import edu.zhoutt.mall.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private ICategoryMapper categoryMapper;

    @Autowired
    private FTPHelper ftpHelper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product add(String name, String image, BigDecimal price, String description, Long total, Long categoryId) {

        Date currTime = new Date();

        Product product = new Product();
        product.setName(name);
        product.setImage(image);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        product.setIsDown(IsDown.NORMAL.getCode());
        product.setTotal(total);
        product.setSell(0L);
        product.setCreateTime(currTime);
        product.setUpdateTime(currTime);

        productMapper.save(product);

        return product;
    }

    @Override
    public Long update(Long id, String name, String image, BigDecimal price, String description, Long total, Long categoryId) {

        Product product = productMapper.findById(id);
        Assert.notNull(product, "商品不存在");

        product.setName(name);
        product.setImage(image);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        product.setTotal(total);
        product.setSell(0L);
        product.setUpdateTime(new Date());

        return productMapper.update(product);
    }


    @Override
    public Long changeIsDown(Long id, IsDown isDown) {

        Product product = productMapper.findById(id);
        Assert.notNull(product, "商品不存在");

        product.setIsDown(isDown.getCode());
        product.setUpdateTime(new Date());

        return productMapper.update(product);
    }

    @Override
    public Long delete(Long id) {

        return productMapper.deleteById(id);
    }

    @Override
    public Product getSingle(Long id) {

        return productMapper.findById(id);
    }

    @Override
    public Page<Product> getProductListByPage(Long categoryId, String keyword, Pageable pageable) {

        List<Long> categoryIds = null;

        if (categoryId != null) {

            categoryIds = new ArrayList<>();

            Category category = categoryMapper.findById(categoryId);
            Assert.notNull(category, "分类信息不正确");

            categoryIds.add(categoryId);
            findSubCategoryId(categoryIds, categoryId);
        }

        if (StringUtil.hasText(keyword)) {
            keyword = "%" + keyword + "%";
        }

        if (categoryId == null && !StringUtil.hasText(keyword)) {
            throw new IllegalArgumentException("参数异常，直接返回");
        }

        return productMapper.findPageByCategoryAndKeyword(categoryIds, keyword, pageable);
    }


    /**
     * 查询指定分类下的所有子分类Id
     */
    private void findSubCategoryId(List<Long> categoryIds, Long parentId) {

        List<Category> categories = categoryMapper.findByParentId(parentId);
        for (Category category : categories) {
            Long id = category.getId();
            categoryIds.add(id);
            findSubCategoryId(categoryIds, id);
        }
    }

    @Override
    public String fileUpload(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String absPath = "/product/" + UUIDUtil.getUuid() + suffix;

        InputStream in = null;

        try {
            in = file.getInputStream();
            ftpHelper.toFTPServer(absPath, in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return absPath;
    }

}
