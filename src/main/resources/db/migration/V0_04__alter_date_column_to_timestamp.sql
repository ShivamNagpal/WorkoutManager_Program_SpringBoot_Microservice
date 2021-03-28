alter table drill alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table drill alter column time_last_modified type TIMESTAMP using time_last_modified::TIMESTAMP;

alter table "section" alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table "section" alter column time_last_modified type TIMESTAMP using time_last_modified::TIMESTAMP;

alter table section_drill alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table section_drill alter column time_last_modified type TIMESTAMP using time_last_modified::TIMESTAMP;

alter table workout alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table workout alter column time_last_modified type TIMESTAMP using time_last_modified::TIMESTAMP;

alter table drill alter column time_created type TIMESTAMPTZ using time_created at time zone 'Asia/Kolkata';
alter table drill alter column time_last_modified type TIMESTAMPTZ using time_last_modified at time zone 'Asia/Kolkata';

alter table "section" alter column time_created type TIMESTAMPTZ using time_created at time zone 'Asia/Kolkata';
alter table "section" alter column time_last_modified type TIMESTAMPTZ using time_last_modified at time zone 'Asia/Kolkata';

alter table section_drill alter column time_created type TIMESTAMPTZ using time_created at time zone 'Asia/Kolkata';
alter table section_drill alter column time_last_modified type TIMESTAMPTZ using time_last_modified at time zone 'Asia/Kolkata';

alter table workout alter column time_created type TIMESTAMPTZ using time_created at time zone 'Asia/Kolkata';
alter table workout alter column time_last_modified type TIMESTAMPTZ using time_last_modified at time zone 'Asia/Kolkata';
