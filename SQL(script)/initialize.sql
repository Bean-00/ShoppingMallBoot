DROP TABLE transaction;
DROP TABLE product;
DROP TABLE users;

DROP SEQUENCE seq_product_prod_no;
DROP SEQUENCE seq_transaction_tran_no;


CREATE SEQUENCE seq_product_prod_no INCREMENT BY 1 START WITH 10000;
CREATE SEQUENCE seq_transaction_tran_no INCREMENT BY 1 START WITH 10000;


CREATE TABLE users
(
    user_id    VARCHAR2(20) NOT NULL,
    user_name  VARCHAR2(50) NOT NULL,
    password   VARCHAR2(10) NOT NULL,
    role       VARCHAR2(5) DEFAULT 'user',
    ssn        VARCHAR2(13),
    cell_phone VARCHAR2(14),
    addr       VARCHAR2(100),
    email      VARCHAR2(50),
    reg_date   DATE,
    PRIMARY KEY (user_id)
);


CREATE TABLE product
(
    prod_no         NUMBER        NOT NULL,
    prod_name       VARCHAR2(100) NOT NULL,
    prod_detail     VARCHAR2(200),
    manufacture_day VARCHAR2(8),
    price           NUMBER(10),
    image_file      VARCHAR2(100),
    reg_date        DATE,
    PRIMARY KEY (prod_no)
);

CREATE TABLE transaction
(
    tran_no          NUMBER       NOT NULL,
    prod_no          NUMBER(16)   NOT NULL REFERENCES product (prod_no),
    buyer_id         VARCHAR2(20) NOT NULL REFERENCES users (user_id),
    payment_option   CHAR(3),
    receiver_name    VARCHAR2(20),
    receiver_phone   VARCHAR2(14),
    demailaddr       VARCHAR2(100),
    dlvy_request     VARCHAR2(100),
    tran_status_code CHAR(3),
    order_data       DATE,
    dlvy_date        DATE,
    PRIMARY KEY (tran_no)
);


