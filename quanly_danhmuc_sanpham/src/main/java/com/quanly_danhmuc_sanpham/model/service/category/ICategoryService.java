package com.quanly_danhmuc_sanpham.model.service.category;

import com.quanly_danhmuc_sanpham.model.entity.Category;
import com.quanly_danhmuc_sanpham.model.service.IGenericService;

public interface ICategoryService extends IGenericService<Category, Integer> {
    Category findByName(String name);
}
