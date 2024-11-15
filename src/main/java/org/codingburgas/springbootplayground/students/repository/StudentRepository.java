package org.codingburgas.springbootplayground.students.repository;

import org.codingburgas.springbootplayground.students.model.Student;

import java.util.List;

public interface StudentRepository {
  List<Student> getStudents();
  Student getStudentByUsername(String username);

  Student getStudentById(Long id);

  void addStudent(Student student);
}