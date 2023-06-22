package itmasters.project.stem.repository;

import itmasters.project.stem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}