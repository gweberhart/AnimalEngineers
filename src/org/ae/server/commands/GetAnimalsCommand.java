package org.ae.server.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.client.model.Animal;
import org.ae.client.rpc.GetAnimalsRequest;
import org.ae.client.rpc.GetAnimalsResponse;
import org.ae.server.domain.AnimalBO;
import org.ae.server.frame.AECommand;
import org.ae.server.frame.PMF;

public class GetAnimalsCommand extends AECommand {
  Cache cache;

  public GetAnimalsCommand() throws AEServicesException {
    try {
      CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
      cache = cacheFactory.createCache(Collections.emptyMap());
    } catch (CacheException e) {
      throw new AEServicesException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException {
    GetAnimalsRequest gar = (GetAnimalsRequest) action;
    PersistenceManager pm = PMF.get().getPersistenceManager();
    Query query = null;
    ArrayList<Animal> animals = new ArrayList<Animal>();
    try {
      query = pm.newQuery(AnimalBO.class);
      query.setFilter("deleted == false && rescueURL == '" + gar.getRescueURL()
          + "'");
      query.setOrdering("dateLastChanged desc");
      List<AnimalBO> results = (List<AnimalBO>) query.execute();
      if (results.iterator().hasNext()) {
        for (AnimalBO model : results) {
          Animal animal = new Animal();
          animal.setKey(model.getKey());
          animal.setDesc(model.getDesc().getValue());
          animal.setName(model.getName());
          animal.setDateLastChanged(model.getDateLastChanged());
          animal.setImageFileName(model.getImageFileName());
          animal.setDeleted(model.isDeleted());
          animal.setPath(model.getPath());
          animal.setRescueURL(model.getRescueURL());
          animals.add(animal);
        }
      }
    } catch (Exception e) {
      throw new AEServicesException(e);
    } finally {
      query.closeAll();
      pm.close();
    }
    return new GetAnimalsResponse(animals);
  }
}