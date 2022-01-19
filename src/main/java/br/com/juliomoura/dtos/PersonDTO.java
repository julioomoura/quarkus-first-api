package br.com.juliomoura.dtos;

public class PersonDTO {
    private String name;
    private String lastName;
    
    
    public PersonDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getFullName() {
        return name + " " + lastName;
    }
    
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



}
