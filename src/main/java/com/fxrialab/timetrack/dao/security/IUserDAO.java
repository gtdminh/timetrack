package com.fxrialab.timetrack.dao.security;

import com.fxrialab.timetrack.model.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Created by Minh T. on 5/25/2018.
 */

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {
    public @Nullable User findByUsernameAndPassword(@Param("username") String userName,@Param("password") String hash);
    public @Nullable User findByUsernameOrEmail(String username, String email);
    public @Nullable User findByEmail(String email);
    public @Nullable User findByActivationCode(String activationCode);
    public Page<User> findByRole(String role, Pageable page);

}
