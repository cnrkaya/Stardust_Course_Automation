-- Course Id Sequence

CREATE SEQUENCE course_id_seq;
SELECT setval('course_id_seq', (SELECT max(id) FROM course));
ALTER TABLE course ALTER COLUMN id SET DEFAULT
nextval('course_id_seq'::regclass);