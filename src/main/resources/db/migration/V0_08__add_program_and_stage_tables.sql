CREATE TABLE "program" (
	id bigserial NOT NULL,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	deleted bool NOT NULL DEFAULT false,
	time_created timestamptz NOT NULL,
	time_last_modified timestamptz NOT NULL,
	"name" varchar NOT NULL,
	"level" varchar NOT NULL,
	description varchar NULL,
	equipments varchar NULL,
	designed_for varchar NULL,
	CONSTRAINT program_pk PRIMARY KEY (id),
	CONSTRAINT program_uuid_un UNIQUE (uuid)
);


CREATE TABLE stage (
	id bigserial NOT NULL,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	deleted bool NOT NULL DEFAULT false,
	time_created timestamptz NOT NULL,
	time_last_modified timestamptz NOT NULL,
	program_id int8 NOT NULL,
	"name" varchar NOT NULL,
	description varchar NULL,
	"order" int4 NOT NULL,
	CONSTRAINT stage_pk PRIMARY KEY (id),
	CONSTRAINT stage_uuid_un UNIQUE (uuid)
);
CREATE INDEX stage_program_id_idx ON public.stage (program_id);

ALTER TABLE public.stage ADD CONSTRAINT stage_fk FOREIGN KEY (program_id) REFERENCES "program"(id);


CREATE TABLE stage_workout (
	id bigserial NOT NULL,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	deleted bool NOT NULL DEFAULT false,
	time_created timestamptz NOT NULL,
	time_last_modified timestamptz NOT NULL,
	stage_id int8 NOT NULL,
	workout_id int8 NOT NULL,
	"order" int4 NOT NULL,
	CONSTRAINT stage_workout_pk PRIMARY KEY (id),
	CONSTRAINT stage_workout_uuid_un UNIQUE (uuid)
);
CREATE INDEX stage_workout_stage_id_idx ON public.stage_workout (stage_id);

ALTER TABLE public.stage_workout ADD CONSTRAINT stage_workout_fk FOREIGN KEY (stage_id) REFERENCES stage(id);
ALTER TABLE public.stage_workout ADD CONSTRAINT stage_workout_fk_1 FOREIGN KEY (workout_id) REFERENCES workout(id);
