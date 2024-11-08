package org.codingburgas.springbootplayground.notes.service;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.NotesOverviewInfo;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

  private final StudentRepository studentRepository;

  public NoteServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Note> getNotes(Subject subject, Integer value) {
    return studentRepository.getStudents()
        .stream()
        .flatMap(s -> s.getNotes().stream())
        .filter(note -> {
          if (subject != null && value != null) {
            return subject.equals(note.getSubject()) && value.equals(note.getValue());
          } else {
            if (subject != null) {
              return subject.equals(note.getSubject());
            }
            if (value != null) {
              return value.equals(note.getValue());
            }
            return true;
          }
        })
        .toList();
  }

  @Override
  public NotesOverviewInfo getOverviewInfo() {
    var notes = getNotes(null, null);
    var notesOverviewInfo = new NotesOverviewInfo();
    notesOverviewInfo.setTotalNotes(notes.size());
    notesOverviewInfo.setAverage(notes.stream().mapToDouble(note -> note.getValue().doubleValue()).average().orElse(0.0));
    notesOverviewInfo.setTotalSubjects(notes.stream().map(Note::getSubject).collect(Collectors.toSet()).size());
    return notesOverviewInfo;
  }
}