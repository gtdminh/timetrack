package com.fxrialab.timetrack.security.persistence.impl;

import com.fxrialab.timetrack.security.model.User;
import com.fxrialab.timetrack.security.persistence.UserService;
import com.fxrialab.timetrack.security.persistence.dao.IUserDAO;
import com.fxrialab.timetrack.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    public @Nullable User findById(Long id) {
        Optional<User> res = userDAO.findById(id);
        return res.orElse(null);
    }

    @Override
    public List<User> findByRole(String role, Pageable page) {
        Page<User> res = userDAO.findByRole(role, page);

        return res.getContent();
    }

    @Override
    public List<User> get(Pageable page) {
        Page<User> res = userDAO.findAll(page);

        return res.getContent();
    }

    @Override
    public void remove(User user) {
        userDAO.delete(user);
    }

    @Override
    public void remove(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User create(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if(user.getSalt() == ""){
            user.setSalt(SecurityUtils.generateSalt());
            user.setPassword(SecurityUtils.hashPassword(user.getPassword(), user.getSalt()));
        }

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
