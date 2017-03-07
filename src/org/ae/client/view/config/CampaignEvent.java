package org.ae.client.view.config;

import com.google.gwt.event.shared.GwtEvent;

public class CampaignEvent extends GwtEvent<CampaignEventHandler> {
  public final static int DELETE_IMAGE = 0;
  public final static int SELECT_NEW_IMAGE = 1;
  public static final int IMAGE_VIEW_CLOSED = 3;
  private int event;
  private ImageWidget imageWidget;

  public CampaignEvent(int event) {
    this.setEvent(event);
  }

  public static Type<CampaignEventHandler> TYPE = new Type<CampaignEventHandler>();

  @Override
  public Type<CampaignEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CampaignEventHandler handler) {
    handler.onEvent(this);
  }

  public void setEvent(int event) {
    this.event = event;
  }

  public int getEvent() {
    return event;
  }

  public void setImageWidget(ImageWidget imageWidget) {
    this.imageWidget = imageWidget;
  }

  public ImageWidget getImageWidget() {
    return imageWidget;
  }
}
