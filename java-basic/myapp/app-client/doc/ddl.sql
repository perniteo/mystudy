-- DDL (Data Definition Language)

create table boards(
board_no int primary key auto_increment,
title varchar(255) not null,
content text not null,
writer varchar(30) not null,
created_date datetime null default now()
);

insert into boards(title,content,writer) values("제목", "내용", "작성자");