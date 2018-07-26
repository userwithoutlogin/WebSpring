create table   users(
id int unsigned not null auto_increment primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
user_name varchar(30) not null,
password varchar(50), 
birth_date date,
unique u_uname (user_name),
unique u_upass (password),
);

create table   roles(
id int unsigned not null auto_increment primary key,
role_name varchar(20) not null,
enabled tinyint not null );

create table user_role(
id int unsigned not null auto_increment primary key,
user_id int unsigned not null ,
role_id int unsigned not null ,
unique u_uid_rid(user_id,role_id),
constraint fk_uid foreign key(user_id) references users(id),
constraint fk_rid foreign key(role_id) references roles(id)

);