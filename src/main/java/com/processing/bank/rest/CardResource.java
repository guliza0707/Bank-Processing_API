package com.processing.bank.rest;

import com.processing.bank.dto.PayRequest;
import com.processing.bank.dto.Response;
import com.processing.bank.model.Card;
import com.processing.bank.model.Transaction;
import com.processing.bank.repository.CardRepository;
import com.processing.bank.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/card")
public class CardResource {

    CardRepository cardRepository;

    TransactionRepository transactionRepository;

    public CardResource(CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
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

    @GetMapping("/is-enough-balance/{number}/{amount}")
    ResponseEntity<Response> getCardById(@PathVariable("number") String number, @PathVariable("amount") BigDecimal amount) {
        try {
            Card card = cardRepository.findCardByNumber(number).orElse(null);
            if(card != null) {
                log.info("Get card by number: {}", number);
                if(card.getAccount().isEnoughBalanceAmount(amount))
                {
                    return ResponseEntity.ok(new Response(1001, "Your Account Balance has enough amount", null));
                }
                return ResponseEntity.ok(new Response(2001, "Your Account Balance has not enough amount", null));
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Get no card by number: {}", number);
        return ResponseEntity.badRequest().body(new Response(1001, String.format("Get no card by number: {}", number), null));
    }

    @PostMapping("/pay")
    ResponseEntity<Card> getCardById(@RequestBody PayRequest request) {
        try {
            Card card = cardRepository.findCardByNumber(request.getCardNumber()).orElse(null);
            Card receiverCard = cardRepository.findCardByNumber(request.getReceiverCardNumber()).orElse(null);
            BigDecimal result = card.getAccount().getBalance().subtract(request.getAmount());
            card.getAccount().setBalance(result);
            cardRepository.save(card);
            BigDecimal subtractResult = receiverCard.getAccount().getBalance().add(request.getAmount());
            receiverCard.getAccount().setBalance(subtractResult);
            cardRepository.save(receiverCard);
            Transaction transaction = new Transaction();
            transaction.setAmount(request.getAmount());
            transaction.setSenderAccount(card.getAccount());
            transaction.setReceiverAccount(receiverCard.getAccount());
            transactionRepository.save(transaction);
            if(card != null) {
                log.info("Subtract an amount {} from the card: {}", request.getAmount(), request.getCardNumber());
                return ResponseEntity.ok(card);
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Can't subtract an amount {} from the card: {}", request.getAmount(), request.getCardNumber());
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
