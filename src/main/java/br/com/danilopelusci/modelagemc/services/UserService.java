package br.com.danilopelusci.modelagemc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.danilopelusci.modelagemc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {

			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} 
		catch (Exception e) {

			return null;
		}
	}

}
