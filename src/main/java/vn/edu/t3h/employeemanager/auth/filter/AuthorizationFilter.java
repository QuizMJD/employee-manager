package vn.edu.t3h.employeemanager.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager.dao.impl.RoleDaoImpl;
import vn.edu.t3h.employeemanager.model.UserModel;
import vn.edu.t3h.employeemanager.service.RoleService;
import vn.edu.t3h.employeemanager.service.imp.RoleServiceImpl;
import vn.edu.t3h.employeemanager.utils.Constants;
import vn.edu.t3h.employeemanager.utils.SessionUtil;

import java.io.IOException;

@WebFilter("/*")

public class AuthorizationFilter implements Filter {

    private RoleService roleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        roleService = new RoleServiceImpl(new RoleDaoImpl());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserModel currentUser = (UserModel) request.getSession().getAttribute(SessionUtil.SESSION_ID_CURRENT_USER);

        String uri = request.getRequestURI();
        if (uri.startsWith("/cms")) {
            if (currentUser == null) {
                response.sendRedirect("/login?message=" + Constants.DONT_LOGIN);
                return;
            }
            if (roleService.getRoleById(currentUser.getRoleId()).getCode().equalsIgnoreCase(Constants.ROLE.ROLE_ADMIN.name())) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/login?message=" + Constants.PERMISSION_DENIED);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

