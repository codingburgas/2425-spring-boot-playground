package org.codingburgas.springbootplayground.notes.model;

import java.util.Random;

public enum Subject {
  MATH("Mathematics"),
  LITERATURE("Literature"),
  BG("Bulgarian Language"),
  EN("English Language"),
  PHYSICS("Physics"),
  CHEMISTRY("Chemistry"),
  HISTORY("History"),
  DB("Databases"),
  GEOGRAPHY("Geography"),
  INTERNET_PROGRAMMING("Internet Programming"),
  JAVA_OOP("Object Oriented Programming with Java"),
  JAVA_CP("Concurrent Programming with Java");

  private final String title;
  private static final Random random = new Random();
  Subject(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public static Subject getRandomSubject() {
    return Subject.values()[random.nextInt(Subject.values().length)];
  }
}