INSERT
INTO users (user_id, user_name, password, role, ssn, cell_phone, addr, email, reg_date)
VALUES ('admin', 'admin', '1234', 'admin', NULL, NULL, '서울시 서초구', 'admin@mvc.com',
        to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'));

INSERT
INTO users (user_id, user_name, password, role, ssn, cell_phone, addr, email, reg_date)
VALUES ('manager', 'manager', '1234', 'admin', NULL, NULL, NULL, 'manager@mvc.com',
        to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO users
VALUES ('user01', 'SCOTT', '1111', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user02', 'SCOTT', '2222', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user03', 'SCOTT', '3333', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user04', 'SCOTT', '4444', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user05', 'SCOTT', '5555', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user06', 'SCOTT', '6666', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user07', 'SCOTT', '7777', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user08', 'SCOTT', '8888', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user09', 'SCOTT', '9999', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user10', 'SCOTT', '1010', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user11', 'SCOTT', '1111', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user12', 'SCOTT', '1212', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user13', 'SCOTT', '1313', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user14', 'SCOTT', '1414', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user15', 'SCOTT', '1515', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user16', 'SCOTT', '1616', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user17', 'SCOTT', '1717', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user18', 'SCOTT', '1818', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user19', 'SCOTT', '1919', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user20', 'SCOTT', '2020', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user21', 'SCOTT', '2121', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user22', 'SCOTT', '2222', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users
VALUES ('user23', 'SCOTT', '2323', 'user', NULL, NULL, NULL, NULL, sysdate);


insert into product
values (seq_product_prod_no.nextval, 'vaio vgn FS70B', '소니 바이오 노트북 신동품', '20120514', 2000000, 'AHlbAAAAtBqyWAAA.jpg',
        to_date('2012/12/14 11:27:27', 'YYYY/MM/DD HH24:MI:SS'));
insert into product
values (seq_product_prod_no.nextval, '자전거', '자전거 좋아요~', '20120514', 10000, 'AHlbAAAAvetFNwAA.jpg',
        to_date('2012/11/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'));
insert into product
values (seq_product_prod_no.nextval, '보르도', '최고 디자인 신품', '20120201', 1170000, 'AHlbAAAAvewfegAB.jpg',
        to_date('2012/10/14 10:49:39', 'YYYY/MM/DD HH24:MI:SS'));
insert into product
values (seq_product_prod_no.nextval, '보드세트', '한시즌 밖에 안썼습니다. 눈물을 머금고 내놓음 ㅠ.ㅠ', '20120217', 200000,
        'AHlbAAAAve1WwgAC.jpg', to_date('2012/11/14 10:50:58', 'YYYY/MM/DD HH24:MI:SS'));
insert into product
values (seq_product_prod_no.nextval, '인라인', '좋아욥', '20120819', 20000, 'AHlbAAAAve37LwAD.jpg',
        to_date('2012/11/14 10:51:40', 'YYYY/MM/DD HH24:MI:SS'));
insert into product
values (seq_product_prod_no.nextval, '삼성센스 2G', 'sens 메모리 2Giga', '20121121', 800000, 'AHlbAAAAtBqyWAAA.jpg',
        to_date('2012/11/14 18:46:58', 'YYYY/MM/DD HH24:MI:SS'));
insert into product
values (seq_product_prod_no.nextval, '연꽃', '정원을 가꿔보세요', '20121022', 232300, 'AHlbAAAAtDPSiQAA.jpg',
        to_date('2012/11/15 17:39:01', 'YYYY/MM/DD HH24:MI:SS'));
insert into product
values (seq_product_prod_no.nextval, '삼성센스', '노트북', '20120212', 600000, 'AHlbAAAAug1vsgAA.jpg',
        to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

commit;

---------[기본 테이터 생성 부분]-----------

insert into product(prod_no, prod_name, prod_detail, manufacture_day, price, image_file, reg_date)
select seq_product_prod_no.nextval, prod_name, prod_detail, manufacture_day, price, image_file, reg_date
from product;

commit;

select *
from product;

---------[파일 테이블 생성 부분]-----------
CREATE SEQUENCE seq_file_no INCREMENT BY 1 START WITH 10000;

CREATE TABLE FILES
(
    file_id          number         NOT NULL primary key,
    file_name        VARCHAR2(1000) NOT NULL,
    file_sys_name    VARCHAR2(100)  NOT NULL,
    file_dir_path    VARCHAR2(100)  NOT NULL,
    file_size        number         NOT NULL,
    file_upload_date TIMESTAMP default sysdate,
    file_mime_type   VARCHAR2(50)   NOT NULL,
    file_userId      VARCHAR2(20) references users (user_id),
    file_is_deleted  NUMBER(1) default 1
);



CREATE TABLE FILE_PRODUCT_MAP
(
    file_id    number not null primary key references FILES (file_id),
    product_no number not null references PRODUCT (prod_no),
    upload_seq number not null
);

--------------------------------------


select *
from users
where user_id = 'user01';

INSERT INTO TRANSACTION (TRAN_NO, PROD_NO, BUYER_ID, PAYMENT_OPTION, RECEIVER_NAME, RECEIVER_PHONE, DLVY_ADDR,
                         DLVY_REQUEST, TRAN_STATUS_CODE, ORDER_DATA, DLVY_DATE)
VALUES (seq_transaction_tran_no.nextval, 10000, 'user01', '1', '구매자 이름', '01000000000', '주소', '구매요청사항', '1',
        '2017-01-01', '2018-01-01');

select *
from product
where prod_no = 10006;

update transaction
set TRAN_STATUS_CODE = '1'
where buyer_id = 'user01';

commit;

select p.prod_no                  AS "prodNo",
       p.prod_name                AS "prodName",
       p.price                    AS "price",
       p.reg_date                 AS "regDate",
       NVL(t.tran_status_code, 0) AS "status"

from product p
         left outer join transaction t on p.PROD_NO = t.prod_no;

select p.prod_no                  AS "prodNo",
       p.prod_name                AS "prodName",
       p.price                    AS "price",
       p.reg_date                 AS "regDate",
       NVL(t.tran_status_code, 0) AS "statusCode"

from product p
         left outer join transaction t on p.PROD_NO = t.prod_no
order by p.prod_no;

select *
from transaction;


SELECT ROW_NUM, user_id, user_name, email
FROM (SELECT ROW_NUMBER() over (ORDER BY USER_ID) AS ROW_NUM,
             user_id,
             user_name,
             email
      FROM USERS) U
WHERE ROW_NUM BETWEEN 4 AND 6;

SELECT COUNT(user_id) AS "totalCount"
FROM USERS;

SELECT *
from transaction;


SELECT ROW_NUMBER() OVER (ORDER BY order_data) AS "rowNum",
       buyer_id                                AS "buyerId",
       receiver_name                           AS "buyerName",
       receiver_phone                          AS "buyerPhone",
       tran_status_code                        AS "tranCode"
FROM transaction
WHERE buyer_id = 'user01'
ORDER BY order_data;

SELECT count(tran_no) AS "totalCount"
FROM transaction
WHERE buyer_id = 'user01';

SELECT count(prod_no) AS "totalCount"
FROM product
;

select a.ROW_NUM   AS "rowNum",
       a.prod_no   AS "prodNo",
       a.PROD_NAME AS "prodName",
       a.PRICE     AS "price"
FROM (SELECT ROW_NUMBER() over (ORDER BY p.reg_date) AS ROW_NUM,
             p.prod_no,
             p.prod_name,
             p.price,
             p.reg_date,
             NVL(t.tran_status_code, 0)
      from product p
               left outer join transaction t on p.PROD_NO = t.prod_no) a
where ROW_NUM between 1 and 3
order by "rowNum";

SELECT ROW_NUM, user_id, user_name, email
FROM (SELECT ROW_NUMBER() over (ORDER BY USER_ID) AS ROW_NUM,
             user_id,
             user_name,
             email
      FROM USERS) U
WHERE ROW_NUM BETWEEN 1 AND 3
ORDER BY user_id;

SELECT "rowNum",
       buyer_id         AS "buyerId",
       receiver_name    AS "buyerName",
       receiver_phone   AS "buyerPhone",
       tran_status_code AS "tranCode"
FROM (SELECT ROW_NUMBER() OVER (ORDER BY order_data) AS "rowNum",
             buyer_id,
             receiver_name,
             receiver_phone,
             tran_status_code,
             order_data
      FROM TRANSACTION) A
WHERE buyer_id = 'user01'
  AND "rowNum" BETWEEN 1 AND 3
ORDER BY order_data;

select p.prod_no                  AS "prodNo",
       p.prod_name                AS "prodName",
       p.price                    AS "price",
       p.reg_date                 AS "regDate",
       NVL(t.tran_status_code, 0) AS "statusCode"
from product p
         left outer join transaction t on p.PROD_NO = t.prod_no
order by p.prod_no;

SELECT "rowNum",
       buyer_id         AS "buyerId",
       receiver_name    AS "buyerName",
       receiver_phone   AS "buyerPhone",
       tran_status_code AS "tranCode"
FROM (SELECT ROW_NUMBER() OVER (ORDER BY order_data) AS "rowNum",
             buyer_id,
             receiver_name,
             receiver_phone,
             tran_status_code,
             order_data
      FROM TRANSACTION)
WHERE buyer_id = 'user01'
  AND "rowNum" BETWEEN ? AND ?
ORDER BY order_data;

SELECT "rowNum",
       buyer_id         AS "buyerId",
       receiver_name    AS "buyerName",
       receiver_phone   AS "buyerPhone",
       tran_status_code AS "tranCode"
FROM (SELECT ROW_NUMBER() OVER (ORDER BY order_data) AS "rowNum",
             buyer_id,
             receiver_name,
             receiver_phone,
             tran_status_code,
             order_data
      FROM TRANSACTION) A
WHERE buyer_id = 'user01'
  AND "rowNum" BETWEEN 1 AND 3
ORDER BY order_data;


SELECT PT.ROW_NUM      AS "rowNum",
       PT."prodNo"     AS "prodNo",
       PT."prodName"   AS "prodName",
       PT."price"      AS "price",
       PT."regDate"    AS "redDate",
       PT."statusCode" AS "statusCode"

FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS "ROW_NUM",
             p.prod_no                             AS "prodNo",
             p.prod_name                           AS "prodName",
             p.price                               AS "price",
             p.reg_date                            AS "regDate",
             NVL(t.tran_status_code, 0)            AS "statusCode"
      from product p
               left outer join transaction t on p.PROD_NO = t.prod_no
      order by p.reg_date) PT;

SELECT PT.ROW_NUM      AS "rowNum",
       PT."prodNo"     AS "prodNo",
       PT."prodName"   AS "prodName",
       PT."price"      AS "price",
       PT."regDate"    AS "redDate",
       PT."statusCode" AS "statusCode"

FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS "ROW_NUM",
             p.prod_no                             AS "prodNo",
             p.prod_name                           AS "prodName",
             p.price                               AS "price",
             p.reg_date                            AS "regDate",
             NVL(t.tran_status_code, 0)            AS "statusCode"
      from product p
               left outer join transaction t on p.PROD_NO = t.prod_no
      order by p.prod_no) PT;

SELECT PT.ROW_NUM      AS "rowNum",
       PT."prodNo"     AS "prodNo",
       PT."prodName"   AS "prodName",
       PT."price"      AS "price",
       PT."regDate"    AS "redDate",
       PT."statusCode" AS "statusCode"

FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS "ROW_NUM",
             p.prod_no                             AS "prodNo",
             p.prod_name                           AS "prodName",
             p.price                               AS "price",
             p.reg_date                            AS "regDate",
             NVL(t.tran_status_code, 0)            AS "statusCode"
      from product p
               left outer join transaction t on p.PROD_NO = t.prod_no
      WHERE rowNum BETWEEN 1 AND 3
      order by ROW_NUM) PT;

SELECT ROW_NUM, user_id, user_name, email
FROM (SELECT ROW_NUMBER() over (ORDER BY USER_ID) AS ROW_NUM,
             user_id,
             user_name,
             email
      FROM USERS) U
WHERE ROW_NUM BETWEEN 4 AND 6
order by USER_ID;

SELECT PT.ROW_NUM      AS "rowNum",
       PT."prodNo"     AS "prodNo",
       PT."prodName"   AS "prodName",
       PT."price"      AS "price",
       PT."regDate"    AS "regDate",
       PT."statusCode" AS "statusCode"

FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS "ROW_NUM",
             p.prod_no                             AS "prodNo",
             p.prod_name                           AS "prodName",
             p.price                               AS "price",
             p.reg_date                            AS "regDate",
             NVL(t.tran_status_code, 0)            AS "statusCode"
      from product p
               left outer join transaction t on p.PROD_NO = t.prod_no) PT
