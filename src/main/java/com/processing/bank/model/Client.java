package com.processing.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity()
@Setter
@Getter
@Table(name = "clients", schema = "bank")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Basic
    @Column(name = "inn", nullable = true)
    private Integer inn;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @JsonIgnore
    private Collection<Account> accounts;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Collection<Card> cards;
}
