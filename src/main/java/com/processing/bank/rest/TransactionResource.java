package com.processing.bank.rest;

import com.processing.bank.dto.Error;
import com.processing.bank.dto.PayRequest;
import com.processing.bank.dto.Response;
import com.processing.bank.model.Card;
import com.processing.bank.model.Transaction;
import com.processing.bank.repository.CardRepository;
import com.processing.bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/transaction")
public class TransactionResource {

    CardRepository cardRepository;

    TransactionRepository transactionRepository;

    @PostMapping("/pay")
    ResponseEntity<Response<?>> getCardById(@RequestBody PayRequest request) {
        try {
            Card card = cardRepository.findCardByNumber(request.getCardNumber()).orElse(null);
            if(card != null) {
                log.info("Get card by number: {}", request.getCardNumber());
                if(card.getAccount().isEnoughBalanceAmount(request.getAmount()))
                {
                    // Get receiver card
                    Card receiverCard = cardRepository.findCardByNumber(request.getReceiverCardNumber()).orElse(null);
                    // Check returned card
                    if(receiverCard != null) {
                        // Subtract an amount of money from the account balance of sender
                        card.getAccount().subtractFromBalance(request.getAmount());
                        cardRepository.save(card);
                        // Add an amount of money from the account balance of receiver
                        receiverCard.getAccount().addToBalance(request.getAmount());
                        cardRepository.save(receiverCard);
                        // Create an operation transaction
                        //Transaction transaction = new Transaction(card.getAccount(), receiverCard.getAccount(), request.getAmount());
                        transactionRepository.save(new Transaction(card.getAccount(), receiverCard.getAccount(), request.getAmount()));

                        log.info("Subtract an amount {} from the card: {}", request.getAmount(), request.getCardNumber());

                        return ResponseEntity.ok(new Response(1002, String.format("Subtracted an amount {} from the card: {}",
                                request.getAmount(),
                                request.getCardNumber()), null));

                    } else {
                        log.info("Unable to get receiver card: {}", request.getReceiverCardNumber());
                        return ResponseEntity.ok(new Response(2002, String.format("Something is wrong. Unable to get receiver card: {}",
                                request.getReceiverCardNumber()), null));
                    }
                }
                return ResponseEntity.ok(new Response(2001, "Your Account Balance has not enough amount", null));
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
        }
        log.error("Get no card by number: {}", request.getCardNumber());
        return ResponseEntity.badRequest().body(new Response(1001, String.format("Get no card by number: {}", request.getCardNumber()), null));
    }
}
