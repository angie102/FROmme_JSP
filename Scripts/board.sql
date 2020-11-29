-- genre Table Create SQL
CREATE TABLE category
(
    category_no      NUMBER(18, 0)    NOT NULL, 
    category_name    VARCHAR2(100)    NOT NULL, 
    CONSTRAINT CATEGORI_PK PRIMARY KEY (category_no)
)
;
SELECT * FROM CATEGORY;
CREATE SEQUENCE category_SEQ
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 100000;

COMMENT ON TABLE categori IS '게시판 카테고리'
;

COMMENT ON COLUMN categori.category_no IS '카테고리 번호'
;

COMMENT ON COLUMN categori.category_name IS '카테고리 이름'
;

SELECT * FROM CATEGORY;

-- genre Table Create SQL
CREATE TABLE post
(
    post_no         NUMBER(18, 0)    NOT NULL, 
    post_title      VARCHAR2(100)    NOT NULL, 
    post_content    VARCHAR2(300)    NOT NULL, 
    post_date       DATE             DEFAULT SYSDATE NOT NULL, 
    users_id         VARCHAR2(20)     NOT NULL, 
    category_no     NUMBER(18, 0)    NOT NULL, 
    post_views      NUMBER(18, 0)    DEFAULT 0 NOT NULL, 
    CONSTRAINT POST_PK PRIMARY KEY (post_no)
)
;

ALTER TABLE post MODIFY post_content varchar2(4000); 


CREATE VIEW POST_AUTHORITY
AS
SELECT PO.*, U.USERS_AUTHORITY FROM POST PO, USERS U WHERE PO.USERS_ID = U.USERS_ID;

SELECT * FROM POST_AUTHORITY;

SELECT P.USERS_AUTHORITY
  FROM POST_AUTHORITY P LEFT JOIN REPLY R ON P.POST_NO = R.POST_NO;


CREATE VIEW POST_VIEW
AS
SELECT P.POST_NO, P.POST_TITLE,P.POST_CONTENT,P.POST_DATE,
       P.USERS_ID,P.CATEGORY_NO,P.POST_VIEWS, P.USERS_AUTHORITY,P.POST_PUB, COUNT(R.REPLYS_ID) REPLY_COUNT
FROM POST_AUTHORITY P LEFT JOIN REPLY R 
ON P.POST_NO  = R.POST_NO
GROUP BY P.POST_NO, P.POST_TITLE,P.POST_CONTENT,P.POST_DATE,
         P.USERS_ID,P.CATEGORY_NO,P.POST_VIEWS,P.POST_PUB, P.USERS_AUTHORITY;

--테스트용 더미데이터
INSERT INTO POST 
VALUES (POST_SEQ.NEXTVAL, '제목', '내용', sysdate,'ee',1,0);

SELECT SYSDATE FROM dual;

select dbtimezone from dual;
select sessiontimezone from dual;
ALTER DATABASE SET time_zone = '+09:00';

SELECT * FROM POST ORDER BY post_no desc;
COMMIT;

CREATE SEQUENCE post_SEQ
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 100000;

COMMENT ON TABLE post IS '게시판'
;

COMMENT ON COLUMN post.post_no IS '게시글 번호'
;

COMMENT ON COLUMN post.post_title IS '게시글 제목'
;

COMMENT ON COLUMN post.post_content IS '게시글 내용'
;

COMMENT ON COLUMN post.post_date IS '게시글 날짜'
;

COMMENT ON COLUMN post.users_id IS '회원 아이디'
;

COMMENT ON COLUMN post.category_no IS '카테고리 번호'
;

ALTER TABLE post
    ADD CONSTRAINT FK_post_users_id FOREIGN KEY (users_id)
        REFERENCES users (users_id)
;

ALTER TABLE post
    ADD CONSTRAINT FK_post_category FOREIGN KEY (category_no)
        REFERENCES categori (category_no)
;

-- genre Table Create SQL
CREATE TABLE reply
(
    post_no             NUMBER(18, 0)    NOT NULL, 
    replys_no         NUMBER(18, 0)    NOT NULL, 
    replys_id         VARCHAR2(20)     NOT NULL, 
    replys_content    VARCHAR2(300)    NOT NULL, 
    replys_date       DATE             DEFAULT SYSDATE NOT NULL, 
    CONSTRAINT reply_PK PRIMARY KEY (replys_no)
)
;

CREATE SEQUENCE reply_SEQ
START WITH 1
INCREMENT BY 1;
;

COMMENT ON TABLE reply IS '댓글'
;

COMMENT ON COLUMN reply.post_no IS '게시글 번호'
;

COMMENT ON COLUMN reply.replys_no IS '댓글 번호'
;

COMMENT ON COLUMN reply.replys_id IS '댓글 아이디'
;

COMMENT ON COLUMN reply.replys_content IS '댓글 내용'
;

COMMENT ON COLUMN reply.replys_date IS '댓글 날짜'
;

ALTER TABLE reply
    ADD CONSTRAINT FK_comment_post_no FOREIGN KEY (post_no)
        REFERENCES post (post_no)
;


CREATE TABLE image
(
    post_no       NUMBER(18, 0)    NOT NULL, 
    image_path    VARCHAR2(100)    NOT NULL
)
;
SELECT * FROM IMAGE;

COMMENT ON TABLE image IS 'key + 일련번호'
;

COMMENT ON COLUMN image.post_no IS '게시글 번호'
;

COMMENT ON COLUMN image.image_path IS 'url'
;

ALTER TABLE image
    ADD CONSTRAINT FK_image_post_no_post_post_no FOREIGN KEY (post_no)
        REFERENCES post (post_no)
;