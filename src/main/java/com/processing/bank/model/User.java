package com.processing.bank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity()
@Setter
@Getter
@Table(name = "users", schema = "bank")
public class User extends AbstractAuditable<User, Long> {

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    String username;

    @Basic
    @Column(name = "password", length = 255)
    String password;

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    String email;

    @Basic
    @Column(name = "full_name", nullable = false, length = 255)
    String fullName;
}
