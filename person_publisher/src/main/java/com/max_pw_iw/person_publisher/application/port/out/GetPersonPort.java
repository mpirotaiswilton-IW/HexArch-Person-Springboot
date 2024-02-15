package com.max_pw_iw.person_publisher.application.port.out;

import com.max_pw_iw.person_publisher.application.domain.model.Person;

public interface GetPersonPort {
    
    Person getPerson(Person person);

}
