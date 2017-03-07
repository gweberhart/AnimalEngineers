package org.ae.client.view.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ae.client.AEHOME;
import org.ae.shared.CampaignProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;

public class CampaignEditView extends Composite implements
    Editor<CampaignProxy>, Serializable {
  private static final long serialVersionUID = 1L;
  private static CampaignEditViewUiBinder uiBinder = GWT
      .create(CampaignEditViewUiBinder.class);
  @UiField
  TextBox adTitle;
  @UiField(provided = true)
  ValueListBox<String> subCategory = new ValueListBox<String>(
      new Renderer<String>() {

        @Override
        public String render(String object) {
          String s = "";
          if (object != null) {
            s = object.toString();
          }
          return s;
        }

        @Override
        public void render(String object, Appendable appendable)
            throws IOException {
          render(object);
        }

      });
  @UiField
  Button btnSaveCampaign;
  @UiField
  Button btnCancelSaveCampaign;
  @UiField
  TextArea description;
  @UiField
  ImageFlowEditor imagePaths;
  @UiField()
  ImageWidget primaryImagePath;
  @UiField
  TextBox price;
  @UiField
  TextBox contactName;
  @UiField
  TextBox street1;
  @UiField
  TextBox emailAddress;
  @UiField
  TextBox street2;
  @UiField
  TextBox city;
  @UiField
  TextBox state;
  @UiField
  TextBox zipCode;
  @UiField
  CheckBox active;
  @UiField
  TextBox phoneAreaCode;
  @UiField
  TextBox phonePrefix;
  @UiField
  TextBox phoneSuffix;
  @UiField
  DateLabel lastPostTime;

  @Ignore
  ConfigurationWorkflow configurationWorkflow;

  interface CampaignEditViewUiBinder extends UiBinder<Widget, CampaignEditView> {
  }

  public CampaignEditView() {
    this(null);
  }

  public CampaignEditView(ConfigurationWorkflow configurationWorkflow) {
    this.configurationWorkflow = configurationWorkflow;
    List<String> values = new ArrayList<String>();
    values.add("Animal Shelters and Rescues");
    values.add("Birds");
    values.add("Cats");
    values.add("Dogs");
    values.add("Other Pets");
    values.add("Rabbits");
    subCategory.setAcceptableValues(values);
    initWidget(uiBinder.createAndBindUi(this));
    bind();
  }

  private void bind() {
    if (AEHOME.eventBus == null) {
      return;
    }
    AEHOME.eventBus.addHandler(CampaignEvent.TYPE, new CampaignEventHandler() {

      @Override
      public void onEvent(CampaignEvent event) {
        switch (event.getEvent()) {
        case CampaignEvent.DELETE_IMAGE:
          if (event.getImageWidget() == primaryImagePath) {
            primaryImagePath.setValue(null);
          }
          break;
        }
      }
    });
  }

  @UiHandler("btnSaveCampaign")
  void onBtnSaveCampaignClick(ClickEvent event) {
    configurationWorkflow.saveCampaign();
  }

  @UiHandler("btnCancelSaveCampaign")
  void onBtnCancelSaveCampaignClick(ClickEvent event) {
    configurationWorkflow.showListView();
  }

  @UiHandler("adTitle")
  void onAdTitleBlur(BlurEvent event) {
    if (adTitle.getText().contains("Home Page Images")) {
      description
          .setText("Home Page Images. The images in this campaign will show "
              + "up as a slide show on the home page");
      subCategory.setValue("Dogs");
      price.setValue("1");
      contactName.setValue("1");
      emailAddress.setValue("a@e.com");
      street1.setValue("1");
      street2.setValue("1");
      city.setValue("1");
      state.setValue("NV");
      zipCode.setValue("11111");
      phoneAreaCode.setValue("801");
      phonePrefix.setValue("111");
      phoneSuffix.setValue("1111");
    }

  }
}
