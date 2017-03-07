/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class NavigateEvent extends GwtEvent<NavigateEventHandler> {
  public static Type<NavigateEventHandler> TYPE = new Type<NavigateEventHandler>();
  private String continuePage;
  private Long animalKey;
  private String historyToken;

  public NavigateEvent(String historyToken) {
    this.historyToken = historyToken;
  }

  public NavigateEvent(String historyToken, String continuePage) {
    this.historyToken = historyToken;
    this.continuePage = continuePage;
  }

  public NavigateEvent(String historyToken, String continuePage, Long animalKey) {
    this.historyToken = historyToken;
    this.continuePage = continuePage;
    this.animalKey = animalKey;
  }

  @Override
  public Type<NavigateEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(NavigateEventHandler handler) {
    handler.onNavigationEvent(this);
  }

  public void setContinuePage(String continuePage) {
    this.continuePage = continuePage;
  }

  public String getContinuePage() {
    return continuePage;
  }

  public void setAnimalKey(Long animalKey) {
    this.animalKey = animalKey;
  }

  public Long getAnimalKey() {
    return animalKey;
  }

  public String getHistoryToken() {
    return historyToken;
  }

}