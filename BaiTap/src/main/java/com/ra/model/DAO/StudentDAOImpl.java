package com.ra.model.DAO;

import com.ra.model.entity.Student;
import com.ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public List<Student> findAll() {
        Connection connection = null;
        List<Student> students = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM STUDENT");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setUserName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddress(resultSet.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return students;
    }

    @Override
    public boolean saveOrUpdate(Student student) {
        Connection connection = null;
        int check = 0;
        try {
            connection = ConnectionDB.openConnection();
            if (student.getId() == 0) {
                CallableStatement callableStatement = connection.prepareCall("{CALL PROC_CREATE_STUDENT(?,?,?,?)}");
                callableStatement.setString(1, student.getUserName());
                callableStatement.setString(2, student.getEmail());
                callableStatement.setString(3, String.valueOf(student.getBirthday()));
                callableStatement.setString(4, student.getAddress());
                check = callableStatement.executeUpdate();
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL PROC_UPDATE_STUDENT(?,?,?,?,?)}");
                callableStatement.setInt(1, student.getId());
                callableStatement.setString(2, student.getUserName());
                callableStatement.setString(3, student.getEmail());
                callableStatement.setString(4, String.valueOf(student.getBirthday()));
                callableStatement.setString(5, student.getAddress());
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
    public Student findById(Integer integer) {
        Connection connection = null;
        Student student = new Student();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
            pstm.setInt(1, integer);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setUserName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return student;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_DELETE_STUDENT(?)}");
            callableStatement.setInt(1, integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public List<Student> findByName(String name) {
        Connection connection = null;
        List<Student> students = new ArrayList<Student>();
        connection = ConnectionDB.openConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall("{CALL PROCE_SEARCH_STUDENT(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setUserName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddress(resultSet.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return students;
    }

    @Override
    public List<Student> sortByName() {
        Connection connection = null;
        List<Student> students = new ArrayList<Student>();
        connection = ConnectionDB.openConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall("{CALL PROC_SORT_STUDENT}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setUserName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddress(resultSet.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return students;
    }
}
