package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.dao.IAddressMapper;
import edu.zhoutt.mall.pojo.Address;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressMapper addressMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address add(User user, String name, String phone, String addr) {

        Date currTime = new Date();

        Address address = new Address();
        address.setName(name);
        address.setPhone(phone);
        address.setAddress(addr);
        address.setUserId(user.getId());
        address.setCreateTime(currTime);
        address.setUpdateTime(currTime);

        addressMapper.save(address);

        return address;
    }

    @Override
    public Long delete(Long id) {

        return addressMapper.deleteById(id);
    }

    @Override
    public Long update(Long id, String name, String phone, String addr) {

        Address address = addressMapper.findById(id);
        Assert.notNull(address, "没有查询到收货地址信息");

        address.setName(name);
        address.setPhone(phone);
        address.setAddress(addr);
        address.setUpdateTime(new Date());

        return addressMapper.update(address);
    }

    @Override
    public List<Address> getListByUserId(Long userId) {

        return addressMapper.findByUserId(userId);
    }
}
