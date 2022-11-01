package org.hemit.utils;

import io.restassured.response.ValidatableResponse;
import org.hemit.StatusAndContent;
import org.hemit.model.*;

import java.lang.reflect.Type;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ParticipantUtils {
  public static StatusAndContent<CreateResponse> createParticipant(String tournamentId, String participantName, Integer participantElo) {

    ValidatableResponse response = given()
            .contentType("application/json")
            .body(new ParticipantToCreate(participantName,participantElo))
            .when()
            .post("/tournaments/" + tournamentId + "/participants")
            .then();

    int statusCode = response.extract().statusCode();
    CreateResponse content = null;
    if (statusCode == 201) {
      content = response.extract().as(CreateResponse.class);
    }
    return new StatusAndContent<>(statusCode, content);
  }

  public static StatusAndContent<Participant> getParticipantById(String tournamentId, String participantId) {
    ValidatableResponse response = when().get("/tournaments/" + tournamentId + "/participants/"+ participantId).then();

    int statusCode = response.extract().statusCode();
    Participant content = null;
    if (statusCode == 200) {
      content = response.extract().as(Participant.class);
    }

    return new StatusAndContent<>(statusCode, content);
  }

  public static StatusAndContent<Participant> getAllParticipant(String tournamentId) {
    ValidatableResponse response = when().get("/tournaments/" + tournamentId + "/participants/").then();

    int statusCode = response.extract().statusCode();
    Participant content = null;
    if (statusCode == 200) {
      content = response.extract().as((Type) Collection.class);
    }

    return new StatusAndContent<>(statusCode, content);
  }
}
