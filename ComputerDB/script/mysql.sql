CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

INSERT INTO users(username,password,enabled)
VALUES ('admincdb','$2a$04$8hkmsJdpZCNDRHhxrkfVM.7E8FyWU/bwJ5T8TbNzbIs6UAigtNMJW', true);
INSERT INTO users(username,password,enabled)
VALUES ('user','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO user_roles (username, role)
VALUES ('admincdb', 'ROLE_ADD');
INSERT INTO user_roles (username, role)
VALUES ('admincdb', 'ROLE_DEL');
INSERT INTO user_roles (username, role)
VALUES ('admincdb', 'ROLE_MOD');
INSERT INTO user_roles (username, role)
VALUES ('admincdb', 'ROLE_VIEW');
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_VIEW');