WHERE "ROW_NUM" BETWEEN 4 AND 6
order by "regDate";

SELECT PT.ROW_NUM      AS "rowNum",
       PT."prodNo"     AS "prodNo",
       PT."prodName"   AS "prodName",
       PT."price"      AS "price",
       PT."regDate"    AS "regDate",
       PT."statusCode" AS "statusCode"

FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS "ROW_NUM",
             p.prod_no                             AS "prodNo",
             p.prod_name                           AS "prodName",
             p.price                               AS "price",
             p.reg_date                            AS "regDate",
             NVL(t.tran_status_code, 0)            AS "statusCode"
      from product p
               left outer join transaction t on p.PROD_NO = t.prod_no) PT
WHERE "ROW_NUM" BETWEEN ? AND ?
order by PT."regDate";

SELECT *
FROM TRANSACTION
WHERE prod_no = '10000';

UPDATE TRANSACTION
SET TRAN_STATUS_CODE = 2
WHERE prod_no = 10000;

UPDATE TRANSACTION
SET TRAN_STATUS_CODE = TRAN_STATUS_CODE + 1
WHERE prod_no = 10000;

UPDATE TRANSACTION
SET TRAN_STATUS_CODE = TRAN_STATUS_CODE - 1
WHERE prod_no = 10000;

