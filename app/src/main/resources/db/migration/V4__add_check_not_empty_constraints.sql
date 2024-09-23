ALTER TABLE persons
ADD CONSTRAINT check_name_not_empty CHECK (TRIM(name) <> '');

ALTER TABLE persons
ADD CONSTRAINT check_registration_not_empty CHECK (TRIM(registration) <> '');
