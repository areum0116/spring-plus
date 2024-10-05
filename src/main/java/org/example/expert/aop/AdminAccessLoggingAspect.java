package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.expert.domain.common.service.LogService;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminAccessLoggingAspect {

    private final HttpServletRequest request;

    private final LogService logService;

    @Before("execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    public void logAfterChangeUserRole(JoinPoint joinPoint) {
        String userId = String.valueOf(request.getAttribute("userId"));
        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        log.info("Admin Access Log - User ID: {}, Request Time: {}, Request URL: {}, Method: {}",
                userId, requestTime, requestUrl, joinPoint.getSignature().getName());
    }

    private Long getUserId(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if(arg instanceof ManagerSaveRequest managerSaveRequest) {
                return managerSaveRequest.getManagerUserId();
            }
        }
        return null;
    }

    private Long getTodoId(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Annotation[][] annotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations();

        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if (annotation instanceof PathVariable) {
                    return (Long) args[i];
                }
            }
        }
        return null;
    }

    @AfterReturning(pointcut = "execution(* org.example.expert.domain.manager.controller.ManagerController.saveManager(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        Long userId = getUserId(joinPoint);
        Long todoId = getTodoId(joinPoint);

        logService.saveLog(userId, todoId, request.getRequestURI(), request.getMethod(), request.getRemoteAddr(), "SUCCESS");
    }

    @AfterThrowing(pointcut = "execution(* org.example.expert.domain.manager.controller.ManagerController.saveManager(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        Long userId = getUserId(joinPoint);
        Long todoId = getTodoId(joinPoint);

        logService.saveLog(userId, todoId, request.getRequestURI(), request.getMethod(), request.getRemoteAddr(), "FAILURE");
    }
}
