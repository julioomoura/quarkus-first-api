package br.com.juliomoura;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import br.com.juliomoura.dtos.PersonDTO;

@Path("/v1/hello")
public class HelloWorldController {

    private static final Logger LOG = Logger.getLogger(HelloWorldController.class);

    @GET
    @Path("/{lastName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(
        @QueryParam(value = "name") String name, 
        @PathParam(value = "lastName") String lastName) 
    {
        LOG.info("First name=" + name + " Last name=" + lastName);

        PersonDTO person =new PersonDTO(name, lastName);

        return Response.ok(person).build();
    }
}


