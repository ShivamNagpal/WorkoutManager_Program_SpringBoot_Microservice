CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE workout (
	id bigserial NOT NULL,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	deleted bool NOT NULL DEFAULT false,
	time_created date NOT NULL,
	time_last_modified date NOT NULL,
	"name" varchar NOT NULL,
	"level" varchar NOT NULL,
	description varchar NULL,
	equipments varchar NULL,
	CONSTRAINT workout_pk PRIMARY KEY (id),
	CONSTRAINT workout_uuid_un UNIQUE (uuid)
);


CREATE TABLE "section" (
	id bigserial NOT NULL,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	deleted bool NOT NULL DEFAULT false,
	time_created date NOT NULL,
	time_last_modified date NOT NULL,
	workout_id int8 NOT NULL,
	"name" varchar NOT NULL,
	repetitions int4 NOT NULL,
	resting_info varchar NULL,
	"order" int4 NOT NULL,
	CONSTRAINT section_pk PRIMARY KEY (id),
	CONSTRAINT section_uuid_un UNIQUE (uuid)
);

ALTER TABLE public."section" ADD CONSTRAINT section_fk FOREIGN KEY (workout_id) REFERENCES workout(id);


CREATE TABLE drill (
	id bigserial NOT NULL,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	deleted bool NOT NULL DEFAULT false,
	time_created date NOT NULL,
	time_last_modified date NOT NULL,
	"name" varchar NOT NULL,
	description varchar NULL,
	CONSTRAINT drill_pk PRIMARY KEY (id),
	CONSTRAINT drill_uuid_un UNIQUE (uuid)
);


CREATE TABLE section_drill (
	id bigserial NOT NULL,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	deleted bool NOT NULL DEFAULT false,
	time_created date NOT NULL,
	time_last_modified date NOT NULL,
	section_id int8 NOT NULL,
	drill_id int8 NOT NULL,
	length int8 NOT NULL,
	units varchar NOT NULL,
	CONSTRAINT section_drill_pk PRIMARY KEY (id),
	CONSTRAINT section_drill_uuid_un UNIQUE (uuid)
);

ALTER TABLE public.section_drill ADD CONSTRAINT section_drill_fk FOREIGN KEY (section_id) REFERENCES section(id);
ALTER TABLE public.section_drill ADD CONSTRAINT section_drill_fk_1 FOREIGN KEY (drill_id) REFERENCES drill(id);
