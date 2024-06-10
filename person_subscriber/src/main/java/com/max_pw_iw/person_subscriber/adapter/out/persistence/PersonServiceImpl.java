package com.max_pw_iw.person_subscriber.adapter.out.persistence;


import org.springframework.stereotype.Service;

import com.max_pw_iw.person_subscriber.application.domain.model.Person;

import lombok.AllArgsConstructor;

@Service(value = "personService")
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{
    
    private PersonRepository personRepository;

    @Override
    public void AddPerson(Person person) {
        personRepository.save(person);
    }

}

