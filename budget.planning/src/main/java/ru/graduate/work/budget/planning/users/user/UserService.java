package ru.graduate.work.budget.planning.users.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.graduate.work.budget.planning.users.role.Role;
import ru.graduate.work.budget.planning.users.role.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void save(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        userRepository.save(registeredUser);
    }
}
