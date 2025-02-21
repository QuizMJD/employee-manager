package vn.edu.t3h.employeemanager.auth;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.employeemanager.dao.impl.RoleDaoImpl;
import vn.edu.t3h.employeemanager.dao.impl.UserDaoImpl;
import vn.edu.t3h.employeemanager.model.RoleModel;
import vn.edu.t3h.employeemanager.model.UserModel;
import vn.edu.t3h.employeemanager.service.RoleService;
import vn.edu.t3h.employeemanager.service.UserService;
import vn.edu.t3h.employeemanager.service.imp.RoleServiceImpl;
import vn.edu.t3h.employeemanager.service.imp.UserServiceImpl;
import vn.edu.t3h.employeemanager.utils.Constants;
import vn.edu.t3h.employeemanager.utils.SessionUtil;

public class AuthenticationServiceImpl implements AuthenticationService{
    private UserService userService;
    private RoleService roleService;
    public AuthenticationServiceImpl() {
        this.userService = new UserServiceImpl(new UserDaoImpl());
        this.roleService = new RoleServiceImpl(new RoleDaoImpl());
    }
    @Override
    public String login(String username, String password, HttpServletRequest request) {
        //Authentication: Kiểm tra username, password có tồn tại trong database không
        UserModel userModel = userService.findUserByUserAndPassword(username, password);
        if (userModel == null) {
            return "/login?message=loginError";
        }
        request.getSession().setAttribute(SessionUtil.SESSION_ID_CURRENT_USER, userModel);
        // Authorization: Kiểm tra user nếu có quyền Admin sẽ cho phép truy cập vào trang quản lý employee
        RoleModel roleModel = roleService.getRoleById(userModel.getRoleId());
        String urlRedirect = "";
        if (Constants.ROLE.ROLE_ADMIN.name().equalsIgnoreCase(roleModel.getCode())) {
            urlRedirect = "/employee/show";
        } else if (Constants.ROLE.ROLE_USER.name().equalsIgnoreCase(roleModel.getCode())) {
            urlRedirect = "/";
        }
        return urlRedirect;
    }
}
