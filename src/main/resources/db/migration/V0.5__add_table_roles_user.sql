Create TABLE roles_user(
    id_user int not null primary key unique,
    id_roles int not null ,
    foreign key(id_roles) references Role(id),
    foreign key(id_user) references User(id)
)