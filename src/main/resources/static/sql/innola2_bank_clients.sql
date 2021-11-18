create table clients
(
    id         serial       not null
        constraint clients_pk
            primary key,
    full_name  varchar(255) not null,
    inn        integer,
    secret_key text,
    username   varchar(255),
    password   text
);

alter table clients
    owner to postgres;

INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (1, 'Guliza Tulkinova', 234567, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (2, 'Sobirov Muhammad', 123344, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (3, 'Xasanov Jamshid', 897564, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (4, 'Rahmonov Javohir', 374658, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (5, 'Abdullayeva Sevara', 128765, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (6, 'Gulamov Anvar', 128764, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (7, 'Saidova Muhabbat', 874653, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (8, 'Mirkomilov Doniyor', 127366, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (9, 'Turdiyev Alisher', 123345, null, null, null);
INSERT INTO bank.clients (id, full_name, inn, secret_key, username, password) VALUES (10, 'Usmonov Yulduz', 895747, null, null, null);