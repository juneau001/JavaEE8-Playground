-- *****************************************************
-- * Simple database model for AcmePools application.
-- * 
-- * Author:  J. Juneau
-- * Description:  Ready for summer!  Run this SQL in
-- *   your favorite Apache derby schema if you are
-- *   not using the NetBeans sample database
-- *****************************************************

CREATE TABLE CUSTOMER (
CUSTOMER_ID INTEGER NOT NULL,
DISCOUNT_CODE CHAR(1) NOT NULL,
ZIP VARCHAR(10) NOT NULL,
"NAME" VARCHAR(30),
ADDRESSLINE1 VARCHAR(30),
ADDRESSLINE2 VARCHAR(30),
CITY VARCHAR(25),
"STATE" CHAR(2),
PHONE CHAR(12),
FAX CHAR(12),
EMAIL VARCHAR(40),
CREDIT_LIMIT INTEGER,
PRIMARY KEY (CUSTOMER_ID));

INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (1, 'N', '95117', 'Jumbo Eagle Corp', '111 E. Las Olivas Blvd', 'Suite 51', 'Fort Lauderdale', 'FL', '305-555-0188', '305-555-0189', 'jumboeagle@example.com', 100000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (2, 'M', '95035', 'New Enterprises', '9754 Main Street USA', 'P.O. Box 567', 'Miami', 'FL', '305-555-0148', '305-555-0149', 'www.new.example.com', 50000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (25, 'M', '85638', 'Wren Computers', '8989 Red Albatross Drive', 'Suite 9897', 'Houston', 'TX', '214-555-0133', '214-555-0134', 'www.wrencomp.example.com', 25000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (3, 'L', '12347', 'Small Bill Company', '8585 South Upper Murray Drive', 'P.O. Box 456', 'Alanta', 'GA', '555-555-0175', '555-555-0176', 'www.smallbill.example.com', 90000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (36, 'H', '94401', 'Bob Hosting Corp.', '65653 Lake Road', 'Suite 2323', 'San Mateo', 'CA', '650-555-0160', '650-555-0161', 'www.bobhostcorp.example.com', 65000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (106, 'L', '95035', 'Early CentralComp', '829 E Flex Drive', 'Suite 853', 'San Jose', 'CA', '408-555-0157', '408-555-0150', 'www.centralcomp.example.com', 26500);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (149, 'L', '95117', 'John Valley Computers', '4381 Kelly Valley Ave', 'Suite 77', 'Santa Clara', 'CA', '408-555-0169', '408-555-0167', 'www.johnvalley.example.com', 70000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (863, 'N', '94401', 'Big Network Systems', '456 444th Street', 'Suite 45', 'Redwood City', 'CA', '650-555-0181', '650-555-0180', 'www.bignet.example.com', 25000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (777, 'L', '48128', 'West Valley Inc.', '88 Northsouth Drive', 'Building C', 'Dearborn', 'MI', '313-555-0172', '313-555-0171', 'www.westv.example.com', 100000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (753, 'H', '48128', 'Zed Motor Co', '2267 NE Michigan Ave', 'Building 21', 'Dearborn', 'MI', '313-555-0151', '313-555-0152', 'www.parts@ford.example.com', 5000000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (722, 'N', '48124', 'Big Car Parts', '52963 Notouter Dr', 'Suite 35', 'Detroit', 'MI', '313-555-0144', '313-555-0145', 'www.bparts.example.com', 50000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (409, 'L', '10095', 'Old Media Productions', '4400 527th Street', 'Suite 562', 'New York', 'NY', '212-555-0110', '212-555-0111', 'www.oldmedia.example.com', 10000);
INSERT INTO CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
	VALUES (410, 'M', '10096', 'Yankee Computer Repair Ltd', '9653 211th Ave', 'Floor 4', 'New York', 'NY', '212-555-0191', '212-555-0197', 'www.nycompltd@repair.example.com', 25000);

CREATE TABLE DISCOUNT_CODE (DISCOUNT_CODE CHAR(1) NOT NULL, RATE DECIMAL(4, 2), PRIMARY KEY (DISCOUNT_CODE));

INSERT INTO DISCOUNT_CODE (DISCOUNT_CODE, RATE) 
	VALUES ('H', 16.00);
INSERT INTO DISCOUNT_CODE (DISCOUNT_CODE, RATE) 
	VALUES ('L', 7.00);
INSERT INTO DISCOUNT_CODE (DISCOUNT_CODE, RATE) 
	VALUES ('M', 11.00);
INSERT INTO DISCOUNT_CODE (DISCOUNT_CODE, RATE) 
	VALUES ('N', 0.00);

CREATE TABLE MICRO_MARKET (ZIP_CODE VARCHAR(10) NOT NULL, RADIUS DOUBLE, AREA_LENGTH DOUBLE, AREA_WIDTH DOUBLE, PRIMARY KEY (ZIP_CODE));

INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('95051', 255.59, 689.856, 478.479);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('94043', 157.869, 385.821, 147.538);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('85638', 758.648, 328.963, 482.164);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('12347', 475.965, 385.849, 146.937);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('94401', 368.386, 285.848, 173.794);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('95035', 683.396, 472.859, 379.757);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('95117', 755.778, 547.967, 468.858);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('48128', 684.675, 475.854, 408.074);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('48124', 753.765, 487.664, 456.632);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('10095', 1987.854, 975.875, 865.681);
INSERT INTO MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) 
	VALUES ('10096', 1876.766, 955.666, 923.556);


create table pool_customer(
    id              int primary key,
    pool_id         int,
    customer_id     int);
    
create table job (
    id              int primary key,
    customer_id     int,
    description     clob,
    est_hours       float,
    cost            numeric);



create table pool (
    id              int primary key,
    style           varchar(10),
    shape           varchar(10),
    length          float,
    width           float,
    radius          float,
    gallons         float);

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

-- Uncomment the following to create sequences if using Apache Derby 10.6+
-- create sequence column_model_s
-- start with 100;
-- 
-- create sequence pool_s
-- start with 100;
-- 
-- create sequence job_s
-- start with 100;
-- 
-- create sequence pool_cust_s
-- start with 100;