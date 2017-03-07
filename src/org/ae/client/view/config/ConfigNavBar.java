package org.ae.client.view.config;

import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

public class ConfigNavBar extends Composite {

  public ConfigNavBar() {

    LayoutContainer layoutContainer = new LayoutContainer();
    TableLayout tl_layoutContainer = new TableLayout(1);
    tl_layoutContainer.setCellPadding(6);
    tl_layoutContainer.setWidth("100%");
    layoutContainer.setLayout(tl_layoutContainer);
    layoutContainer.setSize("167px", "231px");

    ToggleButton btnCampaigns = new ToggleButton("Campaigns");
    btnCampaigns.setHeight(75);
    btnCampaigns.setToggleGroup("configMenu");
    btnCampaigns.setWidth(150);
    layoutContainer.add(btnCampaigns);

    initComponent(layoutContainer);
  }
}
