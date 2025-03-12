CREATE TABLE measurement (
    id SERIAL PRIMARY KEY NOT NULL,
    measurement_date TIMESTAMP NOT NULL,
    value INT,
    meter_id BIGINT);