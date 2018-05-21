create database if not exists timetrack;

use timetrack;

create table if not exists users(
	id int not null auto_increment,
	name varchar(255),
	password varchar(512),
	salt varchar(512),
	addedon date
	
)

