UPDATE member
    SET
        a = b
WHERE
    mem_id =:v0
    AND   mem_pass =:v1
    AND   mem_name =:v2
    AND   mem_regno1 =:v3
    AND   mem_regno2 =:v4
    AND   mem_bir =:v5
    AND   mem_zip =:v6
    AND   mem_add1 =:v7
    AND   mem_add2 =:v8
    AND   mem_hometel =:v9
    AND   mem_comtel =:v10
    AND   mem_hp =:v11
    AND   mem_mail =:v12
    AND   mem_job =:v13
    AND   mem_like =:v14
    AND   mem_memorial =:v15
    AND   mem_memorialday =:v16
    AND   mem_mileage =:v17
    AND   mem_delete =:v18;


-- 최대 몇개까지 가능한지
SHOW PARAMETER SESSIONS;
-- 몇개까지 허용하는지 알기
SHOW PARAMETER PROCESSES;

ALTER SYSTEM 
SET PROCESSES = 200 SCOPE=SPFILE;