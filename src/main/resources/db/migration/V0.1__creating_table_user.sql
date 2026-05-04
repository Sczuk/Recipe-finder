Create table user
(
    id       int primary key AUTO_INCREMENT unique not null,
    login    varchar(100) unique not null,
    password varchar(100) not null
)