CREATE TYPE task_status AS ENUM ('PENDING', 'RUNNING', 'COMPLETED', 'FAILED', 'CANCELLED');

CREATE SEQUENCE task_topic_id_seq
    START WITH 1
    INCREMENT BY 100
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;


CREATE TABLE task_topic (
                            id INTEGER PRIMARY KEY DEFAULT nextval('task_topic_id_seq'),
                            name VARCHAR(255) NOT NULL,
                            description TEXT,
                            is_active BOOLEAN NOT NULL DEFAULT FALSE,
                            priority BIGINT
);


CREATE SEQUENCE task_id_seq
    START WITH 1
    INCREMENT BY 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


CREATE TABLE task (
                      id BIGINT PRIMARY KEY DEFAULT nextval('task_id_seq'),
                      task_topic_id INTEGER,
                      name VARCHAR(255) NOT NULL,
                      queue_name VARCHAR(255),
                      created_by VARCHAR(255),
                      parameters TEXT,
                      status task_status NOT NULL,
                      priority BIGINT NOT NULL DEFAULT 0,
                      delay_ms BIGINT,
                      period_ms BIGINT,
                      start_time TIMESTAMP WITH TIME ZONE,
                      last_execution_time TIMESTAMP WITH TIME ZONE,
                      execution_count BIGINT NOT NULL DEFAULT 0,
                      timeout_ms BIGINT,
                      created TIMESTAMP WITH TIME ZONE NOT NULL,
                      updated TIMESTAMP WITH TIME ZONE,
                      FOREIGN KEY (task_topic_id) REFERENCES task_topic(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE task_dependency_id_seq
    START WITH 1
    INCREMENT BY 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


CREATE TABLE task_dependency (
                                 id BIGINT PRIMARY KEY DEFAULT nextval('task_dependency_id_seq'),
                                 task_id BIGINT NOT NULL,
                                 depends_on_task_id BIGINT NOT NULL,
                                 FOREIGN KEY (task_id) REFERENCES task(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
                                 FOREIGN KEY (depends_on_task_id) REFERENCES task(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE INDEX idx_task_task_topic_id ON task(task_topic_id);
CREATE INDEX idx_task_status ON task(status);
CREATE INDEX idx_task_dependency_task_id ON task_dependency(task_id);
CREATE INDEX idx_task_dependency_depends_on_task_id ON task_dependency(depends_on_task_id);