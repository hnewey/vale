
-- ===============     USER TABLE      ===============

DROP TABLE IF EXISTS USER_TABLE CASCADE;
CREATE TABLE USER_TABLE
(
  username      VARCHAR(255)  NOT NULL,
  password      BYTEA         NOT NULL,
  salt          BYTEA         NOT NULL,
  PRIMARY KEY (username)
);
