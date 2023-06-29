package itmasters.project.stem.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    @Query("select u.id from _user as u where u.username = :username")
    Integer findIdByUsername(String username);
    @Query("select u from _user as u where u.id = :userId")
    User findUserByUserId(Integer userId);
}