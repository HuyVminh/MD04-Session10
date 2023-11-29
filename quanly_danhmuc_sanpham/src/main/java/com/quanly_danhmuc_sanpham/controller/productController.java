package com.quanly_danhmuc_sanpham.controller;

import com.quanly_danhmuc_sanpham.model.entity.Category;
import com.quanly_danhmuc_sanpham.model.entity.Product;
import com.quanly_danhmuc_sanpham.model.service.category.CategoryServiceIMPL;
import com.quanly_danhmuc_sanpham.model.service.category.ICategoryService;
import com.quanly_danhmuc_sanpham.model.service.product.IProductService;
import com.quanly_danhmuc_sanpham.model.service.product.ProductServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "productController", value = "/product-list")
public class productController extends HttpServlet {
    private static final IProductService productService = new ProductServiceIMPL();
    private static final ICategoryService categoryService = new CategoryServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showListProduct(request, response);
        }
        switch (action) {
            case "create":
                List<Category> categories = categoryService.findAll();
                request.setAttribute("listCategory", categories);
                request.getRequestDispatcher("views/create-product.jsp").forward(request, response);
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
            Product product = new Product();
            product.setProductName(request.getParameter("productName"));
            product.setPrice(Integer.parseInt(request.getParameter("price")));
            int id = Integer.parseInt(request.getParameter("categoryId"));
            Category category = categoryService.findById(id);
            product.setCategory(category);
            if (productService.save(product)) {
                showListProduct(request, response);
            }
            response.sendRedirect("views/create-product.jsp?err");
        }
    }

    public void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        request.setAttribute("list", products);
        request.getRequestDispatcher("views/listProduct.jsp").forward(request, response);
    }
}