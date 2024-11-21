package org.codingburgas.springbootplayground.notes.service;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.NotesOverviewInfo;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  public NoteServiceImpl(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  @Override
  public List<Note> getNotes(Subject subject, BigDecimal value) {
    return noteRepository.getNotes().stream()
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
    var notesOverviewInfo = new NotesOverviewInfo();
    notesOverviewInfo.setTotalNotes((int) noteRepository.getTotalNoteCount());
    notesOverviewInfo.setAverage(noteRepository.getNotesAverage());
    // Total subjects should be returned from the repositories
    notesOverviewInfo.setTotalSubjects(noteRepository.getNotes().stream().map(note -> note.getSubject().name())
        .collect(Collectors.toSet()).size());
    return notesOverviewInfo;
  }
}