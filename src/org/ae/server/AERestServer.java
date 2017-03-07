package org.ae.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class AERestServer extends Application {

  @Override
  public Restlet createInboundRoot() {
    Router router = new Router(getContext());
    router.attachDefault(CampaignServerResource.class);
    return router;
  }
}