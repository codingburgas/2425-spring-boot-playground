package org.codingburgas.springbootplayground.students.repository;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.students.model.Student;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class InMemoryStudentRepository implements StudentRepository {

  private final List<Student> students = new ArrayList<>();

  public InMemoryStudentRepository() {
    generateSomeStudents();
  }

  private final Random random = new Random();

  @Override
  public List<Student> getStudents() {
    return students;
  }

  @Override
  public Student getStudentByUsername(String username) {
    return null;
  }

  @Override
  public Student getStudentById(Integer id) {
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

  private void generateSomeStudents() {
    for (var i = 0; i < 26; i++) {
      final var student = new Student();
      student.setId(i + 1);
      student.setFirstname(String.format("Student %d", i + 1));
      student.setLastname(String.format("Unknown %d", i + 1));
      student.setUsername(String.format("student%d@condingburgas.org", i + 1));
      student.setSchoolClass("12Г");
      student.setBirthday(LocalDate.now().minusYears(18).minusDays(random.nextInt(0, 300)));
      student.setAddress("ул. Хелоууърлд 123, 8001 гр. Бургас");
      for (var j = 0; j < random.nextInt(30, 70); j++) {
        final var note = new Note();
        note.setDate(LocalDate.now().minusDays(random.nextInt(50)));
        note.setSubject(Subject.getRandomSubject());
        note.setValue(random.nextInt(2, 7));
        student.getNotes().add(note);
      }
      students.add(student);
    }
  }
}