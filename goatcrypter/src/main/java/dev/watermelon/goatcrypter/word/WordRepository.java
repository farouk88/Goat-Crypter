package dev.watermelon.goatcrypter.word;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class WordRepository {
    
    private List<Word> words = new ArrayList<>();
    private final JdbcClient jdbcClient;

    public WordRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }
    
    public List<Word> findAll() {
        return jdbcClient.sql("select * from history")
                        .query(Word.class)
                        .list();
    }

    public Optional<Word> findById(Integer id) {
        return jdbcClient.sql("SELECT id,UserId,word,keyword,result,encryption, time FROM HISTORY WHERE id = :id" )
                .param("id", id)
                .query(Word.class)
                .optional();
    }

    public void create(Word word) {
        var updated = jdbcClient.sql("INSERT INTO HISTORY(id,UserId,word,keyword,result,encryption,time) values(?,?,?,?,?,?,?)")
                .params(List.of(word.id(),word.UserId(),word.word(),word.keyword(),word.result(),word.encryption().toString(), word.time().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create word " + word.UserId());
    }

    public void update(Word word, Integer id) {
        var updated = jdbcClient.sql("update HISTORY set UserId = ?, word = ?, keyword = ?, result = ?, encryption = ?, time = ? where id = ?")
                .params(List.of(word.UserId(),word.word(),word.keyword(),word.result(),word.encryption().toString(),word.time().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update word " + word.UserId());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from HISTORY where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete word " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from HISTORY").query().listOfRows().size();
    }

    public void saveAll(List<Word> words) {
        words.stream().forEach(this::create);
    }

    Word encrypt(String wordToBeEncrypted){
        int id = (int) jdbcClient.sql("select max(id) from history").query().singleValue() + 1;
        int UserId = 0;
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
            UserId,
            wordToBeEncrypted,
            keyword,
            result,
            encryption,
            time
        );

        create(word);
        return word;
    }

    Word decrypt(String wordToBeDecrypted){
        int id = (int) jdbcClient.sql("select max(id) from history").query().singleValue() + 1;
        int UserId = 0;
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
            UserId,
            wordToBeDecrypted,
            keyword,
            result,
            encryption,
            time
        );

        create(word);
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
    
}
