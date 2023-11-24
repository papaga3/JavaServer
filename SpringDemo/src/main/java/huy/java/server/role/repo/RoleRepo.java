package huy.java.server.role.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import huy.java.server.role.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
