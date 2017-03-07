/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.frame;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AEServicesAsync {

  public void execute(RPC<RPCResponse> action, AsyncCallback<RPCResponse> callback);

  public void getArrayList(RPC<ArrayList<?>> action, AsyncCallback<ArrayList<?>> callback);

}
