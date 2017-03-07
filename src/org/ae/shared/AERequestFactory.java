package org.ae.shared;

import com.google.web.bindery.requestfactory.shared.RequestFactory;



public interface AERequestFactory extends RequestFactory {

  CampaignRequest createCampaignRequest();

  RescueRequest rescueRequest();
}