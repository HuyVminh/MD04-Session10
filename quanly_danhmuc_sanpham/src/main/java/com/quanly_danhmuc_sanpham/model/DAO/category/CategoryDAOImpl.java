package com.quanly_danhmuc_sanpham.model.DAO.category;

import com.quanly_danhmuc_sanpham.model.entity.Category;
import com.quanly_danhmuc_sanpham.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements ICategoryDAO {

    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CATEGORY");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setQuantity_product(resultSet.getInt("quantity_product"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public boolean saveOrUpdate(Category category) {
        Connection connection = null;
        int check = 0;
        try {
            connection = ConnectionDB.openConnection();
            if (category.getCategoryId() == 0) {
                CallableStatement callableStatement = connection.prepareCall("{CALL PROC_CREATE_CATEGORY(?)}");
                callableStatement.setString(1, category.getCategoryName());
                check = callableStatement.executeUpdate();
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL PROC_UPDATE_CATEGORY(?,?)}");
                callableStatement.setString(1, category.getCategoryName());
                callableStatement.setBoolean(2, category.isStatus());
                check = callableStatement.executeUpdate();
            }
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Category findById(Integer integer) {
        Connection connection = null;
        Category category = new Category();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CATEGORY WHERE id = ?");
            pstm.setInt(1, integer);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setQuantity_product(resultSet.getInt("quantity_product"));
                category.setStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return category;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_DELETE_CATEGORY(?)}");
            callableStatement.setInt(1, integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public Category findByName(String name) {
        Connection connection = null;
        Category category = new Category();
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("SELECT*FROM category WHERE name=?");
            callableStatement.setString(1,name);
            ResultSet rs= callableStatement.executeQuery();
            while (rs.next()){
                category.setCategoryId(rs.getInt("id"));
                category.setCategoryName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));
                category.setQuantity_product(rs.getInt("quantity_product"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return category;
    }
}
