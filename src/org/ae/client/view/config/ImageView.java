package org.ae.client.view.config;

import org.ae.client.AEHOME;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ImageView extends Composite implements LeafValueEditor<String> {

  private static ImageViewUiBinder uiBinder = GWT
      .create(ImageViewUiBinder.class);
  @UiField
  Image image;

  interface ImageViewUiBinder extends UiBinder<Widget, ImageView> {
  }

  public ImageView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setValue(String url) {
    image.setUrl(url);
  }

  @Override
  public String getValue() {
    return image.getUrl();
  }

  @UiHandler("image")
  void onImageClick(ClickEvent event) {
    CampaignEvent campaignEvent = new CampaignEvent(
        CampaignEvent.IMAGE_VIEW_CLOSED);
    AEHOME.eventBus.fireEvent(campaignEvent);
  }

  @UiHandler("image")
  void onImageAttachOrDetach(AttachEvent event) {
    if (!event.isAttached()) {
      CampaignEvent campaignEvent = new CampaignEvent(
          CampaignEvent.IMAGE_VIEW_CLOSED);
      AEHOME.eventBus.fireEvent(campaignEvent);
    }
  }
}
