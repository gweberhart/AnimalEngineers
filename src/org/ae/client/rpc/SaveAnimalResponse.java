package org.ae.client.rpc;

import org.ae.client.frame.RPCResponse;
import org.ae.client.model.Animal;

public class SaveAnimalResponse extends RPCResponse {
  private static final long serialVersionUID = 1L;
  private Animal animal;

  public SaveAnimalResponse() {
  }

  public SaveAnimalResponse(Animal animal) {
    this.setAnimal(animal);
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public Animal getAnimal() {
    return animal;
  }

}