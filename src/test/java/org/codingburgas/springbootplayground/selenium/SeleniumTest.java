package org.codingburgas.springbootplayground.selenium;

import org.codingburgas.springbootplayground.students.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This is just a demonstration of how selenium is working
 */
@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
public class SeleniumTest {

  @LocalServerPort
  int port;
  private static final String NOTES_PATH =  "notes";

  private static final String CREATE_STUDENT_PATH = "students/create";


  WebDriver driver;

  @BeforeEach
  void setUp() {
    driver = new ChromeDriver();
  }

  @AfterEach
  void tearDown() {
    driver.close();
  }


  @Test
  void testBestStudentIsPresent() {
    // arrange
    driver.get(getUrl(NOTES_PATH));

    // act
    WebElement bestStudent = driver.findElement(By.id("best-student"));

    // assert
    assertNotNull(bestStudent);
    assertNotNull(bestStudent.getAttribute("href"));
    assertTrue(bestStudent.getAttribute("href").contains("/students/"));
    assertNotNull(bestStudent.getText());
  }

  @Test
  void testWorstStudentIsPresent() {
    // arrange
    driver.get(getUrl(NOTES_PATH));

    // act
    WebElement worstStudent = driver.findElement(By.id("worst-student"));

    // assert
    assertNotNull(worstStudent);
    assertNotNull(worstStudent.getAttribute("href"));
    assertTrue(worstStudent.getAttribute("href").contains("/students/"));
    assertNotNull(worstStudent.getText());
  }

  @Test
  void testStudentWithMostBestNotesIsPresent() {
    // arrange
    driver.get(getUrl(NOTES_PATH));

    // act
    WebElement studentMostBestNotes = driver.findElement(By.id("student-most-6"));

    // assert
    assertNotNull(studentMostBestNotes);
    assertNotNull(studentMostBestNotes.getAttribute("href"));
    assertTrue(studentMostBestNotes.getAttribute("href").contains("/students/"));
    assertNotNull(studentMostBestNotes.getText());
  }

  @Test
  void testStudentWithMostWorstNotesIsPresent() {
    // arrange
    driver.get(getUrl(NOTES_PATH));

    // act
    WebElement studentMostWorstNotes = driver.findElement(By.id("student-most-2"));

    // assert
    assertNotNull(studentMostWorstNotes);
    assertNotNull(studentMostWorstNotes.getAttribute("href"));
    assertTrue(studentMostWorstNotes.getAttribute("href").contains("/students/"));
    assertNotNull(studentMostWorstNotes.getText());
  }

  @Test
  void testStudentIsCreatedSuccessfully() {
    // arrange
    Student student = new Student();
    student.setFirstname("Super");
    student.setLastname("Mario");
    student.setUsername("super@mario.com");

    // act
    createStudent(student);

    // assert
    // check that the new student is present
    var elements = driver.findElements(By.cssSelector("tbody tr"));
    assertNotNull(elements);

    var lastUser = elements.getLast();
    assertTrue(lastUser.getText().contains(student.getFirstname()));
    assertTrue(lastUser.getText().contains(student.getLastname()));
    assertTrue(lastUser.getText().contains(student.getUsername()));
  }


  private void createStudent(Student student) {
    // load create user page
    driver.get(getUrl(CREATE_STUDENT_PATH));

    // set firstname
    WebElement firstnameForm = driver.findElement(By.id("firstname"));
    firstnameForm.sendKeys(student.getFirstname());

    // set lastname
    WebElement lastnameForm = driver.findElement(By.id("lastname"));
    lastnameForm.sendKeys(student.getLastname());

    // set username
    WebElement usernameForm = driver.findElement(By.id("username"));
    usernameForm.sendKeys(student.getUsername());

    WebElement submitButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
    submitButton.click();
  }

  private String getUrl(String path) {
    return String.format("http://localhost:%d/%s", port, path == null ? "" : path);
  }
}
