use base;
drop table if exists job_required_ability;
drop table if exists staff_ability;
drop table if exists ability;
drop table if exists staff;
drop table if exists job;
drop table if exists department;
drop table if exists user;

create table user
(
    id       varchar(32) not null comment 'id',
    username     varchar(20) not null comment '用户名',
    password varchar(100) not null comment '密码',
    role varchar(20) not null comment '角色',
    update_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

create table department
(
    id        varchar(32) not null comment 'department_id',
    name      varchar(20) not null comment '部门名称',
    staff_num int(20) comment '人员数量',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='部门表';


create table job
(
    id             varchar(32)  not null comment 'job_id',
    name           varchar(100) not null comment '岗位名称',
    requirement    varchar(256) not null comment '岗位要求',
    number         int(10)               default 0 comment '招聘人数，可不显示',
    current_number int(10)               default 0 comment '应聘人数，可不显示',
    salary         int(10)               default -1 comment '薪资，-1表示不显示',
    department_id  varchar(32)  not null comment '部门',
    create_time    TIMESTAMP    not null default CURRENT_TIMESTAMP comment '创建时间',
    update_time    TIMESTAMP    not null default CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP comment '更新时间',
    foreign key (department_id) references department (id) on delete cascade on update cascade,
    check ( current_number <= number),
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='岗位表';


create table staff
(
    id                 varchar(32)  not null comment '唯一id',
    name               varchar(100) not null comment 'name',
    gender             varchar(2)   not null comment '性别',
    age                int(10)      not null comment '年龄',
    degree             int          NOT NULL comment '学历，0-中专，1-大专，2-本科，3-研究生，4-博士',
    school             varchar(200) NOT NULL comment '毕业学校',
    working_years      int          NOT NULL comment '工作年限',
    edu_exp            varchar(500) comment '教育经历/限制500字',
    job_exp            varchar(500) comment '工作经验/限制500字',
    project_exp        varchar(500) comment '项目经历/限制500字',
    job_id             varchar(32)  NOT NULL comment '应聘职位',
    employ_status      int(10)      NOT NULL comment '招聘状态，:-1:待面试，0：未录取，1：面试，2：试用期，3：已录用',
    interview_comments varchar(100) comment '面试官评语',
    interview_score    int(10) comment '面试综合评分',
    probation_score    int(10) comment '试用期评分',
    interviewers       varchar(100) comment '面试官成员，用“，”分隔',
    resume_path        varchar(256) not null default '/' comment '简历存储路径',
    speciality         varchar(256) comment '突出特点，面试官填写',
    interview_times    int(10)      not null default 0 comment '应聘次数',
    email              varchar(100) not null comment 'email',
    phone              varchar(11)  not null comment '手机号码',
    address            varchar(200) NOT NULL comment '家庭地址',
    city               varchar(200) NOT NULL comment '城市',
    create_time        TIMESTAMP    not null default CURRENT_TIMESTAMP comment '入库时间',
    update_time        TIMESTAMP    not null default CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP comment '更新时间',
    check (degree < 5 and degree >= 0),
    check (employ_status < 4 and employ_status >= -1),
    foreign key (job_id) references job (id) on delete cascade on update cascade,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='人员表';

create table ability
(
    id       varchar(32)  not null comment 'ability_id',
    name     varchar(100) not null comment '能力名称',
    classify varchar(100) not null comment '能力类别',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='能力表';

create table staff_ability
(
    staff_id   varchar(32) not null comment '人员',
    ability_id varchar(32) not null comment '能力',
    score      int(10)     not null default 0 comment '评分',
    check (score >= 0 and score <= 100),
    foreign key (staff_id) references staff (id) on delete cascade on update cascade,
    foreign key (ability_id) references ability (id) on delete cascade on update cascade,
    primary key (staff_id, ability_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='员工能力表';


create table job_required_ability
(
    job_id     varchar(32) not null comment '岗位',
    ability_id varchar(32) not null comment '能力',
    score      int(10)     not null default 0 comment '评分',
    check (score >= 0 and score <= 100),
    foreign key (job_id) references job (id) on delete cascade on update cascade,
    primary key (job_id, ability_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='岗位需求能力表';
















