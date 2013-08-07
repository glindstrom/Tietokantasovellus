CREATE TABLE users
(
	userId serial PRIMARY KEY,
	username varchar UNIQUE,
	password varchar
);
