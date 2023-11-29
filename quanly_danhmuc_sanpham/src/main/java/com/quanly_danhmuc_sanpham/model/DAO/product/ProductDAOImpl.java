package com.quanly_danhmuc_sanpham.model.DAO.product;

import com.quanly_danhmuc_sanpham.model.DAO.category.CategoryDAOImpl;
import com.quanly_danhmuc_sanpham.model.DAO.category.ICategoryDAO;
import com.quanly_danhmuc_sanpham.model.entity.Category;
import com.quanly_danhmuc_sanpham.model.entity.Product;
import com.quanly_danhmuc_sanpham.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    private static final ICategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Product> findAll() {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PRODUCT");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                Category category = categoryDAO.findById(resultSet.getInt("category_id"));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return products;
    }

    @Override
    public boolean saveOrUpdate(Product product) {
        Connection connection = null;
        try {
            if (product.getProductId() == 0) {
                connection = ConnectionDB.openConnection();
                connection.setAutoCommit(false);
                CallableStatement callableStatement1 = connection.prepareCall("{CALL PROC_CREATE_PRODUCT(?,?,?)}");
                callableStatement1.setString(1, product.getProductName());
                callableStatement1.setInt(2, product.getCategory().getCategoryId());
                callableStatement1.setInt(3, product.getPrice());
                int check1 = callableStatement1.executeUpdate();
                CallableStatement callableStatement2 = connection.prepareCall("{CALL PROC_UPDATE_QUANTITY_WHEN_ADD_PRODUCT(?)}");
                callableStatement2.setInt(1, product.getCategory().getCategoryId());
                int check2 = callableStatement2.executeUpdate();
                if (check1 > 0 && check2 > 0) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                }
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL PROC_UPDATE_PRODUCT(?,?,?,?)}");
                callableStatement.setInt(1, product.getProductId());
                callableStatement.setString(2, product.getProductName());
                callableStatement.setInt(3, product.getPrice());
                callableStatement.setInt(4, product.getCategory().getCategoryId());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Product findById(Integer integer) {
        Connection connection = null;
        Product product = new Product();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PRODUCT WHERE id = ?");
            pstm.setInt(1, integer);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                product.setProductId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                Category categoryUpdate = categoryDAO.findById(resultSet.getInt("category_id"));
                product.setCategory(categoryUpdate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return product;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_DELETE_PRODUCT(?)}");
            callableStatement.setInt(1, integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }
}
