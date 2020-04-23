INSERT INTO book (title) VALUES ('The Pragmatic Programmer');
INSERT INTO member (name) VALUES ('Gerard Fabrego');
INSERT INTO checkout_item (member_id, book_id) VALUES ((SELECT id FROM member WHERE name = 'Gerard Fabrego'), (SELECT id FROM book WHERE title = 'The Pragmatic Programmer')); 