package org.ae.client.view.signin;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

public class SigninView extends Dialog {

  public SigninView() {
    setModal(true);
    setHeading("Animal Engineers");
    setLayout(new RowLayout(Orientation.VERTICAL));
  }

}
