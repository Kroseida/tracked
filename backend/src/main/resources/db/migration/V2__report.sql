CREATE TABLE activity
(
    id          BINARY(16) NOT NULL,
    type        INT        NULL,
    name        text       NULL,
    description LONGTEXT   NULL,
    project_id  BINARY(16) NOT NULL,
    CONSTRAINT pk_activity PRIMARY KEY (id)
);

CREATE TABLE project
(
    id              BINARY(16) NOT NULL,
    type            INT        NULL,
    name            text       NULL,
    organization_id BINARY(16) NOT NULL,
    CONSTRAINT pk_project PRIMARY KEY (id)
);

CREATE TABLE organization
(
    id   BINARY(16) NOT NULL,
    type INT        NULL,
    name text       NULL,
    CONSTRAINT pk_organization PRIMARY KEY (id)
);

CREATE TABLE report
(
    id              BINARY(16) NOT NULL,
    description     LONGTEXT   NULL,
    start           LONG       NOT NULL,
    end             LONG       NOT NULL,
    user_id         BINARY(16) NOT NULL,
    organization_id BINARY(16) NOT NULL,
    project_id      BINARY(16) NOT NULL,
    activity_id     BINARY(16) NOT NULL,
    CONSTRAINT pk_organization PRIMARY KEY (id)
);

ALTER TABLE project
    ADD CONSTRAINT FK_PROJECT_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organization (id);

ALTER TABLE activity
    ADD CONSTRAINT FK_ACTIVITY_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);

ALTER TABLE report
    ADD CONSTRAINT FK_REPORT_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organization (id);

ALTER TABLE report
    ADD CONSTRAINT FK_REPORT_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);

ALTER TABLE report
    ADD CONSTRAINT FK_REPORT_ON_ACTIVITY FOREIGN KEY (activity_id) REFERENCES activity (id);

ALTER TABLE report
    ADD CONSTRAINT FK_REPORT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);