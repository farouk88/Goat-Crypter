package dev.watermelon.goatcrypter.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.watermelon.goatcrypter.model.MyAppUser;
import dev.watermelon.goatcrypter.model.MyAppUserRepository;



@RestController
public class ProfileController {
    
    private final MyAppUserRepository myAppUserRepository;

    public ProfileController(MyAppUserRepository myAppUserRepository){
        this.myAppUserRepository = myAppUserRepository;
    }

    @GetMapping("/api/profile")
    public MyAppUser getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return myAppUserRepository.findByUsername(currentUsername).get();
    }

}
