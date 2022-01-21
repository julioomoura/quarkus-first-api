package br.com.juliomoura.api;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.juliomoura.dtos.PersonDTO;
import br.com.juliomoura.services.person.IPersonService;
import io.quarkus.logging.Log;

@Path("/v1/people")
public class PersonController {

    @Inject
    IPersonService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final PersonDTO personDTO, @Context UriInfo uriInfo) {

        Log.info("PersonController.create - Input: " + personDTO);

        PersonDTO created = service.create(personDTO);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(PersonController.class, "listPerson")
                .build(created);

        return Response.created(location).build();
    }

    @GET
    public Response listPeople() {
        Log.info("PersonController.listPeople");

        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response listPerson(@PathParam(value = "id") UUID uuid) {
        Log.info("PersonController.listPerson - Input: " + uuid);
        Optional<PersonDTO> personFound = service.findById(uuid);
        if(personFound.isPresent()) {
            return Response.ok(personFound.get()).build();
        };
        Log.info("PersonController.listPerson - Not Found for UUID: " + uuid);
        throw new NotFoundException();
    }
}
