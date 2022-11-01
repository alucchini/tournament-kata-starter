package org.hemit.api;

import org.hemit.model.*;
import org.hemit.services.ParticipantRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("tournaments/{tounamentId}/participants")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParticipantController {
  @Inject
  ParticipantRepository participantRepository;

  @POST
  public Response createParticipant(ParticipantToCreate participant) {
    String id = participantRepository.create(participant);
    return Response.status(Response.Status.CREATED).entity(new CreateResponse(id)).build();
  }

  @GET
  @Path("{id}")
  public Participant getParticipantById(@PathParam("id") String id) {
    return participantRepository.get(id);
  }

  @GET
  public Collection<Participant> getParticipants() {
    return participantRepository.getAll();
  }
}
