package com.max_pw_iw.person_publisher.application.port.in;

import com.max_pw_iw.person_publisher.application.domain.model.Person;

public interface SendPersonUseCase {
    
    void SendPerson(Person person);

}
