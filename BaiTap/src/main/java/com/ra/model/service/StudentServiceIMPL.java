package com.ra.model.service;

import com.ra.model.DAO.IStudentDAO;
import com.ra.model.DAO.StudentDAOImpl;
import com.ra.model.entity.Student;

import java.util.List;

public class StudentServiceIMPL implements IStudentService {
    private static final IStudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public boolean save(Student student) {
        return studentDAO.saveOrUpdate(student);
    }

    @Override
    public Student findById(Integer integer) {
        return studentDAO.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        studentDAO.delete(integer);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public List<Student> sortByName() {
        return studentDAO.sortByName();
    }
}
