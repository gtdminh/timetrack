package com.fxrialab.timetrack.dao;

import com.fxrialab.timetrack.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Minh T. on 5/25/2018.
 */
@Repository
public interface UserDAO extends CrudRepository<User, String> {
    public User findByUsernameAndPassword(@Param("username") String userName,@Param("password") String hash);
    public User findByEmailOrUsername(String email);
}
