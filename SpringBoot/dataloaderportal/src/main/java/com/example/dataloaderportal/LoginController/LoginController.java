package com.example.dataloaderportal.LoginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dataloaderportal.Logindomain.Login;
import com.example.dataloaderportal.Loginrepository.LoginRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private LoginRepository repo;

	@PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody Login user) {
  Login oathUser=repo.findByUsername(user.getUsername());
 if(oathUser.getPassword().equals(user.getPassword()))
 return ResponseEntity.ok(user);
 else
 return (ResponseEntity<?>) ResponseEntity.internalServerError();
 }
}
