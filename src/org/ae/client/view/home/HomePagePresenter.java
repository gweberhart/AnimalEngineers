package org.ae.client.view.home;

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

public class HomePagePresenter implements Presenter {
  private final SimpleEventBus eventBus;
  private Display display;

  public interface Display extends AEDisplay {
    Widget asWidget();
  }

  public HomePagePresenter(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory) {
    this.eventBus = eventBus;
  }

  private void createDisplay() {
    if (display == null) {
      switch (Rescue.getCurrentRescue().getHomePageVersion()) {
      case 3:
        display = new HomePage3(eventBus);
        break;
      case 4:
        display = new HomePage4(eventBus);
        break;
      default:
        display = new HomePage(eventBus);
        break;
      }
    }
  }

  @Override
  public void go(final HasWidgets container) {
    if (container != null) {
      createDisplay();
      container.clear();
      container.add(display.asWidget());
      eventBus.fireEvent(new ViewChangeEvent(ViewENUM.HOME));
    }
  }

}
