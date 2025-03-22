package com.rodrigomoreira.app.services;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomoreira.app.domain.Person;
import com.rodrigomoreira.app.dtos.PersonDTO;
import com.rodrigomoreira.app.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> personList = personRepository.findAll().stream().map(PersonDTO::new).toList();
        return personList;
    }

    public Person updatePerson(String registration, Person updatedPerson) throws Exception{
        Person person = findPersonByRegistration(registration);
        person.setName(updatedPerson.getName());
        person.setRegistration(updatedPerson.getRegistration());
        return person;
    }

    public Person findPersonByRegistration(String registration) throws Exception {
        return personRepository.findPersonByRegistration(registration).orElseThrow(
                () -> new EntityNotFoundException("Person not found")
        );
    }

    public void deletePersonByUUID(UUID id) {
        personRepository.deleteById(id);
    }
}
