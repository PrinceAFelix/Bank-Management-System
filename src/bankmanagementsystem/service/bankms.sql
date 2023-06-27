-- Database: bmsDB

-- DROP DATABASE IF EXISTS "bmsDB";

-- CREATE DATABASE "bmsDB"
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'C'
--     LC_CTYPE = 'C'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1
--     IS_TEMPLATE = False;



DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS user_accounts;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	ID VARCHAR(4),
	FullName VARCHAR(255),
	Email VARCHAR(255),
	Phone VARCHAR(20),
	Password VARCHAR(255),
	Username VARCHAR(255),
	Address VARCHAR(255),
	CardNumber VARCHAR(255),
	PRIMARY KEY (ID)
);



CREATE TABLE user_accounts (
    ID SERIAL PRIMARY KEY,
	AccountID1 VARCHAR(4),
	AccountID2 VARCHAR(4),
    AccountNumber VARCHAR(255) NOT NULL,
    Balance DECIMAL(10, 2) NOT NULL,
    AccountTitle VARCHAR(255) NOT NULL,
    FOREIGN KEY (AccountID1) REFERENCES users (ID) ON DELETE CASCADE,
    FOREIGN KEY (AccountID2) REFERENCES users (ID) ON DELETE CASCADE,
    CONSTRAINT unique_accountid1 UNIQUE (AccountID1),
    CONSTRAINT unique_accountid2 UNIQUE (AccountID2)
);

CREATE TABLE transactions (
    ID SERIAL PRIMARY KEY,
    TransactionID1 VARCHAR(4),
	TransactionID2 VARCHAR(4),
    Account VARCHAR(255) NOT NULL,
    DateTime VARCHAR(255) NOT NULL,
    TransactType VARCHAR(255) NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    PostBalance DECIMAL(10, 2) NOT NULL,
 	FOREIGN KEY (TransactionID1) REFERENCES user_accounts (AccountID1) ON DELETE CASCADE,
    FOREIGN KEY (TransactionID2) REFERENCES user_accounts (AccountID2) ON DELETE CASCADE
);


INSERT INTO users (id, fullname, email, phone, password, username, address, cardnumber)
VALUES ('0001', 'Test test', 'test@test.com', '123456789', '1', 'test', 'Test St', '123');

INSERT INTO user_accounts (accountid1, accountnumber, balance, accounttitle) 
VALUES ('0001', '9998', 500.00, 'Chequing')





