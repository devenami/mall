package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.common.IsDown;
import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.dao.IHotMapper;
import edu.zhoutt.mall.pojo.Product;
import edu.zhoutt.mall.service.IHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotServiceImpl implements IHotService {

    @Autowired
    private IHotMapper hotMapper;

    @Override
    public Page<Product> getPage(Pageable pageable) {

        return hotMapper.findPageByIsDown(IsDown.NORMAL.getCode(), pageable);
    }
}
