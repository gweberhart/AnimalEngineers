package org.ae.client.view.adoptables;

import org.ae.client.events.AnimalEvent;
import org.ae.client.events.AnimalEventHandler;
import org.ae.client.frame.AEDisplay;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.Presenter;
import org.ae.client.frame.RPCCallback;
import org.ae.client.frame.RPCResponse;
import org.ae.client.model.Animal;
import org.ae.client.model.ModelFactory;
import org.ae.client.rpc.GetLastUploadedImageServingURLRequest;
import org.ae.client.rpc.GetUploadFormActionRequest;

import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;

public class AEDPresenter implements Presenter {
  private final SimpleEventBus eventBus;
  private Display display;
  private AEServicesAsync rpcService;
  private ModelFactory modelFactory;
  private FormBinding binding;

  public interface Display extends AEDisplay {
    Button getBtnOK();

    TextField<String> getTxtAnimalName();

    TextArea getTextArea();

    FileUploadField getFileUploadField();

    Image getImage();

    FormPanel getFormPanel();

    Dialog asDialog();

  }

  public AEDPresenter(AEServicesAsync rpcService, SimpleEventBus eventBus,
      ModelFactory modelFactory) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    this.modelFactory = modelFactory;
  }

  public void bind() {
    initDataBindings();
    display.getBtnOK().addListener(Events.Select, new Listener<ButtonEvent>() {
      public void handleEvent(ButtonEvent e) {
        display.asDialog().mask("Saving Animal");
        Scheduler.get().scheduleDeferred(new Command() {
          @Override
          public void execute() {
            Animal animal = modelFactory.getSelectedAnimal();
            animal.setDesc(display.getTextArea().getValue());
            animal.setName(display.getTxtAnimalName().getValue());
            animal.setImageFileName(display.getFileUploadField().getValue());
            animal.setPath(display.getImage().getUrl());
            animal.setRescueURL(ClientUtils.getInstitutionID());
            modelFactory.saveSelectedAnimal();
          }
        });
      }
    });

    display.getFileUploadField().addListener(Events.OnChange,
        new Listener<BaseEvent>() {
          @Override
          public void handleEvent(BaseEvent be) {
            display.asDialog().mask("Loading Image...");
            rpcService.execute(new GetUploadFormActionRequest(),
                new RPCCallback() {
                  @Override
                  public void onSuccess(RPCResponse response) {
                    display.getFormPanel().setAction(response.getData());
                    display.getFormPanel().submit();
                  }
                });
          }
        });

    display.getFormPanel().addListener(Events.Submit,
        new Listener<FormEvent>() {
          public void handleEvent(FormEvent e) {
            rpcService.execute(new GetLastUploadedImageServingURLRequest(),
                new RPCCallback() {
                  @Override
                  public void onSuccess(RPCResponse response) {
                    if (response.getData() == null) {
                      display.getImage().setUrl(ClientUtils.DEFAULT_IMAGE);
                    } else {
                      display.getImage().setUrl(response.getData());
                    }
                    display.asDialog().unmask();
                  }

                  @Override
                  public void onFailure(Throwable caught) {
                    ClientUtils.handleError(caught);
                    display.asDialog().unmask();
                  }
                });
          }
        });

    eventBus.addHandler(AnimalEvent.TYPE, new AnimalEventHandler() {
      @Override
      public void onListEvent(AnimalEvent event) {
        display.asDialog().hide();
        display.asDialog().unmask();

      }
    });
  }

  private void createDisplay() {
    if (display == null) {
      display = new AED2();
      bind();
    }
  }

  protected void initDataBindings() {
    binding = new FormBinding(display.getFormPanel());
    binding.addFieldBinding(new FieldBinding(display.getTextArea(), "desc"));
    binding
        .addFieldBinding(new FieldBinding(display.getTxtAnimalName(), "name"));
    binding.addFieldBinding(new FieldBinding(display.getFileUploadField(),
        "imageFileName"));
  }

  @Override
  public void go(HasWidgets container) {
    Animal animal = modelFactory.getSelectedAnimal();
    if (animal != null) {
      createDisplay();
      binding.bind(modelFactory.getSelectedAnimalBeanModel());
      display.asDialog().show();
      display.getImage().setUrl(animal.getPath());
      display.asDialog().center();
    }
  }
}
