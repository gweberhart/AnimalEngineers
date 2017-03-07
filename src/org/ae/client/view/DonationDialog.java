package org.ae.client.view;

import org.ae.client.frame.ClientUtils;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.ui.Image;

public class DonationDialog extends Dialog {

  public static final String URL = ClientUtils.getImagePath() + "donateLogo.png";

  public DonationDialog() {
    setSize("600", "400");
    setHideOnButtonClick(true);
    setModal(true);
    setHeading("Donate");
    setShadowOffset(10);
    TableLayout tableLayout = new TableLayout(2);
    tableLayout.setWidth("100%");
    tableLayout.setHeight("100%");
    setLayout(tableLayout);

    Image image = new Image(URL);
    add(image);
    Html htmldonations = new Html();
    if (ClientUtils.isBulldog()) {
      htmldonations = new Html(
          "<b>Thank you for deciding to donate. With your help we can save many animals. "
              + "\r\n<br>\r\n<br>We use PayPal to accept donations. "
              + "Please click the Donate button to get started.<br>\r\n<br>\r\n<br>\r\n"
              + "<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">\r\n<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\">\r\n<input type=\"hidden\" name=\"hosted_button_id\" value=\"4EWT8U2L3JE8A\">\r\n<input type=\"image\" src=\"https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online!\">\r\n<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\">\r\n</form>"
              + "\r\n<br>\r\n<br>Northern Nevada Bulldog Rescue \r\n<br>A 501(c)(3) Non-Profit Organization \r\n<br>Tax ID: 27-3485645\r\n<br>\r\n<br><center><h1><b>Thank You!</b>");
    } else if (ClientUtils.isGreyhounds()) {
      htmldonations = new Html(
          "<b>Thank you for deciding to donate. With your help we can find homes for many Greyhounds. "
              + "\r\n<br>\r\n<br>We use PayPal to accept donations. "
              + "Please click the Donate button to get started.<br>\r\n<br>\r\n<br>\r\n"
              + "<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">\r\n<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\">\r\n<input type=\"hidden\" name=\"hosted_button_id\" value=\"NEVDSULVEL444\">\r\n<input type=\"image\" src=\"https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online!\">\r\n<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\">\r\n</form>"
              + "\r\n<br>\r\n<br>Nevada Greyhounds Unlimited  \r\n<br>A 501(c)(3) Non-Profit Organization \r\n<br>Tax ID: 88-0423255\r\n<br>\r\n<br><center><h1><b>Thank You!</b>");
    }
    htmldonations.setAutoWidth(true);
    TableData td_htmldonations = new TableData();
    td_htmldonations.setVerticalAlign(VerticalAlignment.MIDDLE);
    td_htmldonations.setWidth("100%");
    td_htmldonations.setHeight("100%");
    td_htmldonations.setPadding(15);
    add(htmldonations, td_htmldonations);
    htmldonations.setWidth("362");
  }
}
