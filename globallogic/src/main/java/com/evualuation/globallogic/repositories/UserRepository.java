package com.evualuation.globallogic.repositories;

import com.evualuation.globallogic.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u from User u where u.email = :email")
    Optional<User> findUserByEmail (@Param("email") String email);

}
