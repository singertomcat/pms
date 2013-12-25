drop table if exists ss_task;
drop table if exists ss_user;

create table ss_task (
	id bigint generated by default as identity,
	title varchar(128) not null,
	description varchar(255),
	user_id bigint not null,
    primary key (id)
);

create table ss_user (
	id bigint generated by default as identity,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp not null,
	primary key (id)
);


create table PMS_GROUP(
	id bigint generated by default as identity,
	code varchar(64) not null unique,
	name varchar(255) not null,
	user_id bigint  ,
	description varchar(255),
	parent_id bigint  ,
	primary key (id)
);
create table pms_keywords(
	id bigint generated by default as identity,
	code varchar(64) not null unique,
	name varchar(255) not null,
	description varchar(255),
	key_words varchar(500)  ,
	primary key (id)
);
create table PMS_LEVEL(
	id bigint generated by default as identity,
	code varchar(64) not null unique,
	name varchar(255) not null,
	description varchar(255),
	user_id bigint  ,
	parent_id bigint  ,
	primary key (id)
);
create table PMS_EMAIL(
	id bigint generated by default as identity,
	code varchar(64) not null unique,
	name varchar(255) not null,
	email varchar(255) not null,
	description varchar(255),
	user_id bigint  ,
	primary key (id)
);

create table pms_rule(
	id bigint generated by default as identity,
	code varchar(64) not null unique,
	name varchar(255) not null,
	rule varchar(500) not null,
	description varchar(255),
	primary key (id)
);

create table pms_url(
	id bigint generated by default as identity,
	code varchar(64) not null unique,
	name varchar(255)   null,
	province varchar(255)   null,
	department varchar(255)   null,
	module varchar(255)   null,
	submodule varchar(255)   null,
	url varchar(1000)   null,	
	url_prefix varchar(1000) ,
	subj_path varchar(1000) ,
	subj_replace  varchar(1000) ,
	link_path  varchar(1000) ,
	date_path varchar(1000) ,
	date_replace  varchar(1000) ,	
	rule_id bigint,
	task_id bigint,
	charset varchar(20),
	start_begin bigint,
	description varchar(255),
	level_id bigint,
	group_id bigint,
	primary key (id)
);

create table pms_task(
	id bigint generated by default as identity,
	code varchar(64) not null unique,
	name varchar(255) not null,
	cron varchar(255) not null,
	description varchar(1000),
	duration varchar(200),
	url_rule varchar(200),
	end_date timestamp,
	start_date timestamp,
	status varchar(30),
	catch_type varchar(20),
	primary key (id)
);


create table pms_taskhistory(
	id bigint generated by default as identity,
	task_id bigint,
	start_date timestamp,
	end_date timestamp,
	duration varchar(100),
	status varchar(100),
	logInfo varchar(3000),
	primary key (id)
);
create table pms_urlhistory(
	id bigint generated by default as identity,
	task_id bigint,
	url_id bigint,
	task_history_id bigint,
	start_date timestamp,
	end_date timestamp,
	duration varchar(100),
	status varchar(100),
	subjects varchar(1000),
	primary key (id)
);

create table pms_subjects(
	id bigint generated by default as identity,
	url_id bigint,
	level_id bigint,
	group_id bigint,
	from_url varchar(1000) null,
	subject varchar(500)   null,
	publish_date timestamp   null,
	publish_date_time timestamp   null,
	subj_url varchar(1000)   null,
	content varchar(3000)   null,
	catch_time timestamp   null,
	relative_url  varchar(1000)   null,
	dr int default 0,
	primary key (id)
);
