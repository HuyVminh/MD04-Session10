package com.quanly_danhmuc_sanpham.model.service.product;

import com.quanly_danhmuc_sanpham.model.DAO.category.CategoryDAOImpl;
import com.quanly_danhmuc_sanpham.model.DAO.category.ICategoryDAO;
import com.quanly_danhmuc_sanpham.model.DAO.product.IProductDAO;
import com.quanly_danhmuc_sanpham.model.DAO.product.ProductDAOImpl;
import com.quanly_danhmuc_sanpham.model.entity.Product;

import java.util.List;

public class ProductServiceIMPL implements IProductService {

    private static final IProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public boolean save(Product product) {
        return productDAO.saveOrUpdate(product);
    }

    @Override
    public Product findById(Integer integer) {
        return productDAO.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        productDAO.delete(integer);
    }
}
