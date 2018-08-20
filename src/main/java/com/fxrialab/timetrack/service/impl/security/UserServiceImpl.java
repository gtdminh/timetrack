package com.fxrialab.timetrack.service.impl.security;

import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;
import com.fxrialab.timetrack.service.intf.security.UserService;
import com.fxrialab.timetrack.dao.security.IUserDAO;
import com.fxrialab.timetrack.utils.SecurityUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

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
    public boolean registerConfirm(String code) throws ServiceException{
        User user = userDAO.findByActivationCode(code);
        if (user == null){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.NO_CONFIRMATION_CODE);
        }
        else if (!User.USER_STATUS.USER_EMAIL_VERIFYING.equals(user.getStatus())){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.NO_WAITING_FOR_CONFIRMATION);
        }
        user.setStatus(User.USER_STATUS.USER_ACTIVE);
        userDAO.save(user);
        return true;
    }

    @Override
    public User checkUserWithActivationCode(String code) throws ServiceException{
        User user = userDAO.findByActivationCode(code);
        if (user == null){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.NO_CONFIRMATION_CODE);
        }
        else if (!User.USER_STATUS.USER_EMAIL_VERIFYING.equals(user.getStatus())){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.NO_WAITING_FOR_CONFIRMATION);
        }

        return user;
    }

    @Override
    public User registerNameAndPassword(String code,String fullname, String password) throws ServiceException {
        User user = userDAO.findByActivationCode(code);
        if (user == null){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.NO_CONFIRMATION_CODE);
        }
        else if (!User.USER_STATUS.USER_EMAIL_VERIFYING.equals(user.getStatus())){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.NO_WAITING_FOR_CONFIRMATION);
        }
        user.setFullname(fullname);
        user.setPassword(password);
        user.setStatus(User.USER_STATUS.USER_ACTIVE);
        userDAO.save(user);

        return user;
    }

    @Override
    public @Nullable
    User registerNewUser(String email) throws ServiceException {


        User existingUser = userDAO.findByUsernameOrEmail(email,email);
        if (existingUser != null){
            throw new ServiceException(ServiceException.SERVICE_EXCEPTION_CODE.USER_EXISTING);
        }
        else{
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setStatus(User.USER_STATUS.USER_EMAIL_VERIFYING);
            newUser.setActivationCode(UUID.randomUUID().toString());
            newUser.setCreatedon(new Date());
            userDAO.save(newUser);

            // send mail code here

            return newUser;
        }


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
