package com.quanly_danhmuc_sanpham.controller;

import com.quanly_danhmuc_sanpham.model.entity.Category;
import com.quanly_danhmuc_sanpham.model.service.category.CategoryServiceIMPL;
import com.quanly_danhmuc_sanpham.model.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "categoryController", value = "/category-list")
public class categoryController extends HttpServlet {
    private static final ICategoryService categoryService = new CategoryServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showListCategory(request, response);
        }
        switch (action) {
            case "create":
                response.sendRedirect("views/create-category.jsp");
                break;
            case "update":
                break;
            case "delete":
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Category category = new Category();
            category.setCategoryName(request.getParameter("categoryName".trim()));
            if (categoryService.save(category)) {
                showListCategory(request, response);
            }
            response.sendRedirect("views/create-category.jsp?err");
        }else {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("categoryName");
            Boolean status = Boolean.valueOf(request.getParameter("status"));
            Category categoryEdit = categoryService.findById(id);
            categoryEdit.setCategoryName(name);
            categoryEdit.setStatus(status);
            if (categoryService.save(categoryEdit)) {
                showListCategory(request, response);
            }
            response.sendRedirect("views/edit-category.jsp?err");
        }
    }

    public void showListCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("list", categories);
        request.getRequestDispatcher("views/listCategory.jsp").forward(request, response);
    }
}