UPDATE TRANSACTION
SET TRAN_STATUS_CODE = TRAN_STATUS_CODE + 1
WHERE prod_no = 10000;

select *
from transaction
where prod_no = 10000;

select *
from users;

-----[getALLTotalCount]----------------

SELECT count(user_id)
FROM USERS;

SELECT count(prod_no)
FROM PRODUCT;

SELECT count(tran_no)
FROM TRANSACTION;

-----[getSearchConditionCount]------------

SELECT count(user_id)
FROM USERS
WHERE user_id LIKE '%user%';


create TABLE user_test
(
    user_id       VARCHAR2(100),
    user_password VARCHAR2(100),
    user_name     VARCHAR2(100)
);

SELECT user_id,
       user_name,
       password,
       role,
       cell_phone,
       addr,
       email,
       reg_date
FROM users
WHERE user_id = 'user01';

SELECT *
FROM PRODUCT;

select *
from users;

select *
from transaction;

SELECT ROW_NUM   AS "rowNum",
       user_id   AS "userId",
       user_name AS "userName",
       email
FROM (SELECT ROW_NUMBER() over (ORDER BY USER_ID) AS ROW_NUM,
             user_id,
             user_name,
             email
      FROM USERS) U
WHERE ROW_NUM BETWEEN 4 AND 6;

