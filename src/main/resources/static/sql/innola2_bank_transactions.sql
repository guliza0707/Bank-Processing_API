create table transactions
(
    id                  serial not null
        constraint transactions_pk
            primary key,
    sender_account_id   integer
        constraint transactions_sender_accounts_id_fk
            references accounts,
    receiver_account_id integer
        constraint transactions_receiver_accounts_id_fk
            references accounts,
    amount              numeric(14, 2),
    type                integer,
    currency_id         integer
);

alter table transactions
    owner to postgres;

INSERT INTO bank.transactions (id, sender_account_id, receiver_account_id, amount, type, currency_id) VALUES (2, 1, 1, 10000.00, null, null);
INSERT INTO bank.transactions (id, sender_account_id, receiver_account_id, amount, type, currency_id) VALUES (3, 1, 2, 20000.00, null, null);
INSERT INTO bank.transactions (id, sender_account_id, receiver_account_id, amount, type, currency_id) VALUES (4, 1, 2, 10000.00, null, null);