/**
 * Copyright (c) 2011 Animal Engineers, All Rights Reserved
 */
package org.ae.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LoginEventHandler extends EventHandler {
  void onEvent(LoginEvent event);
}