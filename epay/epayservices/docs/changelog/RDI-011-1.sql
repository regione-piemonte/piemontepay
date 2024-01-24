CREATE TABLE epay_t_enti_temp (
	id_ente_temp bigserial NOT NULL,
	id_operazione VARCHAR(100) NOT NULL,
	id_ente int8 NULL,
	nome varchar(250) NOT NULL,
	codice_fiscale varchar(16) NOT NULL,
	logo bytea NULL,
	codice_gs1_gln numeric(13) NOT NULL,
	orari varchar(2000) NULL,
	flag_invio_pagamenti bool NULL,
	codice_interbancario varchar(5) NULL,
	
	CONSTRAINT epay_t_enti_temp_fk1 FOREIGN KEY (id_ente) REFERENCES epay_t_enti(id_ente),
	CONSTRAINT t_enti_temp_pkey PRIMARY KEY (id_ente_temp)
)
WITH (
	OIDS=FALSE
) ;


CREATE TABLE epay_t_tipo_pagamento_temp (
	id_tipo_pagamento_temp bigserial NOT NULL,
	id_operazione VARCHAR(100) NOT NULL,
	tipo_operazione VARCHAR(100) NOT NULL,
	chiave_intersistema varchar(100) NULL,
	id_tipo_pagamento int8 NULL,
	id_ente int8 NOT NULL,
	codice_versamento bpchar(4) NOT NULL,
	descrizione_portale varchar(140) NOT NULL,
	compilazione_note varchar(2000) NULL,
	inizio_validita date NULL DEFAULT 'now'::text::date,
	fine_validita date NULL,
	id_applicazione varchar(30) NULL,
	contatore_selezioni int8 NULL,
	contatore_compilazioni int8 NULL,
	contatore_pagamenti int8 NULL,
	pagamento_spontaneo bool NULL,
	dati_specifici_riscossione varchar(140) NULL,
	flag_invio_pagamenti bool NULL,
	numero_accertamento varchar(35) NULL,
	anno_accertamento int8 NULL,
	
	CONSTRAINT epay_t_tipo_pagamento_temp_chk CHECK ((length(codice_versamento) = 4)),
	CONSTRAINT t_tipo_pagamento_temp_pkey PRIMARY KEY (id_tipo_pagamento_temp),
	CONSTRAINT t_tipo_pagamento_temp_fk FOREIGN KEY (id_ente) REFERENCES epay_t_enti(id_ente),
	CONSTRAINT t_tipo_pagamento_temp_fk2 FOREIGN KEY (id_tipo_pagamento) REFERENCES epay_t_tipo_pagamento(id_tipo_pagamento)
)
WITH (
	OIDS=FALSE
) ;

ALTER TABLE epay_t_tipo_pagamento ADD chiave_intersistema varchar(100) NULL;

ALTER TABLE epay_t_tipo_pagamento ALTER COLUMN inizio_validita DROP NOT NULL;

ALTER TABLE epay_t_tipo_pagamento_log ALTER COLUMN inizio_validita DROP NOT NULL;
