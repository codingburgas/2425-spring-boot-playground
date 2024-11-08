package org.codingburgas.springbootplayground.notes.service;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

  private NoteServiceImpl serviceUnderTest;

  @Mock
  StudentRepository studentRepository;

  @BeforeEach
  void setUp() {
    var student1 = new Student();
    student1.setNotes(List.of(
        new Note(3, LocalDate.now(), Subject.EN),
        new Note(4, LocalDate.now(), Subject.EN),
        new Note(4, LocalDate.now(), Subject.CHEMISTRY),
        new Note(5, LocalDate.now(), Subject.DB),
        new Note(5, LocalDate.now(), Subject.DB)
    ));

    var student2 = new Student();
    student2.setNotes(List.of(
        new Note(2, LocalDate.now(), Subject.EN),
        new Note(2, LocalDate.now(), Subject.EN),
        new Note(4, LocalDate.now(), Subject.GEOGRAPHY),
        new Note(5, LocalDate.now(), Subject.JAVA_OOP),
        new Note(5, LocalDate.now(), Subject.JAVA_OOP)
    ));

    var student3 = new Student();
    student3.setId(1);
    student3.setNotes(List.of(
        new Note(6, LocalDate.now(), Subject.MATH)
    ));
    when(studentRepository.getStudents()).thenReturn(List.of(student1, student2, student3));
    serviceUnderTest = new NoteServiceImpl(studentRepository);
  }

  @Test
  void getNotes_withoutParameters_returnsAllNotes() {
    // act
    var allNotes = serviceUnderTest.getNotes(null, null);

    // assert
    assertNotNull(allNotes);
    assertEquals(11, allNotes.size());
  }

  @Test
  void getNotes_withSubject_returnsCorrectNotes() {
    // act
    var allNotes = serviceUnderTest.getNotes(Subject.CHEMISTRY, null);

    // assert
    assertNotNull(allNotes);
    assertEquals(1, allNotes.size());
    assertEquals(Subject.CHEMISTRY, allNotes.getFirst().getSubject());
  }

  @Test
  void getNotes_withValue_returnsCorrectNotes() {
    // act
    var allNotes = serviceUnderTest.getNotes(null, 5);

    // assert
    assertNotNull(allNotes);
    assertEquals(4, allNotes.size());
    assertEquals(5, allNotes.getFirst().getValue());
  }

  @Test
  void getNotes_withSubjectAndValue_returnsCorrectNotes() {
    // act
    var allNotes = serviceUnderTest.getNotes(Subject.EN, 4);

    // assert
    assertNotNull(allNotes);
    assertEquals(1, allNotes.size());
    assertEquals(Subject.EN, allNotes.getFirst().getSubject());
    assertEquals(4, allNotes.getFirst().getValue());
  }

  @Test
  void getBestStudent() {
    // act
    var bestStudent = serviceUnderTest.getBestStudent();

    // assert
    assertNotNull(bestStudent);
    assertEquals(1, bestStudent.getId());
  }

  @Test
  void getOverviewInfo() {
    // act
    var info = serviceUnderTest.getOverviewInfo();

    // assert
    assertNotNull(info);
    assertEquals(11, info.getTotalNotes());
    assertEquals(6, info.getTotalSubjects());
    //assertEquals(3.9, info.getAverage());
  }
}