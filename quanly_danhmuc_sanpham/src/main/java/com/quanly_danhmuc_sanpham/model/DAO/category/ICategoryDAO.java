package com.quanly_danhmuc_sanpham.model.DAO.category;

import com.quanly_danhmuc_sanpham.model.DAO.IGenericDAO;
import com.quanly_danhmuc_sanpham.model.entity.Category;

public interface ICategoryDAO extends IGenericDAO<Category,Integer> {
    Category findByName(String name);
}
