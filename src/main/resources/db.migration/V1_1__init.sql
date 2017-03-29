CREATE TABLE users
(
    id INTEGER DEFAULT nextval('users_id_seq'::regclass) PRIMARY KEY NOT NULL,
    login VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL,
    game_count INTEGER DEFAULT 0,
    game_count_win INTEGER DEFAULT 0,
    crystal_green INTEGER DEFAULT 0,
    crystal_blue INTEGER DEFAULT 0,
    crystal_red INTEGER DEFAULT 0,
    crystal_purple INTEGER DEFAULT 0,
    rating INTEGER DEFAULT 0
);
CREATE UNIQUE INDEX users_login_key ON users (login);