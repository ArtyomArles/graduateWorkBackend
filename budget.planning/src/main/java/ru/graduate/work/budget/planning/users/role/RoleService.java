package ru.graduate.work.budget.planning.users.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public List<Role> findByName(String name) {
        if (name != "" && name != null)
            return roleRepository.findByName(name, Sort.by("id").descending());
        return roleRepository.findAll(Sort.by("id").descending());
    }
}
