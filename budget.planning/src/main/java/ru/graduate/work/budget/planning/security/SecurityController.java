package ru.graduate.work.budget.planning.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SecurityController {
    @GetMapping(value = "/")
    public ResponseEntity<?> getAuth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