SELECT PT.ROW_NUM      AS "rowNum",
       PT."prodNo"     AS "prodNo",
       PT."prodName"   AS "prodName",
       PT."price"      AS "price",
       PT."regDate"    AS "redDate",
       PT."statusCode" AS "statusCode"
FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS "ROW_NUM",
             p.prod_no                             AS "prodNo",
             p.prod_name                           AS "prodName",
             p.price                               AS "price",
             p.reg_date                            AS "regDate",
             NVL(t.tran_status_code, 0)            AS "statusCode"
      FROM product p
               left outer join transaction t on p.PROD_NO = t.prod_no
      ORDER BY ROW_NUM) PT
WHERE rowNum BETWEEN ? AND ?;


SELECT "rowNum",
       buyer_id         AS buyerId,
       receiver_name    AS buyerName,
       receiver_phone   AS buyerPhone,
       tran_status_code AS tranCode,
       prod_no          AS prodNo
FROM (SELECT ROW_NUMBER() OVER (ORDER BY order_date) AS "rowNum",
             buyer_id,
             receiver_name,
             receiver_phone,
             tran_status_code,
             prod_no,
             order_date
      FROM TRANSACTION)
WHERE buyer_id = 'user01'
  AND rowNum Between 1 AND 1
ORDER BY rowNum



SELECT ROW_NUMBER() OVER (ORDER BY order_date) AS "rowNum",
       buyer_id,
       receiver_name,
       receiver_phone,
       tran_status_code,
       prod_no,
       order_date
FROM TRANSACTION;

SELECT row_num AS "rowNum",
       buyer_id,
       user_name,
       cell_phone,
       receiver_name,
       receiver_phone,
       tran_status_code,
       prod_no
FROM (SELECT ROW_NUMBER() OVER (ORDER BY order_date) AS row_num,
             buyer_id,
             user_name,
             cell_phone,
             receiver_name,
             receiver_phone,
             tran_status_code,
             prod_no,
             order_date
      FROM TRANSACTION T
               INNER JOIN USERS U on U.USER_ID = T.BUYER_ID
      WHERE buyer_id = 'user01')
WHERE row_num Between 1 AND 4
ORDER BY row_num;

