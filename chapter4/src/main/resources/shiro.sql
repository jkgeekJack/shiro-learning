drop database if exists shiro;
create database shiro;
use shiro;

create table users (
  id bigint auto_increment,
  username varchar(100),
  password varchar(100),
  password_salt varchar(100),
  constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_users_username on users(username);

create table user_roles(
  id bigint auto_increment,
  username varchar(100),
  role_name varchar(100),
  constraint pk_user_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_user_roles on user_roles(username, role_name);

create table roles_permissions(
  id bigint auto_increment,
  role_name varchar(100),
  permission varchar(100),
  constraint pk_roles_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_roles_permissions on roles_permissions(role_name, permission);

insert into users(username, password, password_salt) values('jack', 'fc1709d0a95a6be30bc5926fdb7f22f4', 'jack');
insert into user_roles(username, role_name) values('jack', 'role1');
insert into user_roles(username, role_name) values('jack', 'role2');
insert into roles_permissions(role_name, permission) values('role1', 'user1:*');
insert into roles_permissions(role_name, permission) values('role1', 'user2:*');
insert into roles_permissions(role_name, permission) values('role2', 'user3:*');
