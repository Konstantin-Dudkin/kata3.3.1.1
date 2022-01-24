package testgroup.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testgroup.web.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