SELECT ROW_NUMBER() OVER (ORDER BY order_date) AS row_num,
       buyer_id,
       receiver_name,
       receiver_phone,
       tran_status_code,
       prod_no,
       order_date
from transaction
where buyer_id = 'user01';

select *
from users;

select *
from transaction;

select count(prod_no)
from product;

SELECT PT.row_num    AS "rowNum",
       PT.prod_no    AS "prodNo",
       PT.prod_name  AS "productName",
       PT.price      AS "price",
       PT.reg_date   AS "regDate",
       PT.trans_code AS "status"
FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS row_num,
             p.prod_no,
             p.prod_name,
             p.price,
             p.reg_date,
             NVL(t.tran_status_code, 0)            AS trans_code
      FROM product p
               left outer join transaction t on p.PROD_NO = t.prod_no


      WHERE p.prod_no LIKE '%${searchKeyword}%') PT
WHERE row_num BETWEEN 1 AND 3
ORDER BY row_num;

select *
from transaction
where prod_no = 10001;

commit;


delete
FROM TRANSACTION
WHERE prod_no IN (select prod_no
                  from transaction
                  group by prod_no
                  having count(prod_no) != 1);

SELECT count(prod_no)
FROM transaction
WHERE prod_no = 10010;

select *
from transaction
where prod_no = 10001;

select *
from users;

SELECT ROW_NUM AS "rowNum", user_id, user_name, email
FROM (SELECT ROW_NUMBER() over (ORDER BY USER_ID) AS ROW_NUM, user_id, user_name, email FROM USERS WHERE) U
WHERE ROW_NUM BETWEEN ? AND ?;

SELECT prod_name
FROM PRODUCT;

select *
from PRODUCT;


commit;


SELECT PT.row_num    AS "rowNum",
       PT.prod_no    AS "prodNo",
       PT.prod_name  AS "productName",
       PT.price      AS "price",
       PT.reg_date   AS "regDate",
       PT.trans_code AS "status",
       PT.image_file AS "fileName"
FROM (select ROW_NUMBER() over (ORDER BY p.PROD_NO) AS row_num,
             p.prod_no,
             p.prod_name,
             p.price,
             p.reg_date,
             p.image_file,
             NVL(t.tran_status_code, 0)             AS trans_code
      FROM product p
               left outer join transaction t on p.PROD_NO = t.prod_no
      WHERE p.price BETWEEN 300000 AND 11100000
      ORDER BY p.price) PT
WHERE row_num BETWEEN 0 AND 12
-- AND PT.trans_code = 0
ORDER BY row_num;

SELECT PT.row_num    AS "rowNum",
       PT.prod_no    AS "prodNo",
       PT.prod_name  AS "productName",
       PT.price      AS "price",
       PT.reg_date   AS "regDate",
       PT.trans_code AS "status",
       PT.image_file AS "fileName"
FROM (select ROW_NUMBER() over (ORDER BY p.price desc ) AS row_num,
             p.prod_no,
             p.prod_name,
             p.price,
             p.reg_date,
             p.image_file,
             NVL(t.tran_status_code, 0)                 AS trans_code
      FROM product p
               left outer join transaction t on p.PROD_NO = t.prod_no
      WHERE t.tran_status_code >= 2
      ORDER BY p.price) PT
WHERE row_num BETWEEN 0 AND 12
-- AND PT.trans_code = 0
ORDER BY row_num;


select * from TRANSACTION;

select * from TRANSACTION;

select * from users;


(SELECT ROW_NUMBER() OVER (ORDER BY T.order_date) AS row_num,
       buyer_id,
       user_name,
       cell_phone,
       receiver_name,
       receiver_phone,
       tran_status_code,
       P.prod_no,
       order_date,
       prod_name
FROM TRANSACTION T INNER JOIN USERS U on U.USER_ID = T.BUYER_ID
                    INNER JOIN PRODUCT P on T.prod_no = P.prod_no
WHERE buyer_id = 'user01')
