package org.ae.client.view.home;

import java.util.List;

import org.ae.client.AEHOME;
import org.ae.client.ViewENUM;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.events.ViewChangeEventHandler;
import org.ae.client.frame.ClientUtils;
import org.ae.client.model.Rescue;
import org.ae.client.view.home.HomePagePresenter.Display;
import org.ae.shared.CampaignProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class HomePage3 extends Composite implements Display {

  private static HomePage3UiBinder uiBinder = GWT
      .create(HomePage3UiBinder.class);

  interface HomePage3UiBinder extends UiBinder<Widget, HomePage3> {
  }

  SimpleEventBus eventBus;
  @UiField
  DeckPanel deckPanel;
  @UiField(provided = true)
  LeadBar3 leadBar;
  @UiField
  HTMLPanel mainPanel;
  private List<String> imagePaths;
  private int currentImageIndex = 0;
  protected boolean allLoaded;
  private Timer timer;
  private boolean processTimer;

  public HomePage3() {
    init();
    leadBar = new LeadBar3();
    leadBar.setVisible(false);
  }

  public HomePage3(SimpleEventBus aEventBus) {
    this.eventBus = aEventBus;
    leadBar = new LeadBar3(eventBus);
    init();
    setPanelSizers("constructor");
    if (!Rescue.getCurrentRescue().getShowLeadBar()) {
      leadBar.setVisible(false);
    }
  }

  private void setPanelSizers(String operation) {
    int imageBarOffset;
    int h = Window.getClientHeight();
    if (ClientUtils.isAnimalEngineers()) {
      h -= 90; // footer size
      imageBarOffset = 0; // lead bar size
    } else {
      h -= 100;
      imageBarOffset = 160;
    }
    mainPanel.setHeight(h + "px");
    deckPanel.setHeight(h - imageBarOffset + "px");
    deckPanel.setWidth(Window.getClientWidth() + "px");
  }

  private void init() {
    initWidget(uiBinder.createAndBindUi(this));
    deckPanel.setAnimationEnabled(true);
    addImage(ClientUtils.getImagePath() + "mainIntro.png");
    Window.addResizeHandler(new ResizeHandler() {

      @Override
      public void onResize(ResizeEvent event) {
        startOver();
      }

    });
    AEHOME.requestFactory.createCampaignRequest()
        .findHomePageCampaign(ClientUtils.getInstitutionID())
        .fire(new Receiver<CampaignProxy>() {

          @Override
          public void onSuccess(CampaignProxy response) {
            if (response != null) {
              imagePaths = response.getImagePaths();
              imagePaths.add(0, response.getPrimaryImagePath());
              imagePaths.add(0, ClientUtils.getImagePath() + "mainIntro.png");
              addImageTimer();
            }
          }
        });
    bind();
  }

  private void startOver() {
    if (timer != null) {
      timer.cancel();
    }
    deckPanel.clear();
    currentImageIndex = 0;
    allLoaded = false;
    addImage(ClientUtils.getImagePath() + "mainIntro.png");
    addImageTimer();
  }

  private void bind() {
    eventBus.addHandler(ViewChangeEvent.TYPE, new ViewChangeEventHandler() {
      @Override
      public void onEvent(ViewChangeEvent event) {
        if (timer != null) {
          timer.cancel();
        }
        if (event.getRequestedPage().equals(ViewENUM.HOME)) {
          startOver();
        }
      }
    });
  }

  private void addImageTimer() {
    if (timer != null) {
      timer.cancel();
    }
    timer = new Timer() {

      @Override
      public void run() {
        if (!processTimer || imagePaths == null ) {
          return;
        }
        int imageCount = imagePaths.size();
        currentImageIndex++;
        if (currentImageIndex > imageCount - 1) {
          currentImageIndex = 0;
        }
        String imageURL = imagePaths.get(currentImageIndex);
        if (!allLoaded) {
          addImage(imageURL);
        } else {
          deckPanel.showWidget(currentImageIndex);
        }
        if (currentImageIndex == imageCount - 1) {
          allLoaded = true;
        }
      }

    };
    timer.scheduleRepeating(6000);
  }

  private void addImage(String imageURL) {
    Image image = new Image();
    imageURL = imageURL.replace("=s240", "=s" + 1024);
    image.setUrl(imageURL);
    image.setWidth("100%");
    deckPanel.add(image);
    processTimer = false;
    image.addLoadHandler(new LoadHandler() {

      @Override
      public void onLoad(LoadEvent event) {
        deckPanel.showWidget(currentImageIndex);
        setPanelSizers("onImageLoad");
        processTimer = true;
      }
    });
  }

}
