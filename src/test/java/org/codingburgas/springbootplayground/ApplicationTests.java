package org.codingburgas.springbootplayground;

import org.codingburgas.springbootplayground.students.model.Student;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
class ApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  void mainPageLoads() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Internet Programming 2024/2025")));
  }

  @Test
  @Order(2)
  void studentsPageContainsAllStudents() throws Exception {
    mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("tim@test.com")))
        .andExpect(content().string(containsString("john@best.com")))
        .andExpect(content().string(containsString("michael@worst.com")))
        .andExpect(content().string(containsString("andy@most6.com")))
        .andExpect(content().string(containsString("tom@most2.com")));
  }

  @Test
  @Order(3)
  void notesPageContainsBestStudent() throws Exception {
    var bestStudentPresentation = String.format("<a id=\"best-student\" href=\"/students/%d\">%s</a>", 2, "John Best");

    mockMvc.perform(get("/notes"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(bestStudentPresentation)));
  }

  @Test
  @Order(4)
  void notesPageContainsWorstStudent() throws Exception {
    var presentationString = String.format("<a id=\"worst-student\" href=\"/students/%d\">%s</a>", 3, "Michael Worst");

    mockMvc.perform(get("/notes"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(presentationString)));
  }

  @Test
  @Order(5)
  void notesPageContainsStudentWithMost2Grades() throws Exception {
    var presentationString = String.format("<a id=\"student-most-2\" href=\"/students/%d\">%s</a>", 5, "Tom Most2");

    mockMvc.perform(get("/notes"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(presentationString)));
  }

  @Test
  @Order(6)
  void notesPageContainsStudentWithMost6Grades() throws Exception {
    var presentationString = String.format("<a id=\"student-most-6\" href=\"/students/%d\">%s</a>", 6, "Andy Most6");

    mockMvc.perform(get("/notes"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(presentationString)));
  }

  @Test
  @Order(7)
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
