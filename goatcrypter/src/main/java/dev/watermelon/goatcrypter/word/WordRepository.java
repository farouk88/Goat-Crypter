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

    Word encrypt(String wordToBeEncrypted){
        int id = getNextId();
        String keyword = "G.O.A.T";
        String result = "";
        Encryption encryption = Encryption.ENCRYPT;
        LocalDateTime time = LocalDateTime.now();

        int keywordIndex = 0;

        for(int i=0; i<wordToBeEncrypted.length(); i++){
            if(keywordIndex > keyword.length()){
                keywordIndex = 0;
            }

            result += addAToB(wordToBeEncrypted.charAt(i), keyword.charAt(keywordIndex));
        }

        Word word = new Word(
            id,
            null,
            wordToBeEncrypted,
            keyword,
            result,
            encryption,
            time
        );

        words.add(word);
        return word;
    }

    Word decrypt(String wordToBeDecrypted){
        int id = getNextId();
        String keyword = "G.O.A.T";
        String result = "";
        Encryption encryption = Encryption.DECRYPT;
        LocalDateTime time = LocalDateTime.now();

		int keywordLength = keyword.length();
		int wordLength = wordToBeDecrypted.length();
		int keywordIndex = 0;
		
		for(int i=0; i<wordLength; i++) {
			if(keywordIndex > keywordLength) {
				keywordIndex = 0;
			}
			
			result += subtractAFromB(wordToBeDecrypted.charAt(i), keyword.charAt(keywordIndex));
		}
		
        Word word = new Word(
            id,
            null,
            wordToBeDecrypted,
            keyword,
            result,
            encryption,
            time
        );

        words.add(word);
        return word;
    }

    char addAToB(char a, char b) {
		int asciiA = a;
		int asciiB = b;
		int sum = asciiA + asciiB;
		
		if(sum > 126) {
			return (char) (sum-95);
		} else {
			return (char) sum;
		}
	}

    public char subtractAFromB(char a, char b) {
		int asciiA = a;
		int asciiB = b;
		int sum = asciiA - asciiB;
		
		if(sum < 32) {
			return (char) (sum+95);
		} else {
			return (char) (sum);
		}
		
	}

    int getNextId(){
        int id = 0;
        for(Word word : words){
            if(word.id() > id){
                id = word.id();
            }
        }

        return id+1;
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
