CREATE TABLE `schedule` (
                            `id`	BIGINT	NOT NULL	COMMENT '일정ID',
                            `schedule`	VARCHAR(400)	NOT NULL	COMMENT '할일',
                            `name`	VARCHAR(50)	NOT NULL	COMMENT '작성자명',
                            `password`	VARCHAR(50)	NOT NULL	COMMENT '비밀번호',
                            `in_dtm`	DATETIME	NOT NULL	DEFAULT now()	COMMENT '작성일',
                            `up_dtm`	DATETIME	NOT NULL	DEFAULT now()	COMMENT '수정일'
);

ALTER TABLE `schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
                                                                 `id`
    );

alter table schedule
    modify id bigint auto_increment comment '일정ID';

alter table schedule
    auto_increment = 1;