/**
 * Copyright (c) 2011 Animal Engineers, All Rights Reserved
 */
package org.ae.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginEventHandler> {
  public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();

  public LoginEvent() {
  }

  @Override
  public Type<LoginEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LoginEventHandler handler) {
    handler.onEvent(this);
  }

}