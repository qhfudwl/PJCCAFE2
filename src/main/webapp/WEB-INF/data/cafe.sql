

CREATE TABLE Employee (
   id				BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,		 
   eId				VARCHAR(30)			NOT NULL,										-- 직원아이디
   passwd			VARCHAR(30)			NOT NULL,										-- 직원 비번
   position			CHAR(1)				NOT NULL	DEFAULT 'E',						-- 직원 직책 ( Employee / Manager )
   regDate			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP,			-- 등록날짜(읽기 전용)
   
   CONSTRAINT   	Employee_eId_UK  	UNIQUE(eId),									-- 고객 아이디는 중복 없이
);
CREATE TABLE Customer (
   id				BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,		 
   name				VARCHAR(30)			NOT NULL,										-- 고객이름
   phone			VARCHAR(13)			NOT NULL,										-- 고객 폰번호
   birth			VARCHAR(8)			NOT NULL,										-- 고객 생년월일( 19900102 )
   point			DOUBLE				NOT NULL	DEFAULT 0,
   regDate			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP,			-- 등록날짜(읽기 전용)
);
CREATE TABLE Menu (
   id         		BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   menuType   		CHAR(1)        		NOT NULL,										-- 메뉴타입 ( Beverage / Coffee / Food )
   menuName   		VARCHAR(30)     	NOT NULL,										-- 메뉴이름
   menuPrice   		DOUBLE         		NOT NULL,										-- 메뉴가격
   stock      		BOOLEAN         	NOT NULL   DEFAULT 'true',						-- 재고유무 ( true : 재고있음 )
   
   CONSTRAINT		Menu_menuName_UK	UNIQUE(menuName)
);
CREATE TABLE SalesRecord (
   id         		BIGINT         		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   customerId   	BIGINT        		NOT NULL,										-- 고객아이디
   orderNumber  	VARCHAR(30)     	NOT NULL,										-- 주문번호
   orderList   		VARCHAR(80)         NOT NULL,										-- 주문내역
   amount      		DOUBLE         		NOT NULL,										-- 결제금액
   usePoint 		DOUBLE				NOT NULL,										-- 사용한 포인트
   place			CHAR(1)				NOT NULL   	DEFAULT 'I',						-- 매장 or 포장
   regDate			TIMESTAMP			NOT NULL   	DEFAULT CURRENT_TIMESTAMP,			-- 등록날짜(읽기 전용)
   
   CONSTRAINT		SalesRecord_customerId_FK 	FOREIGN KEY(customerId) REFERENCES Customer(id),
   CONSTRAINT		SalesRecord_orderNumber_UK	UNIQUE(orderNumber)
);


