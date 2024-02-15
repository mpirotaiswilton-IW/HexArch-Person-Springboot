package com.max_pw_iw.person_publisher.application.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Person {
    
	@NotBlank(message =  "first name cannot be blank")
    @NonNull
	private String firstName;

	@NotBlank(message =  "last name cannot be blank")
    @NonNull
	private String lastName;

	@NotNull(message = "age must be defined")
	private int age;

	@NotBlank(message =  "sex cannot be blank")
    @NonNull
	private String sex;

}