package org.ae.server.domain;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Factory for creating EntityManager.
 */
public final class EMF {
  private static final EntityManagerFactory emfInstance = Persistence
      .createEntityManagerFactory("transactions-optional");

  public static EntityManagerFactory get() {
    return emfInstance;
  }

  private EMF() {
    // nothing
  }
}