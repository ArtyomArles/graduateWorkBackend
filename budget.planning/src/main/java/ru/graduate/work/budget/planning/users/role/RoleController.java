package ru.graduate.work.budget.planning.users.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
public class RoleController {
    private final RoleService roleService;
    @GetMapping("/search")
    public ResponseEntity<Role> roleInfo(@RequestParam(name = "name", required = false) String name) {
        Role role = roleService.findByName(name);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
