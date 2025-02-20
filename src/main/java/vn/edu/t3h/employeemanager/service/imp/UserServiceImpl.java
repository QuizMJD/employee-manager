package vn.edu.t3h.employeemanager.service.imp;

import vn.edu.t3h.employeemanager.dao.UserDao;
import vn.edu.t3h.employeemanager.model.UserModel;
import vn.edu.t3h.employeemanager.service.UserService;
import vn.edu.t3h.employeemanager.utils.PasswordUtils;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public UserModel findUserByUserAndPassword(String username, String password) {
        String passwordEncrypt = PasswordUtils.encrypt(password);
        return userDao.findUserByUserNameAndPassword(username, passwordEncrypt);
    }
}
