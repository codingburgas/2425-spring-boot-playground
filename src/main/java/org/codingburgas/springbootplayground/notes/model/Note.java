package org.codingburgas.springbootplayground.notes.model;

import java.time.LocalDate;

public class Note {
  private Integer value;
  private LocalDate date;
  private Subject subject;

  public Note() {
    // No-op
  }

  public Note(Integer value, LocalDate date, Subject subject) {
    this.value = value;
    this.date = date;
    this.subject = subject;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  @Override
  public String toString() {
    return String.format("{value: %d, date: '%s', subject: '%s'}", value, date.toString(), subject.getTitle());
  }
}