package com.fxrialab.timetrack.security.persistence;

import com.fxrialab.timetrack.security.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Minh T. on 6/1/2018.
 */
public interface UserService extends UserDetailsService {
    public User findByUsernameOrEmail(String search);
    public User findByEmail(String email);
    public User findById(Long id);
    public List<User> findByRole(String role, Pageable page);
    public List<User> get(Pageable page);

    public void remove(User user);
    public void remove(Long id);

    public User save(User user);
    public User create(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
