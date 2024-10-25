package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.students.model.Student;

import java.util.List;

public interface StudentService {
  List<Student> getStudents();
  Student getStudentByUsername(String username);

  Student getStudentById(Integer id);

  void addStudent(Student student);
}