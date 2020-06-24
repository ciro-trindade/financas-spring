package br.fatec.financasspring.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.fatec.financasspring.security.UserDetailsImpl;

public class ClienteService {
	
	public static UserDetailsImpl authenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return (UserDetailsImpl) auth.getPrincipal();					
		}
		return null;
	}
}
