package com.processing.bank.rest;

import com.processing.bank.model.Card;
import com.processing.bank.model.Client;
import com.processing.bank.repository.CardRepository;
import com.processing.bank.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/card")
public class CardResource {

    CardRepository cardRepository;

    public CardResource(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<Card> getCardById(@PathVariable("id") Long id) {
        try {
            Card card = cardRepository.findById(id).orElse(null);
            if(card != null) {
                log.info("Get card by id: {}", id);
                return ResponseEntity.ok(card);
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Get no card by id: {}", id);
        return ResponseEntity.badRequest().body(new Card());
    }

    @GetMapping("/by-number/{number}")
    ResponseEntity<Card> getCardById(@PathVariable("number") String number) {
        try {
            Card card = cardRepository.findCardByNumber(number).orElse(null);
            if(card != null) {
                log.info("Get card by number: {}", number);
                return ResponseEntity.ok(card);
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Get no card by number: {}", number);
        return ResponseEntity.badRequest().body(new Card());
    }

    @GetMapping("/by-owner/{full_name}")
    ResponseEntity<Card> getCardByOwnerFullName(@PathVariable("full_name") String fullName) {
        try {
            Card card = cardRepository.findCardByOwnerFullName(fullName).orElse(null);
            if(card != null) {
                log.info("Get card by fullName: {}", fullName);
                return ResponseEntity.ok(card);
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Get no card by fullName: {}", fullName);
        return ResponseEntity.badRequest().body(new Card());
    }
}
