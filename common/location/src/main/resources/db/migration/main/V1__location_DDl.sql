-----------------START--------------------

CREATE TABLE boundary (
    id bigint NOT NULL,
    name character varying(25),
    code character varying(25),
    description character varying(256),
    lat double precision,
    lng double precision,
    createddate timestamp without time zone,
    lastmodifieddate timestamp without time zone,
    createdby bigint,
    lastmodifiedby bigint,
    version bigint
);
CREATE SEQUENCE seq_boundary
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ONLY boundary
    ADD CONSTRAINT pk_boundary PRIMARY KEY (id);

------------------END---------------------
