package org.codingburgas.springbootplayground.notes.model;

public class NotesOverviewInfo {

  private Integer totalNotes;

  private Double average;

  private Integer totalSubjects;

  public Integer getTotalNotes() {
    return totalNotes;
  }

  public void setTotalNotes(Integer totalNotes) {
    this.totalNotes = totalNotes;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public Integer getTotalSubjects() {
    return totalSubjects;
  }

  public void setTotalSubjects(Integer totalSubjects) {
    this.totalSubjects = totalSubjects;
  }
}