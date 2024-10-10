package org.example.expert.domain.user.controller;

import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@Commit
class UserControllerTest {
    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Autowired
    UserRepository userRepository;

    private void saveUsers(List<User> users) {
        String sql = "insert into users (id, email, password, user_role, nickname) values (?, ?, 'password', 'USER', ?)";
        jdbcTemplate.batchUpdate(sql, users, users.size(), (PreparedStatement ps, User user) -> {
            ps.setLong(1, user.getId());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getNickname());
        });
    }

//    @Test
    public void insertUser() throws Exception { // 백만 건 데이터 생성
        // given
        for (long i = 0; i < 1000000L; i += 1000) {
            List<User> batch = new ArrayList<>();
            for (long j = i; j < i + 1000 && j < 1000000L; j++) {
                batch.add(new User(j, UUID.randomUUID().toString(), "nickname" + j));
            }
            saveUsers(batch); // 배치 저장
        }
    }

    @Test
    public void getUserByNicknameTest() throws Exception {
        int count = 5;
        long sum = 0;

        for (int i = 0; i < count; i++) {
            long start = System.currentTimeMillis();
            String nickname = "nickname" + (long) (Math.random() * 1000000);
            userRepository.findByNickname(nickname);
            long end = System.currentTimeMillis();
            sum += (end - start);
        }

        double avg = (double) sum / count;
        System.out.println("findByNickname 평균 소요 시간 : " + avg + "ms");
    }
}