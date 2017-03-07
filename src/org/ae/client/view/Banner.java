package org.ae.client.view;

import org.ae.client.frame.ClientUtils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.ui.Image;

public class Banner extends Composite {
  private LayoutContainer layoutContainer;
  private Image image;

  public Banner() {
    setAutoHeight(true);
    initComponent(getLayoutContainer());
    setAutoWidth(true);
  }

  private LayoutContainer getLayoutContainer() {
    if (layoutContainer == null) {
      layoutContainer = new LayoutContainer();
      layoutContainer.setWidth("");
      TableLayout tl_layoutContainer = new TableLayout(1);
      tl_layoutContainer.setWidth("100%");
      tl_layoutContainer.setCellHorizontalAlign(HorizontalAlignment.CENTER);
      layoutContainer.setLayout(tl_layoutContainer);
      TableData td_image = new TableData();
      td_image.setWidth("100%");
      td_image.setHorizontalAlign(HorizontalAlignment.CENTER);
      layoutContainer.add(getImage(), td_image);
    }
    return layoutContainer;
  }

  private Image getImage() {
    if (image == null) {
      image = new Image(ClientUtils.getImagePath() + "smallHeader.png");
    }
    return image;
  }
}
