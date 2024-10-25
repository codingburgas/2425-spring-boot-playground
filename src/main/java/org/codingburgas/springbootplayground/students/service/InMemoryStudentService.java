package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.students.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryStudentService implements StudentService {

  private List<Student> students = new ArrayList<>();

  public InMemoryStudentService() {
    generateSomeStudents();
  }

  @Override
  public List<Student> getStudents() {
    return students;
  }

  @Override
  public Student getStudentByUsername(String username) {
    return null;
  }


  private void generateSomeStudents() {
    for (var i = 0; i < 26; i++) {
      final var student = new Student();
      student.setFirstname(String.format("Student %d", i + 1));
      student.setLastname(String.format("Unknown %d", i + 1));
      student.setUsername(String.format("student%d@condingburgas.org", i + 1));
      students.add(student);
    }
  }
}