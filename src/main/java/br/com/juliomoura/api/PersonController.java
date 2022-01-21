package br.com.juliomoura.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
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

import org.jboss.logging.Logger;

import br.com.juliomoura.dtos.PersonDTO;
import br.com.juliomoura.services.person.PersonService;

@Path("/v1/people")
public class PersonController {

    private static final Logger LOG = Logger.getLogger(PersonController.class);

    @Inject
    PersonService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final PersonDTO personDTO, @Context UriInfo uriInfo) {

        LOG.info("Creating person: " + personDTO);

        PersonDTO created = service.create(personDTO);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(PersonController.class, "listPerson")
                .build(created);

        return Response.created(location).build();
    }

    @GET
    public Response listPeople() {
        LOG.info("Listing all people");

        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response listPerson(@PathParam(value = "id") UUID uuid) {
        Optional<PersonDTO> personFound = service.findById(uuid);
        if(personFound.isPresent()) {
            return Response.ok(personFound.get()).build();
        };
        throw new NotFoundException();
    }
}
