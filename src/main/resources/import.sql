INSERT INTO Role (id, scope) VALUES (nextval('Role_SEQ'), 'Administrator');
INSERT INTO Role (id, scope) VALUES (nextval('Role_SEQ'), 'Organisator');
INSERT INTO Role (id, scope) VALUES (nextval('Role_SEQ'), 'Player');

INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'admin', 'admin', 'admin@tm.com');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'orga', 'orga', 'orga@gg.fr');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'adrien', 'pwd', 'a.b@mail.fr');
INSERT INTO Person (id, username, password, email) VALUES (nextval('Person_SEQ'), 'sara', 'pass', 's.b@mail.fr');

-- Admin
INSERT INTO Person_Roles (id_person, id_role) VALUES (1, 1);
INSERT INTO Person_Roles (id_person, id_role) VALUES (1, 2);
INSERT INTO Person_Roles (id_person, id_role) VALUES (1, 3);
-- Orga
INSERT INTO Person_Roles (id_person, id_role) VALUES (2, 2);
INSERT INTO Person_Roles (id_person, id_role) VALUES (2, 3);
-- Players
INSERT INTO Person_Roles (id_person, id_role) VALUES (3, 3);
INSERT INTO Person_Roles (id_person, id_role) VALUES (4, 3);