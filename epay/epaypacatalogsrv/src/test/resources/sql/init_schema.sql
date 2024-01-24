DROP ALL OBJECTS;

CREATE TABLE epaycat_d_fruitore (
	id int4 NOT NULL,
	codice varchar(100) NOT NULL,
	descrizione varchar(255),
	data_inizio_validita date,
    data_fine_validita date,
    PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_modalita_integrazione (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
) ;
CREATE TABLE epaycat_d_tipologia_dato_specifico_riscossione (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_stato_aggiornamento (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_tipo_pagamento (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE auditactions (
	idaction CHAR(3) NOT NULL,
	description VARCHAR(200) NOT NULL,
	PRIMARY KEY (idaction)
);
CREATE TABLE csi_log_audit (
	data_ora TIMESTAMP NOT NULL,
	descrizione VARCHAR(2000),
	utente INTEGER NOT NULL,
	codappmodify CHAR(1) NOT NULL,
	idaction CHAR(3) NOT NULL,
	id_app VARCHAR(100),
	operazione VARCHAR(50),
	ogg_oper VARCHAR(500),
	key_oper VARCHAR(500),
	ip_address VARCHAR(100),
	uniqueid int auto_increment primary key,
	codfisc CHAR(16),
	PRIMARY KEY (uniqueid)
);
CREATE TABLE epaycat_d_categoria_cdu (
	id INTEGER NOT NULL,
	codice VARCHAR(50) NOT NULL,
	descrizione VARCHAR(255),
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_errore (
	id INTEGER NOT NULL,
	codice_lingua VARCHAR(10) NOT NULL,
	codice_applicativo VARCHAR(50) NOT NULL,
	codice VARCHAR(50) NOT NULL,
	messaggio VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_macrotipo_ppay (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_modalita_acquisizione_provvisori (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_operazione (
	id INTEGER NOT NULL,
	codice VARCHAR(5) NOT NULL,
	id_servizio INTEGER,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_periodicita_schedulazione_riconciliazione (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_servizio (
	id INTEGER NOT NULL,
	descrizione VARCHAR NOT NULL,
	wsdl VARCHAR NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_sottoscrittore (
	id INTEGER NOT NULL,
	codice VARCHAR(5) NOT NULL,
	descrizione VARCHAR NOT NULL,
	indirizzo VARCHAR NOT NULL,
	priorita INTEGER,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_stato_aggiornamento_ente (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_d_tematica_ppay (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE epaycat_d_tipologia_accertamento (
	id INTEGER NOT NULL,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_r_ente_codice_versamento (
	id_ente INTEGER NOT NULL,
	id_codice_versamento INTEGER NOT NULL,
	PRIMARY KEY (id_ente,id_codice_versamento)
);
CREATE TABLE epaycat_r_operazione_sottoscrittore (
	id_operazione INTEGER NOT NULL,
	id_sottoscrittore INTEGER NOT NULL,
	PRIMARY KEY (id_operazione,id_sottoscrittore)
);
CREATE TABLE epaycat_r_utente_cdu (
	cod_cdu VARCHAR(50) NOT NULL,
	id_utente INTEGER NOT NULL,
	id_ente INTEGER NOT NULL,
	PRIMARY KEY (cod_cdu,id_utente,id_ente)
);
CREATE TABLE epaycat_r_utente_ente (
	id_utente INTEGER NOT NULL,
	id_ente INTEGER NOT NULL,
	PRIMARY KEY (id_utente,id_ente)
);
CREATE TABLE epaycat_t_cdu (
	id INTEGER NOT NULL,
	codice VARCHAR(50) NOT NULL,
	codice_categoria VARCHAR(50) NOT NULL,
	descrizione VARCHAR(255),
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_t_codice_versamento (
	id int auto_increment primary key,
	id_padre int4,
	id_ente int4 NOT NULL,
	codice varchar(4) NOT NULL,
    descrizione varchar(140) NOT NULL,    
    codice_voce_entrata varchar(10) NOT NULL,
    codice_tipo_pagamento varchar(10) NOT NULL,
    codice_stato_aggiornamento varchar(10) NOT NULL,
    iban varchar(35) ,
    bic varchar(11) ,
    flag_invio_flussi BOOLEAN,
    email varchar(250),
    descrizione_errore_aggiornamento varchar(250),
	utente_inserimento varchar(16),
	data_inserimento TIMESTAMP,
	utente_modifica varchar(16),
	data_modifica TIMESTAMP,
	flag_annullato BOOLEAN,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_t_ente (
	id INTEGER NOT NULL,
	codice_fiscale VARCHAR(16) NOT NULL,
	denominazione VARCHAR(250),
	indirizzo VARCHAR(300),
	localita VARCHAR(100),
	cap VARCHAR(5),
	sigla_provincia VARCHAR(2),
	logo_content VARBINARY,
	logo_content_type VARCHAR(20),
	logo_file_name VARCHAR(255),
	logo_file_size INTEGER,
	flag_ente_plurintermediato BOOLEAN,
	flag_riconciliazione_versamenti BOOLEAN,
	flag_accertamento BOOLEAN,
	flag_ricezione_errori BOOLEAN,
	codice_periodicita_schedulazione_riconciliazione VARCHAR(10),
	codice_tipologia_accertamento VARCHAR(10),
	codice_modalita_acquisizione_provvisori VARCHAR(10),
	codice_stato_aggiornamento_ente VARCHAR(10),
	id_utente_amministratore INTEGER,
	flag_ricezione_flusso_base_rendicontazione BOOLEAN,
	flag_qualsiasi_codice_versamento BOOLEAN,
	giorno_schedulazione INTEGER,	
	descrizione_errore_aggiornamento varchar(250),
	civico varchar(20),
	email varchar(250),
	gs1_gln varchar(13),
	cbill varchar(5),
	iban varchar(35),
	bic varchar(11),
	utente_inserimento varchar(16),
	data_inserimento TIMESTAMP,
	utente_modifica varchar(16),
	data_modifica TIMESTAMP,
	codice_modalita_integrazione VARCHAR(10),
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_t_log_transazione (
	id int auto_increment primary key,
	id_transazione INTEGER NOT NULL,
	messaggio VARCHAR,
	esito VARCHAR NOT NULL,
	id_sottoscrittore INTEGER NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_t_storico_ente (
	id int auto_increment primary key,
	id_ente INTEGER NOT NULL,
	codice_fiscale VARCHAR(16) NOT NULL,
	denominazione VARCHAR(250),
	indirizzo VARCHAR(300),
	localita VARCHAR(100),
	cap VARCHAR(5),
	sigla_provincia VARCHAR(2),
	logo_content VARBINARY,
	logo_content_type VARCHAR(20),
	logo_file_name VARCHAR(255),
	logo_file_size INTEGER,
	flag_ente_plurintermediato BOOLEAN,
	flag_riconciliazione_versamenti BOOLEAN,
	flag_accertamento BOOLEAN,
	flag_ricezione_errori BOOLEAN,
	codice_periodicita_schedulazione_riconciliazione VARCHAR(10),
	codice_tipologia_accertamento VARCHAR(10),
	codice_modalita_acquisizione_provvisori VARCHAR(10),
	codice_stato_aggiornamento_ente VARCHAR(10),
	id_utente_amministratore INTEGER,
	flag_ricezione_flusso_base_rendicontazione BOOLEAN,
	flag_qualsiasi_codice_versamento BOOLEAN,
	giorno_schedulazione INTEGER,	
	descrizione_errore_aggiornamento varchar(250),
	civico varchar(20),
	email varchar(250),
	gs1_gln varchar(13),
	cbill varchar(5),
	iban varchar(35),
	utente_inserimento varchar(16),
	data_inserimento TIMESTAMP,
	utente_modifica varchar(16),
	data_modifica TIMESTAMP,
	codice_modalita_integrazione VARCHAR(10),
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_t_transazione (
	id int auto_increment primary key,
	id_operazione INTEGER NOT NULL,
	data TIMESTAMP NOT NULL,
	esito VARCHAR NOT NULL,
	id_transazione_esterna VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_t_utente (
	id INTEGER auto_increment primary key,
	codice_fiscale VARCHAR(16) NOT NULL,
	nome VARCHAR(250) NOT NULL,
	cognome VARCHAR(250) NOT NULL,
	data_inizio_validita DATE,
	data_fine_validita DATE,
	email VARCHAR(250),
	codice_stato_aggiornamento varchar(10),
	utente_inserimento varchar(16),
	data_inserimento TIMESTAMP,
	utente_modifica varchar(16),
	data_modifica TIMESTAMP,
	descrizione_errore_aggiornamento varchar(250),
	PRIMARY KEY (id)
);
CREATE TABLE epaycat_t_voce_entrata (
	id int auto_increment primary key,
	codice VARCHAR(10) NOT NULL,
	descrizione VARCHAR(255),
	codice_tematica_ppay VARCHAR(10),
	codice_macrotipo_ppay VARCHAR(10),
	PRIMARY KEY (id)
);

CREATE TABLE epaycat_t_riferimento_contabile (
	id int auto_increment primary key,
	id_codice_versamento int,
	data_inizio_validita date not NULL,  
	data_fine_validita date,
	codice_tipologia_dato_specifico_riscossione varchar(10),
	dato_specifico_riscossione varchar(140),
	descrizione_dato_specifico_riscossione varchar(80),
	anno_esercizio int, 
	numero_esercizio int, 
	anno_accertamento int,
	numero_accertamento int,
	numero_capitolo int,
	numero_articolo int,
	livello_pdc varchar(50),
	titolo varchar(200),
	tipologia varchar(200),
	categoria varchar(200),
	codice_stato_aggiornamento varchar(10),
	descrizione_errore_aggiornamento varchar(255),
	utente_inserimento varchar(16),
	data_inserimento TIMESTAMP,
	utente_modifica varchar(16),
	data_modifica TIMESTAMP,
	flag_annullato BOOLEAN,
	chiave_intersistema varchar(50) NOT NULL,
	PRIMARY KEY(id)
) ;

CREATE TABLE epaycat_t_storico_riferimento_contabile (
	id int auto_increment primary key,
	id_riferimento_contabile int NOT NULL,
	id_codice_versamento int NOT NULL,
	data_inizio_validita date NOT NULL,
	data_fine_validita date NULL,
	codice_tipologia_dato_specifico_riscossione varchar(10) NULL,
	dato_specifico_riscossione varchar(140) NULL,
	descrizione_dato_specifico_riscossione varchar(80) NULL,
	anno_esercizio int NOT NULL,
	anno_accertamento int NULL,
	numero_accertamento int NULL,
	numero_capitolo int NULL,
	numero_articolo int NULL,
	livello_pdc varchar(50) NULL,
	titolo varchar(200) NULL,
	codice_stato_aggiornamento varchar(10) NULL,
	descrizione_errore_aggiornamento varchar(250) NULL,
	numero_esercizio int NULL,
	utente_inserimento varchar(16) NOT NULL,
	data_inserimento timestamp NOT NULL,
	utente_modifica varchar(16) NULL,
	data_modifica timestamp NULL,
	tipologia varchar(200) NULL,
	categoria varchar(200) NULL,
	chiave_intersistema varchar(50) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE epaycat_r_utente_tematica (
	cod_tematica varchar(50) NOT NULL,
	id_utente int8 NOT NULL,
	id_ente int4 NOT NULL,
	flag_visibilita_totale BOOLEAN,
	PRIMARY KEY(cod_tematica, id_utente, id_ente)
);

CREATE TABLE epaycat_r_utente_codice_versamento (
	id_codice_versamento integer NOT NULL,
	id_utente int8 NOT NULL,
	id_ente int4 NOT NULL,
	PRIMARY KEY(id_codice_versamento, id_utente, id_ente)
);

CREATE TABLE epaycat_d_stato_email (
    id int4 not NULL,
    codice VARCHAR(10) not NULL,
    descrizione VARCHAR(255) not NULL,
    PRIMARY KEY(id)
);

CREATE TABLE epaycat_d_configurazione (
    id int4 not NULL,
    id_ente bigint,
    codice VARCHAR(100) not NULL,
    valore VARCHAR(1000),
    descrizione VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE epaycat_d_template_email (
    id int4 not NULL,
    codice VARCHAR(100) not NULL,
    template TEXT,
    descrizione VARCHAR(255),
    oggetto VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE epaycat_t_email (
    id int auto_increment primary key,
    id_ente bigint NOT NULL,
    codice_stato VARCHAR(10) NOT NULL,
    recipient VARCHAR(1000) NOT NULL,
    subject VARCHAR(1000) NOT NULL,
    body text NOT NULL,
    error text,
    numero_tentativi int4 NOT null,
    data timestamp without time zone NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE epaycat_d_configurazione
	ADD FOREIGN KEY (id_ente) 
	REFERENCES epaycat_t_ente (id);

ALTER TABLE epaycat_t_email
	ADD FOREIGN KEY (id_ente) 
	REFERENCES epaycat_t_ente (id);

ALTER TABLE epaycat_t_email
	ADD FOREIGN KEY (codice_stato) 
	REFERENCES epaycat_d_stato_email(codice);

ALTER TABLE epaycat_r_utente_tematica
	ADD FOREIGN KEY (id_utente,id_ente) 
	REFERENCES epaycat_r_utente_ente (id_utente,id_ente);

ALTER TABLE epaycat_r_utente_tematica
	ADD FOREIGN KEY (cod_tematica) 
	REFERENCES epaycat_d_tematica_ppay (codice);

ALTER TABLE epaycat_r_utente_codice_versamento
	ADD FOREIGN KEY (id_utente,id_ente) 
	REFERENCES epaycat_r_utente_ente (id_utente,id_ente);

ALTER TABLE epaycat_r_utente_codice_versamento
	ADD FOREIGN KEY (id_codice_versamento) 
	REFERENCES epaycat_t_codice_versamento (id);
	
ALTER TABLE csi_log_audit
	ADD FOREIGN KEY (idaction) 
	REFERENCES auditactions (idaction);

ALTER TABLE epaycat_d_operazione
	ADD FOREIGN KEY (id_servizio) 
	REFERENCES epaycat_d_servizio (id);

ALTER TABLE epaycat_r_operazione_sottoscrittore
	ADD FOREIGN KEY (id_operazione) 
	REFERENCES epaycat_d_operazione (id);

ALTER TABLE epaycat_r_operazione_sottoscrittore
	ADD FOREIGN KEY (id_sottoscrittore) 
	REFERENCES epaycat_d_sottoscrittore (id);

ALTER TABLE epaycat_r_utente_cdu
	ADD FOREIGN KEY (id_utente,id_ente) 
	REFERENCES epaycat_r_utente_ente (id_utente,id_ente);

ALTER TABLE epaycat_r_utente_cdu
	ADD FOREIGN KEY (cod_cdu) 
	REFERENCES epaycat_t_cdu (codice);


ALTER TABLE epaycat_r_utente_ente
	ADD FOREIGN KEY (id_ente) 
	REFERENCES epaycat_t_ente (id);

ALTER TABLE epaycat_r_utente_ente
	ADD FOREIGN KEY (id_utente) 
	REFERENCES epaycat_t_utente (id);


ALTER TABLE epaycat_t_cdu
	ADD FOREIGN KEY (codice_categoria) 
	REFERENCES epaycat_d_categoria_cdu (codice);

ALTER TABLE epaycat_t_ente
	ADD FOREIGN KEY (codice_modalita_acquisizione_provvisori) 
	REFERENCES epaycat_d_modalita_acquisizione_provvisori (codice);

ALTER TABLE epaycat_t_ente
	ADD FOREIGN KEY (codice_periodicita_schedulazione_riconciliazione) 
	REFERENCES epaycat_d_periodicita_schedulazione_riconciliazione (codice);

ALTER TABLE epaycat_t_ente
	ADD FOREIGN KEY (codice_stato_aggiornamento_ente) 
	REFERENCES epaycat_d_stato_aggiornamento_ente (codice);

ALTER TABLE epaycat_t_ente
	ADD FOREIGN KEY (codice_tipologia_accertamento) 
	REFERENCES epaycat_d_tipologia_accertamento (codice);

ALTER TABLE epaycat_t_ente
	ADD FOREIGN KEY (id_utente_amministratore) 
	REFERENCES epaycat_t_utente (id);
	
ALTER TABLE epaycat_t_ente
	ADD FOREIGN KEY (codice_modalita_integrazione) 
	REFERENCES epaycat_d_modalita_integrazione (codice);
	


ALTER TABLE epaycat_t_log_transazione
	ADD FOREIGN KEY (id_sottoscrittore) 
	REFERENCES epaycat_d_sottoscrittore (id);

ALTER TABLE epaycat_t_log_transazione
	ADD FOREIGN KEY (id_transazione) 
	REFERENCES epaycat_t_transazione (id);

ALTER TABLE epaycat_t_storico_ente
	ADD FOREIGN KEY (codice_modalita_acquisizione_provvisori) 
	REFERENCES epaycat_d_modalita_acquisizione_provvisori (codice);

ALTER TABLE epaycat_t_storico_ente
	ADD FOREIGN KEY (codice_periodicita_schedulazione_riconciliazione) 
	REFERENCES epaycat_d_periodicita_schedulazione_riconciliazione (codice);

ALTER TABLE epaycat_t_storico_ente
	ADD FOREIGN KEY (codice_stato_aggiornamento_ente) 
	REFERENCES epaycat_d_stato_aggiornamento_ente (codice);

ALTER TABLE epaycat_t_storico_ente
	ADD FOREIGN KEY (codice_tipologia_accertamento) 
	REFERENCES epaycat_d_tipologia_accertamento (codice);

ALTER TABLE epaycat_t_storico_ente
	ADD FOREIGN KEY (id_utente_amministratore) 
	REFERENCES epaycat_t_utente (id);

ALTER TABLE epaycat_t_ente
	ADD FOREIGN KEY (codice_modalita_integrazione) 
	REFERENCES epaycat_d_modalita_integrazione (codice);

ALTER TABLE epaycat_t_transazione
	ADD FOREIGN KEY (id_operazione) 
	REFERENCES epaycat_d_operazione (id);


ALTER TABLE epaycat_t_voce_entrata
	ADD FOREIGN KEY (codice_macrotipo_ppay) 
	REFERENCES epaycat_d_macrotipo_ppay (codice);

ALTER TABLE epaycat_t_voce_entrata
	ADD FOREIGN KEY (codice_tematica_ppay) 
	REFERENCES epaycat_d_tematica_ppay (codice);
	
ALTER TABLE epaycat_r_ente_codice_versamento
	ADD FOREIGN KEY (id_ente) 
	REFERENCES epaycat_t_ente (id);
	
ALTER TABLE epaycat_r_ente_codice_versamento
	ADD FOREIGN KEY (id_codice_versamento) 
	REFERENCES epaycat_t_codice_versamento (id);

	
ALTER TABLE epaycat_t_codice_versamento
	ADD FOREIGN KEY (codice_stato_aggiornamento) 
	REFERENCES epaycat_d_stato_aggiornamento (codice);

ALTER TABLE epaycat_t_codice_versamento
	ADD FOREIGN KEY (codice_tipo_pagamento) 
	REFERENCES epaycat_d_tipo_pagamento (codice);

ALTER TABLE epaycat_t_codice_versamento
	ADD FOREIGN KEY (codice_voce_entrata) 
	REFERENCES epaycat_t_voce_entrata (codice);

ALTER TABLE epaycat_t_codice_versamento
	ADD FOREIGN KEY (id_ente) 
	REFERENCES epaycat_t_ente (id);


ALTER TABLE epaycat_t_riferimento_contabile ADD FOREIGN KEY (id_codice_versamento) REFERENCES epaycat_t_codice_versamento (id);
ALTER TABLE epaycat_t_riferimento_contabile ADD FOREIGN KEY (codice_tipologia_dato_specifico_riscossione) REFERENCES epaycat_d_tipologia_dato_specifico_riscossione (codice);
ALTER TABLE epaycat_t_riferimento_contabile ADD FOREIGN KEY (codice_stato_aggiornamento) REFERENCES epaycat_d_stato_aggiornamento (codice);

ALTER TABLE epaycat_t_utente ADD FOREIGN KEY (codice_stato_aggiornamento) REFERENCES epaycat_d_stato_aggiornamento (codice);

ALTER TABLE epaycat_t_storico_riferimento_contabile 
	ADD FOREIGN KEY (id_riferimento_contabile) REFERENCES epaycat_t_riferimento_contabile(id);

ALTER TABLE epaycat_t_storico_riferimento_contabile 
	ADD FOREIGN KEY (codice_stato_aggiornamento) REFERENCES epaycat_d_stato_aggiornamento(codice);

ALTER TABLE epaycat_t_storico_riferimento_contabile 
	ADD FOREIGN KEY (codice_tipologia_dato_specifico_riscossione) REFERENCES epaycat_d_tipologia_dato_specifico_riscossione(codice);

ALTER TABLE epaycat_t_storico_riferimento_contabile 
	ADD FOREIGN KEY (id_codice_versamento) REFERENCES epaycat_t_codice_versamento(id);
