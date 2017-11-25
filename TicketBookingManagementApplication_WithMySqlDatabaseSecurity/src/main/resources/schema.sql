drop table if exists topics;
drop table if exists users;

create table user_details (
        user_id integer not null auto_increment,
        user_name varchar(100) not null,
        password varchar(300) not null,
        role_assigned varchar(50) not null,
        primary key (user_id)
    );
    
  
