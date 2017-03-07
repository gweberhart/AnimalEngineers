package org.ae.client;

import java.util.HashMap;

import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.Presenter;
import org.ae.client.model.ModelFactory;
import org.ae.client.toolbar.MainToolBarPresenter;
import org.ae.client.view.adoptables.AEDPresenter;
import org.ae.client.view.adoptables.AdoptablesPagePresenter;
import org.ae.client.view.calendar.CalendarPagePresenter;
import org.ae.client.view.config.ConfigurationPagePresenter;
import org.ae.client.view.faq.FAQPagePresenter;
import org.ae.client.view.home.HomePagePresenter;
import org.ae.client.view.mission.MissionPagePresenter;

import com.google.gwt.event.shared.SimpleEventBus;

public class PresenterFactory {
  private HashMap<Presenters, Presenter> presenters = new HashMap<Presenters, Presenter>();

  public enum Presenters {
    MAIN_TOOLBAR,
    ADOPTABLES_PAGE,
    CALENDAR_PAGE,
    HOME_PAGE,
    MISSION_PAGE,
    AED,
    CONFIGURATION,
    FAQ
  }

  public PresenterFactory(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory) {
    presenters.put(Presenters.MAIN_TOOLBAR, new MainToolBarPresenter(eventBus));
    presenters.put(Presenters.ADOPTABLES_PAGE, new AdoptablesPagePresenter(
        rpcService, eventBus, modelFactory));
    presenters.put(Presenters.CALENDAR_PAGE, new CalendarPagePresenter(
        rpcService, eventBus, modelFactory));
    presenters.put(Presenters.CONFIGURATION, new ConfigurationPagePresenter(
        rpcService, eventBus, modelFactory));
    presenters.put(Presenters.FAQ, new FAQPagePresenter(rpcService, eventBus,
        modelFactory));
    presenters.put(Presenters.AED, new AEDPresenter(rpcService, eventBus,
        modelFactory));
    presenters.put(Presenters.HOME_PAGE, new HomePagePresenter(rpcService,
        eventBus, modelFactory));
    presenters.put(Presenters.MISSION_PAGE, new MissionPagePresenter(
        rpcService, eventBus, modelFactory));
  }

  public Presenter getPresenter(Presenters presenter) {
    return presenters.get(presenter);
  }
}
