package com.max_pw_iw.person_subscriber.application.port.out;

import com.max_pw_iw.person_subscriber.application.domain.model.Person;

public interface GetPersonPort {

    Person getPerson(Person person);

}
