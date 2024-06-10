package com.max_pw_iw.person_publisher.adapter.in.web;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.max_pw_iw.person_publisher.application.domain.model.Person;
import com.max_pw_iw.person_publisher.application.port.in.SendPersonUseCase;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
class SendPersonController {

    private final SendPersonUseCase sendPersonUseCase;

    @PostMapping("/")
    ResponseEntity<String> sendPerson(@Valid @RequestBody Person person){
        sendPersonUseCase.SendPerson(person);
        return new ResponseEntity<String>("Sent person: " + person.getFirstName() + " " + person.getLastName(), HttpStatus.OK);
    }

}