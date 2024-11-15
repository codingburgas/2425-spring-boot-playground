package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

  private StudentServiceImpl serviceUnderTest;

  @Mock
  private StudentRepository studentRepository;

  @BeforeEach
  void setUp() {
    var student1 = new Student();
    student1.setNotes(List.of(
        new Note(BigDecimal.valueOf(3), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(4), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(4), LocalDate.now(), Subject.CHEMISTRY),
        new Note(BigDecimal.valueOf(5), LocalDate.now(), Subject.DB),
        new Note(BigDecimal.valueOf(5), LocalDate.now(), Subject.DB)
    ));

    var student2 = new Student();
    student2.setNotes(List.of(
        new Note(BigDecimal.valueOf(2), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(2), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(4), LocalDate.now(), Subject.GEOGRAPHY),
        new Note(BigDecimal.valueOf(5), LocalDate.now(), Subject.JAVA_OOP),
        new Note(BigDecimal.valueOf(6), LocalDate.now(), Subject.JAVA_OOP)
    ));

    var student3 = new Student();
    student3.setId(1L);
    student3.setNotes(List.of(
        new Note(BigDecimal.valueOf(6), LocalDate.now(), Subject.MATH)
    ));
    when(studentRepository.getStudents()).thenReturn(List.of(student1, student2, student3));
    serviceUnderTest = new StudentServiceImpl(studentRepository, null);
  }

  @Test
  void getBestStudent() {
    // act
    var bestStudent = serviceUnderTest.getBestStudent();

    // assert
    assertNotNull(bestStudent);
    assertEquals(1, bestStudent.getId());
  }
}