/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.frame;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface AEServices extends RemoteService {

  public RPCResponse execute(RPC<RPCResponse> action)
      throws AEServicesException;

  public ArrayList<?> getArrayList(RPC<ArrayList<?>> action)
      throws AEServicesException;

}
