create table seller(
	id serial primary key,
    name varchar(50),
    contactInfo varchar(100),
	registrationDate date
);

CREATE TYPE payment_state AS ENUM ('CASH', 'CARD', 'TRANSFER');

CREATE TABLE transaction(
	id serial primary key,
    seller integer REFERENCES seller(id),
    amount integer,
    paymentType payment_state,
	transactionDate date
);

select * from seller;