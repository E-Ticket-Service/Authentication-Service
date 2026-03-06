create table users (
                       created_at timestamp(6),
                       id bigint not null,
                       update_at timestamp(6),
                       lastname varchar(100),
                       name varchar(100),
                       email varchar(255) not null unique,
                       phone_number varchar(255) not null unique,
                       role varchar(255) check ((role in ('ADMIN','ORGANIZER','CUSTOMER'))),
                       status varchar(255) check ((status in ('ACTIVE','BLOCKED'))),
                       primary key (id)
)