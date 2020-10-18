-- auto Generated on 2020-01-02 10:41:04 
-- DROP TABLE IF EXISTS `scripts`; 
CREATE TABLE `scripts`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
    `cron` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'cron',
    `job_class` VARCHAR (100) NOT NULL DEFAULT '' COMMENT 'jobClass',
    `last_exec_time` DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT 'lastExecTime',
    `result` VARCHAR (500) NOT NULL DEFAULT '' COMMENT 'result',
    `status` INT (11) NOT NULL DEFAULT -1 COMMENT 'status',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`scripts`';
