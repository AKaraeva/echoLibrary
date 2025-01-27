insert into authors (id, account_created_at, birthdate, bio, email, firstname, lastname, nationality, password, username)
values (nextval('person_seq'), '2021-01-01', '1950-01-01', 'some text', 'kar220558@spg.at', 'Robert', 'Sapolsky', 'US', 'password', 'sapolsky');

insert into books(id, publication_date, description, genre, isbn, path, title)
values (nextval('books_seq'), '2022-01-01', 'some text', 'SF', '978-3-16-148410-0', 'path', 'Determined');