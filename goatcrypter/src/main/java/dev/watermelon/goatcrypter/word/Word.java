package dev.watermelon.goatcrypter.word;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;

public record Word(
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
