package org.ae.client;

import java.util.HashMap;

import org.ae.client.action.AddAnimalAction;
import org.ae.client.action.AdotablesPageAction;
import org.ae.client.action.CalendarPageAction;
import org.ae.client.action.ConfigurationPageAction;
import org.ae.client.action.DeleteAnimalAction;
import org.ae.client.action.EditAnimalAction;
import org.ae.client.action.FAQPageAction;
import org.ae.client.action.HomePageAction;
import org.ae.client.action.LoginPageAction;
import org.ae.client.action.MissionPageAction;
import org.ae.client.action.PrepareForTestingAction;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.IUIAction;
import org.ae.client.model.ModelFactory;

import com.google.gwt.event.shared.SimpleEventBus;

public class ActionFactory {
  HashMap<String, IUIAction> actions = new HashMap<String, IUIAction>();

  ActionFactory(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory, PresenterFactory presenterFactory) {
    actions.put(ViewENUM.HOME.getHistoryToken(), new HomePageAction(rpcService,
        eventBus, modelFactory, presenterFactory));
    actions.put(ViewENUM.ADOPTABLES.getHistoryToken(), new AdotablesPageAction(
        rpcService, eventBus, modelFactory, presenterFactory));
    actions.put(ViewENUM.CALENDAR.getHistoryToken(), new CalendarPageAction(
        rpcService, eventBus, modelFactory, presenterFactory));
    actions.put(ViewENUM.CONFIGURATION.getHistoryToken(),
        new ConfigurationPageAction(rpcService, eventBus, modelFactory,
            presenterFactory));
    actions.put(ViewENUM.FAQ.getHistoryToken(), new FAQPageAction(rpcService,
        eventBus, modelFactory, presenterFactory));
    actions.put(ViewENUM.MISSION.getHistoryToken(), new MissionPageAction(
        rpcService, eventBus, modelFactory, presenterFactory));
    actions.put(ViewENUM.LOGIN.getHistoryToken(), new LoginPageAction(
        rpcService, eventBus, modelFactory, presenterFactory));
    actions.put(ViewENUM.EDIT_ANIMAL_DIALOG.getHistoryToken(),
        new EditAnimalAction(rpcService, eventBus, modelFactory,
            presenterFactory));
    actions.put(ViewENUM.ADD_ANIMAL_DIALOG.getHistoryToken(),
        new AddAnimalAction(rpcService, eventBus, modelFactory,
            presenterFactory));
    actions.put(ViewENUM.DELETE_ANIMAL_POPUP.getHistoryToken(),
        new DeleteAnimalAction(rpcService, eventBus, modelFactory,
            presenterFactory));
    actions.put(ViewENUM.REFRESHDB.getHistoryToken(),
        new PrepareForTestingAction(rpcService, eventBus, modelFactory,
            presenterFactory));
  }

  public IUIAction getAction(String historyToken) {
    return actions.get(historyToken);
  }

}
