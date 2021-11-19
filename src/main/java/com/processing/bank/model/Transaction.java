package com.processing.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity()
@Setter
@Getter
@Table(name = "transactions", schema = "bank")
public class Transaction {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "amount", nullable = true)
    private BigDecimal amount;

    @Basic
    @Column(name = "type", nullable = true)
    private Integer type;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", referencedColumnName = "id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id", referencedColumnName = "id")
    private Account receiverAccount;

    public Transaction() {
    }

    public Transaction(Account senderAccount, Account receiverAccount, BigDecimal amount) {
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }
}
