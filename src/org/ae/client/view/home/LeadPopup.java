package org.ae.client.view.home;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.PopupPanel;

public class LeadPopup extends PopupPanel {
  private InlineHTML lead2Html;

  public LeadPopup() {
    super(true);
    setSize("120", "200");

    DecoratorPanel decoratorPanel = new DecoratorPanel();
    setWidget(decoratorPanel);
    decoratorPanel.setSize("100", "100");

    ContentPanel contentPanel = new ContentPanel();
    contentPanel.setHeaderVisible(false);
    contentPanel.setFrame(true);
    contentPanel.setHeading("New ContentPanel");
    contentPanel.setLayout(new TableLayout(1));

    lead2Html = new InlineHTML(
        "<div class=\"dialogFont\">\r\n<h2>Are you aware of a Bulldog in need?</h2>\r\n<br>\r\n<p>Do you have a Bulldog that needs a new home but you're not sure where to\r\ngo? We know that sometimes people have huge life changes and pets are\r\naffected. Please do not surrender your dog to Animal Services/HS, we are\r\nhere to help ensure each dog gets care and a wonderful new home.</p>\r\n<br>\r\n<p>Unfortunately at this time we cannot take <a href=\"http://bulldogtypes.webs.com/\" target=_blank\">American Bulldogs</a>. If you have an American Bulldog that needs placing we will take the dogs information and post on our site and Petfinder.com as a courtesty listing only, it will be up to the owner to actually place the dog.</p>\r\n<br>\r\n<p>\r\n</div>");
    lead2Html.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    lead2Html.setDirectionEstimator(true);
    TableData td_lead2Html = new TableData();
    td_lead2Html.setPadding(5);
    contentPanel.add(lead2Html, td_lead2Html);
    lead2Html.setWidth("");
    decoratorPanel.setWidget(contentPanel);
    contentPanel.setWidth("580");
  }

  public InlineHTML getLead2Html() {
    return lead2Html;
  }
}
