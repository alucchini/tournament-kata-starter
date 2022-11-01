package org.hemit.services;

import org.hemit.model.Participant;
import org.hemit.model.ParticipantToCreate;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@ApplicationScoped
public class ParticipantRepository {

  private static final HashMap<String, Participant> Participants = new HashMap<>();

  public String create(ParticipantToCreate participant) {
    if(Participants.containsKey(participant.name)){
      return "already in the tournament";
    }
    String id = UUID.randomUUID().toString();
    Participants.put(id, new Participant(participant.name, participant.elo));
    return id;
  }

  public Participant get(String id) {
    return Participants.get(id);
  }

  public Collection<Participant> getAll() {
    return Participants.values();
  }
}
