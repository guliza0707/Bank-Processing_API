package com.processing.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity()
@Setter
@Getter
@Table (name = "accounts", schema = "bank")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Client owner;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    @JsonIgnore
    private Collection<Card> cards;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    @JsonIgnore
    private Collection<Transaction> transactions;

    @Basic
    @Column(name = "number", nullable = false, length = 50)
    private String number;

    @Basic
    @Column(name = "balance")
    private BigDecimal balance;

    public boolean isEnoughBalanceAmount(BigDecimal amount) {
        return balance.compareTo(amount) > 0;
    }

}
