package org.ae.client.rpc;

import java.io.Serializable;

import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.client.model.Animal;

public class DeleteAnimalRequest implements RPC<RPCResponse>, Serializable {

  private static final long serialVersionUID = 1L;
  private Animal animal;

  public DeleteAnimalRequest() {

  }

  public DeleteAnimalRequest(Animal animal) {
    this.setAnimal(animal);
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public Animal getAnimal() {
    return animal;
  }

}
