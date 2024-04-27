package ru.graduate.work.budget.planning.users.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
public class RoleController {
    private final RoleService roleService;
    @GetMapping("/search")
    public ResponseEntity<List<Role>> roleInfo(@RequestParam(name = "name", required = false) String name) {
        List<Role> roles = roleService.findByName(name);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
