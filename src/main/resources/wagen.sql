create database wagen

use wagen

-- 修改数据库排序规则，以适用中文字符
ALTER DATABASE wagen
COLLATE Chinese_PRC_CI_AS

--部门表
create table dept
(
  d_id   int identity (1, 1) not null primary key,
  d_name varchar(20)
)

--职位表
create table place
(
  pl_id      int identity (1, 1) not null primary key,
  pl_name    varchar(20),
  pl_salary  DECIMAL(9, 2),
  pl_dept_id int references dept (d_id) on delete cascade on update cascade
)

--员工表
create table person
(
  p_id       int identity (1, 1) not null primary key,
  p_name     varchar(20),
  p_sex      varchar(4),
  p_age      int,
  p_time     datetime,
  p_born     datetime,
  p_pass     varchar(20),
  pl_id      int references place (pl_id) on delete cascade on update cascade
)

--工资表
create table salary
(
  sa_id       int identity (1, 1) not null primary key,
  p_id        int references person (p_id) on delete cascade on update cascade,
  base_salary DECIMAL(9, 2),
  overtime_salary DECIMAL(9,2),
  cut_salary DECIMAL(9,2),
  final_salary DECIMAL(9,2),
  record_date datetime
);

--月底奖惩表
create table reward
(
  id int identity (1,1) not null primary key ,
  p_id int references person(p_id) on delete cascade on update cascade,
  ab_days int,
  ov_days int,
  record_date datetime
)
