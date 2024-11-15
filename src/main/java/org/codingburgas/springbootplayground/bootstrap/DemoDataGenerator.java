package org.codingburgas.springbootplayground.bootstrap;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.notes.repository.NoteRepository;
import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Logger;

/**
 * DemoDataGenerator
 * <p>
 * Component that generates some demo data after application start
 * <p>
 * In order to enable/disable the generation, the property playground.generate.data can be used.
 * Default value is false.
 */
@Component
public class DemoDataGenerator implements ApplicationListener<ApplicationReadyEvent> {

  private static final Logger LOGGER = Logger.getLogger(DemoDataGenerator.class.getName());

  @Value("${playground.generate.data:false}")
  Boolean generateDemoData;

  private final NoteRepository noteRepository;
  private final StudentRepository studentRepository;

  private final Random random = new Random();

  public DemoDataGenerator(NoteRepository noteRepository, StudentRepository studentRepository) {
    this.noteRepository = noteRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    if (Boolean.TRUE.equals(generateDemoData)) {
      LOGGER.info("Generating demo data...");
      for (var i = 0; i < 26; i++) {
        final var student = new Student();
        student.setId((long) i + 1);
        student.setFirstname(String.format("Student %d", i + 1));
        student.setLastname(String.format("Unknown %d", i + 1));
        student.setUsername(String.format("student%d@condingburgas.org", i + 1));
        student.setSchoolClass("12Г");
        student.setBirthday(LocalDate.now().minusYears(18).minusDays(random.nextInt(0, 300)));
        student.setAddress("ул. Хелоууърлд 123, 8001 гр. Бургас");
        studentRepository.addStudent(student);
        for (var j = 0; j < random.nextInt(100, 300); j++) {
          final var note = new Note();
          note.setDate(LocalDate.now().minusDays(random.nextInt(50)));
          note.setSubject(Subject.getRandomSubject());
          note.setValue(BigDecimal.valueOf(random.nextInt(2, 7)));
          noteRepository.addNoteForStudent(note, (long) (i + 1));
        }
      }
    }
  }
}
