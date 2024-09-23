package com.rodrigomoreira.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigomoreira.app.domain.Person;
import com.rodrigomoreira.app.dtos.PersonDTO;
import com.rodrigomoreira.app.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> cretePerson(@RequestBody Person person) {
        Person newPerson = personService.createPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<?> getAllPersons(){
        List<PersonDTO> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

}
