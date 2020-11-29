CREATE SEQUENCE cart_SEQ
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 100000;

CREATE TABLE cart
(
	cart_no NUMBER(18, 0) NOT NULL, 
    cart_date DATE DEFAULT SYSDATE NOT NULL, 
    cart_quantity NUMBER(18, 0) NOT NULL, 
    choice_date     DATE             NOT NULL, 
    classes_info_no       NUMBER(18, 0)             NOT NULL, 
    classes_no NUMBER(18, 0) NOT NULL, 
    users_id VARCHAR2(20) NOT NULL, 
    CONSTRAINT CART_PK PRIMARY KEY (cart_no)
)
;


ALTER TABLE cart
    ADD CONSTRAINT FK_cart_calsses_no FOREIGN KEY (classes_no)
        REFERENCES classes (classes_no  )
;
ALTER TABLE cart
    ADD CONSTRAINT FK_cart_user_id FOREIGN KEY (users_id)
        REFERENCES users (users_id)
;
ALTER TABLE cart
    ADD CONSTRAINT FK_classes_info_no FOREIGN KEY (classes_info_no)
        REFERENCES classes_info (classes_info_no)
;
/*ALTER TABLE CART 
RENAME COLUMN choice_start_date TO choice_date;

ALTER TABLE CART 
MODIFY (choice_end_date NUMBER(18, 0));
;
ALTER TABLE CART 
RENAME COLUMN choice_end_date TO classes_info_no;*/

INSERT INTO CART VALUES(cart_SEQ.NEXTVAL, SYSDATE, 1, 
	'2020-11-05', '2020-11-05', 4, 'test1')
;
INSERT INTO CART VALUES(cart_SEQ.NEXTVAL, SYSDATE, 1, 
	'2020-11-21', '2020-11-21', 4, 'ee')
;
INSERT INTO CART VALUES(cart_SEQ.NEXTVAL, SYSDATE, 2, 
	'2020-11-10', '2020-11-10', 3, 'ee')
;
UPDATE CART SET 
				CART_QUANTITY = 
					(SELECT CART_QUANTITY FROM CART 
					WHERE CLASSES_NO = ${classes_no} 
					AND USERS_ID = ${users_id} 
					AND CHOICE_START_DATE = ${choice_start_date})-1 
						WHERE CLASSES_NO = ${classes_no} 
						AND USERS_ID =${users_id}
						AND CHOICE_START_DATE = ${choice_start_date}		;

SELECT * FROM CART 
;


DELETE FROM CART WHERE USERS_ID = 'test1';
TRUNCATE TABLE cart;

SELECT * FROM CLASSES_INFO WHERE CLASSES_NO = ${classes_no} 
AND CLASSES_DATE =${calsses_date};

INSERT INTO CLASSES_INFO VALUES(4, classes_info_SEQ.NEXTVAL, '2020-12-20', 0, 10);
;  
	SELECT COUNT(*) FROM CLASSES_INFO 
			WHERE CLASSES_NO = ${classes_no} AND CLASSES_INDIVIDUAL_STATE = ${classes_individual_state};
