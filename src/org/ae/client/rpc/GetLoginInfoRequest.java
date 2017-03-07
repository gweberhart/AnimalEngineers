package org.ae.client.rpc;

import java.io.Serializable;

import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;

public class GetLoginInfoRequest implements RPC<RPCResponse>, Serializable {
  private static final long serialVersionUID = 1L;
  private String destinationURL;

  public GetLoginInfoRequest() {

  }

  public GetLoginInfoRequest(String destinationURL) {
    this.destinationURL = destinationURL;
  }

  public void setDestinationURL(String destinationURL) {
    this.destinationURL = destinationURL;
  }

  public String getDestinationURL() {
    return destinationURL;
  }

}