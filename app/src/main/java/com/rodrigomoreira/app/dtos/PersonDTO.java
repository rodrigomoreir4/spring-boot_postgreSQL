package com.rodrigomoreira.app.dtos;

import com.rodrigomoreira.app.domain.Person;

public record PersonDTO(String name, String registration) {
    
    public PersonDTO(Person person) {
        this(person.getName(), person.getRegistration());
    }
}
