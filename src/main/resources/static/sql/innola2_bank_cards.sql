create table cards
(
    id           serial      not null
        constraint cards_pk
            primary key,
    owner_id     integer     not null
        constraint cards_clients_fk
            references ??? (),
    account_id   integer     not null
        constraint cards_accounts_id_fk
            references ??? (),
    number       varchar(50) not null,
    expired_date date        not null
);

alter table cards
    owner to postgres;

INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (5, 1, 1, '8699345678650987', '2025-11-14');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (6, 2, 2, '8699345678650981', '2025-11-11');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (7, 3, 3, '8699345678650982', '2025-11-13');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (8, 4, 4, '8699345678650983', '2025-11-15');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (9, 5, 5, '8699345678650984', '2024-11-16');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (10, 6, 6, '8699345678650985', '2025-11-12');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (11, 7, 7, '8699345678650986', '2025-11-11');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (12, 8, 8, '8699345678650988', '2025-11-13');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (13, 9, 9, '8699345678650989', '2024-11-15');
INSERT INTO bank.cards (id, owner_id, account_id, number, expired_date) VALUES (14, 10, 10, '8699345678650980', '2024-11-10');