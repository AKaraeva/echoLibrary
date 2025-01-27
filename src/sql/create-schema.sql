create sequence books_seq start with 1 increment by 50;
create sequence person_seq start with 1 increment by 50;
create sequence reviews_to_books_seq start with 1 increment by 50;
create sequence shelves_seq start with 1 increment by 50;

create table authors
(
    account_created_at timestamp(6),
    birthdate timestamp(6),
    id bigint not null,
    bio varchar(255),
    email varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    nationality enum ('AT', 'FR', 'DE', 'JP', 'KG', 'RU', 'US') not null,
    password varchar(255),
    username varchar(255), primary key (id)
);
create table authors_written_books
(
    authors_id bigint not null,
    written_books_id bigint not null
);
create table books
(
    publication_date date,
    id bigint not null,
    description varchar(255),
    genre enum ('SF', 'F', 'MY', 'TH', 'R', 'HFI', 'B', 'SH', 'H', 'SCI'),
    isbn varchar(255) not null,
    path varchar(255),
    title varchar(255) not null,
    primary key (id)
);
create table person
(
    account_created_at timestamp(6),
    birthdate timestamp(6),
    id bigint not null,
    email varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);
create table readers
(
    account_created_at timestamp(6),
    birthdate timestamp(6),
    id bigint not null,
    email varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);
create table reviews_to_books
(
    rating integer not null,
    book_id bigint not null,
    date timestamp(6),
    id bigint not null,
    reader_id bigint not null,
    review_text varchar(255),
    primary key (id)
);
create table shelf_books
(
    books_id bigint not null,
    shelves_id bigint not null
);
create table shelves
(
    category enum ('W', 'C', 'R') not null,
    id bigint not null,
    reader_id bigint,
    primary key (id)
);
alter table if exists authors_written_books add constraint fk_bookId foreign key (written_books_id) references books;
alter table if exists authors_written_books add constraint fk_author_id foreign key (authors_id) references authors;
alter table if exists reviews_to_books add constraint fk_book_id foreign key (book_id) references books;
alter table if exists reviews_to_books add constraint fk_reader_id foreign key (reader_id) references readers;
alter table if exists shelf_books add constraint fk_shelf_books_book_id foreign key (books_id) references books;
alter table if exists shelf_books add constraint fk_shelf_id foreign key (shelves_id) references shelves;
alter table if exists shelves add constraint fk_readerId foreign key (reader_id) references readers;