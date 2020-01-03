INSERT INTO user (username, password, enabled)
VALUES
('reader', SHA2('123', 512), true),
('writer', SHA2('123', 512), true),
('other', SHA2('123', 512), true);

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