package org.codingburgas.springbootplayground.notes.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Note {
  private BigDecimal value;
  private LocalDate date;
  private Subject subject;

  public Note() {
    // No-op
  }

  public Note(BigDecimal value, LocalDate date, Subject subject) {
    this.value = value;
    this.date = date;
    this.subject = subject;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
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

}