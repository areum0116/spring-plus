package org.example.expert.domain.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Log extends Timestamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long todoId;
    private String url;
    private String method;
    private String ipAddress;
    private String result;

    public Log(Long userId, Long todoId, String url, String method, String ipAddress, String result) {
        this.userId = userId;
        this.todoId = todoId;
        this.url = url;
        this.method = method;
        this.ipAddress = ipAddress;
        this.result = result;
    }
}
