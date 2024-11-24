package dev.watermelon.goatcrypter.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.watermelon.goatcrypter.model.Goat;
import dev.watermelon.goatcrypter.model.GoatRepository;
import dev.watermelon.goatcrypter.model.MyAppUser;
import dev.watermelon.goatcrypter.model.MyAppUserRepository;



@RestController
@RequestMapping("/goat")
public class GoatController {
    
        private final GoatRepository goatRepository;
        private final MyAppUserRepository myAppUserRepository;

        public GoatController(GoatRepository goatRepository, MyAppUserRepository myAppUserRepository){
            this.goatRepository = goatRepository;
            this.myAppUserRepository = myAppUserRepository;
        }

        @PostMapping(consumes="application/json")
        public Goat goat(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Goat goat) {
            String username = userDetails.getUsername();
            MyAppUser user = myAppUserRepository.findByUsername(username).get();

            goat.setUser(user);
            
            if(goat.getKeyword().isEmpty()){
                goat.setKeyword("G.O.A.T");
            }

            if(goat.getEncryption().equalsIgnoreCase("encrypt")){
                goat.setResult(goatEncrypt(goat.getWord(), goat.getKeyword()));
            } else if(goat.getEncryption().equalsIgnoreCase("decrypt")){
                goat.setResult(goatDecrypt(goat.getWord(), goat.getKeyword()));
            }

            goatRepository.save(goat);
            return goat;
        }

        @GetMapping("/history")
        public List<Goat> getHistroy(@AuthenticationPrincipal UserDetails userDetails) {
            String username = userDetails.getUsername();
            long userId = getUserIdByUsername(username);
            return goatRepository.findByUserId(userId);
        }
        
        private long getUserIdByUsername(String username){
            return myAppUserRepository.findByUsername(username).get().getId();
        }

        private String goatEncrypt(String word, String keyword) {
            int keywordIndex = 0;
            String result="";

            for(int i=0; i<word.length(); i++){
                if(keywordIndex > keyword.length()){
                    keywordIndex = 0;
                }

                result += addAToB(word.charAt(i), keyword.charAt(keywordIndex));
            }

            return result;
        }

        private char addAToB(char a, char b) {
            int asciiA = a;
            int asciiB = b;
            int sum = asciiA + asciiB;
            
            if(sum > 125) {
                return (char) (sum-95);
            } else {
                return (char) sum;
            }
        }

        private String goatDecrypt(String word, String keyword) {
            int keywordIndex = 0;
            String result="";

            for(int i=0; i<word.length(); i++) {
                if(keywordIndex > keyword.length()) {
                    keywordIndex = 0;
                }
                
                result += subtractAFromB(word.charAt(i), keyword.charAt(keywordIndex));
            }

            return result;
        }
            
        public char subtractAFromB(char a, char b) {
            int asciiA = a;
            int asciiB = b;
            int sum = asciiA - asciiB;
            
            if(sum < 31) {
                return (char) (sum+95);
            } else {
                return (char) (sum);
            }
            
        }

}
