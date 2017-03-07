package org.ae.shared;

import java.util.List;

import org.ae.server.domain.Campaign;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Campaign.class)
public interface CampaignRequest extends RequestContext {

  Request<CampaignProxy> findHomePageCampaign(String rescueID);

  Request<CampaignProxy> copyCampaign(String id);

  Request<CampaignProxy> findCampaign(String id);

  Request<List<CampaignProxy>> findAllCampaigns();

  Request<List<CampaignProxy>> findCampaigns(String s);

  InstanceRequest<CampaignProxy, Void> persist();

  InstanceRequest<CampaignProxy, Void> remove();
}
