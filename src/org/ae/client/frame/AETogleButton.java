package org.ae.client.frame;

import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

public class AETogleButton extends ToggleButton implements HasClickHandlers {

  public AETogleButton(String string) {
    super(string);
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler handler) {
    return addDomHandler(handler, ClickEvent.getType());
  }

}
