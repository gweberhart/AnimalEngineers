package org.ae.client.view.signin;

import org.ae.client.ViewENUM;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.frame.AEDisplay;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.Presenter;

import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasWidgets;

public class SigninWindowPresenter implements Presenter {
  private final HandlerManager eventBus;
  public Display display;

  interface Display extends AEDisplay {
    Window asWindow();

    Frame getSigninFrame();
  }

  public SigninWindowPresenter(HandlerManager eventBus) {
    this.eventBus = eventBus;
    bind();
  }

  private void bind() {
  }

  private void createDisplay() {
    if (display == null) {
      this.display = new SigninWindow();
    }
  }

  @Override
  public void go(HasWidgets container) {
    createDisplay();
    display.getSigninFrame().setUrl(ClientUtils.loginInfo.getLoginUrl());
    display.asWindow().show();
    eventBus.fireEvent(new ViewChangeEvent(ViewENUM.HOME));
  }
}
