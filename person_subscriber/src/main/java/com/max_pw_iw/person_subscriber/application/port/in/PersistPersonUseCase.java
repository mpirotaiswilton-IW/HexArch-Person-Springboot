package com.max_pw_iw.person_subscriber.application.port.in;

import com.max_pw_iw.person_subscriber.application.domain.model.Person;

public interface PersistPersonUseCase {
    
    void SendPerson(Person person);

}
