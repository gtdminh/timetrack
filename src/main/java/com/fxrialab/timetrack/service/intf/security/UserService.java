package com.fxrialab.timetrack.service.intf.security;

import com.fxrialab.timetrack.common.ServiceException;
import com.fxrialab.timetrack.model.security.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;

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
    //public boolean registerConfirm(String code) throws ServiceException;
    public User checkUserWithActivationCode(String code) throws ServiceException;
    public User registerNewUser(String email) throws ServiceException;
    public User registerUserInformation(String code, String fullname, String password, String managingType, String projectType
            ,String companyName, String conpanySize) throws ServiceException;
    public List<User> findByRole(String role, Pageable page);
    public List<User> get(Pageable page);

    public void remove(User user);
    public void remove(Long id);

    public User save(User user);
    public User create(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
