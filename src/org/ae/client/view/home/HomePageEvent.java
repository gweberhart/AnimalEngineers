package org.ae.client.view.home;

import com.google.gwt.event.shared.GwtEvent;

public class HomePageEvent extends GwtEvent<HomePageEventHandler> {
  public final static int SLIDE_SHOW_LOADED = 0;
  private int event;

  public HomePageEvent(int event) {
    this.setEvent(event);
  }

  public static Type<HomePageEventHandler> TYPE = new Type<HomePageEventHandler>();

  @Override
  public Type<HomePageEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(HomePageEventHandler handler) {
    handler.onEvent(this);
  }

  public void setEvent(int event) {
    this.event = event;
  }

  public int getEvent() {
    return event;
  }

}
