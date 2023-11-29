package com.ra.model.DAO;

import com.ra.model.entity.Student;

import java.util.List;

public interface IStudentDAO extends IGenericDAO<Student, Integer> {
    List<Student> findByName(String name);
    List<Student> sortByName();
}
