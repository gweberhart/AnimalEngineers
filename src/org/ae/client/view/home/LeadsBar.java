package org.ae.client.view.home;

import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableRowLayout;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;

public class LeadsBar extends Composite {
  Image lead1Image;
  Image lead2Image;
  Image lead3Image;
  private LeadPopup popup = new LeadPopup();
  private boolean popped;

  public LeadsBar() {

    LayoutContainer lcLeads = new LayoutContainer();
    TableRowLayout trl_lcLeads = new TableRowLayout();
    trl_lcLeads.setCellPadding(5);
    lcLeads.setLayout(trl_lcLeads);

    lead1Image = new Image("images/ae/Bulldog/lead_1.png");
    lead1Image.addMouseOverHandler(new MouseOverHandler() {
      public void onMouseOver(MouseOverEvent event) {
        lead1Image.addStyleName("ImagePointer-enter");
      }
    });
    lcLeads.add(lead1Image);

    lead2Image = new Image("images/ae/Bulldog/lead_2.png");
    lead2Image.addMouseDownHandler(new MouseDownHandler() {

      @Override
      public void onMouseDown(MouseDownEvent event) {
        popup.show();
        popped = true;
      }
    });
    lead2Image.addMouseOverHandler(new MouseOverHandler() {
      public void onMouseOver(MouseOverEvent event) {
        popup.showRelativeTo(lead2Image);
        popped = true;
      }
    });
    lead2Image.addMouseOutHandler(new MouseOutHandler() {
      public void onMouseOut(MouseOutEvent event) {
        popped = false;
        Timer t = new Timer() {

          @Override
          public void run() {
            if (popped == false) {
              popup.hide();
            }
          }
        };
        t.schedule(50);
      }
    });

    popup.getLead2Html().addMouseOverHandler(new MouseOverHandler() {
      public void onMouseOver(MouseOverEvent event) {
        popped = true;
      }
    });
    popup.getLead2Html().addMouseOutHandler(new MouseOutHandler() {
      public void onMouseOut(MouseOutEvent event) {
        popup.hide();
        popped = false;
      }
    });

    lcLeads.add(lead2Image);

    lead3Image = new Image("images/ae/Bulldog/lead_3.png");
    lead3Image.addMouseOverHandler(new MouseOverHandler() {
      public void onMouseOver(MouseOverEvent event) {
        lead3Image.addStyleName("ImagePointer-enter");
      }
    });
    lcLeads.add(lead3Image);
    initComponent(lcLeads);
  }
}
