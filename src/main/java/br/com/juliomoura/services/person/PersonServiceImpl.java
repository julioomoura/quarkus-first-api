package br.com.juliomoura.services.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Singleton;

import br.com.juliomoura.dtos.PersonDTO;

@Singleton
public class PersonServiceImpl implements PersonService {

    @Override
    public List<PersonDTO> listAll() {
        List<PersonDTO> list = new ArrayList<>();

        return list;
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) {
        PersonDTO savedDto = new PersonDTO();
        return savedDto;
    }

    @Override
    public Optional<PersonDTO> findById(UUID uuid) {
        return Optional.ofNullable(null);
    }
}
