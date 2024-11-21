package dev.watermelon.goatcrypter.word;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;








@RestController
@RequestMapping("/api/words")
public class WordController {
    
    private final WordRepository wordRepository;

    public WordController(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    @GetMapping("")
    List<Word> findAll(){
        return wordRepository.findAll();
    }
    
    @GetMapping("/{id}")
    Word findById(@PathVariable Integer id) {
        Optional<Word> word = wordRepository.findById(id);
        if(word.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return word.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Word word){
        wordRepository.create(word);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Word word, @PathVariable Integer id) {
        wordRepository.update(word, id);
    }
    
    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(Integer id){
        wordRepository.delete(id);
    }

    
    
}
