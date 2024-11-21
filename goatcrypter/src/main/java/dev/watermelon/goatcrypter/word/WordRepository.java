package dev.watermelon.goatcrypter.word;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class WordRepository {
    
    private List<Word> words = new ArrayList<>();

    List<Word> findAll() {
        return words;
    }

    Optional<Word> findById(Integer id){
        return words.stream()
                    .filter(word -> word.id() == id)
                    .findFirst();
    }

    void create(Word word){
        words.add(word);
    }

    void update(Word word, Integer id){
        Optional<Word> existingWord = findById(id);
        if(existingWord.isPresent()){
            words.set(words.indexOf(existingWord.get()), word);
        }
    }

    void delete(Integer id){
        words.removeIf(word -> word.id().equals(id));
    }

    @PostConstruct
    private void init(){
        words.add(new Word(
            1,
            1, 
            ".IZW]S", 
            "G.O.A.T", 
            "Farouk", 
            Encryption.DECRYPT, 
            LocalDateTime.now()));

        words.add(new Word(
            2,
            1, 
            "Farouk", 
            "G.O.A.T", 
            ".IZW]S", 
            Encryption.ENCRYPT, 
            LocalDateTime.now()));
    }

}
