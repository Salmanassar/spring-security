
insert into Role (id, role) values (1, 'ROLE_USER');
insert into Role (id, role) values (2, 'ROLE_ADMIN');
insert into Role (id, role) values (3, 'ROLE_USER, ROLE_ADMIN');
insert into Role (id, role) values (1, 'ROLE_USER');
insert into Role (id, role) values (2, 'ROLE_ADMIN');
insert into Role (id, role) values (3, 'ROLE_ADMIN, ROLE_USER');

INSERT INTO user (id, calendar, firstName, lastName, email, password) VALUES (1,'10.01.11', 'Kesha','Williams', 'user@user.com','123');
INSERT INTO user (id, calendar, firstName, lastName, email, password) VALUES (2,'10.01.11', 'Jane', 'Doe', 'admin@admin.com','123');
INSERT INTO user (id, calendar, firstName, lastName, email, password) VALUES (3, '10.01.11', 'Bookings','Sergey', 'both@both.com','123');
INSERT INTO user (id, calendar, firstName, lastName, email, password) VALUES (4, '10.01.11', 'Search','AInvoices ', 'user@dolly.com','354');
INSERT INTO user (id, calendar, firstName, lastName, email, password) VALUES (5, '10.01.11', 'Audits','Idiot', 'tiffany@Stewart', '134');

insert into roles_access (id_user, role_id) values (1, 1);
insert into roles_access (id_user, role_id) values (2, 2);
insert into roles_access (id_user, role_id) values (3, 3);
insert into roles_access (id_user, role_id) values (4, 1);
insert into roles_access (id_user, role_id) values (5, 2);
insert into roles_access (id_user, role_id) values (6, 3);