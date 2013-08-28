CREATE TABLE users(
	id serial PRIMARY KEY,
	username varchar UNIQUE NOT NULL,
	password varchar NOT NULL
);

CREATE TABLE league(
	id serial PRIMARY KEY,
	name varchar UNIQUE NOT NULL
);

CREATE TABLE season(
	id serial PRIMARY KEY,
	name varchar UNIQUE NOT NULL
);

CREATE TABLE team(
	id serial PRIMARY KEY,
	name varchar UNIQUE NOT NULL
);

CREATE TABLE membership(
	id serial PRIMARY KEY,
	league_id integer NOT NULL REFERENCES league(id) ON DELETE CASCADE,
	season_id integer NOT NULL REFERENCES season(id) ON DELETE CASCADE,
	team_id integer REFERENCES team(id) ON DELETE CASCADE
);

CREATE TABLE game(
	id serial PRIMARY KEY,
	home_team integer NOT NULL REFERENCES team(id) ON DELETE CASCADE,
    away_team integer NOT NULL REFERENCES team(id) ON DELETE CASCADE,
	league_id integer NOT NULL REFERENCES league(id) ON DELETE CASCADE,
    season_id integer NOT NULL REFERENCES season(id) ON DELETE CASCADE,
	home_score integer,
	away_score integer,
	game_date date NOT NULL,
	time_edited timestamp,
	edited_by integer REFERENCES users(id) ON DELETE RESTRICT
);

	

	
	


