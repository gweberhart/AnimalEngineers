package org.ae.client.action;

import org.ae.client.PresenterFactory;
import org.ae.client.PresenterFactory.Presenters;
import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.SecureUIAction;
import org.ae.client.model.ModelFactory;

import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class EditAnimalAction extends SecureUIAction {

  private Listener<BaseEvent> listener;

  public EditAnimalAction(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory, PresenterFactory presenterFactory) {
    super(rpcService, eventBus, modelFactory, presenterFactory);
  }

  @Override
  public void go(HasWidgets container, NavigateEvent event) {
    editAnimal(History.getToken());
  }

  private void editAnimal(final String historyToken) {
    DeferredCommand.addCommand(new Command() {
      @Override
      public void execute() {
        if (historyToken != null
            && historyToken.contains(ViewENUM.EDIT_ANIMAL_DIALOG
                .getHistoryToken())) {
          modelFactory.getLoader().removeListener(Loader.Load,
              getListener(historyToken));
          modelFactory.getLoader().addListener(Loader.Load,
              getListener(historyToken));
        } else {
          presenterFactory.getPresenter(Presenters.AED).go(null);
        }
      }
    });
  }

  private Listener<BaseEvent> getListener(final String historyToken) {
    if (listener == null) {
      listener = new Listener<BaseEvent>() {
        @Override
        public void handleEvent(BaseEvent be) {
          String animalKey = historyToken.split(":")[1];
          modelFactory.setSelectedAnimalBeanModelByKey(animalKey);
          presenterFactory.getPresenter(Presenters.AED).go(null);
        }
      };
    }
    return listener;
  }
}
