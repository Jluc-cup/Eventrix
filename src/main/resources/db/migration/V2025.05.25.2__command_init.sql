CREATE SEQUENCE command_id_seq
    START WITH 1
    INCREMENT BY 100
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE command
(
    id             INT PRIMARY KEY DEFAULT nextval('command_id_seq'),
    logic          VARCHAR(255)             NOT NULL,
    target_service VARCHAR(255)             NOT NULL,
    description    TEXT,
    created        TIMESTAMP WITH TIME ZONE NOT NULL,
    updated        TIMESTAMP WITH TIME ZONE
);


CREATE INDEX idx_command_logic ON command (logic);



ALTER TABLE task
    ADD COLUMN command_id INT NOT NULL;

ALTER TABLE task
    ADD CONSTRAINT fk_task_command FOREIGN KEY (command_id) REFERENCES command (id) ON UPDATE NO ACTION ON DELETE NO ACTION;