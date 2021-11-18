create table currency
(
    id   serial not null,
    name text,
    code text
);

alter table currency
    owner to postgres;

INSERT INTO bank.currency (id, name, code) VALUES (1, 'sum', 'USSD');
INSERT INTO bank.currency (id, name, code) VALUES (2, 'dollar', 'USD');
INSERT INTO bank.currency (id, name, code) VALUES (3, 'euro', 'EUR');