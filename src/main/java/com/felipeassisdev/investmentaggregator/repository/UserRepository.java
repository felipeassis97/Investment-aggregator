package com.felipeassisdev.investmentaggregator.repository;

import com.felipeassisdev.investmentaggregator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
