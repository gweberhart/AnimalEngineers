/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.events;

import org.ae.client.ViewENUM;

import com.google.gwt.event.shared.GwtEvent;

public class ViewChangeEvent extends GwtEvent<ViewChangeEventHandler> {
  public static Type<ViewChangeEventHandler> TYPE = new Type<ViewChangeEventHandler>();
  private ViewENUM requestedPage;

  public ViewChangeEvent(ViewENUM requestedPage) {
    this.setRequestedPage(requestedPage);
  }

  @Override
  public Type<ViewChangeEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ViewChangeEventHandler handler) {
    handler.onEvent(this);

  }

  public ViewENUM getRequestedPage() {
    return requestedPage;
  }

  public void setRequestedPage(ViewENUM requestedPage) {
    this.requestedPage = requestedPage;
  }

}