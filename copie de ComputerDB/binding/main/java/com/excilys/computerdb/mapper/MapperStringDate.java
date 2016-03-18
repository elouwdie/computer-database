package com.excilys.computerdb.mapper;

import java.time.LocalDate;

public class MapperStringDate {

  /**
   * Obtains an instance of LocalDate from a string.
   * @param dateString : the french string to translate.
   * @return : the instance of LocalDate obtained.
   */
  public static LocalDate frenchDateOf(String dateString) {
    int year = Integer.parseInt(dateString.substring(6, 10));
    int month = Integer.parseInt(dateString.substring(3, 5));
    int day = Integer.parseInt(dateString.substring(0, 2));
    LocalDate date = LocalDate.of(year, month, day);
    return date;
  }

  /**
   * Obtains an instance of LocalDate from a string.
   * @param dateString : the english string to translate.
   * @return : the instance of LocalDate obtained.
   */
  public static LocalDate englishDateOf(String dateString) {
    int year = Integer.parseInt(dateString.substring(6, 10));
    int month = Integer.parseInt(dateString.substring(0, 2));
    int day = Integer.parseInt(dateString.substring(3, 5));
    LocalDate date = LocalDate.of(year, month, day);
    return date;
  }

  /**
   * Obtains a String from a LocalDate object.
   * @param date : the french LocalDate to translate.
   * @return : the String obtained.
   */
  public static String frenchStringOf(LocalDate date) {
    String[] dateStrings = date.toString().split("-");
    String dateString = dateStrings[2] + "/" + dateStrings[1] + "/" + dateStrings[0];
    return dateString;
  }

  /**
   * Obtains a String from a LocalDate object.
   * @param date : the english LocalDate to translate.
   * @return : the String obtained.
   */
  public static String englishStringOf(LocalDate date) {
    String[] dateStrings = date.toString().split("-");
    String dateString = dateStrings[1] + "/" + dateStrings[2] + "/" + dateStrings[0];
    return dateString;
  }
}
