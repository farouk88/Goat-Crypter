package dev.watermelon.goatcrypter.word;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WordJsonDataLoader implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(WordJsonDataLoader.class); 
    private final WordRepository wordRepository;
    private final ObjectMapper objectMapper;

    public WordJsonDataLoader(WordRepository wordRepository, ObjectMapper objectMapper){
        this.wordRepository = wordRepository;
        this.objectMapper = objectMapper;
    }


    
    @Override
    public void run(String... args) throws Exception{
        if(wordRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/words.json")) {
                Words allWords = objectMapper.readValue(inputStream, Words.class);
                log.info("Reading {} runs from JSON data and saving to a database.", allWords.words().size());
                wordRepository.saveAll(allWords.words());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Words from JSON data because the collection contains data.");
        }
    }
    
}
