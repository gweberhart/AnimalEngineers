package org.ae.server.domain;

import java.util.ArrayList;


public class CampaignTransport {

  private ArrayList<Campaign> campaigns = new ArrayList<Campaign>();

  public void addCampaign(Campaign aCampaign) {
    campaigns.add(aCampaign);
  }

  public ArrayList<Campaign> getCampaigns() {
    return campaigns;
  }
}
