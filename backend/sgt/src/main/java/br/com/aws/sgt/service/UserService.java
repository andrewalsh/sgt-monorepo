package br.com.aws.sgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.aws.sgt.repositories.natives.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByUserName(username);
		
		if(user != null) {
			return user;
		}
		else {
			throw new RuntimeException("Usuario não encontrado");
		}
	}
}
