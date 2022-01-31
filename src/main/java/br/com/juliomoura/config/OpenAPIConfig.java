package br.com.juliomoura.config;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    info = @Info(
        title = "People API", 
        version = "0.0.1", 
        contact = @Contact(name = "Júlio Moura", email = "juliomourajom@gmail.com")
    ), 
    tags = { @Tag(name = "/v1/people", description = "API for managing people") },
    servers = { @Server(url = "http://localhost:8080") }
)
public class OpenAPIConfig extends Application {
    
}
