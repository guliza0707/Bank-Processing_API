package com.processing.bank.rest;

import com.processing.bank.model.Account;
import com.processing.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountResource {

    AccountRepository accountRepository;

    public AccountResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        try {
            Account account = accountRepository.findById(id).orElse(null);
            if(account != null) {
                log.info("Get account by id: {}", id);
                return ResponseEntity.ok(account);
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Get no account by id: {}", id);
        return ResponseEntity.badRequest().body(new Account());
    }
}
