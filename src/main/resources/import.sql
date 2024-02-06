INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'read.all');
INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'write.all');
INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'delete.all');

INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'read.event');
INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'write.event');
INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'delete.event');

INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'read.team');
INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'write.team');
INSERT INTO Scope (id, scope_name) VALUES (nextval('Scope_SEQ'), 'delete.team');

INSERT INTO Role (id, role_name) VALUES (nextval('Role_SEQ'), 'Administrator');
INSERT INTO Role (id, role_name) VALUES (nextval('Role_SEQ'), 'Organisator');
INSERT INTO Role (id, role_name) VALUES (nextval('Role_SEQ'), 'Team leader');
INSERT INTO Role (id, role_name) VALUES (nextval('Role_SEQ'), 'Player');

INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (1, 1);
INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (1, 2);
INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (1, 3);

INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (2, 4);
INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (2, 5);
INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (2, 6);

INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (3, 7);
INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (3, 8);
INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (3, 9);

INSERT INTO Roles_Scopes (id_role, id_scope) VALUES (4, 7);

INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'admin', 'admin', 'admin@tm.com');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'orga', 'orga', 'orga@gg.fr');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'adrien', 'pwd', 'a.b@mail.fr');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'sara', 'pass', 's.b@mail.fr');

-- Admin
INSERT INTO Person_Roles (id_person, id_role) VALUES (1, 1);
-- Orga
INSERT INTO Person_Roles (id_person, id_role) VALUES (2, 2);
-- Players
INSERT INTO Person_Roles (id_person, id_role) VALUES (3, 4);
INSERT INTO Person_Roles (id_person, id_role) VALUES (4, 4);