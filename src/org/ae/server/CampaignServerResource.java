package org.ae.server;

import java.util.Date;
import java.util.List;

import org.ae.server.domain.Campaign;
import org.ae.server.domain.CampaignTransport;
import org.ae.server.domain.UpdateCampaignTransport;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class CampaignServerResource extends ServerResource implements
    CampaignResource {

  @Get
  public CampaignTransport retrieve() {
    List<Campaign> campaigns = Campaign.findAllCampaigns();
    CampaignTransport transport = new CampaignTransport();
    for (Campaign campaign : campaigns) {
      transport.addCampaign(campaign);
    }
    return transport;
  }

  @Put
  public boolean updateCampaign(UpdateCampaignTransport transport) {
    Campaign campaignFromClient = transport.getCampaign();
    Campaign campaignFromDatabase = Campaign.findCampaign(campaignFromClient
        .getId());
    switch (transport.getOperation()) {
    case UpdateCampaignTransport.UPDATE_LAST_POST_TIME:
      campaignFromDatabase.setLastPostTime(new Date());
      break;
    case UpdateCampaignTransport.UPDATE_PUBLISHER_AD_NUMBER:
      campaignFromDatabase.setPubliserAdNumber(campaignFromClient
          .getPubliserAdNumber());
      break;
    }
    campaignFromDatabase.persist();
    return true;
  }

}