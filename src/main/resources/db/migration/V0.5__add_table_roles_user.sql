Create TABLE roles_user(
    id_user int not null,
    id_roles int not null,
    foreign key(id_roles) references role(id),
    foreign key(id_user) references user(id)
)