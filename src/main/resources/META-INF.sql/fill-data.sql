insert into users(first_name,last_name,user_name,password,birth_date,enabled) values
('Nick','Johnson','someuser','pass123','1999-1-1',1),
('Jack','Worsnope','user','user','1989-11-12',1),
('Stan','Bronson','nnn','pss567','1979-11-12',1);

insert into roles(role_name) values
('ROLE_USER'), 
('ROLE_ADMIN'), 
('ROLE_DISABLED');

insert into users_roles(user_id,role_id) values
(1,1), 
(2,2),
(2,1),
 (3,2),
 (3,3);