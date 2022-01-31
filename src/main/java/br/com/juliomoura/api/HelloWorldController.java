package br.com.juliomoura.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.logging.Log;

@Path("/v1/hello")
@Tag(name = "/v1/hello", description = "Hello World API")
public class HelloWorldController {

    @ConfigProperty(name = "greeting.message") 
    String message;

    @GET
    @APIResponses(value = {@APIResponse(description = "Returns OK with a message for $name", responseCode = "200")})
    public String hello(@QueryParam(value = "name") String name) {
        Log.info("Saying hello to: " + name);
        return message + " " + name;
    }
}
