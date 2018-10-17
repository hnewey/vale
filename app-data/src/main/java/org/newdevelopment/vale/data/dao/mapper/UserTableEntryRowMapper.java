package org.newdevelopment.vale.data.dao.mapper;

import org.newdevelopment.vale.data.model.UserTableEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.newdevelopment.vale.data.util.AppConstants.*;

public class UserTableEntryRowMapper implements RowMapper<UserTableEntry> {

    @Override
    public UserTableEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserTableEntry userTableEntry = new UserTableEntry();

        userTableEntry.setUsername(rs.getString(USER_USERNAME));
        userTableEntry.setPassword(rs.getBytes(USER_PASSWORD));
        userTableEntry.setSalt(rs.getBytes(USER_SALT));

        return userTableEntry;
    }

}

