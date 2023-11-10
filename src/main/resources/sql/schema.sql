Drop database if exists spices_production_management_system;

create database if not exists spices_production_management_system;

use spices_production_management_system;


CREATE TABLE employee(
                         employee_id VARCHAR(20) PRIMARY KEY,
                         employee_name VARCHAR(25) NOT NULL,
                         tel INT (20),
                         job_title VARCHAR(15) NOT NULL,
                         emai VARCHAR(30)
);

DESC employee;



CREATE TABLE user(
                     user_id VARCHAR(20) PRIMARY KEY,
                     user_name VARCHAR(25) NOT NULL,
                     password VARCHAR(20) NOT NULL,
                     employee_id VARCHAR(20) NOT NULL,
                     FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE customer(
                         customer_id VARCHAR(20) PRIMARY KEY,
                         customer_name VARCHAR(25) NOT NULL,
                         address VARCHAR(20) NOT NULL,
                         tel INT(15)
);


DESC customer;


CREATE TABLE orders(
                       Order_id VARCHAR(20) PRIMARY KEY,
                       payment DECIMAL(10,2) NOT NULL,
                       date date,
                       customer_id VARCHAR(20) NOT NULL,
                       FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE
);

DESC orders;


CREATE TABLE product(
                        product_id VARCHAR(20) PRIMARY KEY,
                        product_name VARCHAR(25) NOT NULL,
                        price DECIMAL(10,2)NOT NULL,
                        qty_on_stock INT(30)
);

DESC product;


CREATE TABLE Order_detail(
                             Order_id VARCHAR(20) NOT NULL,
                             product_id VARCHAR(20) NOT NULL,
                             qty INT(25),
                             CONSTRAINT FOREIGN KEY(Order_id) REFERENCES orders(Order_id) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT FOREIGN KEY(product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE
);


DESC Order_detail;


CREATE TABLE salary(
                       salary_id VARCHAR(20) PRIMARY KEY,
                       employee_id VARCHAR(20) NOT NULL,
                       date date,
                       CONSTRAINT FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE ON UPDATE CASCADE
);

DESC salary;


CREATE TABLE delivery(
                         delivery_id VARCHAR(20) PRIMARY KEY,
                         location VARCHAR(25) ,
                         delivery_status VARCHAR(20),
                         tel INT(15),
                         employee_id VARCHAR(20) NOT NULL,
                         Order_id VARCHAR(20)NOT NULL,
                         CONSTRAINT FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE ON UPDATE CASCADE,
                         CONSTRAINT FOREIGN KEY(Order_id) REFERENCES orders(Order_id) ON DELETE CASCADE ON UPDATE CASCADE
);


DESC delivery;


CREATE TABLE supplier(
                         supplier_id VARCHAR(20) PRIMARY KEY,
                         supplier_name VARCHAR(25)NOT NULL,
                         tel INT (20),
                         address VARCHAR(15) NOT NULL
);


DESC supplier;


create table raw_material(
                             rawMaterial_id VARCHAR(20) PRIMARY KEY,
                             material_name VARCHAR(25) NOT NULL,
                             qty_on_stock INT(20)
);


DESC raw_material;


CREATE TABLE bill_of_material(
                                 rawMaterial_id VARCHAR(20) NOT NULL,
                                 product_id VARCHAR(20) NOT NULL,
                                 qty INT(25),
                                 date DATE,
                                 cost DECIMAL(10,2) NOT NULL,
                                 FOREIGN KEY(rawMaterial_id ) REFERENCES raw_material (rawMaterial_id ) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY(product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

DESC bill_of_material;


CREATE TABLE supplier_detail(
                                supplier_id VARCHAR(20) NOT NULL,
                                rawMaterial_id VARCHAR(20) NOT NULL,
                                date DATE,
                                price DECIMAL(10,2) NOT NULL,
                                qty_on_stock INT(25),
                                FOREIGN KEY(supplier_id) REFERENCES supplier(supplier_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY(rawMaterial_id) REFERENCES raw_material (rawMaterial_id) ON DELETE CASCADE ON UPDATE CASCADE
);

DESC supplier_detail;

INSERT INTO employee VALUES('E001', 'Meri', 071-2223334, 'Manager','meri@gmail.com');

INSERT INTO user VALUES ('U001','Meri','1111','E001');


