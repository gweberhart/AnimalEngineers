package org.ae.client.action;

import org.ae.client.PresenterFactory;
import org.ae.client.ViewENUM;
import org.ae.client.PresenterFactory.Presenters;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.UIAction;
import org.ae.client.model.ModelFactory;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class CalendarPageAction extends UIAction {
  
  public CalendarPageAction(AEServicesAsync rpcService,
      SimpleEventBus eventBus, ModelFactory modelFactory,
      PresenterFactory presenterFactory) {
    super(rpcService, eventBus, modelFactory, presenterFactory);
  }
  @Override
  public void go(HasWidgets container, NavigateEvent event) {
    History.newItem(ViewENUM.CALENDAR.getHistoryToken(), false);
    presenterFactory.getPresenter(Presenters.CALENDAR_PAGE).go(container);
  }

}
