delete from users;
delete from user_roles;
delete from roles_permissions;
insert into users(username, password, password_salt) values('jack', '123456', null);
insert into user_roles(username, role_name) values('jack', 'role1');
insert into user_roles(username, role_name) values('jack', 'role2');
insert into roles_permissions(role_name, permission) values('role1', 'user1:*');
insert into roles_permissions(role_name, permission) values('role1', 'user2:*');
