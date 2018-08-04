package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.common.Consts;
import edu.zhoutt.mall.common.UserRole;
import edu.zhoutt.mall.dao.IUserMapper;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.service.IUserService;
import edu.zhoutt.mall.util.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private IUserMapper userMapper;

    public UserServiceImpl(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public Integer checkUsername(String username) {

        Assert.notNull(username, "用户名不能为空");

        return userMapper.countByUsernameAndRole(username, UserRole.USER.getCode());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User register(String username, String password, Integer role) {

        Assert.hasText(username, "用户名不能为空");
        Assert.hasText(password, "密码不能为空");
        Assert.notNull(UserRole.fromCode(role), "身份信息不能为空");

        Date currTime = new Date();
        password = Md5Util.encode(password);

        User user = User.builder()
                .username(username).password(password).role(role)
                .createTime(currTime).updateTime(currTime).build();
        userMapper.save(user);

        // 返回数据
        user.setPassword(null);
        return user;
    }

    @Override
    public User login(String username, String password, HttpSession session) {
        User user = userMapper.findByUsernameAndPassword(username, Md5Util.encode(password));
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(Consts.expireTime);
        }
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long updateUsername(HttpSession session, User user, String username, UserRole userRole) {

        Long id = user.getId();

        user = userMapper.findById(id);
        Assert.notNull(user, "用户信息不存在");

        Assert.isTrue(user.getRole() == userRole.getCode(), "用户角色信息验证失败");

        int count = userMapper.countByUsernameAndRole(username, userRole.getCode());
        Assert.isTrue(count == 0, "该用户名已存在");

        user.setUsername(username);
        user.setUpdateTime(new Date());

        Long success = userMapper.update(user);
        if (success == 1) {
            user.setPassword(null);
            session.setAttribute("user", user);
        }

        return success;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long updatePassword(User user, String password, UserRole userRole) {

        Long id = user.getId();

        user = userMapper.findById(id);
        Assert.notNull(user, "用户信息不存在");

        Assert.isTrue(user.getRole() == userRole.getCode(), "用户角色信息验证失败");

        user.setPassword(Md5Util.encode(password));

        return userMapper.update(user);
    }
}
