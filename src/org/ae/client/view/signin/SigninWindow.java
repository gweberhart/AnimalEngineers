package org.ae.client.view.signin;

import org.ae.client.view.signin.SigninWindowPresenter.Display;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Frame;

public class SigninWindow extends Window implements Display {

  private Frame signinFrame;

  public SigninWindow() {
    setSize("850", "650");
    setHeading("New Window");
    setLayout(new FitLayout());

    LayoutContainer layoutContainer = new LayoutContainer();
    layoutContainer.setLayout(new FitLayout());

    signinFrame = new Frame("http://www.google.com");
    layoutContainer.add(signinFrame);
    add(layoutContainer);
    layoutContainer.setBorders(true);
  }

  @Override
  public Window asWindow() {
    return this;
  }

  @Override
  public Frame getSigninFrame() {
    return signinFrame;
  }

}
