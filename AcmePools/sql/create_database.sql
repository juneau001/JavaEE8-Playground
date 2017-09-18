-- *****************************************************
-- * Simple database model for AcmePools application.
-- *
-- * Author:  J. Juneau
-- * Description:  Ready for summer!  Run this SQL in
-- *   your favorite Apache derby schema
-- *****************************************************

create table pool_customer(
    id              int primary key,
    pool_id         int,
    customer_id     int);


create table job (
    id              int primary key,
    customer_id     int,
    description     clob,
    est_hours       float,
    cost            numeric,
    work_date        date);


create table pool (
    id              int primary key,
    style           varchar(10),
    shape           varchar(10),
    length          float,
    width           float,
    radius          float,
    gallons         float);


-- Add support for data export
create table column_model(
id                  int primary key,
column_name         varchar(30),
column_label        varchar(150));


insert into column_model values(
1,
'addressline1',
'Address Line 1');

insert into column_model values(
2,
'addressline2',
'Address Line 2');

insert into column_model values(
3,
'city',
'City');

insert into column_model values(
4,
'creditLimit',
'Credit Limit');

insert into column_model values(
5,
'customerId',
'Customer Id');

insert into column_model values(
6,
'discountCode',
'Discount Code');

insert into column_model values(
7,
'email',
'Email');

insert into column_model values(
8,
'fax',
'Fax');

insert into column_model values(
9,
'name',
'Name');

insert into column_model values(
10,
'phone',
'Phone');

insert into column_model values(
11,
'state',
'State');

insert into column_model values(
12,
'zip',
'Zip');

alter table pool_customer
add constraint pool_customer_fk
foreign key (pool_id) references pool(id);

alter table pool_customer
add constraint pool_customer_fk2
foreign key (customer_id) references customer(customer_id);

alter table job
add constraint job_fk
foreign key (customer_id) references pool_customer(id);

insert into pool values(
1,
'ABOVE',
'ROUND',
0,
0,
24,
61072.56);

insert into pool values(
2,
'INGROUND',
'ROUND',
32,
16,
0,
23040);

insert into pool_customer values(
1,
1,
1);

insert into pool_customer values(
2,
2,
2);

-- Uncomment the following to create database sequences if using Apache Derby 10.6+
-- create sequence pool_cust_s
-- start with 100;
-- 
-- create sequence job_s
-- start with 100;
-- 
-- 
-- create sequence pool_s
-- start with 100;
-- 
-- create sequence column_model_s
-- start with 100;



