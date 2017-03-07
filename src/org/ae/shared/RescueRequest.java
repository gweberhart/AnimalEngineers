package org.ae.shared;

import java.util.List;

import org.ae.client.model.Rescue;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Rescue.class)
public interface RescueRequest extends RequestContext {

  Request<RescueProxy> findRescueByRescueId(String rescueId);

  Request<List<RescueProxy>> findAllRescues();

}
