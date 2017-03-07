package org.ae.client.view;

import org.ae.client.frame.ClientUtils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;

public class AdsWidget extends Composite {
  private Image image_ad2;
  private Image image_ad1;
  private LayoutContainer pnlAdds;
  private Image imageBCARN;

  public AdsWidget() {
    pnlAdds = new LayoutContainer();
    TableData td_pnlAdds = new TableData();
    td_pnlAdds.setVerticalAlign(VerticalAlignment.TOP);
    TableLayout tl_pnlAdds = new TableLayout(1);
    tl_pnlAdds.setCellHorizontalAlign(HorizontalAlignment.CENTER);
    tl_pnlAdds.setCellPadding(5);
    tl_pnlAdds.setCellSpacing(0);
    pnlAdds.setLayout(tl_pnlAdds);

    image_ad2 = new Image("images/ae/Bulldog/bd_side_ad_dance.png");
    image_ad2.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        ClientUtils.showDonationDialog(); 
      }
    });
    image_ad2.addMouseOverHandler(new MouseOverHandler() {
      public void onMouseOver(MouseOverEvent event) {
        image_ad2.addStyleName("ImagePointer-enter");
      }
    });

    image_ad1 = new Image("images/ae/Bulldog/NoNevadaBulldogRescue.gif");
    pnlAdds.add(image_ad1);
    pnlAdds.add(image_ad2);

    imageBCARN = new Image("images/ae/Bulldog/bcarn.png");
    imageBCARN.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Window.open(
            "http://www.rescuebulldogs.org/rescueroster/rescueroster.pl",
            "new", null);
      }
    });
    imageBCARN.addMouseOverHandler(new MouseOverHandler() {
      public void onMouseOver(MouseOverEvent event) {
        imageBCARN.addStyleName("ImagePointer-enter");
      }
    });

    TableData td_imageBCARN = new TableData();
    td_imageBCARN.setPadding(30);
    td_imageBCARN.setHorizontalAlign(HorizontalAlignment.CENTER);
    pnlAdds.add(imageBCARN, td_imageBCARN);

    initComponent(pnlAdds);
    pnlAdds.setHeight("771px");
  }

}
