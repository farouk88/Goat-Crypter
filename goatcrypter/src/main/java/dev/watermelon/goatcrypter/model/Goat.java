package dev.watermelon.goatcrypter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "goat")
public class Goat {
    
    @Id
    @GeneratedValue
    private long id;

    @Column(name="word", length=100, nullable=false)
    private String word;

    @Column(name="keyword", length=100)
    private String keyword;

    @Column(name="encryption", length=10, nullable=false)
    private String encryption;

    @Column(name="result", length=100, nullable=false)
    private String result;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyAppUser user;

  
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public MyAppUser getUser() {
        return user;
    }

    public void setUser(MyAppUser user) {
        this.user = user;
    }
}
