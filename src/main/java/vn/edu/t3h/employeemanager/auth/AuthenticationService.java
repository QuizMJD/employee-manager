package vn.edu.t3h.employeemanager.auth;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    public String login(String username, String password, HttpServletRequest request);
}
