package org.ae.client.model;

import java.util.ArrayList;

import org.ae.client.events.AnimalEvent;
import org.ae.client.frame.AEServicesAsync;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.RPCCallback;
import org.ae.client.frame.RPCResponse;
import org.ae.client.rpc.DeleteAnimalRequest;
import org.ae.client.rpc.GetAnimalsRequest;
import org.ae.client.rpc.SaveAnimalRequest;
import org.ae.client.rpc.SaveAnimalResponse;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ModelFactory {
  private static ModelFactory factory;
  private ListLoader<ListLoadResult<Animal>> loader;
  private AEServicesAsync rpcService;
  private SimpleEventBus eventBus;
  private ListStore<BeanModel> store;
  private BeanModel selectedAnimalBeanModel;

  private ModelFactory(final AEServicesAsync aRPCService,
      final SimpleEventBus eventBus) {
    this.rpcService = aRPCService;
    this.eventBus = eventBus;
    RpcProxy<ArrayList<?>> proxy = new RpcProxy<ArrayList<?>>() {

      @Override
      protected void load(Object loadConfig,
          AsyncCallback<ArrayList<?>> callback) {
        rpcService.getArrayList(
            new GetAnimalsRequest(ClientUtils.getInstitutionID()), callback);
      }
    };
    loader = new BaseListLoader<ListLoadResult<Animal>>(proxy,
        new BeanModelReader());
    loader.addListener(Loader.Load, new Listener<BaseEvent>() {

      @Override
      public void handleEvent(BaseEvent be) {
        ClientUtils.messageBox.close();
      }
    });

    store = new ListStore<BeanModel>(loader);
  }

  public static ModelFactory get(AEServicesAsync rpcService,
      SimpleEventBus eventBus) {
    if (factory == null) {
      factory = new ModelFactory(rpcService, eventBus);
    }
    return factory;
  }

  public void setSelectedAnimalBeanModelByKey(String animalKey) {
    setSelectedAnimalBeanModel(store.findModel("key", new Long(animalKey)));
  }

  public ListStore<BeanModel> getStore() {
    return store;
  }

  private void saveAnimal(BeanModel model) {
    rpcService.execute(new SaveAnimalRequest(getSelectedAnimal()),
        new RPCCallback() {

          @Override
          public void onSuccess(RPCResponse response) {
            SaveAnimalResponse sar = (SaveAnimalResponse) response;
            Animal selectedAnimal = getSelectedAnimal();
            Animal animal = (Animal) sar.getAnimal();
            if (selectedAnimal.getKey() == null) {
              selectedAnimal.setKey(animal.getKey());
            }
            store.remove(selectedAnimalBeanModel);
            BeanModelFactory factory = BeanModelLookup.get().getFactory(
                Animal.class);
            final BeanModel model = factory.createModel(animal);
            store.insert(model, 0);
            eventBus.fireEvent(new AnimalEvent(AnimalEvent.ANIMAL_SAVED_EVENT));
          }
        });
  }

  public void saveSelectedAnimal() {
    saveAnimal(selectedAnimalBeanModel);
  }

  public void setSelectedAnimalBeanModel(BeanModel selectedAnimalBeanModel) {
    this.selectedAnimalBeanModel = selectedAnimalBeanModel;
  }

  public BeanModel getSelectedAnimalBeanModel() {
    return selectedAnimalBeanModel;
  }

  public Animal getSelectedAnimal() {
    if (selectedAnimalBeanModel == null) {
      return null;
    } else {
      return selectedAnimalBeanModel.getBean();
    }
  }

  public BeanModel createAnimal() {
    Animal animal = new Animal();
    animal.setName("New Animal");
    animal.setPath(ClientUtils.DEFAULT_IMAGE);
    animal.setImageFileName("");
    BeanModelFactory factory = BeanModelLookup.get().getFactory(Animal.class);
    selectedAnimalBeanModel = factory.createModel(animal);
    return selectedAnimalBeanModel;
  }

  public void deleteSelectedAnimal() {
    rpcService.execute(new DeleteAnimalRequest(getSelectedAnimal()),
        new RPCCallback() {
          @Override
          public void onSuccess(RPCResponse result) {
            store.remove(selectedAnimalBeanModel);
          }
        });
  }

  public ListLoader<ListLoadResult<Animal>> getLoader() {
    return loader;
  }
}
