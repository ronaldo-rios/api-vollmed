ALTER TABLE medicos
    ADD COLUMN ativo TINYINT DEFAULT 1 AFTER especialidade;