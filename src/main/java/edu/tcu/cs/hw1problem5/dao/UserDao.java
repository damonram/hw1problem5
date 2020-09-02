package edu.tcu.cs.hw1problem5.dao;

import edu.tcu.cs.hw1problem5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
}
