package org.ae.client.view.config;

import org.ae.client.AEHOME;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class ImageOptionsEditor extends Composite implements HasText {

  private static ImageOptionsEditorUiBinder uiBinder = GWT
      .create(ImageOptionsEditorUiBinder.class);

  interface ImageOptionsEditorUiBinder extends
      UiBinder<Widget, ImageOptionsEditor> {
  }

  private ImageWidget imageWidget;

  public ImageOptionsEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public ImageOptionsEditor(ImageWidget aImageWidget) {
    this.imageWidget = aImageWidget;
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiField
  Button btnDelete;
  @UiField
  Button btnChange;

  public ImageOptionsEditor(String firstName) {
    initWidget(uiBinder.createAndBindUi(this));
    btnDelete.setText(firstName);
  }

  @UiHandler("btnDelete")
  void onClick(ClickEvent e) {
    CampaignEvent campaignEvent = new CampaignEvent(CampaignEvent.DELETE_IMAGE);
    campaignEvent.setImageWidget(imageWidget);
    AEHOME.eventBus.fireEvent(campaignEvent);
  }

  public void setText(String text) {
    btnDelete.setText(text);
  }

  public String getText() {
    return btnDelete.getText();
  }

  @UiHandler("btnChange")
  void onBtnChangeClick(ClickEvent event) {
    CampaignEvent campaignEvent = new CampaignEvent(
        CampaignEvent.SELECT_NEW_IMAGE);
    campaignEvent.setImageWidget(imageWidget);
    AEHOME.eventBus.fireEvent(campaignEvent);
  }
}
