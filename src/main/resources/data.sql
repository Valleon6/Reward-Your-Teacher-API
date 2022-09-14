drop table if exists RYTuserdb;

create table RYTuserdb(
    id INT auto_increment primary key,
    firstName varchar(250) not null,
    lastName varchar(250) not null,
    email varchar(250) not null,
    school varchar(250) not null
);
insert into RYTuserdb (firstName, lastName, email, school)
    VALUES ('Veronica', 'Osakwe','Osakwephysics@gmail.com', 'F.S.T.C Awka');