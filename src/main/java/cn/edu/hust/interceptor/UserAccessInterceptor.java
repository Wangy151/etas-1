package cn.edu.hust.interceptor;

import cn.edu.hust.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiaolei03 on 16/12/5.
 */

/**
 * 未使用
 */
public class UserAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        if (null == session.getAttribute("user")) {
            System.out.println("用户未登录");
            httpServletResponse.sendRedirect("/user_no_login.html");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = httpServletRequest.getSession(true);
        User user = (User) httpSession.getAttribute("user");
        String role = user.getRole();

        String uri = httpServletRequest.getRequestURI();

        if (uri.startsWith("/home/index")) {
            System.out.println("访问主页");
        } else if (("学生".equalsIgnoreCase(role) && uri.startsWith("/home/student")) ||
                ("学院教务员".equalsIgnoreCase(role) && uri.startsWith("/home/teacher")) ||
                ("管理员".equalsIgnoreCase(role) && uri.startsWith("/home/admin"))) {
            System.out.println("权限验证通过, role: " + role + " uri: " + uri);
        } else {
            System.out.println("没有权限, role: " + role + " uri: " + uri);
            modelAndView.setViewName("no_access_rights");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
