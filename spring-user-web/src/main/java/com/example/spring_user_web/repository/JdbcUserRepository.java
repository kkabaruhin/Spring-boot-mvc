package com.example.spring_user_web.repository;


import com.example.spring_user_web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Profile("jdbc")
@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<User> findById(long id) {
        String sql = "select u.id as user_id, u.name as user_name, u.email as user_email, u.age as user_age, u.created as user_created_at from usr u where u.id = ?";
        List<User> users = jdbcTemplate.query(sql, new UserExtractor(), id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

    public List<User> findAll() {
        String sql = "select u.id as user_id, u.name as user_name, u.email as user_email, u.age as user_age, u.created as user_created_at from usr u";
        UserExtractor userExtractor = new UserExtractor();
        return jdbcTemplate.query(sql, userExtractor);
    }

    public User save(User newUser) {
        if (newUser.getId() == null) {
            User user = new User(null, newUser.getName(), newUser.getEmail(), newUser.getAge(), newUser.getCreatedAt());
            String sql = "INSERT INTO usr (name, email, age, created) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAge(), user. getCreatedAt());
        }
        else {
            Optional<User> oldUser = findById(newUser.getId());
            if (oldUser.isPresent()) {
                String sql = "UPDATE usr SET name = ?, email = ?, age = ?, created = ? WHERE id = ?";
                jdbcTemplate.update(sql, newUser.getName(), newUser.getEmail(), newUser.getAge(), newUser.getCreatedAt(), newUser.getId());
            }
        }

        return newUser;
    }

    public void deleteById(long id) {
        String deleteUserSql = "DELETE FROM usr WHERE id = ?";
        jdbcTemplate.update(deleteUserSql, id);
    }

    private static class UserExtractor implements ResultSetExtractor<List<User>> {
        @Override
        public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, User> userMap = new HashMap<>();

            while (rs.next()) {
                long userId = rs.getLong("user_id");
                User user = userMap.get(userId);
                if (user == null) {
                    user = new User();
                    user.setId(userId);
                    user.setName(rs.getString("user_name"));
                    user.setEmail(rs.getString("user_email"));
                    user.setAge(rs.getInt("user_age"));
                    user.setCreatedAt(rs.getString("user_created_at"));
                    userMap.put(userId, user);
                }
            }
            return new ArrayList<>(userMap.values());
        }
    }
}
