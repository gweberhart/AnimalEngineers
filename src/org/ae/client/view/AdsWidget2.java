package org.ae.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AdsWidget2 extends Composite {

  private static AdsWidget2UiBinder uiBinder = GWT
      .create(AdsWidget2UiBinder.class);

  interface AdsWidget2UiBinder extends UiBinder<Widget, AdsWidget2> {
  }

  public AdsWidget2() {
    initWidget(uiBinder.createAndBindUi(this));
  }

}
