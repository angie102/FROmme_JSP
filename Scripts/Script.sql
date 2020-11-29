-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- genre Table Create SQL
CREATE TABLE genre
(
    genre_no      NUMBER(5, 0)    NOT NULL, 
    genre_name    VARCHAR2(20)    NOT NULL, 
    CONSTRAINT GENRE_PK PRIMARY KEY (genre_no)
);

CREATE TABLE visit
(
	user_ip varchar2(40) NOT NULL,
	visit_time DATE DEFAULT sysdate NOT NULL
);

SELECT * FROM users;

INSERT INTO users VALUES()

SELECT * FROM orderlist;

SELECT * FROM USERs;

CREATE TABLE authority (
	AUTHORITY_NO NUMBER,
	AUTHORITY_NAME VARCHAR2(30) NOT NULL,
	CONSTRAINT authority_pk PRIMARY KEY(AUTHORITY_NO)
)

select * from users where users_authority = 4;


SELECT * FROM users;
SELECT * FROM STUDIO s ;

SELECT * FROM classes;

CREATE TABLE test (
	name varchar2(30)
);

INSERT INTO test values('나는 형준2');

SELECT name FROM test;

SELECT * FROM classes;

UPDATE classes SET CLASSES_STATE = 'B' WHERE classes_no = 22;


UPDATE STUDIO SET studio_status = '2' WHERE STUDIO_PRESIDENT = '김세현';
UPDATE users SET USERS_AUTHORITY = '1' WHERE USERS_EMAIL = 'eeee';

SELECT * FROM 
SELECT * FROM AUTHORITY ;
SELECT * FROM POST_VIEW pv ;
SELECT * FROM CATEGORY c ;
SELECT * FROM visit;
INSERT INTO AUTHORITY VALUES(5, '기각');


SELECT to_char(PAYMENT_DATE, 'hh24') as criteriaDate, SUM(QUANTITY) AS views FROM ORDERLIST  WHERE TO_CHAR(PAYMENT_DATE, 'YYYY-MM-DD hh') BETWEEN (TO_CHAR(SYSDATE, 'YYYY-MM-DD')||' '||'00') AND (TO_CHAR(SYSDATE, 'YYYY-MM-DD')||' '||'24') GROUP BY to_char(payment_date, 'hh24') ORDER BY to_char(PAYMENT_DATE, 'hh24') ASC;

SELECT to_char(visit_time, 'hh24') as criteriaDate, count(*) AS views 
FROM visit   
WHERE TO_CHAR(visit_time, 'YYYY-MM-DD hh') BETWEEN (TO_CHAR(SYSDATE, 'YYYY-MM-DD')||' '||'00') 
AND (TO_CHAR(SYSDATE, 'YYYY-MM-DD')||' '||'24')   
GROUP BY to_char(visit_time, 'hh24') ORDER BY to_char(visit_time, 'hh24') ASC;

SELECT TO_CHAR(PAYMENT_DATE +LEVEL-1, 'YYYY-MM-DD hh')
  FROM ORDERLIST o2 
CONNECT BY LEVEL <= (PAYMENT_DATE - PAYMENT_DATE+1);

select  PAYMENT_DATE, substr(vi_time,1,2) hour , count(*) cnt
from ORDERLIST o2 
group by PAYMENT_DATE, substr(vi_time,1,2);

SELECT * FROM ORDERLIST o ;
SELECT (SYSDATE - PAYMENT_DATE) / 180  FROM ORDERLIST o3 ;

SELECT max(PAYMENT_DATE), sum(QUANTITY) 
FROM (SELECT * FROM ORDERLIST o2 
WHERE PAYMENT_DATE 
BETWEEN TO_DATE('2020-11-07 00', 'YYYY-MM-DD hh24') 
AND TO_DATE('2020-11-07 23', 'YYYY-MM-DD hh24')) 
GROUP BY to_char(PAYMENT_DATE, 'DD'), TO_CHAR(PAYMENT_DATE, 'hh24') / 3;

SELECT * FROM ORDERLIST o2 WHERE PAYMENT_DATE BETWEEN TO_DATE('2020-11-07 00:00', 'YYYY-MM-DD hh24:mi') AND TO_DATE('2020-11-07 23:59', 'YYYY-MM-DD hh24:mi');

	SELECT to_char(visit_time, 'DD') s_date, count(*) cnt FROM visit
	WHERE TO_CHAR(visit_time, 'YYYY-MM') BETWEEN '01' AND '31'
	GROUP BY to_char(visit_time, 'DD') ORDER BY to_char(visit_time, 'DD') ASC;

