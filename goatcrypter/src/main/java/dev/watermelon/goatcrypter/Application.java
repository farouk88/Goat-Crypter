package dev.watermelon.goatcrypter;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.watermelon.goatcrypter.word.Encryption;
import dev.watermelon.goatcrypter.word.Word;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner history(){
		return args -> {
			Word word = new Word(1,1, "Farouk", "G.O.A.T", ".IZW]S", Encryption.ENCRYPT, LocalDateTime.now());
			System.out.println(word);
		};
	}
}
