package org.hemit;

import io.quarkus.test.junit.QuarkusTest;
import org.hemit.model.CreateResponse;
import org.hemit.model.Tournament;
import org.hemit.utils.TournamentUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class TournamentResourceTest {

    @Test
    public void create_should_returnAnId() {

        StatusAndContent<CreateResponse> response = TournamentUtils.createTournament("Unreal Tournament");

        assertThat(response.statusCode, is(201));
        assertThat(response.content.id, is(notNullValue()));
    }

    @Test
    public void get_should_return_created_tournament() {
        String tournamentName = "New tournament";

        StatusAndContent<CreateResponse> createResponse = TournamentUtils.createTournament(tournamentName);
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById(createResponse.content.id);

        assertThat(getResponse.statusCode, is(200));
        assertThat(getResponse.content.name, is(tournamentName));
    }
    
    @Test
    public void tournament_already_exist() {

        StatusAndContent<CreateResponse> createResponse1 = TournamentUtils.createTournament("New tournament2");
        StatusAndContent<CreateResponse> createResponse2 = TournamentUtils.createTournament("New tournament2");

        assertThat(createResponse1.statusCode, is(201));
        assertThat(createResponse2.statusCode, is(400));
    }
}
