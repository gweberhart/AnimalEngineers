package org.ae.client.view.config;

import com.google.gwt.event.shared.EventHandler;

public interface CampaignEventHandler extends EventHandler {
  void onEvent(CampaignEvent event);
}