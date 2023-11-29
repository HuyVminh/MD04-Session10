package com.quanly_danhmuc_sanpham.model.service.category;

import com.quanly_danhmuc_sanpham.model.DAO.category.CategoryDAOImpl;
import com.quanly_danhmuc_sanpham.model.DAO.category.ICategoryDAO;
import com.quanly_danhmuc_sanpham.model.entity.Category;

import java.util.List;

public class CategoryServiceIMPL implements ICategoryService {
    private static final ICategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public boolean save(Category category) {
        return categoryDAO.saveOrUpdate(category);
    }

    @Override
    public Category findById(Integer integer) {
        return categoryDAO.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        categoryDAO.delete(integer);
    }

    @Override
    public Category findByName(String name) {
        return categoryDAO.findByName(name);
    }
}
