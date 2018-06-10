create table if not exists users(
	id identity,
	username varchar(255) NOT NULL UNIQUE ,
	password varchar(512) NOT NULL ,
	email varchar(512) NOT NULL UNIQUE ,
	role varchar(1024),
	salt varchar(512),
	status varchar(255)
);

create table if not exists roles(
	role varchar(255) NOT NULL UNIQUE,
	desc varchar(1024)
);
create table if not exists permissions(
	id IDENTITY,
	role_id varchar(255) not null ,
	name varchar(255) not null ,
	desc varchar(1024),
	access array
);
//password = pbkdf2('password',salt,100,32)
INSERT INTO PUBLIC.USERS (ID, USERNAME, PASSWORD, EMAIL, ROLE, SALT, STATUS) VALUES (1, 'gtdminh', 'jIxtvFZQ3U0oun0KXaQS6ZppFwke/xRxcKdgl/jKNls=', 'gtdminh@gmail.com', 'USER,ADMIN', 'D7bc@AjYle7h9Zhy', 'USER_ACTIVE');


INSERT INTO PUBLIC.ROLES (ROLE, DESC) VALUES ('USER', 'regular user');
INSERT INTO PUBLIC.ROLES (ROLE, DESC) VALUES ('ADMIN', 'administrator');

INSERT INTO PUBLIC.PERMISSIONS (ID, ROLE_ID, NAME, DESC, ACCESS) VALUES (1, 'USER', 'user_timesheet', 'access to timesheet app', '/admin/*');
INSERT INTO PUBLIC.PERMISSIONS (ID, ROLE_ID, NAME, DESC, ACCESS) VALUES (2, 'ADMIN', 'admin_app', 'admin access', '/timesheet');
