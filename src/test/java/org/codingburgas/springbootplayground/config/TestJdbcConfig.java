package org.codingburgas.springbootplayground.config;

import org.codingburgas.springbootplayground.notes.repository.JdbcNoteRepository;
import org.codingburgas.springbootplayground.notes.repository.NoteRepository;
import org.codingburgas.springbootplayground.notes.service.NoteService;
import org.codingburgas.springbootplayground.notes.service.NoteServiceImpl;
import org.codingburgas.springbootplayground.students.repository.JdbcStudentRepository;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.codingburgas.springbootplayground.students.service.StudentService;
import org.codingburgas.springbootplayground.students.service.StudentServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Test configuration for the integration tests
 * <p>
 * Loads only a demo h2 database with the schema.sql and data.sql provided in the resources, all
 * JdbcRepositories and all services
 * <p>
 * Can be used for integration tests of all services.
 * If you create new services, feel free to add them to this test configuration.
 */
@TestConfiguration
public class TestJdbcConfig {

  @Bean
  DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .addScript("testdb/schema.sql")
        .addScript("testdb/data.sql")
        .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  StudentRepository studentRepository(JdbcTemplate jdbcTemplate, DataSource dataSource, PasswordEncoder passwordEncoder) {
    return new JdbcStudentRepository(jdbcTemplate, dataSource, passwordEncoder);
  }

  @Bean
  NoteRepository noteRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcNoteRepository(jdbcTemplate);
  }

  @Bean
  StudentService studentService(StudentRepository studentRepository, NoteRepository noteRepository) {
    return new StudentServiceImpl(studentRepository, noteRepository);
  }

  @Bean
  NoteService noteService(NoteRepository noteRepository) {
    return new NoteServiceImpl(noteRepository);
  }
}
