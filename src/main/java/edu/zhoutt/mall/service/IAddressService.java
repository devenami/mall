package edu.zhoutt.mall.service;

import edu.zhoutt.mall.pojo.Address;
import edu.zhoutt.mall.pojo.User;

import java.util.List;

public interface IAddressService {

    Address add(User user, String name, String phone, String address);

    Long delete(Long id);

    Long update(Long id, String name, String phone, String addr);

    List<Address> getListByUserId(Long userId);
}
