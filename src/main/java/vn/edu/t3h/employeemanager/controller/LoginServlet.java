package vn.edu.t3h.employeemanager.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager.auth.AuthenticationService;
import vn.edu.t3h.employeemanager.auth.AuthenticationServiceImpl;
import vn.edu.t3h.employeemanager.utils.SessionUtil;

import java.io.IOException;
@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    private AuthenticationService authenticationService;
    public LoginServlet() {
        this.authenticationService = new AuthenticationServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        req.getSession().setAttribute("message", message);
        if (req.getRequestURI().startsWith("/logout")) {
            SessionUtil.removeValue(req, SessionUtil.SESSION_ID_CURRENT_USER);
            resp.sendRedirect("/home");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String url = this.authenticationService.login(username, password,req);
        resp.sendRedirect(url);
    }
}
