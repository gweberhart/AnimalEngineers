/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AnimalEvent extends GwtEvent<AnimalEventHandler> {
  public final static int ANIMAL_DELETED_EVENT = 0;
  public final static int ANIMAL_SAVED_EVENT = 1;
  private int event;

  public AnimalEvent(int event) {
    this.setEvent(event);
  }

  public static Type<AnimalEventHandler> TYPE = new Type<AnimalEventHandler>();

  @Override
  public Type<AnimalEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AnimalEventHandler handler) {
    handler.onListEvent(this);
  }

  public void setEvent(int event) {
    this.event = event;
  }

  public int getEvent() {
    return event;
  }
}
