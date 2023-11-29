package com.ra.model.service;

import com.ra.model.entity.Student;

import java.util.List;

public interface IStudentService extends IGenericService<Student, Integer> {
    List<Student> findByName(String name);
    List<Student> sortByName();
}
