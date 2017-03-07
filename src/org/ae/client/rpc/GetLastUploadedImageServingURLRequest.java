package org.ae.client.rpc;

import java.io.Serializable;

import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;

public class GetLastUploadedImageServingURLRequest implements RPC<RPCResponse>,
    Serializable {

  private static final long serialVersionUID = 1L;

  public GetLastUploadedImageServingURLRequest() {

  }

}
