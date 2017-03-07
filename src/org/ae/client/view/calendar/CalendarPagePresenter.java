package org.ae.client.view.calendar;

import org.ae.client.ViewENUM;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.frame.AEDisplay;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.Presenter;
import org.ae.client.model.ModelFactory;
import org.ae.client.model.Rescue;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CalendarPagePresenter implements Presenter {
  private final SimpleEventBus eventBus;
  private Display display;
  @SuppressWarnings("unused")
  private AEServicesAsync rpcService;
  @SuppressWarnings("unused")
  private ModelFactory modelFactory;

  public interface Display extends AEDisplay {
    Widget asWidget();

    void setCalendarUrl(String calendarUrl);
  }

  public CalendarPagePresenter(AEServicesAsync rpcService,
      SimpleEventBus eventBus, ModelFactory modelFactory) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    this.modelFactory = modelFactory;
  }

  private void createDisplay() {
    if (display == null) {
      display = new CalendarPageView();
    }
  }

  @Override
  public void go(final HasWidgets container) {
    doGo(container);
  }

  private void doGo(final HasWidgets container) {
    createDisplay();
    container.clear();
    container.add(display.asWidget());
    display.setCalendarUrl(Rescue.getCurrentRescue().getCalendarURL());
    eventBus.fireEvent(new ViewChangeEvent(ViewENUM.CALENDAR));
  }
}
