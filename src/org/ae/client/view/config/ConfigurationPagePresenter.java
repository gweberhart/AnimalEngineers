package org.ae.client.view.config;

import org.ae.client.ViewENUM;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.frame.AEDisplay;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.Presenter;
import org.ae.client.model.ModelFactory;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ConfigurationPagePresenter implements Presenter {
  private final SimpleEventBus eventBus;
  private Display display;
  @SuppressWarnings("unused")
  private AEServicesAsync rpcService;
  @SuppressWarnings("unused")
  private ModelFactory modelFactory;

  public interface Display extends AEDisplay {
    Widget asWidget();
  }

  public ConfigurationPagePresenter(AEServicesAsync rpcService,
      SimpleEventBus eventBus, ModelFactory modelFactory) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    this.modelFactory = modelFactory;
  }

  public void bind() {
  }

  private void createDisplay() {
    if (display == null) {
      display = new ConfigurationWorkflow(eventBus);
      bind();
    }
  }

  @Override
  public void go(HasWidgets container) {
    createDisplay();
    container.clear();
    container.add(display.asWidget());
    eventBus.fireEvent(new ViewChangeEvent(ViewENUM.CONFIGURATION));
  }
}
