package org.ae.client.view.home;

import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.ClientUtils;
import org.ae.client.model.Rescue;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class LeadBar3 extends Composite {

  private static LeadBar3UiBinder uiBinder = GWT.create(LeadBar3UiBinder.class);
  @UiField
  Image lead1;
  @UiField
  Image lead2;
  @UiField
  Image lead3;
  private SimpleEventBus eventBus;

  interface LeadBar3UiBinder extends UiBinder<Widget, LeadBar3> {
  }

  public LeadBar3() {
    init();
  }

  public LeadBar3(SimpleEventBus aEventBus) {
    this.eventBus = aEventBus;
    init();
  }

  private void init() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public LeadBar3(String firstName) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiHandler("lead1")
  void onLead1Click(ClickEvent event) {
    eventBus.fireEvent(new NavigateEvent(ViewENUM.CALENDAR.getHistoryToken()));

  }

  @UiHandler("lead2")
  void onLead2Click(ClickEvent event) {
    Window.open("http://stores.ebay.com/Cindi-Lynchs-Watercolor-Gallery",
        "new", null);
  }

  @UiHandler("lead3")
  void onLead3Click(ClickEvent event) {
    if (Rescue.getCurrentRescue() != null) {
      doGo();
    }
  }

  private void doGo() {
    ClientUtils.mailto(Rescue.getCurrentRescue().getEmailAddress(), "Animals");
  }

  @UiHandler("lead1")
  void onLead1MouseOver(MouseOverEvent event) {
    lead1.addStyleName("ImagePointer-enter");
  }

  @UiHandler("lead2")
  void onLead2MouseOver(MouseOverEvent event) {
    lead2.addStyleName("ImagePointer-enter");
  }

  @UiHandler("lead3")
  void onLead3MouseOver(MouseOverEvent event) {
    lead3.addStyleName("ImagePointer-enter");
  }
}
