package edu.zhoutt.mall.service;

import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Product;

public interface IHotService {

    Page<Product> getPage(Pageable pageable);

}
