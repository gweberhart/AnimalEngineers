package org.ae.client.action;

import org.ae.client.PresenterFactory;
import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.RPCCallback;
import org.ae.client.frame.RPCResponse;
import org.ae.client.frame.UIAction;
import org.ae.client.model.ModelFactory;
import org.ae.client.rpc.GetLoginInfoRequest;
import org.ae.client.rpc.GetLoginInfoResponse;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class LoginPageAction extends UIAction {
  public LoginPageAction(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory, PresenterFactory presenterFactory) {
    super(rpcService, eventBus, modelFactory, presenterFactory);
  }

  @Override
  public void go(HasWidgets container, NavigateEvent event) {
    rpcService.execute(
        new GetLoginInfoRequest(ClientUtils.createDestinationURL(event)),
        new RPCCallback() {
          @Override
          public void onSuccess(RPCResponse result) {
            ClientUtils.loginInfo = ((GetLoginInfoResponse) result)
                .getDetails();
            Window.Location.replace(ClientUtils.loginInfo.getLoginUrl());
            eventBus.fireEvent(new ViewChangeEvent(ViewENUM.LOGIN));
          }
        });
  }
}