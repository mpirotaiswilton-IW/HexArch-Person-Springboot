package com.max_pw_iw.person_subscriber.adapter.out.persistence.repositories.people;

import org.springframework.data.repository.CrudRepository;

import com.max_pw_iw.person_subscriber.application.domain.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
