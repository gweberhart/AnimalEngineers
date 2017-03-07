package org.ae.client.view.adoptables;

import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.frame.AEDisplay;
import org.ae.client.frame.AEListView;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.Presenter;
import org.ae.client.model.ModelFactory;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AdoptablesPagePresenter implements Presenter {
  private final SimpleEventBus eventBus;
  private Display display;
  @SuppressWarnings("unused")
  private AEServicesAsync rpcService;
  private ModelFactory modelFactory;

  public interface Display extends AEDisplay {
    Button getBtnAdd();

    Button getBtnEdit();

    Button getBtnDelete();

    AEListView<BeanModel> getListView();

    void toggleAdiminFunctions(boolean on);

    Widget asWidget();
  }

  public AdoptablesPagePresenter(AEServicesAsync rpcService,
      SimpleEventBus eventBus, ModelFactory modelFactory) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    this.modelFactory = modelFactory;
  }

  public void bind() {
    display.getListView().addListener(Events.DoubleClick,
        new Listener<ListViewEvent<ModelData>>() {
          public void handleEvent(ListViewEvent<ModelData> e) {
            eventBus.fireEvent(new NavigateEvent(ViewENUM.EDIT_ANIMAL_DIALOG
                .getHistoryToken()));
          }
        });
    display
        .getListView()
        .getSelectionModel()
        .addListener(Events.SelectionChange,
            new Listener<SelectionChangedEvent<BeanModel>>() {
              public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                modelFactory.setSelectedAnimalBeanModel(be.getSelectedItem());
              }
            });
    display.getBtnAdd().addListener(Events.Select, new Listener<ButtonEvent>() {
      public void handleEvent(ButtonEvent e) {
        eventBus.fireEvent(new NavigateEvent(ViewENUM.ADD_ANIMAL_DIALOG
            .getHistoryToken()));
      }
    });
    display.getBtnEdit().addListener(Events.Select,
        new Listener<ButtonEvent>() {
          public void handleEvent(ButtonEvent e) {
            eventBus.fireEvent(new NavigateEvent(ViewENUM.EDIT_ANIMAL_DIALOG
                .getHistoryToken()));
          }
        });
    display.getBtnDelete().addListener(Events.Select,
        new Listener<ButtonEvent>() {
          public void handleEvent(ButtonEvent e) {
            eventBus.fireEvent(new NavigateEvent(ViewENUM.DELETE_ANIMAL_POPUP
                .getHistoryToken()));
          }
        });
  }

  private void createDisplay() {
    if (display == null) {
      display = new AdoptablesPageView(eventBus);
      bind();
      ClientUtils.messageBox = MessageBox.wait("Progress",
          "Loading Animals, please wait...", "Loading...");
      Scheduler.get().scheduleDeferred(new Command() {
        @Override
        public void execute() {
          display.getListView().setStore(modelFactory.getStore());
          modelFactory.getStore().getLoader().load();
        }
      });
    }
  }

  @Override
  public void go(HasWidgets container) {
    createDisplay();
    container.clear();
    display.toggleAdiminFunctions(ClientUtils.isLoggedIn());
    container.add(display.asWidget());
    eventBus.fireEvent(new ViewChangeEvent(ViewENUM.ADOPTABLES));
  }
}
