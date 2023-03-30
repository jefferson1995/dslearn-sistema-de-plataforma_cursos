package com.devsuperior.dslearnbds.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class); //Para pegar os logs da aplicação
	
	@Autowired
	private UserRepository repository;
	
	//Método do UserDetails para encontrar o e-mail 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		//Caso não encontre o e-mail do usuário, estoura uma exceção
		if(user == null) {
			logger.error("Usuário não encontrado: " + username);//Para lançar o log no console da aplicação
			throw new UsernameNotFoundException("E-mail não encontrado");
		}
		logger.info("User found: " + username); //Para lançar o log no console da aplicação
		return user;
	}
}