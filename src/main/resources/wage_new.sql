create database wageNew;

use wageNew;

-- 修改数据库排序规则，以适用中文字符
ALTER DATABASE wageNew
COLLATE Chinese_PRC_CI_AS

--部门表
create table dept(
  d_id int identity (1, 1) not null primary key ,
  d_name varchar (20)
)

--职位表
create table place(
  pl_id int identity (1, 1) not null primary key ,
  pl_name varchar (20),
  pl_salary DECIMAL (9, 2),
  pl_dept_id int references dept(d_id) on delete set null on update cascade
)

--员工表
create table person
(
  p_id   int identity (1, 1) not null primary key,
  p_name varchar(20),
  p_sex  varchar(4),
  p_age int,
  p_time datetime,
  p_born datetime,
  p_pass varchar(20),
  pl_id  int references place (pl_id) on delete set null on update cascade,
  p_onduty float(53),
  p_overtime float(53)
)

--工资表
create table salary(
  sa_id int identity (1, 1) not null primary key ,
  p_id int references person(p_id) on delete set null on update cascade ,
  salary DECIMAL (9, 2)
)