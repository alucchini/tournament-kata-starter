package org.hemit.api;

import io.quarkus.logging.Log;
import org.hemit.model.CreateResponse;
import org.hemit.model.Tournament;
import org.hemit.model.TournamentToCreate;
import org.hemit.services.TournamentRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("tournaments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TournamentController {

    @Inject
    TournamentRepository tournamentRepository;

    @POST
    public Response createTournament(TournamentToCreate tournament) {
        String id = tournamentRepository.create(tournament);
        if(id.equals("already Exist")){
            return Response.status(Response.Status.TEMPORARY_REDIRECT).entity(new CreateResponse(id)).build();
        }
        return Response.status(Response.Status.CREATED).entity(new CreateResponse(id)).build();
    }

    @GET
    @Path("{id}")
    public Tournament getTournamentById(@PathParam("id") String id) {
        return tournamentRepository.get(id);
    }

    @GET
    public Collection<Tournament> getTournaments() {
        return tournamentRepository.getAll();
    }

}
