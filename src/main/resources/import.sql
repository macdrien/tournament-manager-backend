INSERT INTO Permission (id, permission_name) VALUES (nextval('Permission_SEQ'), 'read.all');
INSERT INTO Permission (id, permission_name) VALUES (nextval('Permission_SEQ'), 'write.all');
INSERT INTO Permission (id, permission_name) VALUES (nextval('Permission_SEQ'), 'delete.all');

INSERT INTO Permission (id, permission_name) VALUES (nextval('Permission_SEQ'), 'read.role');
INSERT INTO Permission (id, permission_name) VALUES (nextval('Permission_SEQ'), 'write.role');
INSERT INTO Permission (id, permission_name) VALUES (nextval('Permission_SEQ'), 'delete.role');

INSERT INTO Role (id, role_name) VALUES (nextval('Role_SEQ'), 'Administrator');
INSERT INTO Role (id, role_name) VALUES (nextval('Role_SEQ'), 'Organisator');
INSERT INTO Role (id, role_name) VALUES (nextval('Role_SEQ'), 'Player');

INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (1, 1);
INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (1, 2);
INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (1, 3);

INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (2, 4);
INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (2, 5);
INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (2, 6);

INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (3, 7);
INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (3, 8);
INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (3, 9);

INSERT INTO Roles_Permissions (id_role, id_permission) VALUES (4, 7);

INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'admin', 'admin', 'admin@tm.com');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'orga', 'orga', 'orga@gg.fr');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'adrien', 'pwd', 'a.b@mail.fr');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'sara', 'pass', 's.b@mail.fr');

-- Admin
INSERT INTO Person_Roles (id_person, id_role) VALUES (1, 1);
-- Orga
INSERT INTO Person_Roles (id_person, id_role) VALUES (2, 2);
-- Players
INSERT INTO Person_Roles (id_person, id_role) VALUES (3, 3);
INSERT INTO Person_Roles (id_person, id_role) VALUES (4, 3);