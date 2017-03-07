package org.ae.client.view.config;

import java.util.ArrayList;
import java.util.List;

import org.ae.client.AEHOME;
import org.ae.client.frame.ClientUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImageFlowEditor extends Composite implements
    LeafValueEditor<List<String>> {

  private static ImageFlowEditorUiBinder uiBinder = GWT
      .create(ImageFlowEditorUiBinder.class);

  interface ImageFlowEditorUiBinder extends UiBinder<Widget, ImageFlowEditor> {
  }

  @UiField(provided = true)
  FlowPanel flowPanel = new FlowPanel();
  @Ignore
  List<String> paths;

  public ImageFlowEditor() {
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
          flowPanel.remove(event.getImageWidget());
          break;
        case CampaignEvent.SELECT_NEW_IMAGE:
          event.getImageWidget().doUpload();
          break;
        }
      }
    });
  }

  @Override
  public void setValue(List<String> imagePaths) {
    if (imagePaths == null) {
      paths = new ArrayList<String>();
    } else {
      paths = new ArrayList<String>(imagePaths);
    }
    flowPanel.clear();
    if (imagePaths != null) {
      for (String aServingUrl : imagePaths) {
        ImageWidget w = new ImageWidget(aServingUrl);
        flowPanel.add(w);
      }
    }
  }

  @Override
  public List<String> getValue() {
    int widgetCount = flowPanel.getWidgetCount();
    paths.clear();
    for (int i = 0; i < widgetCount; i++) {
      ImageWidget w = (ImageWidget) flowPanel.getWidget(i);
      if (!w.getServingUrl().equals(ClientUtils.DEFAULT_IMAGE)) {
        paths.add(w.getServingUrl());
      }
    }
    return paths;
  }

  @UiHandler("btnAddImage")
  void onBtnAddImageClick(ClickEvent event) {
    ImageWidget w = new ImageWidget(ClientUtils.DEFAULT_IMAGE);
    flowPanel.add(w);
    w.doUpload();
  }
}
