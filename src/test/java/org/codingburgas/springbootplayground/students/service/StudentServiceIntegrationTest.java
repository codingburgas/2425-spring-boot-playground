package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.config.TestJdbcConfig;
import org.codingburgas.springbootplayground.notes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for the StudentService
 * <p>
 * Uses the TestJdbcConfig class. This class loads the demo data included in the testdb folder
 * and also creates the JDBC repositories and services
 */
@SpringJUnitConfig(TestJdbcConfig.class)
class StudentServiceIntegrationTest {

  @Autowired
  StudentService studentService;

  @Autowired
  NoteRepository noteRepository;

  @Test
  void test_getBestStudent_returnsCorrectResult() {
    // act
    var bestStudent = studentService.getBestStudent();

    // assert
    // based on the test data (data.sql), the best student must be the one with id 2
    assertNotNull(bestStudent);
    assertEquals(2L, bestStudent.getId());
  }

  @Test
  void test_getWorstStudent_returnsCorrectResult() {
    // act
    var worstStudent = studentService.getWorstStudent();

    // assert
    // based on the test data (data.sql), the worst student must be the one with id 3
    assertNotNull(worstStudent);
    assertEquals(3L, worstStudent.getId());
  }

  @Test
  void test_getStudentWithMostWorstNotes_returnsCorrectResult() {
    // act
    var student = studentService.getStudentWithMostWorstNotes();

    // assert
    // based on the test data (data.sql), the student with most 2.00 notes has the id 5
    assertNotNull(student);
    assertEquals(5L, student.getId());
  }

  @Test
  void test_getStudentWithMostBestNotes_returnsCorrectResult() {
    // act
    var student = studentService.getStudentWithMostBestNotes();

    // assert
    // based on the test data (data.sql), the student with most 6.00 notes has the id 4
    assertNotNull(student);
    assertEquals(4L, student.getId());
  }

}
