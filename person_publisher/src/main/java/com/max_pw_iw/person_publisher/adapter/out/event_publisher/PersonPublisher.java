package com.max_pw_iw.person_publisher.adapter.out.event_publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import com.max_pw_iw.person_publisher.application.domain.model.Person;
import com.max_pw_iw.person_publisher.application.port.out.GetPersonPort;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.Session;

@Component
public class PersonPublisher implements GetPersonPort {

	@Autowired
	private JmsTemplate jmsTopicTemplate;

	@Value("people/add")
	private String topicName;

    @Override
    public Person getPerson(Person person) {
        sendEvent(person);
        return person;
    }

	public void sendEvent(Person person){

        // Map<String, Object> map = new HashMap<>(); 
        
        jmsTopicTemplate.send(topicName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// System.out.println("==========SENDING PERSON " + eventPerson.getFirstName() + " TO TOPIC: \"" + topicName + " \"========== ");
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("firstName", person.getFirstName()); 
				mapMessage.setString("lastName", person.getLastName()); 
				mapMessage.setString("sex", person.getSex()); 
				mapMessage.setInt("age", person.getAge()); 
				return mapMessage;
			}
		});
	}
    
}
