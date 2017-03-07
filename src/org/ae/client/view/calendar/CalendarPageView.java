package org.ae.client.view.calendar;

import org.ae.client.frame.ClientUtils;
import org.ae.client.view.AdsWidget;
import org.ae.client.view.AdsWidget2;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.ui.Widget;

public class CalendarPageView extends Composite implements
    CalendarPagePresenter.Display {
  private LayoutContainer pnlCalendarAndAdds;
  ContentPanel pnlCalendar;

  public CalendarPageView() {

    LayoutContainer pnlMain = new LayoutContainer();
    TableLayout tl_pnlMain = new TableLayout(1);
    pnlMain.setLayout(tl_pnlMain);

    pnlCalendarAndAdds = new LayoutContainer();
    TableLayout tl = new TableLayout(2);
    pnlCalendarAndAdds.setLayout(tl);

    pnlCalendar = new ContentPanel();
    pnlCalendar.setFrame(true);
    pnlCalendar.setHeading("Calendar");
    pnlCalendar.setHeight(600);

    TableData td1 = new TableData();
    td1.setHorizontalAlign(HorizontalAlignment.LEFT);
    td1.setWidth("742");
    td1.setPadding(5);
    td1.setVerticalAlign(VerticalAlignment.TOP);
    pnlCalendarAndAdds.add(pnlCalendar, td1);

    TableData td_adsWidget = new TableData();
    td_adsWidget.setVerticalAlign(VerticalAlignment.TOP);
    if (ClientUtils.isGreyhounds()) {
      pnlCalendarAndAdds.add(new AdsWidget2(), td_adsWidget);
    } else if (ClientUtils.isBulldog()) {
      pnlCalendarAndAdds.add(new AdsWidget(), td_adsWidget);
    } else {
      pnlCalendar.setWidth(915);
    }

    TableData td2 = new TableData();
    td2.setPadding(5);
    td2.setVerticalAlign(VerticalAlignment.TOP);
    pnlMain.add(pnlCalendarAndAdds, td2);

    initComponent(pnlMain);
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void setCalendarUrl(String calendarUrl) {
    pnlCalendar.setUrl(calendarUrl);
  }

}
