package edu.zhoutt.mall.service;

import edu.zhoutt.mall.common.IsDown;
import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface IProductService {

    Product add(String name, String image, BigDecimal price, String description, Long total, Long categoryId);

    Long update(Long id, String name, String image, BigDecimal price, String description, Long total, Long categoryId);

    Long changeIsDown(Long id, IsDown isDown);

    Long delete(Long id);

    Product getSingle(Long id);

    Page<Product> getPageByCategory(Long categoryId, Pageable pageable);

    String fileUpload(MultipartFile file);
}
