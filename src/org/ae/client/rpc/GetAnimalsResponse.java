package org.ae.client.rpc;

import java.util.ArrayList;

import org.ae.client.frame.RPCResponse;
import org.ae.client.model.Animal;

public class GetAnimalsResponse extends RPCResponse {
  private static final long serialVersionUID = 1L;
  private ArrayList<Animal> details;

  public GetAnimalsResponse() {
    details = null;
  }

  public GetAnimalsResponse(ArrayList<Animal> details) {
    this.details = details;
  }

  public ArrayList<Animal> getDetails() {
    return new ArrayList<Animal>(details);
  }
}