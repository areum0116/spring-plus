package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.example.expert.domain.todo.dto.response.QTodoResponseV2;
import org.example.expert.domain.todo.dto.response.TodoResponseV2;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.example.expert.domain.comment.entity.QComment.comment;
import static org.example.expert.domain.manager.entity.QManager.manager;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

public class TodoQueryDslRepositoryImpl implements TodoQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public TodoQueryDslRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(todo)
                .join(todo.user, user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne());
    }

    @Override
    public Page<TodoResponseV2> findByCustomSearch(Pageable pageable, String title, LocalDateTime startDate, LocalDateTime endDate, String managerNickname) {
        List<TodoResponseV2> content = queryFactory
                .select(new QTodoResponseV2(todo.title, manager.countDistinct(), comment.countDistinct()))
                .from(todo)
                .leftJoin(todo.comments, comment)
                .leftJoin(todo.managers, manager)
                .where(titleEq(title), createdBetween(startDate, endDate), managerNicknameEq(managerNickname))
                .groupBy(todo.id)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(todo.count())
                .from(todo)
                .leftJoin(todo.managers, manager)
                .where(titleEq(title), createdBetween(startDate, endDate), managerNicknameEq(managerNickname));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleEq(String title) {
        return title != null ? todo.title.contains(title) : null;
    }

    private BooleanExpression createdBetween(LocalDateTime start, LocalDateTime end) {
        return start != null && end != null ? todo.createdAt.between(start, end) : null;
    }

    private BooleanExpression managerNicknameEq(String nickname) {
        return nickname != null ? manager.user.nickname.contains(nickname) : null;
    }
}
