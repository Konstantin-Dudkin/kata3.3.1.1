package testgroup.web.service;

import testgroup.web.model.User;

public interface UserService {
    Iterable<User> findAll();
    void save(User user);
    void delete(User user);
    User findById(Long id);
    User getUserByUsername (String username);
}
