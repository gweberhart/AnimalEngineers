/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.server.frame;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
  private static final PersistenceManagerFactory pmfInstance = JDOHelper
      .getPersistenceManagerFactory("ae-transactions-optional");

  private PMF() {
  }

  public static PersistenceManagerFactory get() {
    return pmfInstance;
  }
}