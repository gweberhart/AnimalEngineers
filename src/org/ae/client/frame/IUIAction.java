package org.ae.client.frame;

import org.ae.client.events.NavigateEvent;

import com.google.gwt.user.client.ui.HasWidgets;

public interface IUIAction {
  public void go(HasWidgets container, NavigateEvent event);

  public void trigger(HasWidgets container, NavigateEvent event);

}
