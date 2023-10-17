package com.jhecohe.solvedex.blog.api.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhecohe.solvedex.blog.api.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username);
}
