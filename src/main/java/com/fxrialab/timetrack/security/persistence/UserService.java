package com.fxrialab.timetrack.security.persistence;

import com.fxrialab.timetrack.security.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by Minh T. on 6/1/2018.
 */
public interface UserService extends UserDetailsService {
    public User findByUsernameOrEmail(String search);
    public User findByEmail(String email);
    public User findById(String id);
    public List<User> findByRole(String role, Pageable page);
    public List<User> getUsers(Pageable page);

    public void removeUser(User user);
    public void removeUser(String id);

    public User saveUser(User user);
    public User create(User user);
}
