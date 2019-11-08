-----------------START--------------------

CREATE TABLE department (
    id bigint NOT NULL,
    name character varying(25),
    code character varying(25),
    description character varying(256),
    createddate timestamp without time zone,
    lastmodifieddate timestamp without time zone,
    createdby bigint,
    lastmodifiedby bigint,
    version bigint
);
CREATE SEQUENCE seq_department
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ONLY department
    ADD CONSTRAINT pk_department PRIMARY KEY (id);

------------------END---------------------

-----------------START--------------------

CREATE TABLE employee (
    id bigint NOT NULL,
    email character varying(100),
    mobile character varying(20),
    name character varying(150),
    address character varying(256),
    createddate timestamp without time zone,
    lastmodifieddate timestamp without time zone,
    createdby bigint,
    lastmodifiedby bigint,
    version bigint
);

CREATE SEQUENCE seq_employee
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY employee
    ADD CONSTRAINT pk_employee PRIMARY KEY (id);

------------------END---------------------
    
insert into employee (id,email,mobile,name,address,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(1,'venki@gmail.com','9898989898','venki','Bangalore',now(),now(),1,1,0);

insert into department (id,name,code,description,createddate,lastmodifieddate,createdby,lastmodifiedby,version)
values(1,'Engineering','E','The Municipal Engineering Department is under administrative control of Municipal Administration and Urban Development Department at the Secretariat level.',now(),now(),1,1,0);
