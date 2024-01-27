package com.dread9182.BTD6API.user.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data // Automatically generates getters and setters
@Builder // Creates a builder method
@NoArgsConstructor // Creates a constructor with no arguments
@AllArgsConstructor // Creates a constructor with all arguments
@Document("users")
public class User implements UserDetails { // UserDetails is used to create Users for spring security
	
	@Id
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private Role role;
	
	private String email;
	
	private String password;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
