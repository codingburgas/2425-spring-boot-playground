package org.codingburgas.springbootplayground.students.model;

import org.codingburgas.springbootplayground.notes.model.Note;

import java.util.List;

public class Student {

  private String firstname;
  private String lastname;

  private String username;

  private List<Note> notes;

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

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }
}