
-- ===============     USER TABLE      ===============

DROP TABLE IF EXISTS USER_TABLE CASCADE;
CREATE TABLE USER_TABLE
(
  username      VARCHAR(255)  NOT NULL,
  password      BYTEA         NOT NULL,
  salt          BYTEA         NOT NULL,
  PRIMARY KEY (username)
);

DROP TABLE IF EXISTS GAME CASCADE;
CREATE TABLE GAME
(
  game_id       SERIAL,
  username      VARCHAR(255)  NOT NULL,
  game_state    JSON,
  PRIMARY KEY (game_id),
  FOREIGN KEY (username) REFERENCES USER_TABLE ON DELETE CASCADE
);
