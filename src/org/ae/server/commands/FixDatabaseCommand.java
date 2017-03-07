package org.ae.server.commands;

import java.util.Collection;
import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.ae.client.frame.AEServicesException;
import org.ae.server.domain.AnimalBO;
import org.ae.server.frame.AECommand;
import org.ae.server.frame.PMF;

public class FixDatabaseCommand {

  @SuppressWarnings("unchecked")
  public void fixDatabase() throws AEServicesException {
    AECommand.checkLoggedIn();
    System.out.println("fixDatabase() starting... ");
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      Query q = pm.newQuery(AnimalBO.class);
      Collection<AnimalBO> c = (Collection) q.execute();
      Iterator<AnimalBO> iter = c.iterator();
      while (iter.hasNext()) {
        AnimalBO animalModel = (AnimalBO) iter.next();
        animalModel.setDeleted(false);
        pm.makePersistent(animalModel);
        System.out.println(">  " + animalModel);
      }
    } finally {
      pm.close();
    }
    System.out.println("fixDatabase() finished... ");
  }

}