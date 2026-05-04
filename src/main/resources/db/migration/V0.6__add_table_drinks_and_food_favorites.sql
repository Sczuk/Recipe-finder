create table drink_food_fav(
    id_user int not null,
    id_food int,
    id_drink int,
    foreign key (id_user) references user(id),
    foreign key (id_food) references food(id),
    foreign key (id_drink) references drink(id)
)