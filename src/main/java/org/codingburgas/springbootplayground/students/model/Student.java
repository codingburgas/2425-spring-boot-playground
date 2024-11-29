package org.codingburgas.springbootplayground.students.model;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Student implements UserDetails {

  private Long id;

  private String firstname;
  private String lastname;

  private String username;

  private String schoolClass;

  private LocalDate birthday;

  private String address;

  private String password;

  public void setPassword(String password) {
    this.password = password;
  }

  private String role;

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  private List<Note> notes = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role));
  }

  @Override
  public String getPassword() {
    return password;
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