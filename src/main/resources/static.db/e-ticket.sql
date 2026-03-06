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
);

create table permissions (
                             id bigint not null,
                             name varchar(255),
                             primary key (id)
);
create table role_permissions (
                                  permission_id bigint not null,
                                  role_id bigint not null,
                                  primary key (permission_id, role_id)
);

create table roles (
                       id bigint not null,
                       user_id bigint,
                       name varchar(255),
                       primary key (id)
);

