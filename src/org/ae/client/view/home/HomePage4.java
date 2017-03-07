package org.ae.client.view.home;

import org.ae.client.view.home.HomePagePresenter.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HomePage4 extends Composite implements Display {

  private static HomePage4UiBinder uiBinder = GWT
      .create(HomePage4UiBinder.class);
  @UiField(provided = true)
  SlideShow slideShow;
  @UiField(provided = true)
  LeadBar3 leadBar;
  @UiField
  Image loadingImage;
  @UiField
  Image loadingImage1;
  @UiField
  Image leftDog;
  @UiField
  Image rightDog;

  interface HomePage4UiBinder extends UiBinder<Widget, HomePage4> {
  }

  public HomePage4() {
    slideShow = new SlideShow();
    leadBar = new LeadBar3();
    initWidget(uiBinder.createAndBindUi(this));
  }

  public HomePage4(SimpleEventBus eventBus) {
    slideShow = new SlideShow(eventBus);
    leadBar = new LeadBar3(eventBus);
    initWidget(uiBinder.createAndBindUi(this));
    bind(eventBus);
    setDogVisibe();
    Window.addResizeHandler(new ResizeHandler() {
      
      @Override
      public void onResize(ResizeEvent event) {
        setDogVisibe();
      }
    });
  }

  private void setDogVisibe() {
    if (Window.getClientWidth() < 1115) {
      leftDog.setVisible(false);
      rightDog.setVisible(false);
    } else {
      leftDog.setVisible(true);
      rightDog.setVisible(true);
    }
  }
  private void bind(SimpleEventBus eventBus) {
    eventBus.addHandler(HomePageEvent.TYPE, new HomePageEventHandler() {

      @Override
      public void onEvent(HomePageEvent event) {
        loadingImage1.setVisible(false);
        loadingImage.setVisible(false);
      }
    });
  }

}
