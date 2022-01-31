package br.com.juliomoura.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class PersonDTO {
    @Schema(description = "Person Name")
    private String name;

    @Schema(description = "Person last name")
    private String lastName;

    @JsonIgnore
    public String getFullName() {
        return name + " " + lastName;
    }
}
