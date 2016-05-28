CREATE TABLE "telephones" (
    "id" BIGSERIAL PRIMARY KEY,
    "type_id" BIGINT,
    "country_code" SMALLINT,
    "area_code" SMALLINT,
    "number" INT
);

CREATE TABLE "telephone_types" (
    "id" BIGSERIAL PRIMARY KEY,
    "description" VARCHAR(50)
);
