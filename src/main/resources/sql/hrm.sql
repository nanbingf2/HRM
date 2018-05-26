/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/23 0:39:21                            */
/*==============================================================*/


drop table if exists t_dept;

drop table if exists t_document;

drop table if exists t_employee;

drop table if exists t_job;

drop table if exists t_notice;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_dept                                                */
/*==============================================================*/
create table t_dept
(
   id                   int not null auto_increment,
   remark               varchar(32),
   name                 varchar(320),
   primary key (id)
);

alter table t_dept comment '部门表';

/*==============================================================*/
/* Table: t_document                                            */
/*==============================================================*/
create table t_document
(
   id                   int not null auto_increment,
   filename             varchar(64),
   create_date          timestamp default CURRENT_TIMESTAMP,
   path                 varchar(320),
   user_id              int,
   remark               varchar(300),
   primary key (id)
);

alter table t_document comment '文件表';

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
create table t_employee
(
   id                   int not null auto_increment,
   name                 varchar(32),
   card_id              varchar(32),
   address              varchar(32),
   phone                varchar(32),
   sex                  varchar(16),
   birthday             date,
   hobby                varchar(64),
   dept_id              int,
   job_id               int,
   primary key (id)
);

alter table t_employee comment '员工表';

/*==============================================================*/
/* Table: t_job                                                 */
/*==============================================================*/
create table t_job
(
   id                   int not null auto_increment,
   name                 varchar(32),
   remark               varchar(320),
   primary key (id)
);

alter table t_job comment '职位表';

/*==============================================================*/
/* Table: t_notice                                              */
/*==============================================================*/
create table t_notice
(
   id                   int not null auto_increment,
   title                varchar(64),
   content              longtext,
   create_date          timestamp default CURRENT_TIMESTAMP,
   user_id              int,
   primary key (id)
);

alter table t_notice comment '通知表';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   int not null auto_increment,
   loginname            varchar(32),
   password             varchar(32),
   status               int default 1,
   createdate           timestamp default CURRENT_TIMESTAMP,
   username             varchar(32),
   primary key (id)
);

alter table t_user comment '用户表';

alter table t_document add constraint FK_document_user foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

alter table t_employee add constraint FK_employee_dept foreign key (dept_id)
      references t_dept (id) on delete restrict on update restrict;

alter table t_employee add constraint FK_employee_job foreign key (job_id)
      references t_job (id) on delete restrict on update restrict;

alter table t_notice add constraint FK_notice_user foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

