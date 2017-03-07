/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.toolbar;

import org.ae.client.ViewENUM;
import org.ae.client.events.LoginEvent;
import org.ae.client.events.LoginEventHandler;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.events.ViewChangeEventHandler;
import org.ae.client.frame.AEButton;
import org.ae.client.frame.AETogleButton;
import org.ae.client.frame.ClientUtils;

import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainToolbarView extends Composite implements
    MainToolBarPresenter.Display {
  private AETogleButton btnHome;
  private AETogleButton btnAdoptables;
  private AETogleButton btnCalendar;
  private AETogleButton btnMissionStatement;
  private AEButton btnDonate;
  private AETogleButton btnConfiguration;
  private AETogleButton btnLoginAndLogout;
  private AETogleButton btnFAQ;
  private FillToolItem fillToolItem;
  private SimpleEventBus eventBus;

  public AETogleButton getBtnMissionStatement() {
    return btnMissionStatement;
  }

  public MainToolbarView(SimpleEventBus eventBus) {
    this.eventBus = eventBus;
    ToolBar toolBar = new ToolBar();
    toolBar.setSpacing(5);
    btnHome = new AETogleButton("Home");
    btnHome.setId("btnHome");
    btnHome.setToggleGroup("mainToolbar");
    btnHome.toggle(true);
    toolBar.add(btnHome);
    if (!ClientUtils.isGreyhounds() && !ClientUtils.isCaws()) {
      btnAdoptables = new AETogleButton("Adoptables");
      btnAdoptables.setId("btnAdoptables");
      btnAdoptables.setToggleGroup("mainToolbar");
      toolBar.add(btnAdoptables);
    }
    btnCalendar = new AETogleButton("Calendar");
    btnCalendar.setId("btnCalendar");
    btnCalendar.setToggleGroup("mainToolbar");
    toolBar.add(btnCalendar);
    if (ClientUtils.isGreyhounds()) {
      btnFAQ = new AETogleButton("FAQ");
      btnFAQ.setId("btnFAQ");
      btnFAQ.setToggleGroup("mainToolbar");
      toolBar.add(btnFAQ);
    }
    if (!ClientUtils.isAnimalEngineers() && !ClientUtils.isCaws()) {
      btnMissionStatement = new AETogleButton("Mission Statement");
      btnMissionStatement.setId("btnMissionStatement");
      btnMissionStatement.setToggleGroup("mainToolbar");
      toolBar.add(btnMissionStatement);
      btnDonate = new AEButton("Donate");
      btnDonate.setId("btnDonate");
      toolBar.add(btnDonate);
    }
    addConfigurationButton(toolBar);
    btnConfiguration.setVisible(ClientUtils.isLoggedIn());
    btnLoginAndLogout = new AETogleButton("Sign In");
    btnLoginAndLogout.setId("btnLoginAndLogout");
    btnLoginAndLogout.setToggleGroup("mainToolbar");
    toolBar.add(btnLoginAndLogout);

    fillToolItem = new FillToolItem();
    toolBar.add(fillToolItem);
    initWidget(toolBar);
    bind();
  }

  private void bind() {
    addEventBusHandlers();
  }

  private void addEventBusHandlers() {
    eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
      @Override
      public void onEvent(LoginEvent event) {
        setLogonStatus();
        btnConfiguration.setVisible(ClientUtils.isLoggedIn());
      }
    });
    eventBus.addHandler(ViewChangeEvent.TYPE, new ViewChangeEventHandler() {
      @Override
      public void onEvent(ViewChangeEvent event) {
        processNavEvent(event.getRequestedPage());
      }
    });
  }

  private void addConfigurationButton(ToolBar toolBar) {
    btnConfiguration = new AETogleButton("Configuration");
    btnConfiguration.setId("btnCampaigns");
    btnConfiguration.setToggleGroup("mainToolbar");
    toolBar.add(btnConfiguration);
  }

  public void processNavEvent(ViewENUM page) {
    clearButtons();
    if (page.equals(ViewENUM.HOME)) {
      toogleButton(btnHome);
    } else if (page.equals(ViewENUM.MISSION)) {
      toogleButton(btnMissionStatement);
    } else if (page.equals(ViewENUM.FAQ)) {
      toogleButton(btnFAQ);
    } else if (page.equals(ViewENUM.ADOPTABLES)) {
      toogleButton(btnAdoptables);
    } else if (page.equals(ViewENUM.CALENDAR)) {
      toogleButton(btnCalendar);
    } else if (page.equals(ViewENUM.CONFIGURATION)) {
      toogleButton(btnConfiguration);
    } else if (page.equals(ViewENUM.LOGIN)) {
      toogleButton(btnLoginAndLogout);
    }
    setLogonStatus();
  }

  private void toogleButton(AETogleButton button) {
    button.toggle(true);
    button.focus();
  }

  private void setLogonStatus() {
    if (ClientUtils.isLoggedIn()) {
      btnLoginAndLogout.setText("Sign Out");
    } else {
      btnLoginAndLogout.setText("Sign In");
    }
  }

  public void clearButtons() {
    toggleOff(btnHome);
    toggleOff(btnAdoptables);
    toggleOff(btnCalendar);
    toggleOff(btnLoginAndLogout);
    toggleOff(btnMissionStatement);
    toggleOff(btnConfiguration);
    toggleOff(btnFAQ);
  }

  private void toggleOff(AETogleButton button) {
    if (button != null) {
      button.toggle(false);
    }
  }

  public HasClickHandlers getBtnAdoptables() {
    return (HasClickHandlers) btnAdoptables;
  }

  public HasClickHandlers getBtnCalendar() {
    return (HasClickHandlers) btnCalendar;
  }

  public HasClickHandlers getBtnHome() {
    return (HasClickHandlers) btnHome;
  }

  public HasClickHandlers getBtnLoginAndLogout() {
    return (HasClickHandlers) btnLoginAndLogout;
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  public AEButton getBtnDonate() {
    return btnDonate;
  }

  @Override
  public HasClickHandlers getBtnConfiguration() {
    return (HasClickHandlers) btnConfiguration;
  }

  @Override
  public HasClickHandlers getBtnFAQ() {
    return (HasClickHandlers) btnFAQ;
  }
}
