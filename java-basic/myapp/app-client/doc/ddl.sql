-- DDL (Data Definition Language)

create table assignments(
assignment_no int primary key auto_increment,
title varchar(255) not null,
content text not null,
deadline date not null
);

create table boards(
board_no int primary key auto_increment,
title varchar(255) not null,
content text not null,
writer varchar(30) not null,
created_date datetime null default now()
);

alter table boards add column category int not null;

update boards set category = 1;

create table members(
member_no int primary key auto_increment,
email varchar(255) not null,
name varchar(255) not null,
password varchar(100) not null,
join_date datetime null default now()
);

