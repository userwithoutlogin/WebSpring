create table   users(
u_id int unsigned not null auto_increment primary key,
user_name varchar(30) not null ,
first_name varchar(20) not null,
last_name varchar(20) not null,
password varchar(50) not null, 
birth_date date,
enabled tinyint ,
photo blob ,
unique u_upass (password),
unique u_name (user_name),
);



create table roles(
    r_id int unsigned not null auto_increment primary key,
    role_name varchar(20) not null,
    unique u_rname(role_name)
);


create table users_roles(
    r_id int  unsigned not null auto_increment primary key,
    user_id int not null,
    role_id int not null,
    

);