package com.processing.bank.repository;

import com.processing.bank.model.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> findCardByNumber(String number);

    @Query("SELECT c FROM Card c WHERE c.owner.fullName = ?1")
    Optional<Card> findCardByOwnerFullName(String fullName);
}
