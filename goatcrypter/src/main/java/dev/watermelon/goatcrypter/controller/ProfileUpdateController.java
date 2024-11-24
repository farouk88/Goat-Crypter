package dev.watermelon.goatcrypter.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.watermelon.goatcrypter.model.MyAppUser;
import dev.watermelon.goatcrypter.model.MyAppUserRepository;

@RestController
@RequestMapping("/api/profile/update")
public class ProfileUpdateController {
    private final MyAppUserRepository myAppUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileUpdateController(MyAppUserRepository myAppUserRepository, PasswordEncoder passwordEncoder){
        this.myAppUserRepository = myAppUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping
    public ResponseEntity<?> updateUserProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MyAppUser updatedUser) {
        Optional<MyAppUser> optionalUser = myAppUserRepository.findByUsername(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            MyAppUser existingUser = optionalUser.get();
            existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            myAppUserRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // @PutMapping
    // public MyAppUser updatUserProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MyAppUser updatedUser) {
    //     MyAppUser existingUser = myAppUserRepository.findByUsername(updatedUser.getUsername()).get();

    //     if(existingUser != null){
    //         existingUser.setEmail(updatedUser.getEmail());
    //         if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()){
    //             existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
    //         }

    //         myAppUserRepository.save(existingUser);
    //     }
        
    //     return existingUser;
    // }
}
