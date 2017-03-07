/**
 * Copyright (c) 2011 Animal Engineers, All Rights Reserved
 */
package org.ae.client;

import org.ae.client.PresenterFactory.Presenters;
import org.ae.client.events.LoginEvent;
import org.ae.client.frame.AEServices;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.RPCCallback;
import org.ae.client.frame.RPCResponse;
import org.ae.client.model.ModelFactory;
import org.ae.client.rpc.GetLoginInfoRequest;
import org.ae.client.rpc.GetLoginInfoResponse;
import org.ae.shared.AERequestFactory;

import com.extjs.gxt.themes.client.Access;
import com.extjs.gxt.themes.client.Slate;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

public class AEHOME implements EntryPoint {
  public static AERequestFactory requestFactory;
  public static SimpleEventBus eventBus;

  @Override
  public void onModuleLoad() {
    final AEServicesAsync rpcService = GWT.create(AEServices.class);
    if (switchTheme()) {
      return;
    }
    eventBus = new SimpleEventBus();
    final ModelFactory modelFactory = ModelFactory.get(rpcService, eventBus);
    final PresenterFactory presenterFactory = new PresenterFactory(rpcService,
        eventBus, modelFactory);
    requestFactory = GWT.create(AERequestFactory.class);
    requestFactory.initialize(eventBus);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        presenterFactory.getPresenter(Presenters.MAIN_TOOLBAR).go(
            RootPanel.get("aeToolBar"));
        new AppController(rpcService, eventBus, presenterFactory, modelFactory)
            .go(RootPanel.get("aeMainContent"));
        RootPanel.get("loading").getElement().removeFromParent();
        RootPanel.get("static").getElement().removeFromParent();
      }
    });
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        rpcService.execute(
            new GetLoginInfoRequest(ClientUtils.createDestinationURL(null)),
            new RPCCallback() {
              @Override
              public void onSuccess(final RPCResponse logonInfo) {
                ClientUtils.loginInfo = ((GetLoginInfoResponse) logonInfo)
                    .getDetails();
                eventBus.fireEvent(new LoginEvent());
              }
            });
      }
    });
  }

  private boolean switchTheme() {
    ThemeManager.register(Slate.SLATE);
    ThemeManager.register(Access.ACCESS);
    Theme.BLUE.set("file", "ExtGWT/css/gxt-all.css");
    Theme.GRAY.set("file", "ExtGWT/css/gxt-gray.css");
    Slate.SLATE.set("file", "ExtGWT/themes/slate/css/xtheme-slate.css");
    Access.ACCESS.set("file", "ExtGWT/themes/access/css/xtheme-access.css");
    GXT.init();
    if (ClientUtils.isAnimalEngineers()) {
      if (GXT.getThemeId() == null || !GXT.getThemeId().equals("gray")) {
        GXT.setDefaultTheme(Theme.GRAY, true);
        GXT.switchTheme(Theme.GRAY);
        return true;
      }
    } else if (ClientUtils.isGreyhounds()) {
      if (GXT.getThemeId() == null || !GXT.getThemeId().equals("gray")) {
        GXT.setDefaultTheme(Theme.GRAY, true);
        GXT.switchTheme(Theme.GRAY);
        return true;
      }
    } else {
      if (GXT.getThemeId() == null || !GXT.getThemeId().equals("blue")) {
        GXT.setDefaultTheme(Theme.BLUE, true);
        GXT.switchTheme(Theme.BLUE);
        return true;
      }
    }
    return false;
  }
}
