package org.ae.client;

import org.ae.client.events.NavigateEvent;
import org.ae.client.events.NavigateEventHandler;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.IUIAction;
import org.ae.client.model.ModelFactory;
import org.ae.client.model.Rescue;
import org.ae.client.view.Banner;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

public class AppController implements ValueChangeHandler<String> {
  private final SimpleEventBus eventBus;
  private HasWidgets container;
  private ActionFactory actionFactory;
  private Banner banner = new Banner();
  private boolean pageDisplayed = false;

  public AppController(AEServicesAsync rpcService, SimpleEventBus eventBus,
      PresenterFactory presenterFactory, ModelFactory modelFactory) {
    this.eventBus = eventBus;
    this.actionFactory = new ActionFactory(rpcService, eventBus, modelFactory,
        presenterFactory);
    bind();
  }

  public void go(final HasWidgets container) {
    this.container = container;
    if ("".equals(History.getToken())) {
      History.newItem("home");
    } else {
      History.fireCurrentHistoryState();
    }
  }

  private void bind() {
    History.addValueChangeHandler(this);
    eventBus.addHandler(NavigateEvent.TYPE, new NavigateEventHandler() {
      @Override
      public void onNavigationEvent(NavigateEvent event) {
        go(event, container);
      }
    });
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    go(new NavigateEvent(event.getValue()), container);
  }

  public void go(NavigateEvent event, HasWidgets container) {
    String token = event.getHistoryToken();
    int i = token.lastIndexOf(":");
    if (i > 0) {
      token = token.subSequence(0, i).toString();
    }
    IUIAction action = actionFactory.getAction(token);
    if (action == null && token.equals("EditCampaign")) {
      if (pageDisplayed) {
        History.back();
        return;
      } else {
        action = actionFactory.getAction(ViewENUM.CONFIGURATION
            .getHistoryToken());
      }
    }
    action.trigger(container, event);
    maybeShowBanner(event);
    pageDisplayed = true;
  }

  private void maybeShowBanner(NavigateEvent event) {
    ComplexPanel p = RootPanel.get("aeBanner");
    if (event.getHistoryToken().equals(ViewENUM.HOME.getHistoryToken())) {
      p.remove(banner);
      setScrollBarOffsetVisibility(false);
    } else {
      p.add(banner);
      setScrollBarOffsetVisibility(true);
    }
  }

  private void setScrollBarOffsetVisibility(boolean on) {
    switch (Rescue.getCurrentRescue().getHomePageVersion()) {
    case 4:
      break;
    default:
      RootPanel.get("scollBarOffset").setVisible(on);
    }
  }
}
