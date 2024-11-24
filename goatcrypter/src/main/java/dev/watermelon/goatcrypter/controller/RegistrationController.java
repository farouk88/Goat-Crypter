package dev.watermelon.goatcrypter.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.watermelon.goatcrypter.model.MyAppUser;
import dev.watermelon.goatcrypter.model.MyAppUserRepository;


@RestController
public class RegistrationController {

    private MyAppUserRepository myAppUserRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(MyAppUserRepository myAppUserRepository, PasswordEncoder passwordEncoder){
        this.myAppUserRepository = myAppUserRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping(value = "/signup", consumes="application/json")
    public MyAppUser signup(@RequestBody MyAppUser user) {
        try {
            
            // Check if user already exists
            if(myAppUserRepository.existsByUsername(user.getUsername())){
                throw new DataIntegrityViolationException("Username already exists");
            }

            if(myAppUserRepository.existsByEmail(user.getEmail())){
                throw new DataIntegrityViolationException("Email already exists");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return myAppUserRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
        
    }
    
}
