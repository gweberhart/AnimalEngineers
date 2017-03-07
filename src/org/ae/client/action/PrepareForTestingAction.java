package org.ae.client.action;

import org.ae.client.PresenterFactory;
import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.RPCCallback;
import org.ae.client.frame.RPCResponse;
import org.ae.client.frame.UIAction;
import org.ae.client.model.ModelFactory;
import org.ae.client.rpc.PrepareForTestingRequest;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;

public class PrepareForTestingAction extends UIAction {
  public PrepareForTestingAction(AEServicesAsync rpcService,
      SimpleEventBus eventBus, ModelFactory modelFactory,
      PresenterFactory presenterFactory) {
    super(rpcService, eventBus, modelFactory, presenterFactory);
  }

  @Override
  public void go(HasWidgets container, NavigateEvent event) {
    rpcService.execute(new PrepareForTestingRequest(), new RPCCallback() {
      @Override
      public void onSuccess(RPCResponse response) {
        eventBus.fireEvent(new NavigateEvent(ViewENUM.HOME.getHistoryToken()));
      }
    });
  }
}