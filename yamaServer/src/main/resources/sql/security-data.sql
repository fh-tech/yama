INSERT INTO user (username, password, enabled)
VALUES
('reader', '$2y$12$uw.U7N/3d0o6s9czGFG9PuKSUBs4At/H05K3vUZaxMVHunxsPGTOK', true),
('writer', '$2y$12$uw.U7N/3d0o6s9czGFG9PuKSUBs4At/H05K3vUZaxMVHunxsPGTOK', true),
('other', '$2y$12$uw.U7N/3d0o6s9czGFG9PuKSUBs4At/H05K3vUZaxMVHunxsPGTOK', true);

INSERT INTO role (rolename)
VALUES
('MSRead'),
('MSWrite'),
('MSOther');


INSERT INTO user_role (user_id, role_id)
VALUES
((SELECT id FROM user WHERE username = 'reader'), (SELECT id FROM role WHERE rolename = 'MSRead')),
((SELECT id FROM user WHERE username = 'writer'), (SELECT id FROM role WHERE rolename = 'MSWrite')),
((SELECT id FROM user WHERE username = 'other'), (SELECT id FROM role WHERE rolename = 'MSOther'));


SELECT username, rolename FROM
(user JOIN user_role ur on user.id = ur.user_id) JOIN role on role_id = role.id