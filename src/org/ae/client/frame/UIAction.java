package org.ae.client.frame;

import org.ae.client.PresenterFactory;
import org.ae.client.events.NavigateEvent;
import org.ae.client.model.ModelFactory;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class UIAction implements IUIAction {

  protected AEServicesAsync rpcService;
  protected SimpleEventBus eventBus;
  protected ModelFactory modelFactory;
  protected PresenterFactory presenterFactory;

  public UIAction() {
  }

  public UIAction(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory, PresenterFactory presenterFactory) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.modelFactory = modelFactory;
    this.presenterFactory = presenterFactory;
  }

  @Override
  public abstract void go(HasWidgets container, NavigateEvent event);

  @Override
  public void trigger(HasWidgets container, NavigateEvent event) {
    go(container, event);
  }
}
