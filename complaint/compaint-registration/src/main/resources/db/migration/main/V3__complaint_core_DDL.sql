-----------------START--------------------
CREATE TABLE complaintstatus (
    id bigint NOT NULL,
    name character varying(25),
    version bigint
);
CREATE SEQUENCE seq_complaintstatus
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ONLY complaintstatus
    ADD CONSTRAINT pk_complaintstatus PRIMARY KEY (id);

------------------END---------------------

-----------------START-------------------

CREATE SEQUENCE seq_complainttype_category;

CREATE TABLE complainttype_category(
	id numeric primary key,
	name varchar(100) unique,
	description varchar(250),
	version numeric default 0
);

------------------END---------------------
-----------------START--------------------
CREATE TABLE complainttype (
    id numeric NOT NULL,
    name character varying(150),
    department bigint,
    category bigint,
    code character varying(20),
    isactive boolean,
    description character varying(100),
    createddate timestamp without time zone,
    lastmodifieddate timestamp without time zone,
    createdby bigint,
    lastmodifiedby bigint,
    version bigint
);


CREATE SEQUENCE seq_complainttype
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY complainttype
    ADD CONSTRAINT pk_pgr_complainttype_id PRIMARY KEY (id);

ALTER TABLE ONLY complainttype
    ADD CONSTRAINT uk_complainttype_code UNIQUE (code);

ALTER TABLE ONLY complainttype
    ADD CONSTRAINT uk_pgr_complainttype_name UNIQUE (name);

ALTER TABLE ONLY complainttype
    ADD CONSTRAINT fk_pgr_complainttype_categoryid FOREIGN KEY (category) REFERENCES complainttype_category(id);

------------------END---------------------

-----------------START--------------------


CREATE TABLE complainant (
    id bigint NOT NULL,
    email character varying(100),
    mobile character varying(20),
    name character varying(150),
    address character varying(256),
    version bigint
);

CREATE SEQUENCE seq_complainant
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY complainant
    ADD CONSTRAINT pk_complainant PRIMARY KEY (id);

------------------END---------------------

-----------------START--------------------
CREATE TABLE complaint (
    id bigint NOT NULL,
    crn character varying(100),
    createddate timestamp without time zone,
    lastmodifieddate timestamp without time zone,
    createdby bigint,
    lastmodifiedby bigint,
    complainttype bigint NOT NULL,
    complainant bigint NOT NULL,
    assignee bigint,
    location bigint,
    details character varying(500) NOT NULL,
    landmarkdetails character varying(200),
    lat double precision,
    lng double precision,
    status bigint,
    department bigint,
    comments character varying(1024),
    version bigint
);


CREATE SEQUENCE seq_complaint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER TABLE ONLY complaint
    ADD CONSTRAINT pk_complaint PRIMARY KEY (id);

ALTER TABLE ONLY complaint
    ADD CONSTRAINT uk_complaint_crn UNIQUE (crn);


CREATE INDEX idx_pgr_complaint_complainttype ON complaint USING btree (complainttype);
CREATE INDEX idx_pgr_complaint_user ON complaint USING btree (assignee);

ALTER TABLE ONLY complaint
    ADD CONSTRAINT fk_complaint_ FOREIGN KEY (complainant) REFERENCES complainant(id);
ALTER TABLE ONLY complaint
    ADD CONSTRAINT fk_complaint_complainttype FOREIGN KEY (complainttype) REFERENCES complainttype(id);

ALTER TABLE ONLY complaint
    ADD CONSTRAINT fk_complaint_status FOREIGN KEY (status) REFERENCES complaintstatus(id);

CREATE INDEX idx_pgr_complaint_complainant ON complaint USING btree (complainant);

------------------END---------------------


insert into complaintstatus (id,name,version)
values(1,'PROCESSING',0);

insert into complaintstatus (id,name,version)
values(2,'FORWARDED',0);

insert into complaintstatus (id,name,version)
values(3,'REGISTERED',0);

insert into complaintstatus (id,name,version)
values(4,'COMPLETED',0);

insert into complaintstatus (id,name,version)
values(5,'REJECTED',0);

insert into complaintstatus (id,name,version)
values(6,'NOTCOMPLETED',0);

insert into complaintstatus (id,name,version)
values(7,'WITHDRAWN',0);

insert into complaintstatus (id,name,version)
values(8,'CLOSED',0);

insert into complaintstatus (id,name,version)
values(9,'REOPENED',0);

insert into complainttype_category (id,name,description,version)
values(1,'Street Lighting','Street Lighting',0);

insert into complainttype_category (id,name,description,version)
values(2,'Water Supply','Water Supply',0);

insert into complainttype_category (id,name,description,version)
values(3,'Public Health and Sanitation','Public Health and Sanitation',0);

insert into complainttype_category (id,name,description,version)
values(4,'Administration','Administration',0);

insert into complainttype_category (id,name,description,version)
values(5,'Town Planning','Town Planning',0);

insert into complainttype_category (id,name,description,version)
values(6,'Revenue','Revenue',0);

insert into complainttype (id,name,department,category,code,isactive,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(1,'Damage to the  Electric Pole',1,1,'DTTEP',true,'',now(),now(),1,1,0);

insert into complainttype (id,name,department,category,code,isactive,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(2,'Electric Shock due to street light',1,1,'ESDTSL',true,'',now(),now(),1,1,0);

insert into complainttype (id,name,department,category,code,isactive,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(3,'New Street light',1,1,'NSL',true,'',now(),now(),1,1,0);

insert into complainttype (id,name,department,category,code,isactive,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(4,'Non Burning of Street Lights',1,1,'NBOSL',true,'',now(),now(),1,1,0);

insert into complainttype (id,name,department,category,code,isactive,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(5,'Covering of Man holes of SWD',1,2,'COMHOSWD',true,'',now(),now(),1,1,0);

insert into complainttype (id,name,department,category,code,isactive,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(6,'Desilting of Canal',1,2,'DOC',true,'',now(),now(),1,1,0);

insert into complainttype (id,name,department,category,code,isactive,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(7,'Obstruction of water flow',1,2,'OOWF',true,'',now(),now(),1,1,0);
