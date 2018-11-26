create database wage;

use wage;

-- 修改数据库排序规则，以适用中文字符
ALTER DATABASE wage
COLLATE Chinese_PRC_CI_AS

create table dept (
  d_id        int identity(1,1) not null primary key,
  d_name      varchar(20),
  d_father_id int,
  foreign key (d_father_id) references dept (d_id)
)

create table place(
  pl_id int identity(1,1) not null primary key ,
  pl_name varchar(20),
  pl_salary DECIMAL(9,2),
  pl_dept_id int,
  foreign key (pl_dept_id) references dept(d_id) on delete set null on update cascade
)

create table person(
  p_id int not null identity(1,1) primary key ,
  p_name varchar(20),
  p_salary DECIMAL(9,2),
  p_place_id int,
  foreign key (p_place_id) references place(pl_id) on delete set null on update cascade
)

create table bonus (
  b_id int not null identity(1,1) primary key,
  p_id int not null,
  rate float(53),
foreign key (p_id) references person (p_id) on delete cascade on update cascade
)
