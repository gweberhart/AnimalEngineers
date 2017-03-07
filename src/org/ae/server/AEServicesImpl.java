/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.server;

import java.util.ArrayList;

import org.ae.client.frame.AEServices;
import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.server.frame.CommandProcessor;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AEServicesImpl extends RemoteServiceServlet implements AEServices {
  private static final long serialVersionUID = 1L;

  @Override
  public RPCResponse execute(RPC<RPCResponse> action)
      throws AEServicesException {
    return CommandProcessor.execute(action, getThreadLocalRequest());
  }

  @Override
  public ArrayList<?> getArrayList(RPC<ArrayList<?>> action)
      throws AEServicesException {
    return (ArrayList<?>) CommandProcessor.executeReturnList(action,
        getThreadLocalRequest());
  }
}
