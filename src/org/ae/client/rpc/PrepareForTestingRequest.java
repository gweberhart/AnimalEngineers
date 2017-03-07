package org.ae.client.rpc;

import java.io.Serializable;

import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;

public class PrepareForTestingRequest implements RPC<RPCResponse>,
    Serializable {

  private static final long serialVersionUID = 1L;

  public PrepareForTestingRequest() {

  }

}
