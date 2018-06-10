create schema if not exists timetrack;
set schema timetrack;

create table if not exists users(
	id VARCHAR(255) GENERATED always AS IDENTITY ,
	username varchar(255) NOT NULL UNIQUE ,
	password varchar(512) NOT NULL ,
	email varchar(512) NOT NULL UNIQUE ,
	role varchar(1024),
	salt varchar(512),
	status varchar(255)
	
)

