/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface AnimalEventHandler extends EventHandler {
  void onListEvent(AnimalEvent event);
}
