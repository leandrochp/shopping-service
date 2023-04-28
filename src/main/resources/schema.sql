create table shop (
    id bigint primary key auto_increment,
    buyer_identifier varchar not null,
    identifier varchar not null,
    status varchar not null,
    date_shop date
);

create table shop_item (
    id bigint primary key auto_increment,
    product_identifier varchar not null,
    amount int not null,
    price float not null,
    shop_id bigint REFERENCES shop(id)
);

create table shop_report (
   id bigint primary key auto_increment,
   status varchar not null,
   amount int not null
);

create table product (
    id bigint primary key auto_increment,
    product_identifier varchar not null,
    amount int not null
);
