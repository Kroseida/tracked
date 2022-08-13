CREATE TABLE authentication
(
    id      BINARY(16) NOT NULL,
    type    INT        NULL,
    data    longtext   NULL,
    user_id BINARY(16) NOT NULL,
    CONSTRAINT pk_authentication PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BINARY(16)   NOT NULL,
    username   VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE authentication
    ADD CONSTRAINT FK_AUTHENTICATION_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);