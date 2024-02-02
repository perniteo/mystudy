-- DDL (Data Definition Language)

create table delivers(
deliver_no int primary key auto_increment,
title varchar(255) not null,
carrierName varchar(255) not null,
trackId varchar(255) not null,
detail json not null
);
