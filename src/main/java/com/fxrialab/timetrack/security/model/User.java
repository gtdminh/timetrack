package com.fxrialab.timetrack.security.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Created by Minh T. on 5/18/2018.
 */
@Entity
@Table(name = "users")
public class User extends AbstractPersistable<Long> {

    public enum USER_STATUS{
        USER_CREATED, USER_EMAIL_VERIFIED, USER_LOCKED, USER_ACTIVE;

        public static USER_STATUS parse(final String status){
            for(final USER_STATUS s : USER_STATUS.values()){
                if(s.toString() == status) return s;
            }

            return null;
        }
    }


    private String username;
    private String password;

    @javax.validation.constraints.Email
    private String email;
    private String role= "USER";
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String salt;
    @Column(name = "status")
    private String status = USER_STATUS.USER_CREATED.toString();

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public USER_STATUS getStatus() {
        return USER_STATUS.parse(status);
    }

    public void setStatus(USER_STATUS status) {
        this.status = status.toString();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
