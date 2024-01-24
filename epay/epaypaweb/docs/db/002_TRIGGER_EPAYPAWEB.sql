-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
-- T R I G G E R - PEC
-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION epaypa_t_codice_versamento_trigger_func() RETURNS TRIGGER AS $table$
	BEGIN
		INSERT INTO epaypa.epaypa_t_codice_versamento_log 
			-- COLS
			(
				id_codice_versamento,
				id_ente,
				cod_versamento,
				descrizione,
				codice_modalita_integrazione,
				id_tipo_pagamento,
				bollettino_postale
			)
			VALUES
			-- NEW
			(
				NEW.id_codice_versamento,
				NEW.id_ente,
				NEW.cod_versamento,
				NEW.descrizione,
				NEW.codice_modalita_integrazione,
				NEW.id_tipo_pagamento,
				NEW.bollettino_postale
			);
		RETURN NEW;
	END;
$table$ LANGUAGE plpgsql;

-- ------------------------------------------------------------------------------------------------------------

drop trigger IF EXISTS epaypa_t_codice_versamento_trigger ON epaypa.epaypa_t_codice_versamento;
CREATE TRIGGER epaypa_t_codice_versamento_trigger AFTER INSERT OR UPDATE ON epaypa.epaypa_t_codice_versamento
FOR EACH ROW EXECUTE PROCEDURE epaypa_t_codice_versamento_trigger_func();