package org.ae.server.frame;

import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;

public interface AECommandInterface {
  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException;
}
