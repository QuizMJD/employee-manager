package vn.edu.t3h.employeemanager.service;

import vn.edu.t3h.employeemanager.model.UserModel;

public interface UserService {
    UserModel findUserByUserAndPassword(String username, String password);
}

