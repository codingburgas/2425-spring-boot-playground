package org.codingburgas.springbootplayground.students.repository;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.students.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
@Profile("default")
public class InMemoryStudentRepository implements StudentRepository {

  private final List<Student> students = new ArrayList<>();

  @Override
  public List<Student> getStudents() {
    return students;
  }

  @Override
  public Student getStudentByUsername(String username) {
    return null;
  }

  @Override
  public Student getStudentById(Long id) {
    return students
        .stream()
        .filter(student -> student.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void addStudent(Student student) {
    students.add(student);
  }

}