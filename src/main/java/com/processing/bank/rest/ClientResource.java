package com.processing.bank.rest;

import com.processing.bank.model.Account;
import com.processing.bank.model.Client;
import com.processing.bank.repository.AccountRepository;
import com.processing.bank.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/client")
public class ClientResource {

    ClientRepository clientRepository;

    public ClientResource(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        try {
            Client client = clientRepository.findById(id).orElse(null);
            if(client != null) {
                log.info("Get client by id: {}", id);
                return ResponseEntity.ok(client);
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Exception: {}", exception.getStackTrace());
        }
        log.error("Get no client by id: {}", id);
        return ResponseEntity.badRequest().body(new Client());
    }
}
