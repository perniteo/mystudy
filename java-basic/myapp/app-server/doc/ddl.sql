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

--alter table boards add column category int not null;
--update boards set category = 1;

create table members(
member_no int primary key auto_increment,
email varchar(255) not null,
name varchar(255) not null,
password varchar(100) not null,
join_date datetime null default now()
);

select b.board_no, b.title, b.writer, b.created_date, count(file_no) as fileCount
from boards b
left join board_files f
on b.board_no = f.board_no
where b.category = 1
group by board_no
order by board_no desc;