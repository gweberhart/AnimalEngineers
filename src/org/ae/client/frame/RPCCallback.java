package org.ae.client.frame;


import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class RPCCallback implements AsyncCallback<RPCResponse> {

  @Override
  public void onFailure(Throwable caught) {
    ClientUtils.handleError(caught);

  }

  @Override
  public abstract void onSuccess(RPCResponse response);

}
