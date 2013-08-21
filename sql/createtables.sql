CREATE TABLE users(
	id serial PRIMARY KEY,
	username varchar UNIQUE,
	password varchar NOT NULL
);

CREATE TABLE league(
	id serial PRIMARY KEY,
	name varchar NOT NULL
);

CREATE TABLE season(
	id serial PRIMARY KEY,
	name varchar NOT NULL
);

CREATE TABLE team(
	id serial PRIMARY KEY,
	name varchar NOT NULL
);

CREATE TABLE membership(
	id serial PRIMARY KEY,
	league_id integer references league(id) ON DELETE CASCADE,
	season_id integer references season(id) ON DELETE CASCADE,
	team_id integer references team(id) ON DELETE CASCADE
);

CREATE TABLE game(
	id serial PRIMARY KEY,
	home_team integer references team(id) ON DELETE CASCADE,
    away_team integer references team(id) ON DELETE CASCADE,
	league_id int references league(id) ON DELETE CASCADE,
    season_id int references season(id) ON DELETE CASCADE,
	home_score integer,
	away_score integer,
	game_date date,
	time_edited timestamp,
	edited_by integer references users(id) ON DELETE RESTRICT
);

	

	
	


