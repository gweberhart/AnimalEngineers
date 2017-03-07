package org.ae.client.action;

import org.ae.client.PresenterFactory;
import org.ae.client.PresenterFactory.Presenters;
import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.UIAction;
import org.ae.client.model.ModelFactory;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class FAQPageAction extends UIAction {
  public FAQPageAction(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory, PresenterFactory presenterFactory) {
    super(rpcService, eventBus, modelFactory, presenterFactory);
  }

  @Override
  public void go(HasWidgets container, NavigateEvent event) {
    History.newItem(ViewENUM.FAQ.getHistoryToken(), false);
    presenterFactory.getPresenter(Presenters.FAQ).go(container);
  }
}