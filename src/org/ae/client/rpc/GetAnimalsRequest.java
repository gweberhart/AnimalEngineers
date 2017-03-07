package org.ae.client.rpc;

import java.io.Serializable;
import java.util.ArrayList;

import org.ae.client.frame.RPC;

public class GetAnimalsRequest implements RPC<ArrayList<?>>, Serializable {
  private static final long serialVersionUID = 1L;
  private String rescueURL;

  public GetAnimalsRequest() {
  }

  public GetAnimalsRequest(String rescueURL) {
    this.rescueURL = rescueURL;
  }

  public void setRescueURL(String rescueURL) {
    this.rescueURL = rescueURL;
  }

  public String getRescueURL() {
    return rescueURL;
  }

}