package com.fxrialab.timetrack.security.persistence.impl;

import com.fxrialab.timetrack.security.model.User;
import com.fxrialab.timetrack.security.persistence.UserService;
import com.fxrialab.timetrack.security.persistence.dao.IUserDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Minh T. on 6/1/2018.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private IUserDAO userDAO;


    @Override
    public @Nullable
    User findByUsernameOrEmail(String search) {
        return userDAO.findByUsernameOrEmail(search,search);
    }

    @Override
    public @Nullable User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public @Nullable User findById(String id) {
        Optional<User> res = userDAO.findById(id);
        return res.orElse(null);
    }

    @Override
    public List<User> findByRole(String role, Pageable page) {
        Page<User> res = userDAO.findByRole(role, page);

        return res.getContent();
    }

    @Override
    public List<User> getUsers(Pageable page) {
        Page<User> res = userDAO.findAll(page);

        return res.getContent();
    }

    @Override
    public void removeUser(User user) {
        userDAO.delete(user);
    }

    @Override
    public void removeUser(String id) {
        userDAO.deleteById(id);
    }

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public User create(User user) {
        return userDAO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsernameOrEmail(username,username);

        if(user == null) throw new UsernameNotFoundException("User " + username + " is not found");
        else {
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return user.getStatus() != User.USER_STATUS.USER_LOCKED;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return user.getStatus() == User.USER_STATUS.USER_ACTIVE;
                }
            };
        }
    }
}
