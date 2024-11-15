package org.codingburgas.springbootplayground.notes.service;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.notes.repository.NoteRepository;
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
class NoteServiceImplTest {

  private NoteServiceImpl serviceUnderTest;

  @Mock
  NoteRepository noteRepository;

  @BeforeEach
  void setUp() {
    var notes = List.of(
        new Note(BigDecimal.valueOf(3), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(4), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(4), LocalDate.now(), Subject.CHEMISTRY),
        new Note(BigDecimal.valueOf(5), LocalDate.now(), Subject.DB),
        new Note(BigDecimal.valueOf(5), LocalDate.now(), Subject.DB),
        new Note(BigDecimal.valueOf(2), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(2), LocalDate.now(), Subject.EN),
        new Note(BigDecimal.valueOf(4), LocalDate.now(), Subject.GEOGRAPHY),
        new Note(BigDecimal.valueOf(5), LocalDate.now(), Subject.JAVA_OOP),
        new Note(BigDecimal.valueOf(5), LocalDate.now(), Subject.JAVA_OOP),
        new Note(BigDecimal.valueOf(6), LocalDate.now(), Subject.MATH)
    );
    when(noteRepository.getNotes()).thenReturn(notes);
    serviceUnderTest = new NoteServiceImpl(noteRepository);
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
    var allNotes = serviceUnderTest.getNotes(null, BigDecimal.valueOf(5));

    // assert
    assertNotNull(allNotes);
    assertEquals(4, allNotes.size());
    assertEquals(BigDecimal.valueOf(5), allNotes.getFirst().getValue());
  }

  @Test
  void getNotes_withSubjectAndValue_returnsCorrectNotes() {
    // act
    var allNotes = serviceUnderTest.getNotes(Subject.EN, BigDecimal.valueOf(4));

    // assert
    assertNotNull(allNotes);
    assertEquals(1, allNotes.size());
    assertEquals(Subject.EN, allNotes.getFirst().getSubject());
    assertEquals(BigDecimal.valueOf(4), allNotes.getFirst().getValue());
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