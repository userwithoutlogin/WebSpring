insert into users(first_name,last_name,user_name,password,birth_date) values
('Nick','Johnson','someuser','pass123','1999-1-1'),
('Jack','Worsnope','sameuser','pass567','1989-11-12');

insert into roles(role_name,enabled) values
('ROLE_USER',1), 
('ROLE_ADMIN',1), 
('ROLE_DISABLED',0); 

insert into user_role(user_id,role_id) values
(1,1),
(1,2),
(1,3),
(2,1);