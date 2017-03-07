package org.ae.client.view.home;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class ContactBar extends Composite {

  public ContactBar() {

    LayoutContainer lcContacts = new LayoutContainer();
    lcContacts.setLayout(new TableLayout(2));

    ContentPanel cntntpnlKarenVigil = new ContentPanel();
    cntntpnlKarenVigil.setHeaderVisible(false);
    cntntpnlKarenVigil.setFrame(true);
    cntntpnlKarenVigil.setHeading("Karen Vigil");
    cntntpnlKarenVigil.setCollapsible(true);
    TableLayout tl_cntntpnlKarenVigil = new TableLayout(1);
    tl_cntntpnlKarenVigil.setWidth("100%");
    cntntpnlKarenVigil.setLayout(tl_cntntpnlKarenVigil);

    Label lblKarenVigil = new Label("Karen Vigil");
    lblKarenVigil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    TableData td_lblKarenVigil = new TableData();
    td_lblKarenVigil.setPadding(5);
    cntntpnlKarenVigil.add(lblKarenVigil, td_lblKarenVigil);

    Html htmlbulymegmailcom = new Html(
        "<a href=\"mailto:karen@nnvbr.org\" target=\"_blank\">karen@nnvbr.org</a>");
    TableData td_htmlbulymegmailcom = new TableData();
    td_htmlbulymegmailcom.setHorizontalAlign(HorizontalAlignment.CENTER);
    td_htmlbulymegmailcom.setWidth("");
    td_htmlbulymegmailcom.setPadding(5);
    cntntpnlKarenVigil.add(htmlbulymegmailcom, td_htmlbulymegmailcom);
    htmlbulymegmailcom.setWidth("");

    Label label = new Label("775-771-0293");
    TableData td_label = new TableData();
    td_label.setHorizontalAlign(HorizontalAlignment.CENTER);
    td_label.setPadding(5);
    cntntpnlKarenVigil.add(label, td_label);
    TableData td_cntntpnlKarenVigil = new TableData();
    td_cntntpnlKarenVigil.setPadding(10);
    td_cntntpnlKarenVigil.setWidth("150");
    lcContacts.add(cntntpnlKarenVigil, td_cntntpnlKarenVigil);

    ContentPanel cntntpnlPattiHawkinson = new ContentPanel();
    cntntpnlPattiHawkinson.setHeaderVisible(false);
    cntntpnlPattiHawkinson.setFrame(true);
    cntntpnlPattiHawkinson.setHeading("Patti Hawkinson");
    cntntpnlPattiHawkinson.setCollapsible(true);
    TableLayout tl_cntntpnlPattiHawkinson = new TableLayout(1);
    tl_cntntpnlPattiHawkinson.setWidth("100%");
    tl_cntntpnlPattiHawkinson
        .setCellHorizontalAlign(HorizontalAlignment.CENTER);
    cntntpnlPattiHawkinson.setLayout(tl_cntntpnlPattiHawkinson);

    Label lblPattiHawkinson = new Label("Patti Hawkinson");
    lblPattiHawkinson
        .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    TableData td_lblPattiHawkinson = new TableData();
    td_lblPattiHawkinson.setWidth("100%");
    td_lblPattiHawkinson.setHorizontalAlign(HorizontalAlignment.CENTER);
    td_lblPattiHawkinson.setPadding(5);
    cntntpnlPattiHawkinson.add(lblPattiHawkinson, td_lblPattiHawkinson);

    Html htmlSendMail = new Html(
        "<a href=\"mailto:patti@nnvbr.org\" target=\"_blank\">patti@nnvbr.org</a>");
    TableData td_htmlSendMail = new TableData();
    td_htmlSendMail.setPadding(5);
    cntntpnlPattiHawkinson.add(htmlSendMail, td_htmlSendMail);

    Label label_2 = new Label("775-972-6497");
    TableData td_label_2 = new TableData();
    td_label_2.setHorizontalAlign(HorizontalAlignment.CENTER);
    td_label_2.setPadding(5);
    cntntpnlPattiHawkinson.add(label_2, td_label_2);
    TableData td_cntntpnlPattiHawkinson = new TableData();
    td_cntntpnlPattiHawkinson.setWidth("150");
    td_cntntpnlPattiHawkinson.setPadding(10);
    lcContacts.add(cntntpnlPattiHawkinson, td_cntntpnlPattiHawkinson);
    lcContacts.setSize("100%", "100%");

    initComponent(lcContacts);
  }

}
