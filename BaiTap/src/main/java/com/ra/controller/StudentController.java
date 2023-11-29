package com.ra.controller;

import com.ra.model.entity.Student;
import com.ra.model.service.IStudentService;
import com.ra.model.service.StudentServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "StudentController", value = "/student-list")
public class StudentController extends HttpServlet {
    private IStudentService studentService = new StudentServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showListStudent(request, response);
        }
        switch (action) {
            case "create":
                response.sendRedirect("views/create-student.jsp");
                break;
            case "edit":
                int studentIdUpdate = Integer.parseInt(request.getParameter("id"));
                Student student = studentService.findById(studentIdUpdate);
                request.setAttribute("student", student);
                request.getRequestDispatcher("views/edit-student.jsp").forward(request, response);
                break;
            case "delete":
                int studentIdDelete = Integer.parseInt(request.getParameter("id"));
                studentService.delete(studentIdDelete);
                showListStudent(request, response);
                break;
            case "search":
                String search = request.getParameter("search");
                List<Student> listSearch = studentService.findByName(search);
                request.setAttribute("list", listSearch);
                request.getRequestDispatcher("views/listStudent.jsp").forward(request, response);
                break;
            case "sortByName":
                List<Student> listSort = studentService.sortByName();
                request.setAttribute("list", listSort);
                request.getRequestDispatcher("views/listStudent.jsp").forward(request, response);
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            // lấy dữ liệu
            Student student = new Student();
            student.setUserName(request.getParameter("studentName".trim()));
            student.setEmail(request.getParameter("email".trim()));
            student.setBirthday(java.sql.Date.valueOf(request.getParameter("birthday")));
            student.setAddress(request.getParameter("address".trim()));
            if (studentService.save(student)) {
                showListStudent(request, response);
            }
            response.sendRedirect("views/create-student.jsp?err");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("studentName");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            Student studentEdit = studentService.findById(id);
            studentEdit.setUserName(name);
            studentEdit.setAddress(address);
            studentEdit.setEmail(email);
            studentEdit.setBirthday(birthday);
            if (studentService.save(studentEdit)) {
                showListStudent(request, response);
            }
            response.sendRedirect("views/edit-student.jsp?err");
        }
    }

    public void showListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentService.findAll();
        request.setAttribute("list", students);
        request.getRequestDispatcher("views/listStudent.jsp").forward(request, response);
    }
}