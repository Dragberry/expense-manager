USE TEST;
DROP DATABASE EXPENSEMANAGER;
CREATE DATABASE EXPENSEMANAGER;
USE EXPENSEMANAGER;

CREATE TABLE customer (
	customer_key BIGINT AUTO_INCREMENT NOT NULL,
	customer_name VARCHAR(64),
    enabled BIT,
	password VARCHAR(64),
	PRIMARY KEY (customer_key)
) ENGINE=INNODB;

INSERT INTO customer (customer_key, customer_name, enabled, password) VALUES (1, 'admin', true, 'password');
INSERT INTO customer (customer_key, customer_name, enabled, password) VALUES (2, 'user', true, 'password');

CREATE TABLE role (
	role_key BIGINT AUTO_INCREMENT NOT NULL,
	role_name VARCHAR(64),
	PRIMARY KEY (role_key)
) ENGINE=INNODB;

INSERT INTO role (role_key, role_name) VALUES (1, 'admin');
INSERT INTO role (role_key, role_name) VALUES (2, 'customer');
INSERT INTO role (role_key, role_name) VALUES (3, 'guest');

CREATE TABLE customer_role (
	customer_key BIGINT NOT NULL,
	role_key BIGINT NOT NULL,
	CONSTRAINT FK_CUSTOMER_ROLE FOREIGN KEY (customer_key) REFERENCES customer (customer_key),
	CONSTRAINT FK_ROLE_CUSTOMER FOREIGN KEY (role_key) REFERENCES role (role_key)
) ENGINE=INNODB;

INSERT INTO customer_role (customer_key, role_key) VALUES (1, 1);
INSERT INTO customer_role (customer_key, role_key) VALUES (2, 2);

CREATE TABLE interchange (
	interchange_key BIGINT AUTO_INCREMENT NOT NULL,
	amount NUMERIC(19,2),
	currency VARCHAR(3),
	description VARCHAR(255),
	type VARCHAR(1),
	customer_key BIGINT NOT NULL,
	PRIMARY KEY (interchange_key),
	CONSTRAINT FK_INTERCHANGE_CUSTOMER FOREIGN KEY (customer_key) REFERENCES customer (customer_key)
) ENGINE=INNODB;
