package org.codingburgas.springbootplayground.students.model;

import org.codingburgas.springbootplayground.notes.model.Note;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {

  private Integer id;

  private String firstname;
  private String lastname;

  private String username;

  private String schoolClass;

  private LocalDate birthday;

  private String address;

  private List<Note> notes = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSchoolClass() {
    return schoolClass;
  }

  public void setSchoolClass(String schoolClass) {
    this.schoolClass = schoolClass;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  public double getNoteAverage() {
    if (notes == null || notes.isEmpty()) {
      return 0.0;
    }

    return notes.stream().mapToDouble(note -> note.getValue().doubleValue()).average().getAsDouble();
  }
}