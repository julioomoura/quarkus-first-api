package br.com.juliomoura.services.person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.juliomoura.dtos.PersonDTO;

public interface IPersonService {
    List<PersonDTO> listAll();
    PersonDTO create(PersonDTO personDTO);
    Optional<PersonDTO> findById(UUID uuid);
}
