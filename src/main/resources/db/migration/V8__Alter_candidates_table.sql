ALTER TABLE candidates
    ADD COLUMN address VARCHAR(100),
    ADD COLUMN country_id BIGINT,
    ADD COLUMN state_id BIGINT,
    ADD COLUMN city_id BIGINT,
    ADD COLUMN zipcode VARCHAR(20),
    ADD COLUMN genre CHAR(1),
    ADD COLUMN birthdate DATE,
    ADD COLUMN education_id BIGINT,
    ADD COLUMN employed SMALLINT;