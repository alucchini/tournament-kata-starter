package org.hemit.model;

public class ParticipantToCreate {
  public String name;
  public Integer elo;

  public ParticipantToCreate() {
  }

  public ParticipantToCreate(String name, Integer elo) {
    this.name = name;
    this.elo = elo;
  }
}
