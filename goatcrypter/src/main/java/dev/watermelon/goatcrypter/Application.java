package dev.watermelon.goatcrypter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Bean
	// CommandLineRunner history(WordRepository wordRepository){
	// 	return args -> {
	// 		Word word = new Word(1,1, "Farouk", "G.O.A.T", ".IZW]S", Encryption.ENCRYPT, LocalDateTime.now());
	// 		wordRepository.create(word);
	// 	};
	// }
}
