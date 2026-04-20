Create table User
(
    id       int          primary key unique not null,
    login    varchar(100)             unique not null,
    password varchar(100)                    not null
)