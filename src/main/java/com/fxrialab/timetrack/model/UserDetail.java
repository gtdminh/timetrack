package com.fxrialab.timetrack.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * Created by Minh T. on 6/11/2018.
 */
@Entity(name="user_details")
public class UserDetail extends AbstractPersistable<Long> {
}
