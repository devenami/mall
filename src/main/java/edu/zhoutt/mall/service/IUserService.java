package edu.zhoutt.mall.service;

import edu.zhoutt.mall.common.UserRole;
import edu.zhoutt.mall.pojo.User;

import javax.servlet.http.HttpSession;

public interface IUserService {

    Integer checkUsername(String username);

    User register(String username, String password, Integer role);

    User login(String username, String password, HttpSession session);

    Long updateUsername(HttpSession session, User user, String username, UserRole userRole);

    Long updatePassword(User user, String password, UserRole userRole);
}
