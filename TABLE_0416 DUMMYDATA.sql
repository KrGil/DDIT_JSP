desc board;
desc notice;
-- NOTICE
CREATE SEQUENCE BO_SEQ START WITH 1 INCREMENT BY 1;
INSERT INTO notice (
    bo_no,
    bo_title,
    bo_content,
    bo_date,
    bo_hit,
    bo_rec
) 
SELECT BO_SEQ.NEXTVAL, '더미 공지 게시글 제목'||ROWNUM,
    '더미 공지 게시글의 내용<br/> 임의로 작성한 내용',
    SYSDATE - (500 - ROWNUM), TRUNC(DBMS_RANDOM.VALUE() * 1000),
    TRUNC(DBMS_RANDOM.VALUE() * 1000)
FROM DUAL
CONNECT BY LEVEL < 500;

SELECT TRUNC(DBMS_RANDOM.VALUE() * 1000) FROM DUAL;

COMMIT;
SELECT BO_NO, BO_DATE
FROM NOTICE;


-- BOARD
-- 한사람당 10개씩 글을 작성했다는 더미 데이터 만들기
INSERT INTO board (
    bo_no,  bo_title,   bo_writer,
    bo_pass, bo_content,    bo_date,
    bo_hit,    bo_rec,    bo_rep,
    bo_sec,    bo_parent
) 
SELECT BO_SEQ.NEXTVAL, MEM_NAME||'님이 작성한 글', MEM_NAME,
MEM_PASS, MEM_NAME||'이 작성한 글의 내용<br/>임의로 작성한 내용',
SYSDATE-(400 - ROWNUM), 
TRUNC(DBMS_RANDOM.VALUE() *1000), 
TRUNC(DBMS_RANDOM.VALUE() *1000), 
0,'N', NULL
FROM MEMBER,
(
SELECT ROWNUM RNUM
FROM DUAL
CONNECT BY LEVEL < 11);

SELECT BO_NO, BO_DATE
FROM BOARD;

ROLLBACK;
COMMIT;

SELECT * 
FROM(
SELECT BO_NO, BO_DATE, BO_TITLE, 'NOTICE' BO_TYPE
FROM NOTICE
UNION ALL
SELECT BO_NO, BO_DATE, BO_TITLE, 'BOARD' BO_TYPE
FROM BOARD
)
ORDER BY BO_NO DESC;

SELECT DECODE(TRUNC(ROWNUM/4), 0, 1, 999) BO_SORT, NOTICE.*
FROM(
    SELECT BO_NO, BO_DATE, BO_TITLE, 'NOTICE' BO_TYPE
    FROM NOTICE 
    ORDER BY BO_NO DESC
) NOTICE
ORDER BY BO_SORT ASC, BO_NO ASC;

-- 가상의 뷰로 만들기
CREATE OR REPLACE VIEW BOARDVIEW AS
SELECT *
FROM(
SELECT DECODE(TRUNC(ROWNUM/4),0, 1, 999) BO_SORT, NOTICE.*
FROM(
SELECT  'NOTICE' BO_TYPE,
   bo_no,    bo_title,    '관리자' BO_WRITER,
   NULL BO_PASS,    bo_content,bo_date,    bo_hit,
    bo_rec ,NULL BO_REP, NULL BO_SEQ, NULL BO_PARENT
FROM NOTICE
ORDER BY BO_NO DESC
)NOTICE
UNION ALL
SELECT
 2 BO_SORT, 'BOARD' BO_TYPE,
   bo_no,    bo_title,    bo_writer,
    bo_pass,    bo_content,bo_date,    bo_hit,
    bo_rec ,bo_rep, bo_sec, bo_parent
FROM BOARD
);


SELECT *
FROM BOARDVIEW
ORDER BY BO_SORT ASC, BO_DATE DESC;

SELECT 'private '||
    DECODE(DATA_TYPE, 'NUMBER', 'Integer ', 'String ')||
    LOWER(COLUMN_NAME)||';'
FROM cols
WHERE table_name = 'REPLY2';

desc board;
SELECT * FROM BOARD;

SELECT B.*
FROM(
    SELECT A.*, ROWNUM-3 RNUM
    FROM(
        SELECT *
        FROM BOARDVIEW
        ORDER BY BO_SORT ASC, BO_DATE DESC
        WHERE INSTR(BO_TITLE, 'asd') > 0 
        ) A
    )B
WHERE BO_SORT = 1 OR RNUM >= 51 AND RNUM <= 60;


SELECT *
FROM BOARDVIEW
WHERE BO_SORT = 1 OR INSTR(BO_TITLE, 'asd') > 0 
ORDER BY BO_SORT ASC, BO_DATE DESC;
select *
from boardview
where INSTR(BO_TITLE, 'asd') > 0 ;

select *
from boardview
where bo_no = 1796;



SELECT '<tr><th>' || COMMENTS ||
    '</th><td><%=' ||
    LOWER(TABLE_NAME) ||
    '.get'|| SUBSTR(COLUMN_NAME, 1, 1)||
    LOWER(SUBSTR(COLUMN_NAME, 2)) ||
    '() %></td></tr>'
FROM USER_COL_COMMENTS
WHERE TABLE_NAME = 'BOARDVIEW';

SELECT *
		FROM BOARDVIEW
		WHERE BO_NO = 598; 

desc boardview;

SELECT 'private '||
    DECODE(DATA_TYPE, 'NUMBER', 'Integer ', 'String ')||
    LOWER(COLUMN_NAME)||';'
FROM cols
WHERE table_name = 'MEMBER';