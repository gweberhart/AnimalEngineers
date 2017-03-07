package org.ae.client.view.home;

import java.util.List;

import org.ae.client.AEHOME;
import org.ae.client.frame.ClientUtils;
import org.ae.client.view.home.HomePagePresenter.Display;
import org.ae.shared.CampaignProxy;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.reveregroup.gwt.imagepreloader.Dimensions;
import com.reveregroup.gwt.imagepreloader.ImageLoadEvent;
import com.reveregroup.gwt.imagepreloader.ImageLoadHandler;
import com.reveregroup.gwt.imagepreloader.ImagePreloader;

public class SlideShow extends Composite implements Display {

  SimpleEventBus eventBus;
  DeckPanel deckPanel = new DeckPanel();
  private List<String> imagePaths;
  private int currentImageIndex = 0;
  protected boolean allLoaded;
  private Timer timer;
  private boolean processTimer;
  private Image currentImage;
  String[] imageHeigths;
  String[] imageWidths;

  public SlideShow() {
    initWidget(deckPanel);
  }

  public SlideShow(SimpleEventBus eventBus) {
    this.eventBus = eventBus;
    initWidget(deckPanel);
    init();
    setPanelSizers("constructor");
    processTimer = true;
  }

  private void setPanelSizers(String operation) {
  }

  private void init() {
    deckPanel.setAnimationEnabled(true);
    AEHOME.requestFactory.createCampaignRequest()
        .findHomePageCampaign(ClientUtils.getInstitutionID())
        .fire(new Receiver<CampaignProxy>() {

          @Override
          public void onSuccess(CampaignProxy response) {
            if (response != null) {
              imagePaths = response.getImagePaths();
              imagePaths.add(0, response.getPrimaryImagePath());
              imageHeigths = new String[imagePaths.size()];
              imageWidths = new String[imagePaths.size()];
              changeImage();
              addImageTimer();
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
        if (!processTimer) {
          return;
        }
        changeImage();
      }

    };
    timer.scheduleRepeating(6000);
  }

  private void changeImage() {
    int imageCount = imagePaths.size();
    if (currentImageIndex > imageCount - 1) {
      currentImageIndex = 0;
    }
    String imageURL = imagePaths.get(currentImageIndex);
    if (!allLoaded) {
      addImage(imageURL);
    } else {
      deckPanel.showWidget(currentImageIndex);
      deckPanel.setHeight(imageHeigths[currentImageIndex]);
      deckPanel.setWidth(imageWidths[currentImageIndex]);
      currentImageIndex++;
    }
    if (currentImageIndex == imageCount - 1) {
      allLoaded = true;
    }
  }

  private void addImage(String imageURL) {
    currentImage = new Image();
    imageURL = imageURL.replace("=s240", "=s" + 800);
    currentImage.setUrl(imageURL);
    deckPanel.add(currentImage);
    processTimer = false;
    ImagePreloader.load(imageURL, new ImageLoadHandler() {
      public void imageLoaded(ImageLoadEvent event) {
        if (event.isLoadFailed()) {
          Window.alert("Image " + event.getImageUrl() + " failed to load.");
        } else {
          afterLoad(event.getDimensions());
        }
      }
    });
  }

  private void afterLoad(Dimensions dimensions) {
    String h = dimensions.getHeight() + "px";
    String w = dimensions.getWidth() + "px";
    imageWidths[currentImageIndex] = w;
    imageHeigths[currentImageIndex] = h;
    deckPanel.setHeight(imageHeigths[currentImageIndex]);
    deckPanel.setWidth(imageWidths[currentImageIndex]);
    deckPanel.showWidget(currentImageIndex);
    currentImageIndex++;
    HomePageEvent hpe = new HomePageEvent(HomePageEvent.SLIDE_SHOW_LOADED);
    AEHOME.eventBus.fireEvent(hpe);
    processTimer = true;
  }
  
  public int getWidth() {
    return getWidth();
  }
}
