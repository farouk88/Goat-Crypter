package dev.watermelon.goatcrypter.word;

import java.time.LocalDateTime;

public record Word(
    Integer id,
    Integer UserId,
    String word,
    String keyword,
    String result,
    Encryption encryption,
    LocalDateTime time
) {
    
}
