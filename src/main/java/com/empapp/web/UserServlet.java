package com.empapp.web;

import com.empapp.bean.User;
import com.empapp.dao.UserDao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(urlPatterns = {"/new", "/insert", "/delete", "/edit", "/update", "/list"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDao();
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/new":    showNewForm(req, resp); break;
            case "/insert": insertUser(req, resp);  break;
            case "/delete": deleteUser(req, resp);  break;
            case "/edit":   showEditForm(req, resp);break;
            case "/update": updateUser(req, resp);  break;
            default:        listUser(req, resp);    break;
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("user-form.jsp").forward(req, resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        try { userDao.insert(new User(name, email, country)); } catch (Exception e) { e.printStackTrace(); }
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try { userDao.delete(Integer.parseInt(req.getParameter("id"))); }
        catch (Exception e) { e.printStackTrace(); }
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            User u = userDao.getById(Integer.parseInt(req.getParameter("id")));
            if (u == null) { resp.sendRedirect(req.getContextPath()+"/list"); return; }
            req.setAttribute("user", u);
            req.getRequestDispatcher("user-form.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/list");
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            User u = new User(id, req.getParameter("name"),
                                   req.getParameter("email"),
                                   req.getParameter("country"));
            userDao.update(u);
        } catch (Exception e) { e.printStackTrace(); }
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<User> list = userDao.getAll();
            req.setAttribute("listUser", list);
            req.getRequestDispatcher("user-list.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
