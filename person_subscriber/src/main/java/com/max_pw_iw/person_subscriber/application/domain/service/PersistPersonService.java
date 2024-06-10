package com.max_pw_iw.person_subscriber.application.domain.service;

import com.max_pw_iw.person_subscriber.application.domain.model.Person;
import com.max_pw_iw.person_subscriber.application.port.in.PersistPersonUseCase;
import com.max_pw_iw.person_subscriber.application.port.out.GetPersonPort;
import com.max_pw_iw.person_subscriber.infrastructure.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class PersistPersonService implements PersistPersonUseCase {

    private final GetPersonPort getPersonPort;

    @Override
    public void SendPerson(Person person) {
        
        getPersonPort.getPerson(person);

    }

}
