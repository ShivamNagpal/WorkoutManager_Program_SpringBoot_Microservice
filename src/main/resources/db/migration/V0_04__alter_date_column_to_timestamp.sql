alter table drill alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table drill alter column time_last_modified type TIMESTAMP using time_created::TIMESTAMP;

alter table "section" alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table "section" alter column time_last_modified type TIMESTAMP using time_created::TIMESTAMP;

alter table section_drill alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table section_drill alter column time_last_modified type TIMESTAMP using time_created::TIMESTAMP;

alter table workout alter column time_created type TIMESTAMP using time_created::TIMESTAMP;
alter table workout alter column time_last_modified type TIMESTAMP using time_created::TIMESTAMP;
