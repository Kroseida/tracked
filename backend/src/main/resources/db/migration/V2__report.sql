create table user_projects
(
    user_id     binary(16) not null,
    projects_id binary(16) not null
);


CREATE TABLE user_organizations
(
    user_id          binary(16) not null,
    organizations_id binary(16) not null
);


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
    id          BINARY(16) NOT NULL,
    name        text       NULL,
    description LONGTEXT   NULL,
    active      BOOLEAN    NULL,
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

ALTER TABLE user_organizations
    ADD CONSTRAINT FK_USER_ORGANIZATIONS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_organizations
    ADD CONSTRAINT FK_USER_ORGANIZATIONS_ON_ORGANIZATION FOREIGN KEY (organizations_id) REFERENCES organization (id);

ALTER TABLE user_projects
    ADD CONSTRAINT FK_USER_PROJECTS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_projects
    ADD CONSTRAINT FK_USER_PROJECTS_ON_PROJECT FOREIGN KEY (projects_id) REFERENCES project (id);