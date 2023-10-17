INSERT INTO "user" (username, name, password, role) VALUES ('administrator', 'admin istrator', '$2a$10$m/2OHMbkRRNwquz3.Z7.QObkKZw/0GKViCIGjv6Hgr6mbps2dQ6iW', 'ADMINISTRATOR');
INSERT INTO "user" (username, name, password, role) VALUES ('editor', 'edit tor', '$2a$10$wTqXldD4M5PObFX.1e0j1udyCgSRzoCk0Qau9cJz.iZTYgAj.jPw2', 'EDITOR');
INSERT INTO "user" (username, name, password, role) VALUES ('follower', 'fol lower', '$2a$10$YlpwQgpjs0jLDQokJG1iC.yVBmpCBmTe92b./m1tS23BXHonAS.Xy', 'FOLLOWER');

INSERT INTO post (title, content) VALUES ('Post number 1', 'This is the post number one');
INSERT INTO post (title, content) VALUES ('Post number 2', 'This is the post number two');
INSERT INTO post (title, content) VALUES ('Post number 3', 'This is the post number three');
INSERT INTO post (title, content) VALUES ('Post number 4', 'This is the post number four');
INSERT INTO post (title, content) VALUES ('Post number 5', 'This is the post number five');
INSERT INTO post (title, content) VALUES ('Post number 6', 'This is the post number six');

INSERT INTO comment (text, post_id) VALUES ('This is a comment for the post number one', 1);
INSERT INTO comment (text, post_id) VALUES ('This is a comment for the post number two', 2);
INSERT INTO comment (text, post_id) VALUES ('This is a comment for the post number three', 3);
INSERT INTO comment (text, post_id) VALUES ('This is a comment for the post number four', 4);
INSERT INTO comment (text, post_id) VALUES ('This is a comment for the post number five', 5);
INSERT INTO comment (text, post_id) VALUES ('This is a comment for the post number six', 6);