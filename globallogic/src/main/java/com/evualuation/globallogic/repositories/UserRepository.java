package com.evualuation.globallogic.repositories;

import com.evualuation.globallogic.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


}
