--<ScriptOptions statementTerminator=";"/>

ALTER TABLE t_items DROP PRIMARY KEY;

ALTER TABLE t_user DROP PRIMARY KEY;

ALTER TABLE t_sys_menu DROP PRIMARY KEY;

ALTER TABLE t_client DROP PRIMARY KEY;

ALTER TABLE t_flow_card_detail DROP PRIMARY KEY;

ALTER TABLE t_fiscal_year_period DROP PRIMARY KEY;

ALTER TABLE t_data_dict DROP PRIMARY KEY;

ALTER TABLE t_flow_card_master DROP PRIMARY KEY;

ALTER TABLE t_flow_card_master DROP INDEX FK9282FD4B6BB700F2;

ALTER TABLE t_flow_card_detail DROP INDEX FK835FEB3A9A19152;

ALTER TABLE t_items DROP INDEX FKA06D243547F0FD4E;

ALTER TABLE t_sys_menu DROP INDEX FK2B50BAFC84541B60;

ALTER TABLE t_flow_card_master DROP INDEX FK9282FD4BB74DE7D1;

ALTER TABLE t_flow_card_detail DROP INDEX FK835FEB3A3D592FCF;

ALTER TABLE t_flow_card_master DROP INDEX FK9282FD4B7CE23D8F;

ALTER TABLE t_client DROP INDEX FK628B45F62A7C62A0;

ALTER TABLE t_flow_card_master DROP INDEX FK9282FD4BB7916380;

ALTER TABLE t_flow_card_master DROP INDEX FK9282FD4B2AFCF852;

ALTER TABLE t_flow_card_detail DROP INDEX FK835FEB3AF826DE4E;

ALTER TABLE t_client DROP INDEX FK628B45F6B5B1F6F7;

ALTER TABLE t_flow_card_master DROP INDEX FK9282FD4B44A9DF2;

ALTER TABLE t_client DROP INDEX FK628B45F699AF2177;

ALTER TABLE t_items DROP INDEX itemNo;

ALTER TABLE t_items DROP INDEX FKA06D24357C04C042;

DROP TABLE t_client;

DROP TABLE t_flow_card_detail;

DROP TABLE t_sys_menu;

DROP TABLE t_fiscal_year_period;

DROP TABLE t_items;

DROP TABLE t_data_dict;

DROP TABLE t_user;

DROP TABLE t_flow_card_master;

CREATE TABLE t_sys_menu (
	id VARCHAR(255) NOT NULL,
	isMainMenu BIT NOT NULL,
	nodeName VARCHAR(255),
	url VARCHAR(255),
	pid VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE t_fiscal_year_period (
	id VARCHAR(255) NOT NULL,
	begin_date DATETIME,
	end_date DATETIME,
	fiscal_period INT,
	fiscal_year INT,
	period_sts VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE t_client (
	category VARCHAR(31) NOT NULL,
	id VARCHAR(255) NOT NULL,
	address VARCHAR(255),
	clientNo VARCHAR(255),
	contactTel VARCHAR(255),
	isClient VARCHAR(255),
	name VARCHAR(255),
	nodeType INT NOT NULL,
	zipCode VARCHAR(255),
	bankAcctNo VARCHAR(255),
	contactPerson VARCHAR(255),
	pId VARCHAR(255),
	levelId VARCHAR(255),
	temiClientTypeId VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE t_flow_card_detail (
	flowCardDetailId VARCHAR(255) NOT NULL,
	adjustFlag VARCHAR(255),
	adjustQty DOUBLE NOT NULL,
	adjustReason VARCHAR(255),
	optQty DOUBLE NOT NULL,
	flowCard_fcId VARCHAR(255),
	item_id VARCHAR(255),
	temiClient_id VARCHAR(255),
	PRIMARY KEY (flowCardDetailId)
) ENGINE=InnoDB;

CREATE TABLE t_items (
	id VARCHAR(255) NOT NULL,
	itemName VARCHAR(255),
	itemNo VARCHAR(255),
	pattern VARCHAR(255),
	spec VARCHAR(255),
	uploadFileName VARCHAR(255),
	categoryId VARCHAR(255),
	unitId VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE t_data_dict (
	category VARCHAR(31) NOT NULL,
	id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE t_user (
	user_id VARCHAR(255) NOT NULL,
	contact_tel VARCHAR(255),
	create_date DATETIME,
	email VARCHAR(255),
	password VARCHAR(255),
	user_name VARCHAR(255),
	PRIMARY KEY (user_id)
) ENGINE=InnoDB;

CREATE TABLE t_flow_card_master (
	fcId VARCHAR(255) NOT NULL,
	adjustDate DATETIME,
	confDate DATETIME,
	flowCardNo VARCHAR(255),
	optDate DATETIME,
	optType VARCHAR(255),
	spotDate DATETIME,
	spotDesc VARCHAR(255),
	vouSts VARCHAR(255),
	adjuster_user_id VARCHAR(255),
	client_id VARCHAR(255),
	confirmer_user_id VARCHAR(255),
	fiscalYearPeriod_id VARCHAR(255),
	recorder_user_id VARCHAR(255),
	spotter_user_id VARCHAR(255),
	PRIMARY KEY (fcId)
) ENGINE=InnoDB;

CREATE INDEX FK9282FD4BB7916380 ON t_flow_card_master (confirmer_user_id ASC);

CREATE INDEX FK9282FD4B2AFCF852 ON t_flow_card_master (client_id ASC);

CREATE INDEX FK9282FD4B6BB700F2 ON t_flow_card_master (fiscalYearPeriod_id ASC);

CREATE INDEX FK835FEB3A9A19152 ON t_flow_card_detail (item_id ASC);

CREATE INDEX FK835FEB3AF826DE4E ON t_flow_card_detail (temiClient_id ASC);

CREATE INDEX FK628B45F6B5B1F6F7 ON t_client (pId ASC);

CREATE INDEX FK9282FD4B44A9DF2 ON t_flow_card_master (spotter_user_id ASC);

CREATE INDEX FK628B45F699AF2177 ON t_client (temiClientTypeId ASC);

CREATE UNIQUE INDEX itemNo ON t_items (itemNo ASC);

CREATE INDEX FKA06D243547F0FD4E ON t_items (unitId ASC);

CREATE INDEX FK2B50BAFC84541B60 ON t_sys_menu (pid ASC);

CREATE INDEX FKA06D24357C04C042 ON t_items (categoryId ASC);

CREATE INDEX FK9282FD4BB74DE7D1 ON t_flow_card_master (recorder_user_id ASC);

CREATE INDEX FK835FEB3A3D592FCF ON t_flow_card_detail (flowCard_fcId ASC);

CREATE INDEX FK9282FD4B7CE23D8F ON t_flow_card_master (adjuster_user_id ASC);

CREATE INDEX FK628B45F62A7C62A0 ON t_client (levelId ASC);

