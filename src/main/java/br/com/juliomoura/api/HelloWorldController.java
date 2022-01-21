package br.com.juliomoura.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;

@Path("/v1/hello")
public class HelloWorldController {

    @ConfigProperty(name = "greeting.message") 
    String message;

    @GET
    public String hello(@QueryParam(value = "name") String name) {
        Log.info("Saying hello to: " + name);
        return message + " " + name;
    }
}
