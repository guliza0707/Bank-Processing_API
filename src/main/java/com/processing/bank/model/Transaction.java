package com.processing.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity()
@Setter
@Getter
@Table(name = "transactions", schema = "bank")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "amount", nullable = true)
    private Integer amount;

    @Basic
    @Column(name = "type", nullable = true)
    private Integer type;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", referencedColumnName = "id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id", referencedColumnName = "id")
    private Account receiverAccount;

}
