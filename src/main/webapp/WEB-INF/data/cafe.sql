DROP TABLE OrderRecord;
DROP TABLE SalesRecord;
DROP TABLE Employee;
DROP TABLE Customer;
DROP TABLE Menu;


CREATE TABLE Employee (
   id				BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,		 
   eId				VARCHAR(30)			NOT NULL,										-- 직원아이디
   passwd			VARCHAR(30)			NOT NULL,										-- 직원 비번
   position			CHAR(1)				NOT NULL	DEFAULT 'E',						-- 직원 직책 ( Employee / Manager )
   regDate			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP,			-- 등록날짜(읽기 전용)
   
   CONSTRAINT   	Employee_eId_UK  	UNIQUE(eId)										-- 고객 아이디는 중복 없이
);
 
CREATE TABLE Customer (
   id				BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,		 
   name				VARCHAR(30)			NOT NULL,										-- 고객이름
   phone			VARCHAR(13)			NOT NULL,										-- 고객 폰번호
   birth			VARCHAR(8)			NOT NULL,										-- 고객 생년월일( 19900102 )
   point			DOUBLE				NOT NULL	DEFAULT 0,
   regDate			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP			-- 등록날짜(읽기 전용)
);

CREATE TABLE Menu (
   id         		BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   menuType   		CHAR(1)        		NOT NULL,										-- 메뉴타입 ( Beverage / Coffee / Food )
   menuName   		VARCHAR(30)     	NOT NULL,										-- 메뉴이름
   menuPrice   		DOUBLE         		NOT NULL,										-- 메뉴가격
   stock      		BOOLEAN         	NOT NULL   DEFAULT true,						-- 재고유무 ( true : 재고있음 )
   regDate			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP,			-- 등록날짜(읽기 전용)
   
   CONSTRAINT		Menu_menuName_UK	UNIQUE(menuName)
);

ALTER TABLE Menu ADD imgPath VARCHAR(200) NOT NULL DEFAULT '';							-- 메뉴이미지 경로

CREATE TABLE SalesRecord (
   id         		BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   customerId   	BIGINT        		NOT NULL,										-- 고객아이디
   orderNumber  	VARCHAR(30)     	NOT NULL,										-- 주문번호
   amount      		DOUBLE         		NOT NULL,										-- 결제금액
   usePoint 		DOUBLE				NOT NULL,										-- 사용한 포인트
   place			CHAR(1)				NOT NULL   	DEFAULT 'I',						-- 매장 or 포장
   regDate			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP,			-- 등록날짜(읽기 전용)
   
   CONSTRAINT		SalesRecord_customerId_FK 	FOREIGN KEY(customerId) REFERENCES Customer(id)
);

CREATE TABLE OrderRecord (
   id         		BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,		-- 주문번호
   menuId  			BIGINT     			NOT NULL,										-- 메뉴아이디
   quantity      	INT         		NOT NULL,										-- 주문수량 
   regDate 			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP,			-- 결제날짜 
   CONSTRAINT		Menu_menuId_FK 	FOREIGN KEY(menuId) REFERENCES Menu(id)
);

-- 비회원등록
INSERT INTO Customer(name,phone,birth,point) VALUES('김아루','010-0000-0000','00000000',0);
INSERT INTO Customer(name,phone,birth,point) VALUES('최향기','010-0000-0000','00000000',0);


-- 관리자등록
INSERT INTO Employee(eId,passwd,position) VALUES('manager','manager','M');

-- 메뉴등록

INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('B','아이스자몽에이드',1000.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('B','아이스레몬에이드',1000.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('B','핫자스민티',1000.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('C','핫아메리카노',1500.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('C','아이스아메리카노',1500.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('C','핫카페라떼',2000.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('C','아이스카페라떼',2000.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('C','핫카페모카',2500.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('C','아이스카페모카',2500.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('F','햄치즈샌드위치',3000.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('F','촉촉한초코칩쿠키',1000.0,true,'resources/img/IceAmericano.jpg');
INSERT INTO Menu(menuType,menuName,menuPrice,stock,imgPath) VALUES('F','알록달록마카롱',1000.0,true,'resources/img/IceAmericano.jpg');

-- saleRecord 추가
insert into SalesRecord(customerId,orderNumber, amount, usePoint) values(1, '100', 1000, 10);
insert into SalesRecord(customerId,orderNumber, amount, usePoint) values(1, '200', 1000, 0);
insert into SalesRecord(customerId,orderNumber, amount, usePoint) values(1, '300', 1000, 0);
insert into SalesRecord(customerId,orderNumber, amount, usePoint) values(1, '400', 1000, 0);
insert into SalesRecord(customerId,orderNumber, amount, usePoint) values(1, '500', 1000, 0);
insert into SalesRecord(customerId,orderNumber, amount, usePoint) values(1, '600', 1000, 0);
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '700', 1000, 500,'2021-10-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '101', 1000, 500,'2021-07-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '702', 1000, 500,'2021-11-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '703', 1000, 500,'2021-12-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '704', 1000, 500,'2021-06-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '701', 1000, 500,'2021-03-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '705', 1000, 500,'2021-01-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '706', 1000, 500,'2021-02-04 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '707', 1000, 500,'2021-12-06 00:02:11');
insert into SalesRecord(customerId,orderNumber, amount, usePoint, regDate) values(1, '708', 1000, 500,'2021-12-14 00:02:11');

-- orderRecord 추가
insert into OrderRecord(menuId, quantity) values(1,1);
insert into OrderRecord(menuId, quantity) values(2,2);
insert into OrderRecord(menuId, quantity) values(3,3);

insert into OrderRecord(menuId, quantity) values(1,1);
insert into OrderRecord(menuId, quantity) values(3,3);

insert into OrderRecord(menuId, quantity) values(1,1);
insert into OrderRecord(menuId, quantity) values(4,4);
insert into OrderRecord(menuId, quantity) values(5,5);

insert into OrderRecord(menuId, quantity) values(5,5);
insert into OrderRecord(menuId, quantity) values(2,2);
insert into OrderRecord(menuId, quantity) values(3,3);

insert into OrderRecord(menuId, quantity, regDate) values(1,1, '2021-11-03 00:02:10');
insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-11-03 00:02:11');

insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-12-05 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-12-05 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-12-05 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-12-05 00:02:11');

insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-11-30 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-11-30 00:02:11');

insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-12-01 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-12-01 00:02:11');

insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-10-12 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,2, '2021-10-13 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(1,2, '2021-10-17 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(1,2, '2021-10-28 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(3,1, '2021-10-28 00:02:11');

insert into OrderRecord(menuId, quantity, regDate) values(1,1, '2021-09-01 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(3,1, '2021-09-03 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(5,1, '2021-09-09 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(1,1, '2021-09-09 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(1,1, '2021-09-09 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,1, '2021-09-11 00:02:11');
insert into OrderRecord(menuId, quantity, regDate) values(2,1, '2021-09-11 00:02:11');



SELECT * FROM Customer;
SELECT * FROM Employee;
SELECT * FROM Menu;
SELECT * FROM SalesRecord;
SELECT * FROM OrderRecord;
SELECT menuID, SUM(quantity) as quantity FROM OrderRecord WHERE Date(regDate) Between '2021-12-15 00:00:00' AND '2021-12-15 23:59:59' GROUP BY menuId;

DELETE FROM Menu;
DELETE FROM SalesRecord;

INSERT INTO Customer(name,phone,birth,point) VALUES('홍길동','01012345678','19900101',0);
INSERT INTO Customer(name,phone,birth,point) VALUES('김감자','01012345678','19900101',7000);

