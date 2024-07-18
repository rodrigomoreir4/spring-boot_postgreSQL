CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE persons (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    registration VARCHAR(100) NOT NULL
);
