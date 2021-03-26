 CREATE SEQUENCE rv_school_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2000
  CACHE 1;
 
 CREATE TABLE rv_school (
  id int NOT NULL DEFAULT nextval('rv_school_id_seq'),
  school_name VARCHAR (50) NOT null CHECK (school_name <> ''),
  school_level varchar(50) NOT null CHECK (school_level <> ''),
  school_city varchar(50) NOT null CHECK (school_city <> ''),
  PRIMARY KEY (id));
 
 
 CREATE SEQUENCE rv_school_review_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 10000
  CACHE 1;
 
 CREATE TABLE rv_school_review (
  id int NOT NULL DEFAULT nextval('rv_school_review_id_seq'),
  school_id int  NOT NULL references rv_school(id) ON DELETE CASCADE,
  review_comment varchar(200) NOT NULL CHECK (review_comment <> ''),
  PRIMARY KEY (id));
 



  