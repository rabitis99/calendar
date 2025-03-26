package com.example.calendarproject.repository;

import com.example.calendarproject.entity.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserEntity createUser(UserEntity user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email",user.getEmail());
        parameters.put("created_at",user.getCreatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.intValue());

        return user;
    }

    @Override
    public UserEntity updateUser(UserEntity user, long id) {
        jdbcTemplate.update("UPDATE SET name=?,update_at=?,email=? from user where id =?",
                user.getName(),user.getUpdatedAt(),user.getEmail(),id);
        return jdbcTemplate.query("SELECT * FROM user where id=?",userRowMapper(),id).stream().findFirst().orElse(null);
    }

    @Override
    public String deleteUser(long id) {
        if (jdbcTemplate.update("DELETE * FROM user where id=?",id)>0){
            return "유저가 삭제가 됐습니다.";
        }
        else{
            return "유저 삭제 실패했습니다.";
        }

    }

    @Override
    public List<Long> cheakUserId() {
        return jdbcTemplate.query("SELECT id FROM user"
        ,((rs, rowNum) -> rs.getLong("id")));
    }

    private RowMapper<UserEntity> userRowMapper() {
        UserEntity user = new UserEntity();

        return (rs,lowNum)-> {
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));
            user.setUpdatedAt(rs.getString("update_at"));
            return user;
        };

    }


}
