package br.com.juliomoura.api;

import java.net.URI;
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

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.juliomoura.dtos.PersonDTO;
import br.com.juliomoura.services.person.IPersonService;
import io.quarkus.logging.Log;

@Path("/v1/people")
@Tag(name = "/v1/people", description = "People API")
public class PersonController {

    @Inject
    IPersonService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponses(
        value = {@APIResponse(description = "Returns created when creates a person", responseCode = "201")
        }
    )
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
    @APIResponses(
        value = {@APIResponse(description = "Returns OK when finds a person", responseCode = "200"),
                @APIResponse(description = "Returns Not found when doesn't find a person", responseCode = "404")
        }
    )
    public PersonDTO listPerson(@PathParam(value = "id") UUID uuid) {
        Log.info("PersonController.listPerson - Input: " + uuid);
        return service.findById(uuid).orElseThrow(() -> {
            Log.info("PersonController.listPerson - Not Found for UUID: " + uuid);
            throw new NotFoundException();
        });
    }
}
