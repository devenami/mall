package edu.zhoutt.mall.configuration;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.dao.IUserMapper;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.util.JsonUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    private IUserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(true);
        Object user = session.getAttribute("user");

        if (user == null) {
            resolveError(response);
            return false;
        }

        if (userMapper == null) {
            userMapper = ApplicationContextContainer.getBean(IUserMapper.class);
        }

        User exists = userMapper.findById(((User) user).getId());

        if (exists == null) {
            resolveError(response);
            return false;
        }

        session.setAttribute("user", exists);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void resolveError(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String json = JsonUtil.toJson(HttpResult.customer(4, "please login", null));
        writer.write(json);
        writer.flush();
    }


}
