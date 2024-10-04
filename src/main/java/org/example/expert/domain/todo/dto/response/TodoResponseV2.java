package org.example.expert.domain.todo.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoResponseV2 {
    private String title;
    private long managerCnt;
    private long commentsCnt;

    @QueryProjection
    public TodoResponseV2(String title, long managerCnt, long commentsCnt) {
        this.title = title;
        this.managerCnt = managerCnt;
        this.commentsCnt = commentsCnt;
    }
}
