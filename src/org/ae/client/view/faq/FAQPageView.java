package org.ae.client.view.faq;

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

public class FAQPageView extends Composite implements FAQPagePresenter.Display {
  private LayoutContainer pnlStatenentAndAdds;

  public FAQPageView() {

    LayoutContainer pnlMain = new LayoutContainer();
    TableLayout tl_pnlMain = new TableLayout(1);
    pnlMain.setLayout(tl_pnlMain);

    pnlStatenentAndAdds = new LayoutContainer();
    TableLayout tl_pnlAnimalsAndAdds = new TableLayout(2);
    pnlStatenentAndAdds.setLayout(tl_pnlAnimalsAndAdds);

    ContentPanel pnlStatement = new ContentPanel();
    pnlStatement.setFrame(true);
    pnlStatement.setHeading("Frequently Asked Questions");
    pnlStatement.setUrl(ClientUtils.getImagePath() + "faq.html");
    if (ClientUtils.isGreyhounds()) {
      pnlStatement.setHeight(1900);
    } else {
      pnlStatement.setHeight(750);
    }

    TableData td_pnlStatement = new TableData();
    td_pnlStatement.setHorizontalAlign(HorizontalAlignment.LEFT);
    td_pnlStatement.setWidth("742");
    td_pnlStatement.setPadding(5);
    td_pnlStatement.setVerticalAlign(VerticalAlignment.TOP);
    pnlStatenentAndAdds.add(pnlStatement, td_pnlStatement);

    TableData td_adsWidget = new TableData();
    td_adsWidget.setVerticalAlign(VerticalAlignment.TOP);
    if (ClientUtils.isGreyhounds()) {
      pnlStatenentAndAdds.add(new AdsWidget2(), td_adsWidget);
    } else {
      pnlStatenentAndAdds.add(new AdsWidget(), td_adsWidget);
    }

    TableData td_pnlStatementAndAnds = new TableData();
    td_pnlStatementAndAnds.setPadding(5);
    td_pnlStatementAndAnds.setVerticalAlign(VerticalAlignment.TOP);
    pnlMain.add(pnlStatenentAndAdds, td_pnlStatementAndAnds);

    initComponent(pnlMain);
  }

  @Override
  public Widget asWidget() {
    return this;
  }

}
