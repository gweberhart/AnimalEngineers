package org.ae.client.action;

import org.ae.client.PresenterFactory;
import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.SecureUIAction;
import org.ae.client.model.Animal;
import org.ae.client.model.ModelFactory;

import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class DeleteAnimalAction extends SecureUIAction {
  private Listener<BaseEvent> listener;

  public DeleteAnimalAction(AEServicesAsync rpcService,
      SimpleEventBus eventBus, ModelFactory modelFactory,
      PresenterFactory presenterFactory) {
    super(rpcService, eventBus, modelFactory, presenterFactory);
  }

  @Override
  public void go(HasWidgets container, NavigateEvent event) {
    deleteAnimal(History.getToken());
  }

  private void deleteAnimal(final String historyToken) {
    DeferredCommand.addCommand(new Command() {
      @Override
      public void execute() {
        if (historyToken != null
            && historyToken.contains(ViewENUM.DELETE_ANIMAL_POPUP
                .getHistoryToken())) {
          modelFactory.getLoader().removeListener(Loader.Load,
              getListener(historyToken));
          modelFactory.getLoader().addListener(Loader.Load,
              getListener(historyToken));
        } else {
          promptToDelete();
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
          promptToDelete();
        }
      };
    }
    return listener;
  }

  private void promptToDelete() {
    final Animal animal = modelFactory.getSelectedAnimal();
    if (animal == null) {
      return;
    }
    MessageBox box = new MessageBox();
    box.setButtons(MessageBox.YESNO);
    box.getDialog().getButtonBar().getItem(0).setId("BTN_YES");
    box.getDialog().getButtonBar().getItem(1).setId("BTN_NO");
    box.setIcon(MessageBox.QUESTION);
    box.setTitle("Delete Animal?");
    box.setMessage("Delete the selected animal?<br><br>" + animal.getName());
    box.addCallback(new Listener<MessageBoxEvent>() {
      @Override
      public void handleEvent(MessageBoxEvent be) {
        if ("yes".equals(be.getButtonClicked().getItemId())) {
          modelFactory.deleteSelectedAnimal();
        }
      }
    });
    box.show();
  }

}
