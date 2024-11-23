CREATE TABLE IF NOT EXISTS "HISTORY" (
    id INT NOT NULL,
    UserId INT NOT NULL,
    word VARCHAR(250) NOT NULL,
    keyword VARCHAR(250) NOT NULL,
    result VARCHAR(250) NOT NULL,
    encryption VARCHAR(250) NOT NULL,
    time timestamp NOT NULL,
    PRIMARY KEY (id)
);