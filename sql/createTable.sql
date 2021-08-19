
create table script_config(
    id integer not null auto_increment comment '主键id',
    system_name  varchar(100) not null comment '系统名称',
    namespace_name varchar(100) not null comment '命名空间名称',
    namespace_desc varchar(200) not null comment '命名空间描述',
    created_by VARCHAR(32)    COMMENT '创建人' ,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_by VARCHAR(32)    COMMENT '更新人' ,
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
)COMMENT = '脚本配置表';


create table script_config_details(
    id integer not null auto_increment comment '主键id',
    config_id integer not null comment '配置表id',
    method_type varchar(32) not null comment '方法类型',
    method_id varchar(32) not null comment '方法id',
    method_name varchar(200) not null comment '方法描述',
    method_sql varchar(2000) not null comment '查询sql',
    param_map varchar(2000)  DEFAULT null comment '方法参数集',
    result_map varchar(2000) DEFAULT null comment '方法返回集',
    created_by VARCHAR(32)    COMMENT '创建人' ,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_by VARCHAR(32)    COMMENT '更新人' ,
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
)COMMENT = '脚本配置明细表';