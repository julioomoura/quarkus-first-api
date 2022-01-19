package br.com.juliomoura.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
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

@Path("/v1/people")
public class PersonController {

    private static final Logger LOG = Logger.getLogger(PersonController.class);

    private static PersonDTO personOne = new PersonDTO("Júlio", "Moura");
    private static PersonDTO personTwo = new PersonDTO("Gaby", "Cardoso");
    private static List<PersonDTO> people = Arrays.asList(personOne, personTwo);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final PersonDTO personDTO, @Context UriInfo uriInfo) {

        LOG.info("Creating person: " + personDTO);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(PersonController.class, "listPerson")
                .build(personDTO);
        return Response.created(location).build();
    }

    @GET
    public Response listPeople() {
        LOG.info("Listing all people");

        return Response.ok(people).build();
    }

    @GET
    @Path("/{id}")
    public Response listPerson(@PathParam(value = "id") Integer id) {
        try {
            PersonDTO personDTO = people.get(id);
            return Response.ok(personDTO).build();
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new NotFoundException();
        }
    }
}
