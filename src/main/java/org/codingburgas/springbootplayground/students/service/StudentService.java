package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.students.model.Student;

import java.util.List;

public interface StudentService {
  List<Student> getStudents();
  Student getStudentByUsername(String username);

  void addStudent(Student student);
}