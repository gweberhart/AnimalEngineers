package org.ae.client.view.home;

import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.ClientUtils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HomePage extends Composite implements HomePagePresenter.Display {
  private Image image;
  private LeadsBar leadsBar;
  private ContactBar contactBar;
  private SimpleEventBus eventBus;

  public HomePage() {

    LayoutContainer layoutContainer = new LayoutContainer();
    layoutContainer.setAutoHeight(true);
    layoutContainer.setAutoWidth(true);
    layoutContainer.setId("HomePageHeader");
    TableLayout tl_layoutContainer = new TableLayout(1);
    tl_layoutContainer.setCellHorizontalAlign(HorizontalAlignment.CENTER);
    layoutContainer.setLayout(tl_layoutContainer);

    LayoutContainer layoutContainer_4 = new LayoutContainer();
    TableData td_layoutContainer_4 = new TableData();
    td_layoutContainer_4.setPadding(20);
    layoutContainer.add(layoutContainer_4, td_layoutContainer_4);

    LayoutContainer layoutContainer_2 = new LayoutContainer();

    image = new Image("images/ae/AnimalEngineers/mainIntro.png");
    layoutContainer_2.add(image);
    TableData td_layoutContainer_2 = new TableData();
    td_layoutContainer_2.setHorizontalAlign(HorizontalAlignment.CENTER);
    td_layoutContainer_2.setHeight("");
    layoutContainer.add(layoutContainer_2, td_layoutContainer_2);

    LayoutContainer layoutContainer_3 = new LayoutContainer();
    layoutContainer_3.setLayout(new FillLayout(Orientation.HORIZONTAL));
    TableData td_layoutContainer_3 = new TableData();
    td_layoutContainer_3.setPadding(25);
    layoutContainer.add(layoutContainer_3, td_layoutContainer_3);

    leadsBar = new LeadsBar();
    layoutContainer.add(leadsBar);
    leadsBar.setWidth("990");

    contactBar = new ContactBar();
    TableData td_lcContacts = new TableData();
    td_lcContacts.setPadding(20);
    layoutContainer.add(contactBar, td_lcContacts);

    if (ClientUtils.isAnimalEngineers() || ClientUtils.isCaws()
        || ClientUtils.isGreyhounds()) {
      layoutContainer.remove(leadsBar);
      layoutContainer.remove(contactBar);
    }
    initComponent(layoutContainer);
    layoutContainer.setSize("1001px", "704px");
    image.setUrl(ClientUtils.getImagePath() + "mainIntro.png");
  }

  public HomePage(SimpleEventBus aEventBus) {
    this();
    this.eventBus = aEventBus;
    bind();
  }

  public void bind() {
    if (ClientUtils.isBulldog()) {
      leadsBar.lead1Image.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          eventBus.fireEvent(new NavigateEvent(ViewENUM.ADOPTABLES
              .getHistoryToken()));
        }
      });
      leadsBar.lead2Image.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
        }
      });
      leadsBar.lead3Image.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          Window.open("http://www.renobulldogclub.org/", "new", null);
        }
      });
    }
  }

  @Override
  public Widget asWidget() {
    return this;
  }

}
