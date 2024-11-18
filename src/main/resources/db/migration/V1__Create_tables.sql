CREATE TABLE countries
(
    name       CHAR(40) NOT NULL,
    country_id CHAR(3) UNIQUE PRIMARY KEY,
    area_sqkm  INTEGER  NOT NULL,
    population INTEGER  NOT NULL
);

CREATE TABLE olympics
(
    olympic_id CHAR(7) UNIQUE PRIMARY KEY,
    country_id CHAR(3)  NOT NULL REFERENCES countries (country_id),
    city       CHAR(50) NOT NULL,
    year       INTEGER  NOT NULL,
    startdate  DATE     NOT NULL,
    enddate    DATE     NOT NULL
);

CREATE TABLE players
(
    name       CHAR(40) NOT NULL,
    player_id  CHAR(10) UNIQUE PRIMARY KEY,
    country_id CHAR(3)  NOT NULL REFERENCES countries (country_id),
    birthdate  DATE     NOT NULL
);

CREATE TABLE events
(
    event_id            CHAR(7) UNIQUE PRIMARY KEY,
    name                CHAR(40) NOT NULL,
    eventtype           CHAR(20) NOT NULL,
    olympic_id          CHAR(7)  NOT NULL REFERENCES olympics (olympic_id),
    is_team_event       INTEGER  NOT NULL CHECK (is_team_event IN (0, 1)),
    num_players_in_team INTEGER,
    result_noted_in     CHAR(100)
);

CREATE TABLE results
(
    event_id  CHAR(7)  NOT NULL REFERENCES events (event_id),
    player_id CHAR(10) NOT NULL REFERENCES players (player_id),
    medal     CHAR(7),
    result    DOUBLE PRECISION,
    PRIMARY KEY (event_id, player_id)
);
