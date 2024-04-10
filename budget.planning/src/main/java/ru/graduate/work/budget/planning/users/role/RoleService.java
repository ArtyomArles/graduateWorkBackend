package ru.graduate.work.budget.planning.users.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findByName(String name) {
        if (name != "" && name != null)
            return roleRepository.findByName(name);
        return null;
    }
}
