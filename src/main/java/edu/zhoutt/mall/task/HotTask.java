package edu.zhoutt.mall.task;

import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.dao.IHotMapper;
import edu.zhoutt.mall.dao.IProductMapper;
import edu.zhoutt.mall.pojo.Hot;
import edu.zhoutt.mall.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class HotTask {

    @Autowired
    private IHotMapper hotMapper;

    @Autowired
    private IProductMapper productMapper;

    /**
     * 每小时生成热卖商品
     */
    @Scheduled(cron = "0 0 0/1 * * *")
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateHotProduct() {

        Date currTime = new Date();

        Pageable pageable = Pageable.of(0, 10);
        Page<Product> page = productMapper.findPageBySellDesc(pageable);
        List<Product> products = page.getData();
        for (Product product : products) {
            Long productId = product.getId();
            Long sell = product.getSell();
            Hot hot = hotMapper.findByProductId(productId);
            if (hot == null) {
                hot = new Hot();
                hot.setProductId(productId);
                hot.setSell(sell);
                hot.setCreateTime(currTime);
                hot.setUpdateTime(currTime);
                hotMapper.save(hot);
            } else {
                hot.setSell(sell);
                hot.setUpdateTime(currTime);
                hotMapper.update(hot);
            }
        }
    }

}
