package com.rodrigomoreira.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rodrigomoreira.app.domain.Person;
import com.rodrigomoreira.app.dtos.PersonDTO;
import com.rodrigomoreira.app.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<?> getAllPersons(){
        List<PersonDTO> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/registration") // ex: localhost:8080/persons/registration?registration=12345678910
    public ResponseEntity<?> getPersonByRegistration(@RequestParam String registration) throws Exception {
        Person person = personService.findPersonByRegistration(registration);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<?> cretePerson(@RequestBody Person person) {
        Person newPerson = personService.createPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{registration}")
    public ResponseEntity<Person> updatePerson(@PathVariable String registration, @RequestBody Person updatedPerson) throws  Exception {
        Person person = personService.updatePerson(registration, updatedPerson);
        return ResponseEntity.ok(personService.createPerson(person));
    }


    @DeleteMapping("/{registration}")
    public ResponseEntity<Void> deletePersonByRegistration(@PathVariable String registration) throws  Exception{
        personService.deletePersonByUUID(personService.findPersonByRegistration(registration).getId());
        return ResponseEntity.noContent().build();
    }

}
