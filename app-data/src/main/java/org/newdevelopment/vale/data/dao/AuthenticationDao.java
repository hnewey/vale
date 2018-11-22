package org.newdevelopment.vale.data.dao;

import org.newdevelopment.vale.data.dao.mapper.UserTableEntryRowMapper;
import org.newdevelopment.vale.data.exception.AuthenticationException;
import org.newdevelopment.vale.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.newdevelopment.vale.data.util.AppConstants.*;
import static org.newdevelopment.vale.data.util.sql.AuthSql.*;

@Component
public class AuthenticationDao {

    private JdbcTemplate jdbcTemplate;
    private UserTableEntryRowMapper userTableEntryRowMapper;

    @Autowired
    public AuthenticationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userTableEntryRowMapper = new UserTableEntryRowMapper();
    }

    public Boolean checkUsername(String username) {
        return jdbcTemplate.queryForObject(SELECT_USERNAME_AVAILABLE, new Object[]{username}, Boolean.class);
    }

    public User getUser(String username) {
        return jdbcTemplate.queryForObject(SELECT_USER, new Object[]{username}, userTableEntryRowMapper);
    }

    public void createUser(String username, byte[] encryptedPassword, byte[] salt) throws AuthenticationException {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(USER_TABLE);

        Map<String, Object> params = new HashMap<>();
        params.put(USER_USERNAME, username);
        params.put(USER_PASSWORD, encryptedPassword);
        params.put(USER_SALT, salt);

        try {
            jdbcInsert.execute(params);
        } catch (Exception e) {
            throw new AuthenticationException(String.format("Error creating user: %s error: %s",
                    username, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
