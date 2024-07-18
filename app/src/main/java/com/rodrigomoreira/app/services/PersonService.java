package com.rodrigomoreira.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomoreira.app.domain.Person;
import com.rodrigomoreira.app.domain.PersonDTO;
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
}
