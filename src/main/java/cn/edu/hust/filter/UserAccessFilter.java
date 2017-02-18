package cn.edu.hust.filter;

import cn.edu.hust.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by xiaolei03 on 16/12/5.
 */
@WebFilter(filterName = "UserAccessFilter", urlPatterns = "/home/*")
public class UserAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");
        if (null == user) {
            System.out.println("用户未登录");

            response.sendRedirect("/user_no_login.html");
            return;
        }

        String role = user.getRole();
        String uri = request.getRequestURI();

        if ((uri.startsWith("/home/student/thesis/apply/basicInfoTable") && ("学院教务员".equals(role))) ||
                (uri.startsWith("/home/student/thesis/apply/tjb") && ("学院教务员".equals(role))) ||
                (uri.startsWith("/home/student/thesis/apply/basicInfoTable") && ("管理员".equals(role))) ||
                (uri.startsWith("/home/student/thesis/apply/tjb") && ("管理员".equals(role)))) {

            System.out.println("权限验证通过, role: " + role + " uri: " + uri);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        if ((uri.startsWith("/home/student") && (!"学生".equals(role))) ||
                (uri.startsWith("/home/teacher") && (!"学院教务员".equals(role))) ||
                (uri.startsWith("/home/admin") && (!"管理员".equals(role)))) {

            System.out.println("没有权限, role: " + role + " uri: " + uri);
            response.sendError(401, "无权限");
//            response.sendRedirect("/no_access_rights.html");
            return;
        }

        System.out.println("权限验证通过, role: " + role + " uri: " + uri);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
