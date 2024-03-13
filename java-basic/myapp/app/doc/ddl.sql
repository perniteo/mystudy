-- DDL (Data Definition Language)

drop table if exists boards restrict;
drop table if exists board_files restrict;
drop table if exists assignments restrict;
drop table if exists members restrict;


숙소 (
숙소 번호, 숙소 이름, 위치, 호스트, 가격(1박), 날짜, 평점, 숙소 옵션,
)
게시글 (
게시글 번호, 제목, 내용, 첨부파일, 작성자, 조회 수, 추천 수, 분류, 댓글 수
)

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
writer int not null,
category int not null,
created_date datetime null default now()
);

create table board_files(
file_no int not null,
file_path varchar(255) not null,
board_no int not null
);

alter table board_files
add constraint primary key (file_no),
modify column file_no int not null auto_increment,
add constraint board_files_fk foreign key (board_no) references boards(board_no);


create table members(
member_no int primary key auto_increment,
email varchar(255) not null,
name varchar(255) not null,
password varchar(100) not null,
join_date datetime null default now()
);

alter table members
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;
  add constraint members_uk unique (email);
  add column photo varchar(255) null;

alter table boards
  add constraint boards_fk foreign key (writer) references members(member_no);