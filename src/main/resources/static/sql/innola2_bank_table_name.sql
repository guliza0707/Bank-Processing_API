create table table_name
(
    id        serial      not null
        constraint table_name_pk
            primary key,
    username  varchar(50) not null,
    password  varchar(255),
    email     varchar(50),
    full_name varchar(255)
);

alter table table_name
    owner to postgres;

create unique index table_name_username_uindex
    on table_name (username);

