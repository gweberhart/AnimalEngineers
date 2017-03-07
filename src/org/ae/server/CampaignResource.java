package org.ae.server;

import org.ae.server.domain.CampaignTransport;
import org.ae.server.domain.UpdateCampaignTransport;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface CampaignResource {
  @Get
  public CampaignTransport retrieve();

  @Put
  public boolean updateCampaign(UpdateCampaignTransport transport);

}