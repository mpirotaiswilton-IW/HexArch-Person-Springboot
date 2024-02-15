package com.max_pw_iw.person_publisher.application.domain.service;

import com.max_pw_iw.person_publisher.application.domain.model.Person;
import com.max_pw_iw.person_publisher.application.port.in.SendPersonUseCase;
import com.max_pw_iw.person_publisher.application.port.out.GetPersonPort;
import com.max_pw_iw.person_publisher.infrastructure.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SendPersonService implements SendPersonUseCase {

    private final GetPersonPort getPersonPort;

    @Override
    public void SendPerson(Person person) {
        
        getPersonPort.getPerson(person);

    }
    
}
