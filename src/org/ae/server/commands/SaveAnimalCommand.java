package org.ae.server.commands;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.client.model.Animal;
import org.ae.client.rpc.SaveAnimalRequest;
import org.ae.client.rpc.SaveAnimalResponse;
import org.ae.server.domain.AnimalBO;
import org.ae.server.frame.AECommand;
import org.ae.server.frame.PMF;
import org.ae.server.frame.ServerUtils;

public class SaveAnimalCommand extends AECommand {

  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException {
    checkLoggedIn();
    SaveAnimalRequest saa = (SaveAnimalRequest) action;
    Animal animal = saa.getAnimal();
    PersistenceManager pm = PMF.get().getPersistenceManager();
    AnimalBO model = null;
    try {
      model = AnimalBO.createModelFromAnimal(animal);
      model.prePersistEvent(animal, request);
      pm.makePersistent(model);
      animal.setKey(model.getKey());
    } finally {
      pm.close();
    }
    ServerUtils.sendNotifications("Changed", model);
    return new SaveAnimalResponse(animal);
  }
}