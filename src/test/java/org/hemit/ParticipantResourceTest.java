package org.hemit;

import io.quarkus.test.junit.QuarkusTest;
import org.hemit.model.CreateResponse;
import org.hemit.model.Participant;
import org.hemit.utils.ParticipantUtils;
import org.hemit.utils.TournamentUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class ParticipantResourceTest {

  @Test
  public void create_should_returnAnId() {

    StatusAndContent<CreateResponse> tournoi = TournamentUtils.createTournament("GroTournoi");

    StatusAndContent<CreateResponse> response = ParticipantUtils.createParticipant(tournoi.content.id, "Roger", 2);

    assertThat(response.statusCode, is(201));
    assertThat(response.content.id, is(notNullValue()));
  }

  @Test
  public void get_should_return_created_participant() {

    StatusAndContent<CreateResponse> tournoi = TournamentUtils.createTournament("GroTournoi");

    String participantName = "Alain";

    StatusAndContent<CreateResponse> createResponse = ParticipantUtils.createParticipant(tournoi.content.id, participantName, 2500);
    StatusAndContent<Participant> getResponse = ParticipantUtils.getParticipantById(tournoi.content.id, createResponse.content.id);

    assertThat(getResponse.statusCode, is(200));
    assertThat(getResponse.content.name, is(participantName));
  }

  @Test
  public void get_all_participants(){

    StatusAndContent<CreateResponse> tournoi = TournamentUtils.createTournament("Tournoi");

    StatusAndContent<CreateResponse> createResponse1 = ParticipantUtils.createParticipant(tournoi.content.id, "Alain", 2500);
    StatusAndContent<CreateResponse> createResponse2 = ParticipantUtils.createParticipant(tournoi.content.id, "Toto", 2501);
    StatusAndContent<CreateResponse> createResponse3 = ParticipantUtils.createParticipant(tournoi.content.id, "Tata", 2050);

    assertThat(ParticipantUtils.getAllParticipant(tournoi.content.id).statusCode, is(200));
  }

}
