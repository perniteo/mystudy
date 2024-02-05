-- DDL (Data Definition Language)

create table delivers(
deliver_no int primary key auto_increment,
title varchar(255) not null,
carrierName varchar(255) not null,
trackId varchar(255) not null,
detail json not null
-- detail -> detailTrack
);


CREATE TABLE detailTrack (
    detailTrack_id INT PRIMARY KEY AUTO_INCREMENT,
    deliver_no INT,
    status VARCHAR(10) NOT NULL,
    content VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    time DATETIME NOT NULL,
    FOREIGN KEY (deliver_no) REFERENCES delivers(deliver_no)
);
