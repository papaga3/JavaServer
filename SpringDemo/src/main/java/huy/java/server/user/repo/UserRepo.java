package huy.java.server.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import huy.java.server.user.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
