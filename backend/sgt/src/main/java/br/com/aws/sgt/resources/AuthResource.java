package br.com.aws.sgt.resources;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aws.sgt.repositories.natives.UserRepository;
import br.com.aws.sgt.security.AccountCredentialsDTO;
import br.com.aws.sgt.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserRepository repository;
	
	@ApiOperation(value = "Authenticate a user by credentials")
	@PostMapping(value = "/signin")
	public ResponseEntity create(@RequestBody AccountCredentialsDTO credential) {
		try {
			var userName = credential.getUserName();
			var password = credential.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			
			var user = repository.findByUserName(userName);
			
			var token = "";
			
			if(user != null) {
				token = jwtTokenProvider.createToken(userName, user.getRoles());
			}
			else {
				throw new RuntimeException("Username not found");
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", userName);
			model.put("token", token);
			
			return ok(model);
		} catch (Exception e) {
			throw new RuntimeException("Invalid username/password");
		}
	}

}
