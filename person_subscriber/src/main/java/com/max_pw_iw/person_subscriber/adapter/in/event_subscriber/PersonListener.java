package com.max_pw_iw.person_subscriber.adapter.in.event_subscriber;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

import com.max_pw_iw.person_subscriber.application.port.in.PersistPersonUseCase;
import com.max_pw_iw.person_subscriber.application.domain.model.Person;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@EnableJms
public class PersonListener {

	private final PersistPersonUseCase persistPersonUseCase;

    @JmsListener(destination = "PeopleQ")
	public void ConsumeMessage(Message message) {

		System.out.println("New message received!");

		Date receiveTime = new Date();
			
		if(message instanceof MapMessage){

			MapMessage mm = (MapMessage) message;

			try {
				System.out.println(
					"Message Received at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(receiveTime)
							+ " with message content of: \n firstName: " + mm.getString("firstName")
							+ "\n lastName: " + mm.getString("lastName")
							+ "\n sex: " + mm.getString("sex")
							+ "\n age: " + Integer.toString(mm.getInt("age"))
							);
                Person person = new Person();
                person.setFirstName(mm.getString("firstName"));
                person.setLastName(mm.getString("lastName"));
                person.setSex(mm.getString("sex"));
                person.setAge(mm.getInt("age"));

				persistPersonUseCase.SendPerson(person);

                System.out.println("New person added to database: " + person.getFirstName());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(message.toString());
		}
	}

}
