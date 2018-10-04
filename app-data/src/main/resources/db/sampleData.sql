set SEARCH_PATH = public;

INSERT INTO user_table (username, password, salt)
    VALUES ('test', '{testpassword}', '{salt}');


SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END
FROM user_table u
WHERE (u.username = 'test');

SELECT * from user_table;