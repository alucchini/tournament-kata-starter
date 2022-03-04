package org.hemit.services;

import org.hemit.model.Tournament;
import org.hemit.model.TournamentToCreate;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.UUID;

@ApplicationScoped
public class TournamentRepository {

    private static final HashMap<String, Tournament> Tournaments = new HashMap<>();

    public String create(TournamentToCreate tournament) {
        if(Tournaments.containsKey(tournament.name)){
            return "already Exist";
        }
        String id = UUID.randomUUID().toString();
        Tournaments.put(id, new Tournament(tournament.name));
        return id;
    }

    public Tournament get(String id) {
        return Tournaments.get(id);
    }

    public static HashMap<String, Tournament> getAll() {
        return Tournaments.values();
    }
}
