DROP TABLE IF EXISTS neo.conversation;
create table neo.conversation
(
    id          BIGINT PRIMARY KEY NOT NULL,
    start_time  TIMESTAMP,
    end_time    TIMESTAMP,
    customer_id INT                NOT NULL
);

DROP TABLE IF EXISTS neo.conversation_seq;
create table neo.conversation_seq(
    next_val INTEGER NOT null
);

DROP TABLE IF EXISTS neo.customer;
create table neo.customer
(
    id         BIGINT PRIMARY KEY NOT NULL,
    first_name VARCHAR(255)       NOT NULL,
    last_name   VARCHAR(255)       NOT NULL
);

DROP TABLE IF EXISTS neo.customer_seq;
create table neo.customer_seq(
    next_val INTEGER NOT null
);


INSERT INTO neo.customer
VALUES (1, 'Lukas', 'Poehn'),
       (2, 'Johannes', 'Meinhard');