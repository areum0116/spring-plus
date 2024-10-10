package org.example.expert.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        try {
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            return new ResponseEntity<>("Server and DB are running", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Server is running, but DB connection failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

