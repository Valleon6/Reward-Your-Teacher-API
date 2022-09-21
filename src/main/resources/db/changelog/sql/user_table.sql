DROP TABLE IF EXISTS rytusers;
CREATE TABLE rytusers
(
    id text not null,
    ryt_User_Id character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    email character varying(255),
    password varchar,
    PRIMARY KEY(id)
);