/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client;

public enum ViewENUM {
  REFRESHDB(
      "prepareForRunningAllTests",
      "http://127.0.0.1:8888/#prepareForRunningAllTests"),
  HOME(
      "home", "http://127.0.0.1:8888/#home"),
  MISSION(
      "mission", "http://127.0.0.1:8888/#mission"),
  ADOPTABLES(
      "adoptables", "http://127.0.0.1:8888/#adoptables"),
  CALENDAR(
      "calendar", "http://127.0.0.1:8888/#calendar"),
  DONATE(
      "donate", "http://127.0.0.1:8888/#donate"),
  ADD_ANIMAL_DIALOG(
      "addAnimalDialog", "http://127.0.0.1:8888/#addAnimalDialog"),
  EDIT_ANIMAL_DIALOG(
      "editAnimalDialog", "http://127.0.0.1:8888/#editAnimalDialog"),
  DELETE_ANIMAL_POPUP(
      "deleteAnimalPrompt", "http://127.0.0.1:8888/#deleteAnimalPrompt"),
  LOGIN(
      "?", "http://127.0.0.1:8888/_ah/login?continue=http://127.0.0.1:8888/"),
  CONFIGURATION(
      "configuration", "http://127.0.0.1:8888/#configuration"),
  FAQ(
      "FAQ", "http://127.0.0.1:8888/#FAQ"), ;

  private String historyToken;
  private String url;

  ViewENUM(String historyToken, String url) {
    this.historyToken = historyToken;
    this.url = url;
  }

  public String getHistoryToken() {
    return historyToken;
  }

  public String getURL() {
    return url;
  }
}