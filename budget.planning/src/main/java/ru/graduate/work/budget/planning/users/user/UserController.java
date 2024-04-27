package ru.graduate.work.budget.planning.users.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/search")
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "login", required = false) String login) {
        List<User> users = new ArrayList<>();
        if (login != "" && login != null) {
            User user = userService.findByLogin(login);
            users.add(user);
        } else {
            users = userService.getAll();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        if (userService.findByLogin(user.getLogin()) == null) {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/edit")
    public ResponseEntity<User> save(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
