package com.rhein.iam.authenticator.infrastructure;

import com.rhein.iam.authenticator.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
