package com.max_pw_iw.person_subscriber.adapter.out.persistence;

import org.springframework.stereotype.Service;

import com.max_pw_iw.person_subscriber.adapter.out.persistence.repositories.people.PersonRepository;
import com.max_pw_iw.person_subscriber.application.domain.model.Person;

import lombok.AllArgsConstructor;

@Service(value = "personService")
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    
    private PersonRepository personRepository;

    @Override
    public Person getPerson(Person person) {
        AddPerson(person);
        return person;
    }

    @Override
    public void AddPerson(Person person) {
        personRepository.save(person);
    }
}

