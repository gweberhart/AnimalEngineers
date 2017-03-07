package org.ae.client.frame;

import org.ae.client.PresenterFactory;
import org.ae.client.PresenterFactory.Presenters;
import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.model.Animal;
import org.ae.client.model.ModelFactory;
import org.ae.client.rpc.GetLoginInfoRequest;
import org.ae.client.rpc.GetLoginInfoResponse;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class SecureUIAction implements IUIAction {
  protected AEServicesAsync rpcService;
  protected SimpleEventBus eventBus;
  protected ModelFactory modelFactory;
  protected PresenterFactory presenterFactory;

  public SecureUIAction() {
  }

  public SecureUIAction(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory, PresenterFactory presenterFactory) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.modelFactory = modelFactory;
    this.presenterFactory = presenterFactory;
  }

  @Override
  public abstract void go(HasWidgets container, NavigateEvent event);

  @Override
  public void trigger(final HasWidgets container, final NavigateEvent event) {
    rpcService.execute(
        new GetLoginInfoRequest(ClientUtils.createDestinationURL(null)),
        new RPCCallback() {
          @Override
          public void onSuccess(final RPCResponse logonInfo) {
            ClientUtils.loginInfo = ((GetLoginInfoResponse) logonInfo)
                .getDetails();
            doAction(container, event);
          }
        });
  }

  private void doAction(HasWidgets container, NavigateEvent event) {
    String historyToken = event.getHistoryToken();
    Animal animal = modelFactory.getSelectedAnimal();
    Long key = null;
    if (animal != null) {
      key = animal.getKey();
    }
    if (!ClientUtils.isLoggedIn()) {
      eventBus.fireEvent(new NavigateEvent(ViewENUM.LOGIN.getHistoryToken(),
          historyToken, key));
      return;
    }
    if (container.iterator().next() == null) {
      if (historyToken.contains("configuration")) {
        presenterFactory.getPresenter(Presenters.CONFIGURATION).go(container);
      } else {
        presenterFactory.getPresenter(Presenters.ADOPTABLES_PAGE).go(container);
      }
    }
    go(container, event);
    if (historyToken.contains("configuration")) {
      History.newItem(ViewENUM.CONFIGURATION.getHistoryToken(), false);
    } else {
      History.newItem(ViewENUM.ADOPTABLES.getHistoryToken(), false);
    }
  }
}