SELECT to_char(PAYMENT_DATE, 'DD') s_date, sum(QUANTITY) cnt FROM ORDERLIST o2
	WHERE TO_CHAR(PAYMENT_DATE, 'YYYY-MM-DD') BETWEEN to_char(SYSDATE-7, 'YYYY-MM-DD') AND TO_CHAR(SYSDATE, 'YYYY-MM-DD') 
	GROUP BY to_char(PAYMENT_DATE, 'DD') ORDER BY to_char(PAYMENT_DATE, 'DD') ASC;

SELECT SUM(c.CLASSES_PRICE) AS month_profit FROM ORDERLIST o JOIN CLASSES c ON o.CLASSES_NO = c.CLASSES_NO WHERE PAYMENT_DATE BETWEEN SYSDATE-30 AND SYSDATE;

SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'00:00' AND  TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'03:00' 
GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD');

SELECT NVL(SUM(c.CLASSES_PRICE), 0) AS month_profit 
		FROM ORDERLIST o JOIN CLASSES c ON o.CLASSES_NO = c.CLASSES_NO 
		WHERE PAYMENT_DATE BETWEEN SYSDATE-30 AND SYSDATE;
	
SELECT * FROM CLASSES c ;
	
SELECT * FROM ORDERLIST o ;

SELECT (SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'00:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'02:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD')) AS t3,
	(SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'03:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'05:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD')) AS t6,
	(SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'06:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'08:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD')) AS t9,
	(SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'09:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'11:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD')) AS t12,
	(SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'12:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'14:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD')) AS t15,
	(SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'15:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'17:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD')) AS t18,
	(SELECT NVL(SUM(COUNT(*)), 0) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'18:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'20:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD')) AS t21,
	(SELECT count(*) FROM ORDERLIST o WHERE to_char(PAYMENT_DATE, 'YYYY-MM-DD hh24') 
	BETWEEN TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'21:00' 
	AND TO_CHAR(sysdate, 'YYYY-MM-DD')||' '||'23:59' 
	GROUP BY to_char(PAYMENT_DATE, 'YYYY-MM-DD'))  AS t24 FROM dual;
	

SELECT * FROM visit;

SELECT * FROM POST_VIEW pv ;
SELECT * FROM post WHERE POST_NO = 146;
SELECT * FROM CLASSES c ;


CREATE TABLE classes_state(
	state number,
	state_name varchar2(30),
	CONSTRAINT state_pk PRIMARY KEY(state) 
);

DROP TABLE classes_state;

SELECT * FROM classes_state;
INSERT INTO classes_state values(1, '예약 가능');
INSERT INTO classes_state values(2, '준비 중');
INSERT INTO classes_state values(3, '예약 불가');
INSERT INTO classes_state values(4, '비공개');


SELECT (SELECT COUNT(*) FROM VISIT), 
			(SELECT COUNT(*) FROM VISIT WHERE VISIT_TIME > SYSDATE),
			(SELECT COUNT(*) FROM VISIT WHERE VISIT_TIME > SYSDATE - 30)
			FROM DUAL;
		
ALTER TABLE STUDIO ADD studio_status NUMBER ;

SELECT COUNT(*) FROM VISIT WHERE USER_IP = '127.0.0.1' AND VISIT_TIME = TO_CHAR(SYSDATE, 'YYYY-MM-DD'); 

INSERT INTO visit values('127.0.0.1', sysdate - 9/24);

SELECT (SELECT COUNT(*) FROM visit), (SELECT count(*) FROM visit WHERE visit_time > SYSDATE -3) FROM dual;

SELECT * FROM USERs;

SELECT (SELECT COUNT(*) FROM ORDERLIST) AS orderCnt, 
	(SELECT SUM(c.CLASSES_PRICE) FROM ORDERLIST o JOIN CLASSES c ON o.CLASSES_NO = c.CLASSES_NO WHERE PAYMENT_DATE BETWEEN SYSDATE-30 AND sysdate) FROM DUAL;
SELECT * FROM USERS ;
INSERT INTO users VALUES(users_seq.nextval, 'gongbangwa2', '1234', '공방1','01012341234', 'asdasd@naveer.com', 4, 1, 'ssss');
INSERT INTO users VALUES(users_seq.nextval, 'admin', '1234', '이형준', '02-000-0000', 'adsdfsd2321@', 42, '서울 강남구 역삼역', '3번 출구', 4, 3, null);
INSERT INTO ORDERLIST Values(10, 'test1', sysdate-3, sysdate - 3, 1, orderlist_seq.nextval, 1, sysdate+2, sysdate+2, 0, 2);
SELECT * FROM ORDERLIST o3 ;
select  PAYMENT_DATE, substr(PAYMENT_DATE,1,2) hour , count(*) cnt
from ORDERLIST o2 WHERE 
group by PAYMENT_DATE, substr(PAYMENT_DATE,1,2)

SELECT * FROM v$resource_limit;
ALTER system SET processes=200 SCOPE =spfile;
SELECT * FROM STUDIO s ;

SELECT username, count(username) FROM v$session 
WHERE username IS NOT NULL group by username;

SELECT count(PAYMENT_DATE) FROM ORDERLIST o2 GROUP BY PAYMENT_DATE HAVING TO_char(PAYMENT_DATE, 'hh') < TO_char(LEVEL, 'hh') CONNECT BY LEVEL < 9;

SELECT TO_CHAR(sysdate, 'h') FROM dual; 

SELECT TO_char(PAYMENT_DATE, 'hh') FROM ORDERLIST o ;

INSERT INTO ORDERSTATUS values(3, '준비 중');

UPDATE ORDERSTATUS SET ORDERLIST_STATE = '예약 불가' WHERE ORDERLIST_STATE_NO = 2;

SELECT * FROM ORDERSTATUS o ;

SELECT * FROM ORDERLIST o ;

SELECT 
      TO_CHAR(PAYMENT_DATE , 'HH24') AS TIMESLOT
    , COUNT(*) AS CNT 
FROM ORDERLIST o2 
GROUP BY TO_CHAR(PAYMENT_DATE , 'HH24') 
ORDER BY 1;

SELECT (SELECT count(*) FROM ORDERLIST o2 
WHERE TO_CHAR(PAYMENT_DATE, 'YYYY-DD-MM HH') < to_char(SYSDATE+3/24, 'YYYY-MM-DD HH') 
AND TO_CHAR(PAYMENT_DATE, 'YYYY-DD-MM HH') > to_char(SYSDATE+3/24, 'YYYY-MM-DD HH')) AS data FROM dual ;

CREATE SEQUENCE genre_SEQ
START WITH 1
INCREMENT BY 1;
;

COMMENT ON TABLE genre IS '장르';


COMMENT ON COLUMN genre.genre_no IS '장르 번호';


COMMENT ON COLUMN genre.genre_name IS '장르 이름';



-- genre Table Create SQL
CREATE TABLE users
(
    users_no           NUMBER(18, 0)    NOT NULL, 
    users_id           VARCHAR2(20)     NOT NULL, 
    users_pw           VARCHAR2(15)     NOT NULL, 
    users_name         VARCHAR2(45)     NOT NULL, 
    users_phone        CHAR(12)         NOT NULL, 
    users_email        VARCHAR2(45)     NULL, 
    users_authority    CHAR(1)          NOT NULL, 
    genre_no          NUMBER(5, 0)     NOT NULL, 
    users_address      VARCHAR2(300)    DEFAULT 'none' NULL, 
    CONSTRAINT users_PK PRIMARY KEY (users_id)
)
;

--게시판 테스트용 더미데이터
INSERT INTO GENRE 
VALUES (GENRE_SEQ.NEXTVAL, 'text');
INSERT INTO USERS 
VALUES (USERS_SEQ.NEXTVAL, 'ee', '1234','eee','01012341234','eeee','m',1,'');
SELECT * FROM USERS;
DROP TABLE users;

CREATE SEQUENCE users_SEQ
START WITH 1
INCREMENT BY 1;


COMMENT ON TABLE users IS '회원';


COMMENT ON COLUMN users.users_no IS '회원 번호'
;

COMMENT ON COLUMN users.users_id IS '회원 아이디'
;

COMMENT ON COLUMN users.users_pw IS '회원 비밀번호'
;


COMMENT ON COLUMN users.users_name IS '회원 이름'
;

COMMENT ON COLUMN users.users_phone IS '회원 전화번호'
;

COMMENT ON COLUMN users.users_email IS '회원 이메일'
;

COMMENT ON COLUMN users.users_authority IS '회원 권한'
;

COMMENT ON COLUMN users.genre_no IS '장르 번호'
;

COMMENT ON COLUMN users.users_address IS '회원 주소'
;

ALTER TABLE users
    ADD CONSTRAINT FK_users_genre FOREIGN KEY (genre_no)
        REFERENCES genre (genre_no)
;




-- genre Table Create SQL
CREATE TABLE studio
(
    studio_no           NUMBER(18, 0)    NOT NULL, 
    studio_name         VARCHAR2(45)     NOT NULL, 
    studio_president    VARCHAR2(45)     NOT NULL, 
    genre_no            NUMBER(5, 0)     NOT NULL, 
    studio_phone        CHAR(12)         DEFAULT 'none' NOT NULL, 
    studio_address      VARCHAR2(40)     NOT NULL, 
    studio_address2     VARCHAR2(40)     NOT NULL, 
    CONSTRAINT STUDIO_PK PRIMARY KEY (studio_no)
)
;

CREATE SEQUENCE studio_SEQ
START WITH 1
INCREMENT BY 1;
;

COMMENT ON TABLE studio IS '공방'
;

COMMENT ON COLUMN studio.studio_no IS '공방 번호'
;

COMMENT ON COLUMN studio.studio_name IS '공방 이름'
;

COMMENT ON COLUMN studio.studio_president IS '공방 대표이름'
;

COMMENT ON COLUMN studio.genre_no IS '장르 번호'
;

COMMENT ON COLUMN studio.studio_phone IS '공방 전화번호'
;

COMMENT ON COLUMN studio.studio_address IS '공방 주소'
;

COMMENT ON COLUMN studio.studio_address2 IS '공방 상세주소'
;

ALTER TABLE studio
    ADD CONSTRAINT FK_studio_genre_no_genre_genre FOREIGN KEY (genre_no)
        REFERENCES genre (genre_no)
;


-- genre Table Create SQL
CREATE TABLE classes
(
    classes_no           NUMBER(18, 0)    NOT NULL, 
    classes_name         VARCHAR2(45)     NOT NULL, 
    classes_content      VARCHAR2(300)    NOT NULL, 
    genre_no             NUMBER(5, 0)     NOT NULL, 
    classes_teacher      VARCHAR2(45)     NOT NULL, 
    classes_price        NUMBER(10,0)     NOT NULL, 
    classes_limit        NUMBER(3, 0)     NOT NULL, 
    classes_state        CHAR(1)          NOT NULL, 
    classes_start        DATE             NOT NULL, 
    classes_end          DATE             NOT NULL, 
    studio_no            NUMBER(18, 0)    NOT NULL, 
    classes_apply_num    NUMBER(18, 0)    DEFAULT 0 NOT NULL, 
    classes_day          VARCHAR2(20)     NOT NULL, 
    CONSTRAINT CLASSES_PK PRIMARY KEY (classes_no)
)
;

ALTER TABLE classes ADD classes_registerDate DATE DEFAULT SYSDATE;
SELECT * FROM CLASSES c ;

CREATE SEQUENCE classes_SEQ
START WITH 1
INCREMENT BY 1;
;

COMMENT ON TABLE classes IS '클래스'
;

COMMENT ON COLUMN classes.classes_no IS '클래스 번호'
;

COMMENT ON COLUMN classes.classes_name IS '클래스 이름'
;

COMMENT ON COLUMN classes.classes_content IS '클래스 내용'
;

COMMENT ON COLUMN classes.genre_no IS '장르 번호'
;

COMMENT ON COLUMN classes.classes_teacher IS '클래스 강사'
;

COMMENT ON COLUMN classes.classes_price IS '클래스 가격'
;

COMMENT ON COLUMN classes.classes_limit IS '클래스 정원'
;

COMMENT ON COLUMN classes.classes_state IS '클래스 상태'
;

COMMENT ON COLUMN classes.classes_start IS '클래스 시작'
;

COMMENT ON COLUMN classes.classes_end IS '클래스 끝'
;

COMMENT ON COLUMN classes.studio_no IS '공방 번호'
;

COMMENT ON COLUMN classes.classes_apply_num IS '클래스 신청 수'
;

ALTER TABLE classes
    ADD CONSTRAINT FK_classes_studio_no_studio_st FOREIGN KEY (studio_no)
        REFERENCES studio (studio_no)
;

ALTER TABLE classes
    ADD CONSTRAINT FK_classes_genre_no_genre_genr FOREIGN KEY (genre_no)
        REFERENCES genre (genre_no)
;


-- genre Table Create SQL
CREATE TABLE orderstatus
(
    orderlist_state_no    NUMBER(18, 0)    NOT NULL, 
    orderlist_state       VARCHAR2(128)    NOT NULL, 
    CONSTRAINT ORDERSTATUS_PK PRIMARY KEY (orderlist_state_no)
)
;

CREATE SEQUENCE orderstatus_SEQ
START WITH 1
INCREMENT BY 1;
;

COMMENT ON COLUMN orderstatus.orderlist_state_no IS '주문 상태 번호'
;

COMMENT ON COLUMN orderstatus.orderlist_state IS '주문 상태'
;


-- genre Table Create SQL
CREATE TABLE orderlist
(
    classes_no            NUMBER(18, 0)    NOT NULL, 
    users_id               VARCHAR2(20)     NOT NULL, 
    orderlist_date        DATE             DEFAULT SYSDATE NOT NULL, 
    payment_date          DATE             NOT NULL, 
    orderlist_state_no    NUMBER(18, 0)    DEFAULT 1 NOT NULL, 
    orderlist_no          NUMBER(18, 0)    NOT NULL, 
    studio_no             NUMBER(18, 0)    NOT NULL, 
    choice_start_date     DATE             NOT NULL, 
    choice_end_date       DATE             NOT NULL, 
    CONSTRAINT ORDERLIST_PK PRIMARY KEY (orderlist_no)
)
;

ALTER TABLE ORDERLIST ADD quantity NUMBER DEFAULT 1;

SELECT * FROM (SELECT * FROM CLASSES ORDER BY classes_REGISTERDATE ASC) WHERE 4 >= ROWNUM;

SELECT * FROM CLASSES c ;

ALTER TABLE ORDERLIST ADD reservation_no NUMBER DEFAULT 0;

CREATE SEQUENCE orderlist_SEQ
START WITH 1
INCREMENT BY 1;


COMMENT ON TABLE orderlist IS '주문내역'
;

COMMENT ON COLUMN orderlist.classes_no IS '클래스 번호'
;

COMMENT ON COLUMN orderlist.users_id IS '회원 아이디'
;
 
COMMENT ON COLUMN orderlist.orderlist_date IS '주문 날짜'
;

COMMENT ON COLUMN orderlist.payment_date IS '결제 날짜'
;

COMMENT ON COLUMN orderlist.orderlist_state_no IS '주문 상태 번호'
;

COMMENT ON COLUMN orderlist.orderlist_no IS '주문 번호'
;

COMMENT ON COLUMN orderlist.studio_no IS '공방 번호'
;

COMMENT ON COLUMN orderlist.choice_start_date IS '선택 시작 날짜'
;

COMMENT ON COLUMN orderlist.choice_end_date IS '선택 끝 날짜'
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_classes_no_classe FOREIGN KEY (classes_no)
        REFERENCES classes (classes_no)
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_users_id FOREIGN KEY (users_id)
        REFERENCES users (users_id)
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_studio_no_studio_ FOREIGN KEY (studio_no)
        REFERENCES studio (studio_no)
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_orderlist_state_n FOREIGN KEY (orderlist_state_no)
        REFERENCES orderstatus (orderlist_state_no)
;


-- genre Table Create SQL
CREATE TABLE message
(
    send_id            VARCHAR2(20)     NOT NULL, 
    receive_id         VARCHAR2(20)     NOT NULL, 
    message_title      VARCHAR2(100)    NOT NULL, 
    message_content    VARCHAR2(300)    NOT NULL, 
    receive_date       DATE             DEFAULT SYSDATE NOT NULL, 
    message_no         NUMBER(18, 0)    NOT NULL, 
    CONSTRAINT MESSAGE_PK PRIMARY KEY (message_no)
)
;

CREATE SEQUENCE message_SEQ
START WITH 1
INCREMENT BY 1;
;

COMMENT ON TABLE message IS '쪽지함'
;

COMMENT ON COLUMN message.send_id IS '수신 아이디'
;

COMMENT ON COLUMN message.receive_id IS '보낸 아이디'
;

COMMENT ON COLUMN message.message_title IS '쪽지 제목'
;

COMMENT ON COLUMN message.message_content IS '쪽지 내용'
;

COMMENT ON COLUMN message.receive_date IS '보낸 날짜'
;

COMMENT ON COLUMN message.message_no IS '쪽지 번호'
;

ALTER TABLE message
    ADD CONSTRAINT FK_message_send_id FOREIGN KEY (send_id)
        REFERENCES users (users_id)
;

-- genre Table Create SQL
CREATE TABLE classes_image
(
    classes_no    NUMBER(18, 0)    NOT NULL, 
    image_path    VARCHAR2(100)    NULL   
)
;

COMMENT ON TABLE classes_image IS '클래스 이미지'
;

COMMENT ON COLUMN classes_image.classes_no IS '클래스 번호'
;

COMMENT ON COLUMN classes_image.image_path IS 'url'
;

ALTER TABLE classes_image
    ADD CONSTRAINT FK_classes_image_classes_no_cl FOREIGN KEY (classes_no)
        REFERENCES classes (classes_no)
;

SELECT SESSIONTIMEZONE, CURRENT_DATE  FROM dual;


-- genre Table Create SQL
CREATE TABLE orderlist
(
    classes_no            NUMBER(18, 0)    NOT NULL, 
    users_id               VARCHAR2(20)     NOT NULL, 
    orderlist_date        DATE             DEFAULT SYSDATE NOT NULL, 
    payment_date          DATE             NOT NULL, 
    orderlist_state_no    NUMBER(18, 0)    DEFAULT 1 NOT NULL, 
    orderlist_no          NUMBER(18, 0)    NOT NULL, 
    studio_no             NUMBER(18, 0)    NOT NULL, 
    choice_start_date     DATE             NOT NULL, 
    choice_end_date       DATE             NOT NULL, 
    CONSTRAINT ORDERLIST_PK PRIMARY KEY (orderlist_no)
)
;

ALTER TABLE ORDERLIST ADD quantity NUMBER DEFAULT 1;

SELECT * FROM (SELECT * FROM CLASSES ORDER BY classes_REGISTERDATE ASC) WHERE 4 >= ROWNUM;


ALTER TABLE ORDERLIST ADD reservation_no NUMBER DEFAULT 0;

CREATE SEQUENCE orderlist_SEQ
START WITH 1
INCREMENT BY 1;


COMMENT ON TABLE orderlist IS '주문내역'
;

COMMENT ON COLUMN orderlist.classes_no IS '클래스 번호'
;

COMMENT ON COLUMN orderlist.users_id IS '회원 아이디'
;
 
COMMENT ON COLUMN orderlist.orderlist_date IS '주문 날짜'
;

COMMENT ON COLUMN orderlist.payment_date IS '결제 날짜'
;

COMMENT ON COLUMN orderlist.orderlist_state_no IS '주문 상태 번호'
;

COMMENT ON COLUMN orderlist.orderlist_no IS '주문 번호'
;

COMMENT ON COLUMN orderlist.studio_no IS '공방 번호'
;

COMMENT ON COLUMN orderlist.choice_start_date IS '선택 시작 날짜'
;

COMMENT ON COLUMN orderlist.choice_end_date IS '선택 끝 날짜'
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_classes_no_classe FOREIGN KEY (classes_no)
        REFERENCES classes (classes_no)
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_users_id FOREIGN KEY (users_id)
        REFERENCES users (users_id)
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_studio_no_studio_ FOREIGN KEY (studio_no)
        REFERENCES studio (studio_no)
;

ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_orderlist_state_n FOREIGN KEY (orderlist_state_no)
        REFERENCES orderstatus (orderlist_state_no)
;
ALTER TABLE orderlist
    ADD orderer_info_no  NUMBER(18, 0);    
;
ALTER TABLE orderlist
    ADD CONSTRAINT FK_orderlist_orderer_info_no FOREIGN KEY (orderer_info_no)
        REFERENCES orderer_info (orderer_info_no)
;
CREATE SEQUENCE orderer_info_SEQ
START WITH 1
INCREMENT BY 1;


-- genre Table Create SQL
CREATE TABLE orderer_info
(
    orderer_info_no          NUMBER(18, 0)    NOT NULL, 
    users_name         VARCHAR2(45)     NOT NULL, 
    users_phone        CHAR(12)         NOT NULL, 
    users_email        VARCHAR2(45)     NULL, 
    CONSTRAINT orderer_info_PK PRIMARY KEY (orderer_info_no)
)
;
ALTER TABLE orderer_info
    ADD total_price NUMBER(10, 0)   ;
;

TRUNCATE TABLE ORDERLIST ;

--비회원용 임시 저장 정보
-- 로그인 못하게 막기
INSERT INTO USERS 
VALUES (0, 'temp', '0000','name','phone','email',1, 'address', 'address_detail',00000, 6, 1);

ALTER TABLE CLASSES_INFO ADD CLASSES_INDIVIDUAL_STATE NUMBER;