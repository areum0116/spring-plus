package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.dto.response.TodoResponseV2;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoQueryDslRepository {

    Optional<Todo> findByIdWithUser(Long todoId);

    Page<TodoResponseV2> findByCustomSearch(Pageable pageable, String title, LocalDateTime startDate, LocalDateTime endDate, String managerNickname);
}
