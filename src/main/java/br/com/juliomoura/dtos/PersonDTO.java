package br.com.juliomoura.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {
    private String name;
    private String lastName;

    @JsonIgnore
    public String getFullName() {
        return name + " " + lastName;
    }
}
