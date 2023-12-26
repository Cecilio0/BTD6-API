package com.dread9182.BTD6API.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {
	
	@Id
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String role;
	
	private String email;
	
	private String password;
}
