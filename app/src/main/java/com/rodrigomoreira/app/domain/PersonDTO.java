package com.rodrigomoreira.app.domain;

public record PersonDTO(String name, String registration) {
    
    public PersonDTO(Person person){
        this(person.getName(), person.getRegistration());
    }
}
