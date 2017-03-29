-- DROP TABLE IF EXISTS users;
-- DROP SEQUENCE IF EXISTS users_id_seq;

-- CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
--     INCREMENT 1
--     START 1
--     MINVALUE 1
--     MAXVALUE 9223372036854775807
--     CACHE 1;

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY NOT NULL,
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