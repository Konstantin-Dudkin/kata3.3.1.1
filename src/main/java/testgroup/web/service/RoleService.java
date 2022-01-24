package testgroup.web.service;

import testgroup.web.model.Role;

import java.util.List;

public interface RoleService {
    Iterable<Role> findAll();
    void save(Role role);
    void delete(Role role);
    Role findById(Long id);
    Iterable<Role> findAllById (List<Long> list);
}
