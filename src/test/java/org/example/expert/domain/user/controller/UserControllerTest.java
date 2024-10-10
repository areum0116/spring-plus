package org.example.expert.domain.user.controller;

import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.example.expert.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
@ContextConfiguration(classes = UserController.class)
class UserControllerTest {
    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserController userController;

    private void saveUsers(List<User> users) {
        String sql = "insert into users (id, email, password, user_role, nickname) values (?, ?, 'password', 'USER', ?)";
        jdbcTemplate.batchUpdate(sql, users, users.size(), (PreparedStatement ps, User user) -> {
            ps.setLong(1, user.getId());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getNickname());
        });
    }

    @Test
    public void insertUser() throws Exception {
        // given
        List<User> users = new ArrayList<>();
        for (long i = 0; i < 1000000; i++) {
            users.add(new User(i, UUID.randomUUID().toString(), "nickname" + i));
        }
        saveUsers(users);

        // when & then
        assertEquals(users.size(), userRepository.count());
    }

    @Test
    public void getUserByNicknameTest() throws Exception {

    }
}