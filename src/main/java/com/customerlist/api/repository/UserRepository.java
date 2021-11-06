package com.customerlist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.customerlist.api.entity.UserEntity;

@Component
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	@Query("SELECT t FROM UserEntity t WHERE t.username = ?1")
	UserEntity findByUsername(String username);
}
