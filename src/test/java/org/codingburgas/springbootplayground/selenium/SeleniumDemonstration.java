package org.codingburgas.springbootplayground.selenium;

import org.codingburgas.springbootplayground.students.model.Student;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This is just a demonstration of how selenium is working
 *
 * No tests here
 */
public class SeleniumDemonstration {

  private static final String BASE_URL = "http://localhost:8081/";
  private static final String NOTES_URL = BASE_URL + "notes";

  private static final String CREATE_STUDENT_URL = BASE_URL + "students/create";

  public static void main(String[] args) {
    // Create the driver
    WebDriver driver = new ChromeDriver();

    // load page
    driver.get("http://localhost:8081/notes");
    WebElement bestStudent = driver.findElement(By.id("best-student"));
    String attr = bestStudent.getAttribute("href");


    Student newStudent = new Student();
    newStudent.setUsername("TEST@test.com");

    createStudent(driver, newStudent);



    driver.close();

  }


  private static void createStudent(WebDriver driver, Student student) {
    driver.get(CREATE_STUDENT_URL);
    WebElement usernameForm = driver.findElement(By.id("username"));
    usernameForm.sendKeys(student.getUsername());

    WebElement submitButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
    submitButton.click();

  }
}
