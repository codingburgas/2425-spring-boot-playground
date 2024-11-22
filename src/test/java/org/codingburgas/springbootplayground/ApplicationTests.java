package org.codingburgas.springbootplayground;

import org.codingburgas.springbootplayground.config.TestJdbcConfig;
import org.codingburgas.springbootplayground.students.model.Student;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Import(TestJdbcConfig.class)
@AutoConfigureMockMvc
@Disabled
class ApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void mainPageLoads() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Internet Programming 2024/2025")));
  }

  @Test
  void studentsPageContainsAllStudents() throws Exception {
    mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("tim@test.com")))
        .andExpect(content().string(containsString("john@best.com")))
        .andExpect(content().string(containsString("michael@worst.com")))
        .andExpect(content().string(containsString("andy@most6.com")))
        .andExpect(content().string(containsString("tom@most2.com")))
    ;
  }

  @Test
  void createStudentIsWorkingCorrectly() throws Exception {
    // arrange
    var studentToBeCreated = new Student();
    studentToBeCreated.setUsername("padawan@test.com");
    studentToBeCreated.setFirstname("NEW");
    studentToBeCreated.setLastname("STUDENT");
    studentToBeCreated.setBirthday(LocalDate.parse("1999-01-01"));

    // act
    mockMvc.perform(post("/students/create")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", studentToBeCreated.getUsername())
                .param("firstname", studentToBeCreated.getFirstname())
                .param("lastname", studentToBeCreated.getLastname())
                .param("birthday", studentToBeCreated.getBirthday().format(DateTimeFormatter.ISO_DATE))
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/students"));

    // assert
    mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(studentToBeCreated.getUsername())));


  }

// DEMONSTRATE XPATH Problems!
//  @Test
//  void studentsPageLoadsCorrectly() throws Exception {
//    mockMvc.perform(get("/students"))
//        .andExpect(status().isOk())
//        .andExpect(xpath("//tbody/td").nodeCount(5));
//  }

}
