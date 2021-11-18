create table accounts
(
    id       serial      not null
        constraint accounts_pk
            primary key,
    owner_id integer     not null
        constraint accounts_clients_fk
            references ??? (),
    number   varchar(50) not null,
    balance  numeric(14, 2) default 0.0
);

alter table accounts
    owner to postgres;

INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (3, 3, '87781234875609871325', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (4, 4, '87781234875609871326', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (5, 5, '87781234875609871327', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (6, 6, '87781234875609871328', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (7, 7, '87781234875609871329', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (8, 8, '87781234875609871320', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (9, 9, '87781234875609871322', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (10, 10, '87781234875609871321', 0.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (1, 1, '87781234875609871324', 30000.00);
INSERT INTO bank.accounts (id, owner_id, number, balance) VALUES (2, 2, '87781234875609871323', 30000.00);