package com.max_pw_iw.person_subscriber.adapter.out.persistence;

import com.max_pw_iw.person_subscriber.application.domain.model.Person;
import com.max_pw_iw.person_subscriber.application.port.out.GetPersonPort;

public interface PersonService extends GetPersonPort {
    void AddPerson(Person person);
}
