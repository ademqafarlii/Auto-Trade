package org.adem.autotrade.repository;

import org.adem.autotrade.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmailAndPassword(String email, String password);

    @EntityGraph(value = "user-graph")
    Optional<User> findByEmail(String email);

    void deleteByEmailAndPassword(String email, String password);

}
