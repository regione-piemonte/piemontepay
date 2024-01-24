-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- T R I G G E R - PEC
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

drop table IF EXISTS epaypa.epaypa_t_ente_log;
CREATE TABLE epaypa.epaypa_t_ente_log (
	seq bigserial,
	id_ente int4 NOT NULL,
	cod_fiscale_ente varchar(35) NOT NULL,
	denominazione varchar(200),
	email varchar(256),
	cod_gs1_gln varchar(13),
	cod_interbancario varchar(5),
	logo bytea,
	data_trigger TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT epaypa_t_ente_log_pkey PRIMARY KEY (seq),
	CONSTRAINT epaypa_t_ente_log_parent_fk FOREIGN KEY (id_ente) REFERENCES epaypa.epaypa_t_ente(id_ente)
) ;

drop table IF EXISTS epaypa.epaypa_t_codice_versamento_log;
CREATE TABLE epaypa.epaypa_t_codice_versamento_log (
	seq bigserial,
	id_codice_versamento int4 NOT NULL,
	id_ente int4 NOT NULL,
	cod_versamento varchar(4) NOT NULL,
	descrizione varchar(200),
	data_trigger TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT epaypa_t_codice_versamento_log_pkey PRIMARY KEY (seq),
	CONSTRAINT epaypa_t_codice_versamento_log_parent_fk1 FOREIGN KEY (id_ente) REFERENCES epaypa.epaypa_t_ente(id_ente),
	CONSTRAINT epaypa_t_codice_versamento_log_parent_fk2 FOREIGN KEY (id_codice_versamento) REFERENCES epaypa.epaypa_t_codice_versamento(id_codice_versamento)
) ;

-- ------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION epaypa_t_ente_trigger_func() RETURNS TRIGGER AS $table$
	BEGIN
		INSERT INTO epaypa.epaypa_t_ente_log 
			-- COLS
			(
				id_ente,
				cod_fiscale_ente,
				denominazione,
				email,
				cod_gs1_gln,
				cod_interbancario,
				logo
			)
			VALUES
			-- NEW
			(
				NEW.id_ente,
				NEW.cod_fiscale_ente,
				NEW.denominazione,
				NEW.email,
				NEW.cod_gs1_gln,
				NEW.cod_interbancario,
				NEW.logo
			);
		RETURN NEW;
	END;
$table$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION epaypa_t_codice_versamento_trigger_func() RETURNS TRIGGER AS $table$
	BEGIN
		INSERT INTO epaypa.epaypa_t_codice_versamento_log 
			-- COLS
			(
				id_codice_versamento,
				id_ente,
				cod_versamento,
				descrizione
			)
			VALUES
			-- NEW
			(
				NEW.id_codice_versamento,
				NEW.id_ente,
				NEW.cod_versamento,
				NEW.descrizione
			);
		RETURN NEW;
	END;
$table$ LANGUAGE plpgsql;

-- ------------------------------------------------------------------------------------------------------------

drop trigger IF EXISTS epaypa_t_ente_trigger ON epaypa.epaypa_t_ente;
CREATE TRIGGER epaypa_t_ente_trigger AFTER INSERT OR UPDATE ON epaypa.epaypa_t_ente
FOR EACH ROW EXECUTE PROCEDURE epaypa_t_ente_trigger_func();

drop trigger IF EXISTS epaypa_t_codice_versamento_trigger ON epaypa.epaypa_t_codice_versamento;
CREATE TRIGGER epaypa_t_codice_versamento_trigger AFTER INSERT OR UPDATE ON epaypa.epaypa_t_codice_versamento
FOR EACH ROW EXECUTE PROCEDURE epaypa_t_codice_versamento_trigger_func();