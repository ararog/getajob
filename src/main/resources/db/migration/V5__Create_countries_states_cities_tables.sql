CREATE TABLE "countries" (
    "id" BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(50)
);

CREATE TABLE "states" (
    "id" BIGSERIAL PRIMARY KEY,
    "country_id" BIGINT,
    "name" VARCHAR(50)
);

CREATE TABLE "cities" (
    "id" BIGSERIAL PRIMARY KEY,
    "state_id" BIGINT,
    "name" VARCHAR(50)
);