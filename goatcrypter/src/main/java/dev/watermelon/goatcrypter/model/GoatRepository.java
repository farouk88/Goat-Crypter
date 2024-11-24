package dev.watermelon.goatcrypter.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GoatRepository extends JpaRepository<Goat, Long> {
    
    Optional<Goat> findById(long id);
    List<Goat> findByUserId(long userId);
    List<Goat> findByUser(MyAppUser user);
}
