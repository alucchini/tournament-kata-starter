package org.hemit.model;

public class Participant {
  public String name;
  public Integer elo;

  public Participant(String name, Integer elo) {
    this.name = name;
    this.elo = elo;
  }

  public Participant() {
  }
}
