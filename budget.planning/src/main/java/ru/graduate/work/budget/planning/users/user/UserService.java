package ru.graduate.work.budget.planning.users.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.graduate.work.budget.planning.users.role.Role;
import ru.graduate.work.budget.planning.users.role.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll() {
        return userRepository.findAll(Sort.by("id").descending()).stream().filter(user -> user.getStatus() != Status.DELETED).collect(Collectors.toList());
    }

    public List<User> findByLogin(String login) {
        if (login != "" && login != null)
            return userRepository.findByLogin(login, Sort.by("id").descending()).stream().filter(user -> user.getStatus() != Status.DELETED).collect(Collectors.toList());
        return this.getAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        User user = this.findById(id);
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }

    public void save(User user, boolean isEdit) {
        Role role = roleRepository.findByName("ROLE_USER", Sort.by("id").descending());
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() != null) {
            roles.addAll(user.getRoles());
        }
        roles.add(role);
        if (!isEdit)
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        userRepository.save(registeredUser);
    }
}
