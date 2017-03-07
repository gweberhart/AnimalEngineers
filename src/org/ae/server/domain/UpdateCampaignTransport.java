package org.ae.server.domain;


public class UpdateCampaignTransport {
  public static final int UPDATE_LAST_POST_TIME = 0;
  public static final int UPDATE_PUBLISHER_AD_NUMBER = 1;
  private Integer operation;
  private Campaign campaign;

  public void setOperation(Integer operation) {
    this.operation = operation;
  }

  public Integer getOperation() {
    return operation;
  }

  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  public Campaign getCampaign() {
    return campaign;
  }

}
