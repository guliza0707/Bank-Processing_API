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
            // Get sender card
            Card card = cardRepository.findCardByNumber(request.getCardNumber()).orElse(null);

            // Check returned card
            if(card != null) {
                // Get receiver card
                Card receiverCard = cardRepository.findCardByNumber(request.getReceiverCardNumber()).orElse(null);
                // Check returned card
                if(receiverCard != null) {
                    // Subtract an amount of money from the account balance of sender
                    BigDecimal result = card.getAccount().getBalance().subtract(request.getAmount());
                    card.getAccount().setBalance(result);
                    cardRepository.save(card);
                    // Add an amount of money from the account balance of receiver
                    BigDecimal addResult = receiverCard.getAccount().getBalance().add(request.getAmount());
                    receiverCard.getAccount().setBalance(addResult);
                    cardRepository.save(receiverCard);
                    // Create an operation transaction
                    //Transaction transaction = new Transaction(card.getAccount(), receiverCard.getAccount(), request.getAmount());
                    transactionRepository.save(new Transaction(card.getAccount(), receiverCard.getAccount(), request.getAmount()));

                    log.info("Subtract an amount {} from the card: {}", request.getAmount(), request.getCardNumber());
                    return ResponseEntity.ok(new Response<>(2, "Something is wrong;)))",
                            new Error(234,
                                    String.format("Subtract an amount {} from the card: {}",
                                            request.getAmount(),
                                            request.getCardNumber()))));

                } else {
                    log.info("Unable to get receiver card: {}", request.getReceiverCardNumber());
                    return ResponseEntity.ok(new Response<>(2, "Something is wrong;)))",
                            new Error(234,
                                    String.format("Unable to get receiver card: {}",
                                            request.getReceiverCardNumber()))));
                }
            } else {
                log.info("Unable to get sender card: {}", request.getCardNumber());
                return ResponseEntity.ok(new Response<>(2, "Something is wrong;)))",
                        new Error(234,
                                String.format("Unable to get sender card: {}", request.getCardNumber()))));
            }

        }
        catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Can't subtract an amount {} from the card: {}", request.getAmount(), request.getCardNumber());
        return ResponseEntity.ok(new Response<>(235456, "", new Error(234, "")));
    }
}
