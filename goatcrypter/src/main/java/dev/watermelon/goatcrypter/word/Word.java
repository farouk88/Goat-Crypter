package dev.watermelon.goatcrypter.word;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public record Word(
    @Id
    Integer id,
    Integer UserId,
    @NotEmpty
    String word,
    String keyword,
    String result,
    Encryption encryption,
    LocalDateTime time
) {
    
}
