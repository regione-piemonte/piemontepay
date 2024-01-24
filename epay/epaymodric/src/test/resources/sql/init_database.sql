DROP ALL OBJECTS;
CREATE SCHEMA IF NOT EXISTS epay;
CREATE TABLE epay.cbl_d_errore (
	id INTEGER NOT NULL,
	codice_errore VARCHAR,
	descrizione_errore VARCHAR,
	tipologia VARCHAR,
	flag_mail BOOLEAN,
	flag_rielaborazione BOOLEAN,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_d_modalita_provvisori (
	id INTEGER NOT NULL,
	modalita_acquisizione DECIMAL(21,6),
	descrizione_modalita VARCHAR(50),
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_d_schedulazione (
	id INTEGER NOT NULL,
	periodicita_schedulazione DECIMAL(21,6),
	descrizione_periodicita VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_d_stato_elaborazione (
	id INTEGER NOT NULL,
	codice_stato VARCHAR,
	descrizione_stato VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_d_stato_flusso (
	id INTEGER NOT NULL,
	codice_stato VARCHAR,
	descrizione_stato VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_r_stato_flusso_errore (
	id INTEGER NOT NULL,
	data_aggiornamento_stato TIMESTAMP,
	descrizione_aggiuntiva_errore VARCHAR,
	id_flusso_origine INTEGER,
	id_errore INTEGER,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_catalogo (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	codice_versamento VARCHAR,
	sottovoce VARCHAR,
	descrizione_versamento VARCHAR,
	anno_esercizio INTEGER,
	accertamento_anno INTEGER,
	accertamento_nro INTEGER,
	accertamento_desc VARCHAR,
	capitolo DECIMAL(21,6),
	articolo DECIMAL(21,6),
	piano_dei_conti VARCHAR,
	dati_specifici_riscossione VARCHAR,
	titolo VARCHAR,
	tipologia VARCHAR,
	categoria VARCHAR,
	data_inizio_validita TIMESTAMP,
	data_fine_validita TIMESTAMP,
	codice_fiscale_ente VARCHAR(50),
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_codiciversamento_esclusione (
	id INTEGER NOT NULL,
	codice_versamento VARCHAR,
	id_ente VARCHAR,
	motivazione VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_configurazione (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	nome_attributo VARCHAR,
	valore VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_elaborazione (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	data_elaborazione TIMESTAMP,
	stato_elaborazione VARCHAR,
	data_inizio TIMESTAMP,
	data_fine TIMESTAMP,
	lista_flussi VARCHAR,
	id_errore INTEGER,
	msg_errore VARCHAR,
	utente_ins VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_email (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	recipient VARCHAR NOT NULL,
	subject VARCHAR NOT NULL,
	body VARCHAR NOT NULL,
	error VARCHAR,
	data TIMESTAMP NOT NULL,
	stato VARCHAR NOT NULL,
	retry INTEGER NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_ente (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	codice_fiscale VARCHAR(16),
	denominazione VARCHAR(500),
	flag_accertamento BOOLEAN,
	flag_riconciliazione BOOLEAN,
	iban_tesoreria VARCHAR,
	ente_plurintermediato BOOLEAN,
	periodicita_schedulazione INTEGER,
	giorno_schedulazione INTEGER,
	modalita_acquisizione_provvisori INTEGER,
	flag_ricezione_errori BOOLEAN,
	email_ente VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_flusso_dettaglio (
	id INTEGER NOT NULL,
	codice_versamento VARCHAR,
	dati_specifici_di_riscossione VARCHAR,
	id_flusso_sintesi INTEGER,
	identificativo_unico_versamento VARCHAR,
	identificativo_unico_riscossione VARCHAR,
	importo_singolo_versamento DECIMAL(18,2) NOT NULL,
	data_pagamento TIMESTAMP,
	esito_pagamento CHAR(1),
	descrizione_causale_versamento VARCHAR,
	transactionid VARCHAR,
	stato_invio_fruitore CHAR(1),
	anagrafica_pagatore VARCHAR,
	codicefiscale_pagatore VARCHAR,
	indice_singolo_versamento INTEGER,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_flusso_origine (
	id INTEGER NOT NULL,
	identificativo_flusso VARCHAR,
	identificativo_istituto_ricevente VARCHAR,
	identificativo_psp VARCHAR,
	numero_totale_pagamenti INTEGER,
	importo_totale_pagamenti DECIMAL(18,2),
	iban_riversamento_flusso VARCHAR,
	dataora_flusso TIMESTAMP,
	data_inserimento TIMESTAMP,
	xml_flusso VARCHAR,
	contatore_tentativi INTEGER,
	id_istituto_ricevente INTEGER,
	id_elaborazione INTEGER,
	id_stato_flusso INTEGER,
	importo_totale_pagamenti_intermediati DECIMAL(18,2) NOT NULL,
	numero_totale_pagamenti_intermediati INTEGER NOT NULL,
	utente_ins_var VARCHAR,
	data_ins_var TIMESTAMP,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_flusso_sintesi (
	id INTEGER NOT NULL,
	codice_versamento VARCHAR,
	importo_quota_aggregazione DECIMAL(18,2) NOT NULL,
	numero_vers_quota_aggregazione DECIMAL(10) NOT NULL,
	dati_specifici_di_riscossione VARCHAR,
	provvisorio_anno INTEGER,
	provvisorio_nro INTEGER,
	id_istituto_ricevente INTEGER,
	id_errore INTEGER,
	id_flusso_origine INTEGER,
	capitolo DECIMAL(21,6),
	articolo DECIMAL(21,6),
	piano_dei_conti VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_iban (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	iban VARCHAR,
	id_applicativo_mdp VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_lista_di_carico (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	codice_versamento VARCHAR,
	dati_specifici_riscossione VARCHAR,
	anno_esercizio INTEGER,
	accertamento_anno INTEGER,
	accertamento_nro INTEGER,
	descrizione_causale VARCHAR,
	rif_posizione_debitoria INTEGER,
	importo_versamento DECIMAL(18,2),
	data_fine_validita TIMESTAMP,
	data_inizio_validita TIMESTAMP,
	capitolo DECIMAL(21,6),
	articolo DECIMAL(21,6),
	piano_dei_conti VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_lock_applicativo (
	id INTEGER NOT NULL,
	id_ente INTEGER NOT NULL,
	id_utente VARCHAR,
	data_inizio TIMESTAMP,
	data_fine TIMESTAMP,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_provvisorio (
	id INTEGER NOT NULL,
	id_ente VARCHAR,
	identificativo_flusso VARCHAR,
	causale VARCHAR,
	data_movimento TIMESTAMP,
	anno_esercizio INTEGER,
	anno_provvisorio INTEGER,
	numero_provvisorio INTEGER,
	importo_provvisorio DECIMAL(18,2),
	importo_disponibilita DECIMAL(18,2),
	stato VARCHAR,
	descrizione VARCHAR,
	tipo_movimento VARCHAR,
	data_fine TIMESTAMP,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_psp (
	id INTEGER NOT NULL,
	identificativo_psp VARCHAR,
	denominazione_psp VARCHAR,
	flag_riconciliabile BOOLEAN,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_storico_flusso_dettaglio (
	id INTEGER NOT NULL,
	identificativo_flusso VARCHAR NOT NULL,
	codice_versamento VARCHAR NOT NULL,
	iuv VARCHAR NOT NULL,
	progressivo_elaborazione DECIMAL(21,6),
	data_storicizzazione TIMESTAMP,
	utente_modifica VARCHAR,
	importo_singolo_versamento DECIMAL(18,2),
	dataora_flusso TIMESTAMP,
	anagrafica_pagatore VARCHAR,
	codicefiscale_pagatore VARCHAR,
	id_versamento INTEGER,
	dati_specifici_riscossione VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_storico_flusso_origine (
	id INTEGER NOT NULL,
	identificativo_flusso VARCHAR,
	identificativo_istituto_ricevente VARCHAR,
	identificativo_psp VARCHAR,
	numero_totale_pagamenti INTEGER,
	importo_totale_pagamenti DECIMAL(18,2),
	iban_riversamento_flusso VARCHAR,
	dataora_flusso TIMESTAMP,
	data_inserimento TIMESTAMP,
	xml_flusso VARCHAR,
	stato_flusso VARCHAR,
	contatore_tentativi INTEGER,
	id_istituto_ricevente INTEGER,
	id_elaborazione INTEGER,
	id_stato_flusso INTEGER,
	utente_ins_var VARCHAR,
	data_ins_var TIMESTAMP,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_storico_flusso_sintesi (
	id INTEGER NOT NULL,
	identificativo_flusso VARCHAR NOT NULL,
	codice_versamento VARCHAR NOT NULL,
	progressivo_elaborazione DECIMAL(21,6),
	data_storicizzazione TIMESTAMP,
	utente_modifica VARCHAR,
	importo_quota_aggregazione DECIMAL(18,2),
	dataora_flusso TIMESTAMP,
	descrizione_versamento VARCHAR,
	stato_flusso VARCHAR,
	accertamento_anno INTEGER,
	accertamento_nro INTEGER,
	provvisorio_anno INTEGER,
	provvisorio_nro INTEGER,
	id_elaborazione DECIMAL(21,6),
	identificativo_istituto_ricevente VARCHAR,
	identificativo_psp VARCHAR,
	denominazione_psp VARCHAR,
	numero_totale_pagamenti INTEGER,
	importo_totale_pagamenti DECIMAL(18,2),
	codice_fiscale_ente VARCHAR,
	descrizione_accertamento VARCHAR,
	capitolo INTEGER,
	articolo INTEGER,
	piano_dei_conti VARCHAR,
	id_catalogo INTEGER,
	id_lista INTEGER,
	dati_specifici_riscossione VARCHAR,
	PRIMARY KEY (id)
);
CREATE TABLE epay.cbl_t_storico_lock_applicativo (
	id INTEGER NOT NULL,
	id_ente INTEGER NOT NULL,
	id_utente VARCHAR,
	data_inizio TIMESTAMP,
	data_fine TIMESTAMP,
	PRIMARY KEY (id)
);
CREATE TABLE epay.test (
	id INTEGER NOT NULL,
	descrizione VARCHAR(500)
);
CREATE OR REPLACE VIEW epay.cbl_v_dati_accertamento
(
  id,
  id_ente,
  articolo,
  capitolo,
  piano_dei_conti,
  codice_versamento,
  anno_esercizio,
  accertamento_anno,
  accertamento_nro,
  dati_specifici_riscossione,
  pdc,
  priorita,
  data_inizio_validita,
  data_fine_validita
)
AS 
 SELECT epay.cbl_t_catalogo.id,
    epay.cbl_t_catalogo.id_ente,
    epay.cbl_t_catalogo.articolo,
    epay.cbl_t_catalogo.capitolo,
    epay.cbl_t_catalogo.piano_dei_conti,
    epay.cbl_t_catalogo.codice_versamento,
    epay.cbl_t_catalogo.anno_esercizio,
    epay.cbl_t_catalogo.accertamento_anno,
    epay.cbl_t_catalogo.accertamento_nro,
    epay.cbl_t_catalogo.dati_specifici_riscossione,
    substring(epay.cbl_t_catalogo.dati_specifici_riscossione, position(epay.cbl_t_catalogo.dati_specifici_riscossione, '/E.'::text)) AS pdc,
    2 AS priorita,
    epay.cbl_t_catalogo.data_inizio_validita,
    epay.cbl_t_catalogo.data_fine_validita
   FROM epay.cbl_t_catalogo
UNION
 SELECT epay.cbl_t_lista_di_carico.id,
    epay.cbl_t_lista_di_carico.id_ente,
    epay.cbl_t_lista_di_carico.articolo,
    epay.cbl_t_lista_di_carico.capitolo,
    epay.cbl_t_lista_di_carico.piano_dei_conti,
    epay.cbl_t_lista_di_carico.codice_versamento,
    epay.cbl_t_lista_di_carico.anno_esercizio,
    epay.cbl_t_lista_di_carico.accertamento_anno,
    epay.cbl_t_lista_di_carico.accertamento_nro,
    epay.cbl_t_lista_di_carico.dati_specifici_riscossione,
    substring(epay.cbl_t_lista_di_carico.dati_specifici_riscossione, position(epay.cbl_t_lista_di_carico.dati_specifici_riscossione, '/E.'::text)) AS pdc,
    1 AS priorita,
    epay.cbl_t_lista_di_carico.data_inizio_validita,
    epay.cbl_t_lista_di_carico.data_fine_validita
   FROM epay.cbl_t_lista_di_carico
  WHERE epay.cbl_t_lista_di_carico.data_fine_validita IS NULL;
  
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (1, '200', 'Errore di sistema', 'FATAL', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (2, '201', 'Errore dati mancanti', 'FATAL', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (3, '202', 'Errore spacchettamento', 'FATAL', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (4, '220', 'Ente non presente', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (5, '221', 'Elaborazione non presente', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (6, '222', 'Schedulazione non attiva', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (7, '223', 'Non riconciliabile importi', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (8, '224', 'No dati riscossione', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (9, '225', 'No dati accertamento', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (10, '226', 'Numero massimo tentativi', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (12, '227', 'No riferimenti contabili', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (13, '229', 'Dati versamento mancanti', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (14, '228', 'No codice di versamento', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (15, '230', 'Impossibile aggiornare Anno e Numero Provvisorio sulla Tabella di Sintesi', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (17, '232', 'Errore in fase di acquisizione', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (18, '233', 'Errore di sistema generico', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (19, '234', 'Errore di business generico ', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (20, '235', 'Errore recupero informazioni : identificativo flusso mancante', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (21, '240', 'Errore in fase di elaborazione', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (22, '241', 'Valorizzare almeno uno dei parametri di input', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (23, '242', 'Parametro obbligatorio non valorizzato $1 ', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (24, '243', 'Il flusso $1 non risulta spacchettato', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (25, '300', 'Errore no plurimandatario', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (26, '301', 'Schedulazione giorno non corretto', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (27, '302', 'Ente non riconciliabile', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (28, '303', 'Ente no accertamento', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (29, '304', 'PSP non valorizzato', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (30, '305', 'PSP non riconciliabile', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (31, '306', 'Errore no provvisori', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (32, '307', 'Codici versamento da escludere', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (33, '308', 'Data elaborazione della schedulazione non valorizzata', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (34, '309', 'Non ci sono flussi elaborabili', 'WARNING', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (35, '401', 'Parametro di input Ente non presente', 'MESSAGE', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (36, '402', 'Parametro di input Rielaborazione non presente', 'MESSAGE', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (37, '403', 'Elaborazione terminata con successo', 'MESSAGE', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (38, '404', 'Non sono presenti dati', 'MESSAGE', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (39, '244', 'I seguenti parametri sono obbligatori tra di loro', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (40, '243', 'Errore durante il salvataggio dei provvisori', 'ERROR', 'f', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (42, '245', 'Totale dei pagamenti intermediati non corrispondente a importi di testata', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (16, '231', 'Trovati piu'' record sui provvisori', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_errore(id, codice_errore, descrizione_errore, tipologia, flag_mail, flag_rielaborazione) VALUES (0, '100', 'Errore generico per errore non trovato su cbl_d_errore', 'ERROR', 't', 'f');
INSERT INTO epay.cbl_d_modalita_provvisori(id, modalita_acquisizione, descrizione_modalita) VALUES (1, 1, 'Servizi');
INSERT INTO epay.cbl_d_modalita_provvisori(id, modalita_acquisizione, descrizione_modalita) VALUES (2, 2, 'xml - siope+');
INSERT INTO epay.cbl_d_modalita_provvisori(id, modalita_acquisizione, descrizione_modalita) VALUES (3, 3, 'xls/csv');
INSERT INTO epay.cbl_d_modalita_provvisori(id, modalita_acquisizione, descrizione_modalita) VALUES (4, 4, 'da GUI');
INSERT INTO epay.cbl_d_modalita_provvisori(id, modalita_acquisizione, descrizione_modalita) VALUES (5, 0, 'Nessuna');
INSERT INTO epay.cbl_d_modalita_provvisori(id, modalita_acquisizione, descrizione_modalita) VALUES (0, 0, 'Nessuna');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (1, 0, 'Nessuna schedulazione');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (2, 1, 'Giornaliera');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (3, 2, 'Settimanale');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (4, 3, 'Mensile');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (5, 99, 'Singolo Flusso');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (6, 4, 'Bimestrale');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (7, 5, 'Trimestrale');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (8, 6, 'Quadrimestrale');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (9, 7, 'Semestrale');
INSERT INTO epay.cbl_d_schedulazione(id, periodicita_schedulazione, descrizione_periodicita) VALUES (10, 8, 'Annuale');
INSERT INTO epay.cbl_d_stato_elaborazione(id, codice_stato, descrizione_stato) VALUES (1, 'SCHEDULATA', 'Elaborazione schedulata');
INSERT INTO epay.cbl_d_stato_elaborazione(id, codice_stato, descrizione_stato) VALUES (2, 'AVVIATA', 'Elaborazione avviata');
INSERT INTO epay.cbl_d_stato_elaborazione(id, codice_stato, descrizione_stato) VALUES (3, 'IN ERRORE', 'Elaborazione in errore');
INSERT INTO epay.cbl_d_stato_elaborazione(id, codice_stato, descrizione_stato) VALUES (4, 'TERMINATA', 'Elaborazione terminata');
INSERT INTO epay.cbl_d_stato_elaborazione(id, codice_stato, descrizione_stato) VALUES (5, 'FORZATA', 'Elaborazione per cui viene richiesta la riesecuzione');
INSERT INTO epay.cbl_d_stato_elaborazione(id, codice_stato, descrizione_stato) VALUES (6, 'NON ATTIVA', 'Elaborazione non attiva');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (1, 'BOZZA', 'Bozza');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (2, 'IN ATTESA', 'Flusso in attesa di essere elaborato');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (3, 'IN ELABORAZIONE', 'Elaborazione in corso');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (4, 'ELABORATO', 'Elaborato');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (5, 'IN ERRORE', 'Errore durante l''elaborazione');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (7, 'ACQUISITO', 'Documento acquisito da Sistema Contabile');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (8, 'GENERATO', 'Documento contabile Generato');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (9, 'RIFIUTATO', 'Rifiutato da sistema contabile');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (10, 'FORZATO', 'Richiesta riesecuzione');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (12, 'DA SPACCHETTARE', 'Flusso in attesa di spacchettamento');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (6, 'NON ELABORABILE', 'Flusso non elaborabile');
INSERT INTO epay.cbl_d_stato_flusso(id, codice_stato, descrizione_stato) VALUES (11, 'NON RICONCILIABILE', 'Flusso non riconciliabile');
INSERT INTO epay.cbl_r_stato_flusso_errore(id, data_aggiornamento_stato, descrizione_aggiuntiva_errore, id_flusso_origine, id_errore) VALUES (315, '2018-07-26 11:51:59.534', 'Numero massimo tentativi', 401, 10);
INSERT INTO epay.cbl_r_stato_flusso_errore(id, data_aggiornamento_stato, descrizione_aggiuntiva_errore, id_flusso_origine, id_errore) VALUES (323, '2018-07-26 15:35:13.453', 'Errore no provvisori', 400, 31);
INSERT INTO epay.cbl_r_stato_flusso_errore(id, data_aggiornamento_stato, descrizione_aggiuntiva_errore, id_flusso_origine, id_errore) VALUES (324, '2018-07-27 17:20:11.354', 'Errore no provvisori', 400, 31);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (4, '0001', 'LU00', '1- Tassa', 'Ludoteca - iscrizione a ludoteca 2017', 2017, 2017, 211, 'Acc.to Ludoteche 1', 100002, 980, 'E.401.002.006', '9/100002/E.401.002.006', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (5, '0001', 'LU10', '1- Tassa', 'Ludoteca - iscrizione a estate ragazzi', 2017, 2017, 211, 'Acc.to Ludoteche 2', 100002, 980, 'E.401.002.006', '9/100002/E.401.002.006', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (6, '0001', 'ED01', '1- Tassa', 'Edilizia - monetizzazione parcheggi rateizzato edilizia', 2017, 2017, 213, 'Acc.to EDIL1 - privati', 100003, 981, 'E.401.002.007', '9/100003/E.401.002.007', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (7, '0001', 'ED02', '1- Tassa', 'Edilizia - monetizzazione parcheggi rateizzato commercio', 2017, 2017, 213, 'Acc.to EDIL2 - commercio', 100003, 981, 'E.401.002.007', '9/100003/E.401.002.007', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (8, '0001', 'AS01', '1- Tassa', 'Retta Asilo Nido Snoopy', 2017, 2017, 212, 'Acc.to Asilo 2018', 100002, 980, 'E.401.002.008', '9/100002/E.401.002.008', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (9, '0001', 'AS02', '1- Tassa', 'Retta Asilo Nido Campanellino', 2017, 2017, 212, 'Acc.to Asilo 2018', 100002, 980, 'E.401.002.008', '9/100002/E.401.002.008', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (10, '0001', 'AS03', '1- Tassa', 'Retta Asilo Nido PeppaPig', 2017, 2017, 212, 'Acc.to Asilo 2018', 100002, 980, 'E.401.002.008', '9/100002/E.401.002.008', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (23, '0002', 'LU10', '1- Tassa', 'Ludoteca - iscrizione a estate ragazzi', 2017, 2017, 211, 'Acc.to Bollo Auto', 100002, 980, 'E.401.002.006', '9/100002/E.401.002.006', null, null, null, '2017-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (2, '0001', 'T002', '2-Interessi', 'Tassa auto - tassa di circolazione (interessi)', 2017, 2017, 121, 'Acc.to Bollo Auto - Interessi', 100001, 2332, 'E.001.002.004', '9/100001/E.001.002.004', null, null, null, '2017-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (3, '0001', 'T002', '3-Sanzioni', 'Tassa auto - tassa di circolazione(sanzioni)', 2017, 2017, 122, 'Acc.to Bollo Auto - Sanzioni ', 100001, 2332, 'E.001.002.005', '9/100001/E.001.002.005', null, null, null, '2017-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (1, '0001', 'T002', '1- Tassa', 'Tassa auto - tassa di circolazione', 2017, 2017, 120, 'Acc.to Bollo Auto', 100001, 2332, 'E.001.002.003', '2/1210', null, null, null, '2017-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (28, '0001', 'T002', '1- Tassa', 'Tassa auto - tassa di circolazione', 2017, 2017, 120, 'Acc.to Bollo Auto', 100002, 2332, 'E.001.002.003', '2/1210', null, null, null, '2017-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (11, '0001', 'T002', '1- Tassa', 'Tassa auto - tassa di circolazione', 2018, 2017, 1120, 'Acc.to Bollo Auto', 100001, 2332, 'E.001.002.003', '2/1210', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (16, '0001', 'SF00', '1- Diritti', 'Servizi Funebri - servizio completo (diritti)', 2018, 2018, 121, 'Acc.to Servizi Funebri Completo 1', 100001, 2332, 'E.001.002.004', '9/100001/E.001.002.004', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (17, '0001', 'SF00', '2- Trasporto', 'Servizi Funebri - servizio completo (trasporto)', 2018, 2018, 122, 'Acc.to Servizi Funebri Completo 2', 100001, 2332, 'E.001.002.005', '9/100001/E.001.002.005', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (18, '0001', 'SF00', '3- Concessione', 'Servizi Funebri - servizio completo (concessione)', 2018, 2018, 122, 'Acc.to Servizi Funebri Completo 3', 100001, 2332, 'E.001.002.005', '9/100001/E.001.002.005', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (19, '0001', 'SF10', '1- Tassa', 'Servizi Funebri - diritti', 2018, 2018, 121, 'Acc.to Servizi Funebri Diritti', 100001, 2332, 'E.001.002.004', '9/100001/E.001.002.004', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (20, '0001', 'SF20', '1- Tassa', 'Servizi Funebri - trasporto', 2018, 2018, 122, 'Acc.to Servizi Funebri Trasporto', 100001, 2332, 'E.001.002.005', '9/100001/E.001.002.005', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (21, '0001', 'C001', '1- Tassa', 'Ge.Ri.CA Demanio Idrico Fluviale', 2018, 2017, 21232, 'Acc.to GERICA 2018', 30615, 9180, 'E.3.01.03.02.000', '9/30615/E.3.01.03.02.000', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (22, '0002', 'LU00', '1- Tassa', 'Ludoteca - iscrizione a ludoteca 2018', 2018, 2018, 211, 'Acc.to Bollo Auto', 100002, 980, 'E.500.000.001', '9/100002/E.500.000.001', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (24, '0002', 'I001', '1- Tassa', 'Retta Asilo Nido', 2018, 2017, 212, 'Acc.to Asilo Nido', 301020100, 0, 'E.3.01.02.01.000', '9/301020100/E.3.01.02.01.000', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (12, '0001', 'T002', '2-Interessi', 'Tassa auto - tassa di circolazione (interessi)', 2018, 2017, 1121, 'Acc.to Bollo Auto - Interessi', 1121, 2332, 'E.001.002.004', '9/1121/E.001.002.004', null, null, null, '2018-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (13, '0001', 'T002', '3-Sanzioni', 'Tassa auto - tassa di circolazione(sanzioni)', 2018, 2017, 1122, 'Acc.to Bollo Auto - Sanzioni', 100001, 2332, 'E.001.002.005', '9/100001/E.001.002.005', null, null, null, '2018-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (14, '0001', 'TF00', '1- Tassa', 'Tassa rilascio tesserino raccolta tartufi', 2018, 2018, 1, 'Acc.to Tassa Tarfufi 1', 0, 0, 'E.1.01.01.48.000', '9/0/E.1.01.01.48.000', null, null, null, '2018-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (15, '0001', 'TF10', '1- Tassa', 'Tassa Raccolta Tartufi - Tassa Annuale', 2018, 2018, 111, 'Acc.to Tassa Tarfufi 2', 0, 0, 'E.1.01.01.48.001', '9/0/E.1.01.01.48.001', null, null, null, '2018-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (27, '0005', 'RAG1', '1- Tassa', 'Illuminazione Votiva', 2018, 2018, 178, 'Acc.to Lumini 2018', 0, 0, 'E.3.01.02.01.000', '9/0/E.3.01.02.01.000', null, null, null, '2018-01-01 00:00:00', null, null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (25, '0002', 'TF00', '1- Tassa', 'tassa Tartufi - tassa annuale', 2018, 2018, 77, 'Acc.to Tassa Tarfufi 1', 177, 9180, 'E.450.115.008', '9/177/E.450.115.008', null, null, null, '2018-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (26, '0002', 'TF10', '1- Tassa', 'tassa Tartufi - rinnovo tesserino', 2018, 2018, 78, 'Acc.to Tassa Tarfufi 2', 177, 9180, 'E.450.115.009', '9/177/E.450.115.009', null, null, null, '2018-01-01 00:00:00', '2017-12-31 00:00:00', null);
INSERT INTO epay.cbl_t_catalogo(id, id_ente, codice_versamento, sottovoce, descrizione_versamento, anno_esercizio, accertamento_anno, accertamento_nro, accertamento_desc, capitolo, articolo, piano_dei_conti, dati_specifici_riscossione, titolo, tipologia, categoria, data_inizio_validita, data_fine_validita, codice_fiscale_ente) VALUES (29, '0001', 'T002', '1- Tassa', 'Tassa auto - tassa di circolazione', 2017, 2017, 120, 'Acc.to Bollo Auto', 100002, 2332, 'E.001.002.003', '2/1210', null, null, null, '2018-06-11 00:00:00', null, null);
INSERT INTO epay.cbl_t_codiciversamento_esclusione(id, codice_versamento, id_ente, motivazione) VALUES (1, 'TSTA', '0001', 'Codice versamento di test');
INSERT INTO epay.cbl_t_codiciversamento_esclusione(id, codice_versamento, id_ente, motivazione) VALUES (2, 'TSTA', '0002', 'Codice versamento di test');
INSERT INTO epay.cbl_t_codiciversamento_esclusione(id, codice_versamento, id_ente, motivazione) VALUES (3, 'TSTA', '0003', 'Codice versamento di test');
INSERT INTO epay.cbl_t_codiciversamento_esclusione(id, codice_versamento, id_ente, motivazione) VALUES (4, 'TSTA', '0004', 'Codice versamento di test');
INSERT INTO epay.cbl_t_codiciversamento_esclusione(id, codice_versamento, id_ente, motivazione) VALUES (5, 'TST1', '0001', 'pagamento di prova');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (1, '0001', 'LIMITE_TENTATIVI', '10');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (2, '0002', 'LIMITE_TENTATIVI', '5');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (3, '0003', 'LIMITE_TENTATIVI', '1');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (4, '0004', 'LIMITE_TENTATIVI', '0');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (5, '0005', 'LIMITE_TENTATIVI', '2');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (14, null, 'email_template_sistema', 'Si è verificato il seguente errore: <cod_errore><desc_errore>.');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (15, null, 'email_template_acquisizione', 'Durante l’acquisizione del flusso <id_flusso> si è verificato l’errore seguente: <cod_errore><desc_errore>.');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (16, '0001', 'email_template_business', 'Si è verificato un errore durante l’elaborazione del <data_elaborazione> dell’Ente <denominazione_ente> sul flusso <identificativo_flusso>.');
INSERT INTO epay.cbl_t_configurazione(id, id_ente, nome_attributo, valore) VALUES (17, '0001', 'email_template_flusso', 'Si è verificato un errore durante la trasmissione dei flussi in errore / non elaborabili.');
INSERT INTO epay.cbl_t_elaborazione(id, id_ente, data_elaborazione, stato_elaborazione, data_inizio, data_fine, lista_flussi, id_errore, msg_errore) VALUES (2, '0002', '2018-07-26 15:31:57.724', 'SCHEDULATA', null, null, null, null, null);
INSERT INTO epay.cbl_t_elaborazione(id, id_ente, data_elaborazione, stato_elaborazione, data_inizio, data_fine, lista_flussi, id_errore, msg_errore) VALUES (1, '0001', null, 'TERMINATA', '2018-07-26 14:28:12.686', '2018-07-26 14:39:02.408', '2018-05-11ITBBITM1-0000000000037488', null, '');
INSERT INTO epay.cbl_t_elaborazione(id, id_ente, data_elaborazione, stato_elaborazione, data_inizio, data_fine, lista_flussi, id_errore, msg_errore) VALUES (6, '0001', '2018-07-26 15:31:57.724', 'TERMINATA', '2018-07-26 15:31:57.763', '2018-07-26 15:31:58.051', '2018-05-11ITBBITM1-0000000000037485', null, 'L''elaborazione e'' terminata con uno o piu'' flussi in errore.');
INSERT INTO epay.cbl_t_elaborazione(id, id_ente, data_elaborazione, stato_elaborazione, data_inizio, data_fine, lista_flussi, id_errore, msg_errore) VALUES (7, '0001', '2018-07-26 15:32:31.059', 'TERMINATA', '2018-07-26 15:32:31.085', '2018-07-26 15:32:31.288', '2018-05-11ITBBITM1-0000000000037485', null, 'L''elaborazione e'' terminata con uno o piu'' flussi in errore.');
INSERT INTO epay.cbl_t_elaborazione(id, id_ente, data_elaborazione, stato_elaborazione, data_inizio, data_fine, lista_flussi, id_errore, msg_errore) VALUES (3, '0003', null, 'SCHEDULATA', '2018-06-20 00:00:00', null, ' ', null, ' ');
INSERT INTO epay.cbl_t_elaborazione(id, id_ente, data_elaborazione, stato_elaborazione, data_inizio, data_fine, lista_flussi, id_errore, msg_errore) VALUES (4, '0004', null, 'SCHEDULATA', '2018-06-20 00:00:00', null, ' ', null, '');
INSERT INTO epay.cbl_t_elaborazione(id, id_ente, data_elaborazione, stato_elaborazione, data_inizio, data_fine, lista_flussi, id_errore, msg_errore) VALUES (8, '0001', '2018-07-26 15:31:57.724', 'SCHEDULATA', null, null, null, null, null);
INSERT INTO epay.cbl_t_ente(id, id_ente, codice_fiscale, denominazione, flag_accertamento, flag_riconciliazione, iban_tesoreria, ente_plurintermediato, periodicita_schedulazione, giorno_schedulazione, modalita_acquisizione_provvisori, flag_ricezione_errori, email_ente) VALUES (2, '0002', '00429440068', 'Comune di Alessandria', 't', 't', 'IT72Z0558410400000000054245', 'f', 3, 1, 1, 't', 'assistenza.epay@csi.it');
INSERT INTO epay.cbl_t_ente(id, id_ente, codice_fiscale, denominazione, flag_accertamento, flag_riconciliazione, iban_tesoreria, ente_plurintermediato, periodicita_schedulazione, giorno_schedulazione, modalita_acquisizione_provvisori, flag_ricezione_errori, email_ente) VALUES (3, '0003', '10771180014', 'A.U.O. Citta''  della Salute e della Scienza di Torino', 'f', 'f', 'IT35F0306909219100000046290', 'f', 0, 0, 0, 'f', '');
INSERT INTO epay.cbl_t_ente(id, id_ente, codice_fiscale, denominazione, flag_accertamento, flag_riconciliazione, iban_tesoreria, ente_plurintermediato, periodicita_schedulazione, giorno_schedulazione, modalita_acquisizione_provvisori, flag_ricezione_errori, email_ente) VALUES (4, '0004', '06827170017', 'ASL To5 - Chieri, Carmagnola, Moncalieri e Nichelino', 'f', 'f', '', 'f', 0, 0, 0, 'f', '');
INSERT INTO epay.cbl_t_ente(id, id_ente, codice_fiscale, denominazione, flag_accertamento, flag_riconciliazione, iban_tesoreria, ente_plurintermediato, periodicita_schedulazione, giorno_schedulazione, modalita_acquisizione_provvisori, flag_ricezione_errori, email_ente) VALUES (5, '0005', '00364170043', 'Comune di Santo Stefano Belbo', 't', 't', 'IT50A0853046840000200890700', 'f', 99, 0, 4, 't', 'servizio.mdp@csi.it');
INSERT INTO epay.cbl_t_ente(id, id_ente, codice_fiscale, denominazione, flag_accertamento, flag_riconciliazione, iban_tesoreria, ente_plurintermediato, periodicita_schedulazione, giorno_schedulazione, modalita_acquisizione_provvisori, flag_ricezione_errori, email_ente) VALUES (1, '0001', '80087670016', 'Regione Piemonte', 't', 't', '', 'f', 1, 1, 1, 't', 'servizio.mdp@csi.it');
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (510, 'T002', '2/1210', 193, '180090493499081', '1867276101605', 32.25, '2018-05-10 02:00:00', '0', '/RFB/R', 'PRD000000002231758', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (511, 'T002', '2/1210', 193, '180090485481168', '1867273642207', 132.91, '2018-05-10 02:00:00', '0', '/RFB/QQQ', 'PRD000000002231685', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (512, 'T002', '2/1210', 193, '180090487309834', '1867269410606', 132.91, '2018-05-10 02:00:00', '0', '/PPPP', 'PRD000000002231536', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (513, 'T002', '2/1210', 193, '180090497830962', '1867268564609', 132.91, '2018-05-10 02:00:00', '0', '/RFB/OOOOOOOOOOOOOOOOOO', 'PRD000000002231504', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (514, 'T002', '2/1210', 193, '180090491684874', '1867267974712', 275.75, '2018-05-10 02:00:00', '0', 'nnn', 'PRD000000002231481', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (515, 'T002', '2/1210', 193, '173390459840514', '1867266373705', 390.02, '2018-05-10 02:00:00', '0', '/RFB/mmm', 'PRD000000002231413', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (516, 'T002', '2/1210', 193, '180090480897783', '1867261329402', 46.26, '2018-05-10 02:00:00', '0', '/RFB/llll/11', 'PRD000000002231170', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (517, 'T002', '2/1210', 193, '180090478086456', '1867260593110', 125.09, '2018-05-10 02:00:00', '0', 'iii', 'PRD000000002231129', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (518, 'T002', '2/1210', 193, '163560177175671', '1867259364004', 45.73, '2018-05-10 02:00:00', '0', '/RFB/hhh', 'PRD000000002231027', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (519, 'T002', '2/1210', 193, '180090497747161', '1867258510207', 71.72, '2018-05-10 02:00:00', '0', 'ggg', 'PRD000000002230950', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (520, 'T002', '2/1210', 193, '180090480944283', '1867256978908', 37.14, '2018-05-10 02:00:00', '0', '1ffff', 'PRD000000002230827', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (521, 'T002', '2/1210', 193, '180090496998919', '1867256709202', 29.70, '2018-05-10 02:00:00', '0', '/eee123', 'PRD000000002230812', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (522, 'T002', '2/1210', 193, '180090490224976', '1867256206102', 165.45, '2018-05-10 02:00:00', '0', '/dd', 'PRD000000002230787', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (523, 'T002', '2/1210', 193, '180090504283546', '1867254311409', 168.74, '2018-05-10 02:00:00', '0', '/RFB/ccc', 'PRD000000002230706', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (524, 'T002', '2/1210', 193, '180090496557064', '1867252980007', 28.66, '2018-05-10 02:00:00', '0', 'bbbb bollo', 'PRD000000002230673', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (525, 'T002', '2/1210', 193, '180090494884175', '1867249745405', 35.18, '2018-05-10 02:00:00', '0', 'aaa bollo', 'PRD000000002230536', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (526, 'T002', '2/1210', 193, '180090496095506', '1867249383500', 39.61, '2018-05-10 02:00:00', '0', '/RFB/180090496095506/39.61', 'PRD000000002230514', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (527, 'T002', '2/1210', 193, '180090477956660', '1867244939305', 109.09, '2018-05-10 02:00:00', '0', '/RFB/180090477956660/109.09', 'PRD000000002230365', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (528, 'T002', '2/1210', 193, '180090475636314', '1867243407501', 114.67, '2018-05-10 02:00:00', '0', '/RFB/180090475636314/114.67', 'PRD000000002230290', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (529, 'T002', '2/1210', 193, '180090499241027', '1867242323907', 130.30, '2018-05-10 02:00:00', '0', '/RFB/180090499241027/130.30', 'PRD000000002230242', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (530, 'T002', '2/1210', 193, '180090490475874', '1867240183905', 37.37, '2018-05-10 02:00:00', '0', '/RFB/180090490475874/37.37', 'PRD000000002230163', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (531, 'T002', '2/1210', 193, '171800358427353', '1867239556308', 302.78, '2018-05-10 02:00:00', '0', '/RFB/171800358427353/302.78', 'PRD000000002230142', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (532, 'T002', '2/1210', 193, '180090481767539', '1867238113800', 57.33, '2018-05-10 02:00:00', '0', '/RFB/180090481767539/57.33', 'PRD000000002230095', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (533, 'T002', '2/1210', 193, '180090489603290', '1867237845407', 37.14, '2018-05-10 02:00:00', '0', '/RFB/180090489603290/37.14', 'PRD000000002230085', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (534, 'T002', '2/1210', 193, '180090488144006', '1867233829909', 37.14, '2018-05-10 02:00:00', '0', '/RFB/180090488144006/37.14', 'PRD000000002229949', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (535, 'T002', '2/1210', 193, '180090492365802', '1867233637307', 38.44, '2018-05-10 02:00:00', '0', '/RFB/180090492365802/38.44', 'PRD000000002229943', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_dettaglio(id, codice_versamento, dati_specifici_di_riscossione, id_flusso_sintesi, identificativo_unico_versamento, identificativo_unico_riscossione, importo_singolo_versamento, data_pagamento, esito_pagamento, descrizione_causale_versamento, transactionid, stato_invio_fruitore, anagrafica_pagatore, codicefiscale_pagatore, indice_singolo_versamento) VALUES (536, 'T002', '2/1210', 193, '180090485487842', '1867231501104', 130.30, '2018-05-10 02:00:00', '0', '/RFB/180090485487842/130.30', 'PRD000000002229892', null, 'FAKE', 'FAKE', null);
INSERT INTO epay.cbl_t_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, importo_totale_pagamenti_intermediati, numero_totale_pagamenti_intermediati, utente_ins_var, data_ins_var) VALUES (401, '2018-05-11ITBBITM1-0000000000037487', '80087670016', '05435910962', 27, 2884.59, null, '2018-05-13 19:30:00', '2018-07-24 15:39:18.992', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ns2:trasmettiFlussoRendicontazioneExtRequestType xmlns="http://www.csi.it/epay/epaywso/rendicontazione-ext" xmlns:ns2="http://www.csi.it/epay/epaywso/types">
    <Testata>
        <IdMessaggio>2018-05-11ITBBITM1-0000000000037487</IdMessaggio>
        <DataOraMessaggio></DataOraMessaggio>
        <CFEnteCreditore>80087670016</CFEnteCreditore>
        <DenominazioneEnte>Regione Piemonte</DenominazioneEnte>
        <IdPSP>05435910962</IdPSP>
        <DenominazionePSP>Banca 5</DenominazionePSP>
        <IdentificativoFlusso>2018-05-11ITBBITM1-0000000000037487</IdentificativoFlusso>
        <IdentificativoUnivocoRegolamento>1805110634464915480160000000IT74879</IdentificativoUnivocoRegolamento>
        <DataRegolamento>2018-05-11Z</DataRegolamento>
        <NumeroTotalePagamentiFlusso>27</NumeroTotalePagamentiFlusso>
        <NumeroTotalePagamentiIntermediati>27</NumeroTotalePagamentiIntermediati>
        <ImportoTotalePagamentiFlusso>2884.59</ImportoTotalePagamentiFlusso>
        <ImportoTotalePagamentiIntermediati>2884.59</ImportoTotalePagamentiIntermediati>
    </Testata>
    <FlussoRiversamento>FLUSSOBASE64</FlussoRiversamento>
    <PagamentiIntermediati>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090485487842</IUV>
                    <IUR>1867231501104</IUR>
                    <IndiceDatiPagamento>1</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>130.30</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090485487842/130.30</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002229892</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090492365802</IUV>
                    <IUR>1867233637307</IUR>
                    <IndiceDatiPagamento>2</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>38.44</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090492365802/38.44</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002229943</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090488144006</IUV>
                    <IUR>1867233829909</IUR>
                    <IndiceDatiPagamento>3</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.14</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090488144006/37.14</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002229949</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090489603290</IUV>
                    <IUR>1867237845407</IUR>
                    <IndiceDatiPagamento>4</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.14</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090489603290/37.14</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230085</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090481767539</IUV>
                    <IUR>1867238113800</IUR>
                    <IndiceDatiPagamento>5</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>57.33</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090481767539/57.33</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230095</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>171800358427353</IUV>
                    <IUR>1867239556308</IUR>
                    <IndiceDatiPagamento>6</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>302.78</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/171800358427353/302.78</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230142</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090490475874</IUV>
                    <IUR>1867240183905</IUR>
                    <IndiceDatiPagamento>7</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.37</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090490475874/37.37</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230163</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090499241027</IUV>
                    <IUR>1867242323907</IUR>
                    <IndiceDatiPagamento>8</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>130.30</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090499241027/130.30</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230242</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090475636314</IUV>
                    <IUR>1867243407501</IUR>
                    <IndiceDatiPagamento>9</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>114.67</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090475636314/114.67</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230290</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090477956660</IUV>
                    <IUR>1867244939305</IUR>
                    <IndiceDatiPagamento>10</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>109.09</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090477956660/109.09</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230365</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090496095506</IUV>
                    <IUR>1867249383500</IUR>
                    <IndiceDatiPagamento>11</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>39.61</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090496095506/39.61</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230514</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090494884175</IUV>
                    <IUR>1867249745405</IUR>
                    <IndiceDatiPagamento>12</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>35.18</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>aaa bollo</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230536</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090496557064</IUV>
                    <IUR>1867252980007</IUR>
                    <IndiceDatiPagamento>13</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>28.66</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>bbbb bollo</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230673</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090504283546</IUV>
                    <IUR>1867254311409</IUR>
                    <IndiceDatiPagamento>14</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>168.74</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/ccc</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230706</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090490224976</IUV>
                    <IUR>1867256206102</IUR>
                    <IndiceDatiPagamento>15</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>165.45</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/dd</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230787</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090496998919</IUV>
                    <IUR>1867256709202</IUR>
                    <IndiceDatiPagamento>16</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>29.70</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/eee123</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230812</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090480944283</IUV>
                    <IUR>1867256978908</IUR>
                    <IndiceDatiPagamento>17</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.14</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>1ffff</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230827</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090497747161</IUV>
                    <IUR>1867258510207</IUR>
                    <IndiceDatiPagamento>18</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>71.72</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>ggg</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230950</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>163560177175671</IUV>
                    <IUR>1867259364004</IUR>
                    <IndiceDatiPagamento>19</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>45.73</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/hhh</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231027</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090478086456</IUV>
                    <IUR>1867260593110</IUR>
                    <IndiceDatiPagamento>20</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>125.09</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>iii</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231129</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090480897783</IUV>
                    <IUR>1867261329402</IUR>
                    <IndiceDatiPagamento>21</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>46.26</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/llll/11</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231170</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>173390459840514</IUV>
                    <IUR>1867266373705</IUR>
                    <IndiceDatiPagamento>22</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>390.02</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/mmm</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231413</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090491684874</IUV>
                    <IUR>1867267974712</IUR>
                    <IndiceDatiPagamento>23</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>275.75</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>nnn</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231481</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090497830962</IUV>
                    <IUR>1867268564609</IUR>
                    <IndiceDatiPagamento>24</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>132.91</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/OOOOOOOOOOOOOOOOOO</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231504</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090487309834</IUV>
                    <IUR>1867269410606</IUR>
                    <IndiceDatiPagamento>25</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>132.91</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/PPPP</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231536</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090485481168</IUV>
                    <IUR>1867273642207</IUR>
                    <IndiceDatiPagamento>26</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>132.91</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/QQQ</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231685</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090493499081</IUV>
                    <IUR>1867276101605</IUR>
                    <IndiceDatiPagamento>27</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>32.25</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/R</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231758</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
    </PagamentiIntermediati>
</ns2:trasmettiFlussoRendicontazioneExtRequestType>
', 10, 1, 1, 1, 2884.59, 27, null, '2018-07-24 15:39:18.992');
INSERT INTO epay.cbl_t_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, importo_totale_pagamenti_intermediati, numero_totale_pagamenti_intermediati, utente_ins_var, data_ins_var) VALUES (400, '2018-05-11ITBBITM1-0000000000037485', '80087670016', '05435910962', 27, 2884.59, null, '2018-05-13 19:30:00', '2018-07-24 15:39:18.992', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ns2:trasmettiFlussoRendicontazioneExtRequestType xmlns="http://www.csi.it/epay/epaywso/rendicontazione-ext" xmlns:ns2="http://www.csi.it/epay/epaywso/types">
    <Testata>
        <IdMessaggio>2018-05-11ITBBITM1-0000000000037485</IdMessaggio>
        <DataOraMessaggio></DataOraMessaggio>
        <CFEnteCreditore>80087670016</CFEnteCreditore>
        <DenominazioneEnte>Regione Piemonte</DenominazioneEnte>
        <IdPSP>05435910962</IdPSP>
        <DenominazionePSP>Banca 5</DenominazionePSP>
        <IdentificativoFlusso>2018-05-11ITBBITM1-0000000000037485</IdentificativoFlusso>
        <IdentificativoUnivocoRegolamento>1805110634464915480160000000IT74879</IdentificativoUnivocoRegolamento>
        <DataRegolamento>2018-05-11Z</DataRegolamento>
        <NumeroTotalePagamentiFlusso>27</NumeroTotalePagamentiFlusso>
        <NumeroTotalePagamentiIntermediati>27</NumeroTotalePagamentiIntermediati>
        <ImportoTotalePagamentiFlusso>2884.59</ImportoTotalePagamentiFlusso>
        <ImportoTotalePagamentiIntermediati>2884.59</ImportoTotalePagamentiIntermediati>
    </Testata>
    <FlussoRiversamento>FLUSSOBASE64</FlussoRiversamento>
    <PagamentiIntermediati>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090485487842</IUV>
                    <IUR>1867231501104</IUR>
                    <IndiceDatiPagamento>1</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>130.30</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090485487842/130.30</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002229892</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090492365802</IUV>
                    <IUR>1867233637307</IUR>
                    <IndiceDatiPagamento>2</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>38.44</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090492365802/38.44</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002229943</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090488144006</IUV>
                    <IUR>1867233829909</IUR>
                    <IndiceDatiPagamento>3</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.14</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090488144006/37.14</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002229949</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090489603290</IUV>
                    <IUR>1867237845407</IUR>
                    <IndiceDatiPagamento>4</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.14</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090489603290/37.14</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230085</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090481767539</IUV>
                    <IUR>1867238113800</IUR>
                    <IndiceDatiPagamento>5</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>57.33</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090481767539/57.33</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230095</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>171800358427353</IUV>
                    <IUR>1867239556308</IUR>
                    <IndiceDatiPagamento>6</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>302.78</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/171800358427353/302.78</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230142</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090490475874</IUV>
                    <IUR>1867240183905</IUR>
                    <IndiceDatiPagamento>7</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.37</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090490475874/37.37</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230163</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090499241027</IUV>
                    <IUR>1867242323907</IUR>
                    <IndiceDatiPagamento>8</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>130.30</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090499241027/130.30</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230242</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090475636314</IUV>
                    <IUR>1867243407501</IUR>
                    <IndiceDatiPagamento>9</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>114.67</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090475636314/114.67</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230290</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090477956660</IUV>
                    <IUR>1867244939305</IUR>
                    <IndiceDatiPagamento>10</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>109.09</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090477956660/109.09</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230365</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090496095506</IUV>
                    <IUR>1867249383500</IUR>
                    <IndiceDatiPagamento>11</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>39.61</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/180090496095506/39.61</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230514</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090494884175</IUV>
                    <IUR>1867249745405</IUR>
                    <IndiceDatiPagamento>12</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>35.18</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>aaa bollo</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230536</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090496557064</IUV>
                    <IUR>1867252980007</IUR>
                    <IndiceDatiPagamento>13</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>28.66</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>bbbb bollo</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230673</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090504283546</IUV>
                    <IUR>1867254311409</IUR>
                    <IndiceDatiPagamento>14</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>168.74</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/ccc</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230706</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090490224976</IUV>
                    <IUR>1867256206102</IUR>
                    <IndiceDatiPagamento>15</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>165.45</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/dd</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230787</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090496998919</IUV>
                    <IUR>1867256709202</IUR>
                    <IndiceDatiPagamento>16</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>29.70</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/eee123</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230812</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090480944283</IUV>
                    <IUR>1867256978908</IUR>
                    <IndiceDatiPagamento>17</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>37.14</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>1ffff</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230827</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090497747161</IUV>
                    <IUR>1867258510207</IUR>
                    <IndiceDatiPagamento>18</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>71.72</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>ggg</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002230950</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>163560177175671</IUV>
                    <IUR>1867259364004</IUR>
                    <IndiceDatiPagamento>19</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>45.73</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/hhh</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231027</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090478086456</IUV>
                    <IUR>1867260593110</IUR>
                    <IndiceDatiPagamento>20</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>125.09</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>iii</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231129</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090480897783</IUV>
                    <IUR>1867261329402</IUR>
                    <IndiceDatiPagamento>21</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>46.26</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/llll/11</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231170</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>173390459840514</IUV>
                    <IUR>1867266373705</IUR>
                    <IndiceDatiPagamento>22</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>390.02</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/mmm</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231413</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090491684874</IUV>
                    <IUR>1867267974712</IUR>
                    <IndiceDatiPagamento>23</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>275.75</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>nnn</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231481</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090497830962</IUV>
                    <IUR>1867268564609</IUR>
                    <IndiceDatiPagamento>24</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>132.91</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/OOOOOOOOOOOOOOOOOO</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231504</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090487309834</IUV>
                    <IUR>1867269410606</IUR>
                    <IndiceDatiPagamento>25</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>132.91</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/PPPP</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231536</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090485481168</IUV>
                    <IUR>1867273642207</IUR>
                    <IndiceDatiPagamento>26</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>132.91</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/QQQ</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231685</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
        <PagamentoIntermediato>
            <DatiSingoliPagamenti>
                <DatiSingoloPagamento>
                    <IUV>180090493499081</IUV>
                    <IUR>1867276101605</IUR>
                    <IndiceDatiPagamento>27</IndiceDatiPagamento>
                    <CodiceEsitoPagamento>0</CodiceEsitoPagamento>
                    <SingoloImportoPagato>32.25</SingoloImportoPagato>
                    <DataEsitoSingoloPagamento>2018-05-10Z</DataEsitoSingoloPagamento>
                    <CodiceVersamento>T002</CodiceVersamento>
                    <DatiSpecificiRiscossione>2/1210</DatiSpecificiRiscossione>
                    <DescrizioneCausaleVersamento>/RFB/R</DescrizioneCausaleVersamento>
                    <TransactionId>PRD000000002231758</TransactionId>
                    <AnagraficaPagatore>
                        <ns2:PersonaFisica>
                            <ns2:Cognome>FAKE</ns2:Cognome>
                        </ns2:PersonaFisica>
                        <ns2:IdentificativoUnivocoFiscale>FAKE</ns2:IdentificativoUnivocoFiscale>
                        <ns2:Indirizzo>FAKE</ns2:Indirizzo>
                        <ns2:Civico>FAKE</ns2:Civico>
                        <ns2:CAP>FAKE</ns2:CAP>
                        <ns2:Localita>FAKE</ns2:Localita>
                        <ns2:Provincia>FAKE</ns2:Provincia>
                        <ns2:Nazione>FAKE</ns2:Nazione>
                        <ns2:EMail>FAKE</ns2:EMail>
                    </AnagraficaPagatore>
                </DatiSingoloPagamento>
            </DatiSingoliPagamenti>
        </PagamentoIntermediato>
    </PagamentiIntermediati>
</ns2:trasmettiFlussoRendicontazioneExtRequestType>
', 1, 1, 8, 3, 2884.59, 27, null, '2018-07-24 15:39:18.992');
INSERT INTO epay.cbl_t_flusso_sintesi(id, codice_versamento, importo_quota_aggregazione, numero_vers_quota_aggregazione, dati_specifici_di_riscossione, provvisorio_anno, provvisorio_nro, id_istituto_ricevente, id_errore, id_flusso_origine, capitolo, articolo, piano_dei_conti) VALUES (193, 'T002', 2884.59, 27, '2/1210', null, null, 1, null, 400, null, null, null);
INSERT INTO epay.cbl_t_iban(id, id_ente, iban, id_applicativo_mdp) VALUES (1, '0001', 'IT94V0200801044000040777516', 'STASPNODO');
INSERT INTO epay.cbl_t_iban(id, id_ente, iban, id_applicativo_mdp) VALUES (2, '0001', 'IT03T0760101000000022208128', 'EPAY_RP_TARTUFI');
INSERT INTO epay.cbl_t_iban(id, id_ente, iban, id_applicativo_mdp) VALUES (3, '0001', 'IT03T0760101000000022208128', 'EPAY_RP_GERICA');
INSERT INTO epay.cbl_t_iban(id, id_ente, iban, id_applicativo_mdp) VALUES (4, '0002', 'IT17U0760110400000010741155', 'EPAY_RP_AL_NIDI');
INSERT INTO epay.cbl_t_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (1, 1, 'USERID', '2018-07-26 15:35:13.173', '2018-07-26 15:35:13.546');
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (2, '0001', '2018-05-19BCITITMM-0000000033', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-19BCITITMM-0000000033', '50402-05-18 16:55:10.672', 2018, 2018, 102, 1000.00, 1000.00, 'VALIDO', 'Provvisorio per incasso n.102/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (3, '0001', '2018-05-2018-05-22CIPBITMM-08-00-20-31570', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-2018-05-22CIPBITMM-08-00-20-31570', '50402-05-18 16:55:09.672', 2018, 2018, 103, 1016.00, 1016.00, 'VALIDO', 'Provvisorio per incasso n.103/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (4, '0001', '2018-05-2018-05-24BCITITMM-0000000037', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-2018-05-24BCITITMM-0000000037', '50402-05-18 16:55:08.672', 2018, 2018, 104, 1016.00, 1016.00, 'VALIDO', 'Provvisorio per incasso n.104/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (5, '0001', '2018-05-21ABI03069-ADRSY00040777516', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-21ABI03069-ADRSY00040777516', '50402-05-18 16:55:07.672', 2018, 2018, 105, 1016.00, 1016.00, 'VALIDO', 'Provvisorio per incasso n.105/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (6, '0001', '2018-05-21CIPBITMM-08-00-17-94244', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-21CIPBITMM-08-00-17-94244', '50402-05-18 16:55:06.672', 2018, 2018, 106, 1032.00, 1032.00, 'VALIDO', 'Provvisorio per incasso n.106/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (7, '0001', '2018-05-22BCITITMM-0000000037', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-22BCITITMM-0000000037', '50402-05-18 16:55:05.672', 2018, 2018, 107, 1032.00, 1032.00, 'VALIDO', 'Provvisorio per incasso n.107/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (8, '0001', '2018-05-22BIC32698-010502907', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-22BIC32698-010502907', '50402-05-18 16:55:04.672', 2018, 2018, 108, 1032.00, 1032.00, 'VALIDO', 'Provvisorio per incasso n.108/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (9, '0001', '2018-05-22UNCRITMM-1h7fues9osh29', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-22UNCRITMM-1h7fues9osh29', '50402-05-18 16:55:03.672', 2018, 2018, 109, 1048.00, 1048.00, 'VALIDO', 'Provvisorio per incasso n.109/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (10, '0001', '2018-05-23BCITITMM-0000000034', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-23BCITITMM-0000000034', '50402-05-18 16:55:02.672', 2018, 2018, 110, 1048.00, 1048.00, 'VALIDO', 'Provvisorio per incasso n.110/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (11, '0001', '2018-05-23BPPIITRRXXX-000037616703', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-23BPPIITRRXXX-000037616703', '50402-05-18 16:55:01.672', 2018, 2018, 111, 1048.00, 1048.00, 'VALIDO', 'Provvisorio per incasso n.111/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (12, '0001', '2018-05-23BPPIITRRXXX-001018526952', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-23BPPIITRRXXX-001018526952', '50402-05-18 16:55:00.672', 2018, 2018, 112, 1064.00, 1064.00, 'VALIDO', 'Provvisorio per incasso n.112/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (13, '0001', '2018-05-23PASCITMM-0E0JHIC0WV60G', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-23PASCITMM-0E0JHIC0WV60G', '50402-05-18 16:54:59.672', 2018, 2018, 113, 1064.00, 1064.00, 'VALIDO', 'Provvisorio per incasso n.113/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (14, '0001', '2018-05-24BCITITMM-0000000213', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-24BCITITMM-0000000213', '50402-05-18 16:54:58.672', 2018, 2018, 114, 1064.00, 1064.00, 'VALIDO', 'Provvisorio per incasso n.114/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (15, '0001', '2018-05-24SIGPITM1XXX-010652695', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-24SIGPITM1XXX-010652695', '50402-05-18 16:54:57.672', 2018, 2018, 115, 1080.00, 1080.00, 'VALIDO', 'Provvisorio per incasso n.115/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (16, '0001', '2018-05-24UNCRITMM-1iuqfjbo3kmec', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-24UNCRITMM-1iuqfjbo3kmec', '50402-05-18 16:54:56.672', 2018, 2018, 116, 1080.00, 1080.00, 'VALIDO', 'Provvisorio per incasso n.116/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (17, '0001', '2018-05-25PASCITMM-0G0JHL782SG0S', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-25PASCITMM-0G0JHL782SG0S', '50402-05-18 16:54:55.672', 2018, 2018, 117, 1080.00, 1080.00, 'VALIDO', 'Provvisorio per incasso n.117/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (18, '0001', '2018-05-25SIGPITM1XXX-010451428', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-25SIGPITM1XXX-010451428', '50402-05-18 16:54:54.672', 2018, 2018, 118, 1096.00, 1096.00, 'VALIDO', 'Provvisorio per incasso n.118/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (19, '0001', '2018-05-28BIC32698-010134541', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-28BIC32698-010134541', '50402-05-18 16:54:53.672', 2018, 2018, 119, 1096.00, 1096.00, 'VALIDO', 'Provvisorio per incasso n.119/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (20, '0001', '2018-05-29ABI02008-ADRSY00040777516', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-29ABI02008-ADRSY00040777516', '50402-05-18 16:54:52.672', 2018, 2018, 120, 1096.00, 1096.00, 'VALIDO', 'Provvisorio per incasso n.120/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (21, '0001', '2018-05-29ITBBITM1-0000000000039698', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-29ITBBITM1-0000000000039698', '50402-05-18 16:54:51.672', 2018, 2018, 121, 1112.00, 1112.00, 'VALIDO', 'Provvisorio per incasso n.121/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (22, '0001', '2018-05-30BCITITMM-0000000036', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-30BCITITMM-0000000036', '50402-05-18 16:54:50.672', 2018, 2018, 122, 1112.00, 1112.00, 'VALIDO', 'Provvisorio per incasso n.122/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (23, '0001', '2018-05-30BPPIITRRXXX-000037616703', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-30BPPIITRRXXX-000037616703', '50402-05-18 16:54:49.672', 2018, 2018, 123, 1112.00, 1112.00, 'VALIDO', 'Provvisorio per incasso n.123/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (24, '0001', '2018-05-31ABI02008-ADRSY00040777516', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-31ABI02008-ADRSY00040777516', '50402-05-18 16:54:48.672', 2018, 2018, 124, 1128.00, 1128.00, 'VALIDO', 'Provvisorio per incasso n.124/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (25, '0001', '2018-05-31CIPBITMM-08-00-23-88091', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-31CIPBITMM-08-00-23-88091', '50402-05-18 16:54:47.672', 2018, 2018, 125, 1128.00, 1128.00, 'VALIDO', 'Provvisorio per incasso n.125/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (26, '0001', '2018-05-31ITBBITM1-0000000000040091', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-31ITBBITM1-0000000000040091', '50402-05-18 16:54:46.672', 2018, 2018, 126, 1128.00, 1128.00, 'VALIDO', 'Provvisorio per incasso n.126/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (27, '0001', '2018-05-31SIGPITM1XXX-010744739', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-31SIGPITM1XXX-010744739', '50402-05-18 16:54:45.672', 2018, 2018, 127, 1144.00, 1144.00, 'VALIDO', 'Provvisorio per incasso n.127/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (28, '0001', '2018-05-31UNCRITMM-1h82j01jv23hy', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-31UNCRITMM-1h82j01jv23hy', '50402-05-18 16:54:44.672', 2018, 2018, 128, 1144.00, 1144.00, 'VALIDO', 'Provvisorio per incasso n.128/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (29, '0001', '2018-06-01SIGPITM1XXX-011844010', '/PUR/LGPE-RIVERSAMENTO/URI/2018-06-01SIGPITM1XXX-011844010', '50402-05-18 16:54:43.672', 2018, 2018, 129, 1144.00, 1144.00, 'VALIDO', 'Provvisorio per incasso n.129/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (30, '0002', '2018-05-21BCITITMM-0000000208', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-21BCITITMM-0000000208', '50402-05-18 16:54:42.672', 2018, 2018, 130, 1160.00, 1160.00, 'VALIDO', 'Provvisorio per incasso n.130/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (31, '0003', '2018-05-21BCITITMM-0000000G12', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-21BCITITMM-0000000G12', '50402-05-18 16:54:41.672', 2018, 2018, 131, 1160.00, 1160.00, 'VALIDO', 'Provvisorio per incasso n.131/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (32, '0003', '2018-05-31SATYGB21-060320759047', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-31SATYGB21-060320759047', '50402-05-18 16:54:40.672', 2018, 2018, 132, 1160.00, 1160.00, 'VALIDO', 'Provvisorio per incasso n.132/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (33, '0004', '2018-05-19BCITITMM-0000000G47', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-19BCITITMM-0000000G47', '50402-05-18 16:54:39.672', 2018, 2018, 133, 1176.00, 1176.00, 'VALIDO', 'Provvisorio per incasso n.133/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (34, '0004', '2018-05-21UNCRITMM-s10l6f5xqek8', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-21UNCRITMM-s10l6f5xqek8', '50402-05-18 16:54:38.672', 2018, 2018, 134, 1176.00, 1176.00, 'VALIDO', 'Provvisorio per incasso n.134/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (35, '0004', '2018-05-22CRPPIT2PXXX-010553728', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-22CRPPIT2PXXX-010553728', '50402-05-18 16:54:37.672', 2018, 2018, 135, 1176.00, 1176.00, 'VALIDO', 'Provvisorio per incasso n.135/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (36, '0005', '2018-05-25ITBBITM1-0000000000039221', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-25ITBBITM1-0000000000039221', '50402-05-18 16:54:36.672', 2018, 2018, 136, 1192.00, 1192.00, 'VALIDO', 'Provvisorio per incasso n.136/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (1, '0001', '2018-05-18BCITITMM-0000000034', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-18BCITITMM-0000000034', '50402-05-18 16:55:11.672', 2018, 2018, 101, 798.90, 798.90, 'VALIDO', 'Provvisorio per incasso n.101/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (999999, '0001', '2018-05-18BCITITMM-TESTENG3-34', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-18BCITITMM-TESTENG3-34', '2018-07-19 10:30:37.888562', 2018, 2018, 101, 798.90, 798.90, 'VALIDO', 'Provvisorio per incasso n.101/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (999998, '0001', '2018-05-11ITBBITM1-0000000000037488', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-11ITBBITM1-0000000000037488', '2018-07-19 10:30:37.888', 2018, 2018, 101, 2884.59, 2884.59, 'VALIDO', 'Provvisorio per incasso n.101/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_provvisorio(id, id_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine) VALUES (999997, '0001', '2018-05-11ITBBITM1-0000000000037485', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-11ITBBITM1-0000000000037485', '2018-07-19 10:30:37.888', 2018, 2018, 101, 2884.59, 2884.59, 'VALIDO', 'Provvisorio per incasso n.101/2018', 'SOSPESO DI ENTRATA', null);
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (3, 'ABI05036', 'Banca Agricola Popolare di Ragusa Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (4, 'ABI08123', 'CASSA RURALE DI LIZZANA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (5, 'ICRAITRRTM0', 'BCC DEI COMUNI CILENTANI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (7, 'BDBOIT22XXX', 'BANCA DI BOLOGNA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (8, 'CCRTIT2T83A', 'BANCA DEI COLLI EUGANEI CRED. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (9, 'ABI08587', 'Banca di Credito Cooperativo Valdostana - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (10, 'CCRTIT2T30A', 'CASSA RURALE LAVIS-MEZZOCORONA-VALLE DI CEMBRA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (11, 'ABI08753', 'BCC di Pianfei e Rocca De\'' Baldi Societa\'' cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (12, 'CRDRIT2FXXX', 'CREDITO DI ROMAGNA S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (13, 'BPLZIT3V', 'Banca Popolare del Lazio Scpa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (14, 'CCRTIT2TK00', 'CREDITO COOPERATIVO FRIULI SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (15, 'ICRAITRR5Y0', 'BCC CERNUSCO SUL NAVIGLIO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (16, 'ABI03403', 'imprebanca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (17, 'ABI08162', 'Raiffeisenkasse Deutschnofen-Aldein Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (18, 'ABI08595', 'Banca del Crotonese - Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (20, 'ABI08928', 'Banca di Credito Cooperativo del Carso - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (21, 'ABI07056', 'Banca di Credito Cooperativo dell\''Alta Murgia Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (22, 'ABI08637', 'Banca di Credito Cooperativo del Friuli Centrale - Società cooperatva', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (24, 'ABI03488', 'Cassa Lombarda - Società per Azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (25, 'RZSBIT21036', 'Raiffeisenkasse Freienfeld Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (26, 'ABI05856', 'Banca Popolare dell\''Alto Adige S.p.A. - Volksbank', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (27, 'ABI08940', 'BANCA DI CREDITO COOPERATIVO BERGAMASCA E OROBICA S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (28, 'ABI08817', 'BCC di San Marzano di San Giuseppe (Taranto) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (30, 'ABI05023', 'Banca Popolare di Sviluppo - Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (32, 'ABI08692', 'CREDITO COOPERATIVO DI BRESCIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (34, 'ABI08839', 'Banca Suasa Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (35, 'ABI07098', 'Banca di Credito Cooperativo Antonello da Messina - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (39, 'ABI07110', 'Banca di Credito Cooperativo di Napoli', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (40, 'ABI03253', 'Banca Federico del Vecchio - Società per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (41, 'BPVIIT3T', 'Banca Nuova S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (42, 'BLOPIT22', 'UBI Banca S.p.A', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (46, 'ABI08964', 'Banca di Credito Cooperativo S. Barnaba di Marino - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (47, 'ABI08562', 'Banca di Pisa e Fornacette - soc.coop. a responsabilità limitata', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (48, 'ABI07070', 'Banca di Cesena Credito Cooperativo di Cesena e Ronta - SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (49, 'RZSBIT21033', 'Raiffeisenkasse Schenna Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (50, 'ABI08487', 'Banca di Credito Cooperativo di Cherasco - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (51, 'ABI08425', 'Banca Cambiano 1884 Spa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (52, 'CCRTIT2T21A', 'CASSA RURALE BASSA ANAUNIA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (53, 'CCRTIT2TW00', 'BANCA ANNIA S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (54, 'CCRTIT2TU00', 'BANCA SAN BIAGIO VENETO ORIENTALE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (55, 'CCRTIT2TCSS', 'BCC CASALGRASSO E SANT\''ALBANO STURA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (56, 'ABI08163', 'Cassa Rurale Alta Valdisole e Pejo BCC - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (57, 'ABI08673', 'ChiantiBanca Credito Cooperativo Soc. Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (59, 'ABI03058', 'CheBanca! S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (60, 'ICRAITRRK80', 'BANCA PATAVINA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (61, 'ICRAITRRC90', 'Credito Cooperativo di Caravaggio Adda e Cremasco', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (62, 'ABI08214', 'BCC CERNUSCO SUL NAVIGLIO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (64, '07338820967', '', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (65, 'ABI08091', 'CASSA RURALE DEGLI ALTIPIANI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (67, 'ABI08716', 'Banca Centro Lazio Credito Cooperativo Società Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (69, 'ABI06385', 'Cassa di Risparmio in Bologna', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (70, 'ABI08114', 'Raiffeisenkasse Unterland Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (71, 'CCRTIT2TCIV', 'BANCA DI CREDITO COOPERATIVO DI CIVITANOVA MARCHE E MONTECOSARO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (72, 'ICRAITRRAM0', 'BANCA DI CREDITO COOPERATIVO DI BENE VAGIENNA (CUNEO) S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (73, 'CCRTIT2TB01', 'CASSA RURALE ED ARTIGIANA DI VESTENANOVA CRED.COOP.SC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (74, 'BPAAIT2B', 'Banca Popolare dell\''Alto Adige S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (75, 'CRRAIT2R', 'Cassa di Risparmio di Ravenna SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (77, 'ABI03127', 'UNIPOL Banca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (78, 'ABI03017', 'Invest Banca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (79, 'ABI07080', 'Credito Etneo Banca di Credito Cooperativo - SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (80, 'ABI08537', 'Credito Cooperativo Cassa Rurale ed Artigiana di Erchie BR - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (81, 'ABI08332', 'BANCA PICENA TRUENTINA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (83, 'CCRTIT2TBTL', 'BANCA DEL TERRITORIO LOMBARDO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (84, 'ABI06120', 'Cassa di Risparmio di Cesena S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (85, 'ABI08354', 'Banca di Credito Cooperativo di Avetrana (TA) - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (86, 'ABI03365', 'Banco delle Tre Venezie Spa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (87, 'ABI08689', 'BCC MARCON VENEZIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (88, 'ABI05584', 'Banca Popolare di Milano', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (90, 'ABI03075', 'Banca Generali S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (91, 'ABI08460', 'BCC di Cassano delle Murge e Tolve - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (92, 'ABI07072', 'Emil Banca - Credito Cooperativo - Societa\'' cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (94, 'ICRAITRR8R0', 'Valpolicella Benaco Banca Credito Cooperativo Soc.Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (95, 'ICRAITRRU40', 'BANCA DELLA MARCA CREDITO COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (97, 'ABI06090', 'Cassa di Risparmio di Biella e Vercelli S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (100, 'ABI08807', 'BANCA SAN GIORGIO QUINTO  epaymodric.VALLE AGNO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (101, 'ABI05652', 'San Felice 1893 Banca Popolare Soc.Coop.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (102, 'CCRTIT2T99A', 'BCC DEL FRIULI CENTRALE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (104, 'ABI03239', 'INTESA SANPAOLO PRIVATE BANKING S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (106, 'CCRTIT2T67A', 'CASSA RURALE D\''ANAUNIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (107, 'ICRAITRRR40', 'B.C.C. San Giuseppe', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (108, 'ABI08749', 'Centromarca Banca - Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (109, 'ABI08066', 'Raiffeisenkasse Obervinschgau Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (111, 'ABI05297', 'Banca Popolare del Frusinate SCpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (112, 'ICRAITRRAF0', 'BCC DI BASILIANO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (113, 'ABI07108', 'Banca di Credito Cooperativo Agrigentino - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (115, 'CCRTIT2T27A', 'CASSA RURALE VALSUGANA E TESINO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (119, 'ABI08273', 'Raiffeisenkasse Tisens Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (120, 'RZSBIT21049', 'Raiffeisenkasse Villnöß Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (124, 'CCRTIT2T95', 'CRA DI CORTINA E DOLOMITI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (125, 'ABI03441', 'Banca Sviluppo Tuscia', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (130, 'ABI08295', 'Raiffeisenkasse Vintl Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (131, 'ABI08042', 'CASSA RURALE DI RABBI E CALDES BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (132, 'ABI08982', 'BANCA ADRIA CREDITO COOP DEL DELTA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (133, 'ABI05216', 'Credito Valtellinese s.p.a.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (134, 'ICRAITRRB30', 'CRA DI BRENDOLA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (135, 'ABI03205', 'BANCA IFIS S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (136, 'CCRTIT2TL00', 'BCC SAN MARCO DEI CAVOTI E DEL SANNIO CALVI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (137, 'BCPCIT2P', 'Banca di Piacenza soc. coop. per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (138, 'RZSBIT21007', 'Raiffeisenkasse Eisacktal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (139, 'CCRTIT2T76A', 'CASSA RURALE DI TRENTO BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (140, 'ABI08010', 'Raiffeisenkasse Hochpustertal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (141, 'ICRAITRRNN0', 'TERRE ETRUSCHE E DI MAREMMA  - C.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (143, 'RZSBIT21027', 'Raiffeisenkasse Prad-Taufers Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (144, 'ABI05000', 'Istituto Centrale delle Banche Popolari Italiane - Società per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (146, 'ICRAITRRJX0', 'BANCA DI CREDITO COOP. CRA DI PALIANO SC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (147, 'ABI05232', 'Banca Popolare di Lajatico S.C.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (148, 'BPBAIT3B', 'BANCA POPOLARE DI BARI SCPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (149, 'ABI08619', 'Banca di Credito Cooperativo di Lesmo Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (151, 'ABI08829', 'VIBanca Banca di Credito cooperativo di S.Pietro in Vincio - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (152, 'ABI08623', 'Banco Emiliano Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (155, 'CCRTIT2T68A', 'CASSA RURALE DI TASSULLO E NANNO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (156, 'ABI08489', 'Banca Valdichiana Credito Cooperativo Tosco-Umbro - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (157, 'ABI08688', 'Banca di Credito Cooperativo di Marina di Ginosa Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (158, 'CCRTIT2TSMZ', 'BCC DI SAN MARZANO DI SAN GIUSEPPE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (159, 'ABI08461', 'BCC di Castagneto Carducci - soc.coop. per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (160, 'ICRAITRRBK0', 'BANCA DI VERONA CREDITO COOPERATIVO CADIDAVID', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (161, 'ABI06010', 'Cassa di Risparmio di Forli e della Romagna', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (162, 'ABI08824', 'Banca di Credito Cooperativo di Serino (Avellino) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (163, 'ABI08279', 'CASSA RURALE VALLI DI PRIMIERO E VANOI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (164, 'ABI08102', 'CASSA RURALE VALSUGANA E TESINO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (165, 'ABI03430', 'Credito Lombardo Veneto S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (166, 'CCRTIT2TCHB', 'ChiantiBanca Credito Cooperativo Soc. Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (167, 'ICCRAITRB30', 'CRA DI BRENDOLA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (169, 'ICRAIT3FKG0', 'BCC PONTASSIEVE S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (170, 'ABI08575', 'Banca di Credito Cooperativo dell\''Agro Bresciano - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (171, 'ABI08167', 'CASSA RURALE OLLE-SAMONE-SCURELLE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (172, 'BAECIT2B', 'UNIPOL Banca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (173, 'RZSBIT21018', 'Raiffeisenkasse Marling Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (174, 'ABI07084', 'Banca della Marca Credito Cooperativo Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (175, 'ABI08975', 'Banca di Credito Cooperativo "S. Giuseppe" di Mussomeli -soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (176, 'ABI08305', 'CASSA RURALE ALTA VALLAGARINA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (177, 'ABI03048', 'BANCA DEL PIEMONTE SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (180, 'AZZBITT1XXX', 'BANCO DI CREDITO P. AZZOAGLIO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (182, 'ABI08209', 'Cassa Rurale di Rovere\'' della Luna - BCC - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (184, 'RZSBIT21039', 'Raiffeisenkasse Ulten-St. Pankraz-Laurein Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (185, 'ABI03047', 'BANCA CAPASSO ANTONIO SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (186, 'CCRTIT2T20A', 'C.R.GIUDICARIE VALSABBIA PAGANELLA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (187, 'ABI08112', 'Raiffeisenkasse Algund Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (189, 'ABI08873', 'Banca di Credito Cooperativo di Spinazzola (Bari) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (192, 'ABI08342', 'BANCA CREDITO COOPERATIVO DI AQUARA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (193, 'RUAMIT22XXX', 'Banca Ter Credito Cooperativo FVG', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (194, 'ABI08979', 'BCC S. Michele di Caltanissetta e Pietraperzia - soc.coop. per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (195, 'ABI08852', 'RomagnaBanca Credito Cooperativo s.c.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (196, 'ICRAITRRAI0', 'CRA BCC di Battipaglia e Mo.Rovella', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (197, 'ABI08439', 'Banca di Caraglio, del Cuneese e della Riviera dei Fiori-BCC -soc.coop', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (198, 'ABI08578', 'Banca di Credito Cooperativo di Gradara - Società  cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (199, 'BCITITMM', 'Intesa Sanpaolo S.p.A', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (200, 'ABI08676', 'BCC del Garda - BCC Colli Morenici del Garda - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (202, 'CCRTIT2T26A', 'Cassa Rurale di Giovo', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (203, 'ABI08263', 'CASSA RURALE D\''ANAUNIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (204, 'ABI03388', 'Banca Stabiese S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (205, 'ABI36049', 'IPAGOO LLP', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (207, 'ABI08515', 'Banca della Valsassina Credito Cooperativo Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (208, 'ABI08794', 'B.C.C.LAUDENSE LODI S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (209, 'CCRTIT2TXXX', 'CASSA CENTRALE BANCA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (210, 'ABI08481', 'Cereabanca 1897 Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (211, 'ABI05262', 'Banca Popolare Pugliese - Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (213, 'CCRTIT2TNIS', 'BANCA DEL NISSENO CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (214, 'SARCIT2SXXX', 'CASSA DI RISPARMIO DI SAVIGLIANO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (216, 'CRFEIT3F', 'Cassa di Risparmio di Fermo S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (217, 'RZSBIT21052', 'Raiffeisenkasse Welsberg-Gsies-Taisten Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (218, '05435910962', 'Banca 5', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (220, 'CCRTIT2T01A', 'CASSA RURALE VALLAGARINA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (221, 'CCRTIT2T63A', 'CASSA RURALE VAL RENDENA B.C.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (222, 'ABI08416', 'Banca di Verona Credito Cooperativo Cadidavid - soc.coop. per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (223, 'ABI05676', 'Banca di Sassari S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (225, 'ABI06045', 'Cassa di Risparmio di Bolzano S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (226, 'ABI08542', 'LA BCC RAVENNATE FORLIVESE E IMOL.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (227, 'ABI08986', 'ROVIGOBANCA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (228, 'ABI08917', 'CREDITO TREVIGIANO BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (229, 'ABI08248', 'CASSA RURALE VAL RENDENA B.C.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (231, 'ABI08431', 'BCC DI CAPACCIO PAESTUM', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (232, 'ABI08765', 'Banca di Credito Cooperativo di Recanati e Colmurano - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (233, 'SELBIT2B', 'BANCA SELLA S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (234, 'RZSBIT21008', 'Cassa Raiffeisen Val Badia Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (235, 'ABI07085', 'CREDITO COOPERATIVO FRIULI SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (236, 'ICRAITRRKT0', 'CENTROMARCA BANCA CREDITO COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (239, 'BPPUIT33', 'Banca Popolare Pugliese', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (241, 'ICRAITRRNG0', 'BANCA DI CREDITO COOPERATIVO DI SANTERAMO IN COLLE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (242, 'ABI08325', 'Banca del Mugello - Credito Cooperativo - Societa\'' Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (243, 'ABI08784', 'BANCA MONTE PRUNO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (244, 'ABI08026', 'CASSA RURALE DI LEDRO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (246, 'CIPYIT31K', 'City Poste Payment', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (247, 'CCRTIT2TQ00', 'CREDITO COOPERATIVO REGGIANO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (250, 'ABI03211', 'BANCA PATRIMONI SELLA E C. S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (251, 'ABI08631', 'Banca di Credito Cooperativo di Manzano (Udine) - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (252, 'ABI08071', 'Banca di Credito Cooperativo di Valledolmo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (254, 'CCRTIT2T33A', 'CASSA RURALE DI LIZZANA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (256, 'ABI05296', 'Banca Popolare di Fondi - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (257, 'ABI08731', 'Banca di Credito Cooperativo di Pergola - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (258, 'ABI07083', 'Banca di Credito Cooperativo Privernate Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (259, 'RZSBIT21023', 'Raiffeisenkasse Niederdorf Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (260, 'BLPIIT21', 'IWBank', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (261, 'ABI08386', 'CASSA RURALE ED ARTIGIANA DI BINASCO - CREDITO COOPERATIVO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (262, 'CCRTIT2TSUA', 'BANCA SUASA - CREDITO COOPERATIVO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (263, 'CCRTIT2T43A', 'CASSA RURALE VAL DI SOLE BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (264, 'ICRAITRRDJ0', 'BANCA DI CREDITO COOPERATIVO DI CHERASCO SOCIETA\'' COOPERATIVA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (267, 'ABI08179', 'Cassa Rurale Pinzolo - Banca di Credito Cooperativo - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (270, 'CCRTIT2TE01', 'Valpolicella Benaco Banca Credito Cooperativo Soc.Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (271, 'ABI08138', 'Cassa Rurale di Mezzocorona B.C.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (272, 'ABI07078', 'Banca di Credito Cooperativo dei Castelli e degli Iblei - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (273, 'ABI08269', 'Raiffeisenkasse Etschtal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (274, 'ABI05034', 'Banco BPM S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (275, 'ABI08056', 'Raiffeisenkasse Kastelruth - St. Ulrich Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (276, 'ABI03353', 'Banca del Sud S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (277, 'CECRIT2C', 'CASSA DI RISPARMIO DI CESENA SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (279, 'ABI03265', 'Banca Promos - Società per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (281, 'ABI08453', 'Banca di Credito Cooperativo di Carugate - Societa\'' cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (282, 'RZSBIT21048', 'Raiffeisenkasse Überetsch Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (283, 'RZSBIT21009', 'Raiffeisenkasse Hochpustertal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (285, 'ABI08565', 'Banca di Credito Cooperativo di Buccino - società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (286, 'CRLVIT21XXX', 'CREDITO LOMBARDO VENETO SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (288, 'ABI08469', 'Cassa Rurale ed Artigiana di Castellana Grotte - Cred.Coop.-soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (291, '01806800676', 'City Poste Payment', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (293, 'ABI08184', 'Cassa Rurale di Fiemme Bcc', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (295, 'ICRAITRRB80', 'BANCA DI CREDITO COOPERATIVO DI BUSTO GAROLFO E BUGUGGIATE SC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (296, 'ABI08409', 'BANCA DI CRED. COOP. DI BUONABITACOLO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (297, 'ABI08618', 'Banca di credito cooperativo di Lezzeno (Como) società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (299, 'ABI08285', 'Raiffeisenkasse Tauferer-Ahrntal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (300, 'ABI05424', 'Banca Popolare di Bari - Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (301, 'ABI08871', 'BCC di Spello e Bettona - Societa\'' Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (304, 'CCRTIT2T29A', 'CASSA RURALE DI ISERA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (306, 'ABI08474', 'BANCA DEL PICENO CREDITO COOPERATIVO SOC.COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (308, 'ABI08622', 'Cassa Rurale ed Artigiana di Lucinico Farra e Capriva - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (309, 'ABI06295', 'CASSA DI RISPARMIO DI SALUZZO SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (311, 'FARBIT21', 'Farbanca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (313, 'RZSBIT21013', 'Raiffeisenkasse Untereisacktal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (314, 'ABI06260', 'Cassa di Risparmio di Pistoia e della Lucchesia', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (315, 'ABI05018', 'Banca Popolare Etica - Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (316, 'CCRTIT2T36A', 'Cassa Rurale di Mezzocorona B.C.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (317, 'ABI08736', 'Banca di Credito Cooperativo di Pontassieve -Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (318, '00053810149', '', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (320, 'ABI08738', 'Cassa Rurale ed Artigiana dell\''Agro Pontino - BCC -  soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (322, 'ABI03104', 'Deutsche Bank S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (324, 'ABI03296', 'Banca Fideuram', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (325, 'CIPBITMM', 'ICBPI/CartaSi', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (326, 'CCRTIT2TBOV', 'CASSA RURALE E ARTIGIANA DI BOVES', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (327, 'RZSBIT21044', 'Raiffeisenkasse Tirol Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (328, 'ABI08700', 'Banca di Credito Cooperativo del Metauro Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (329, 'ABI05080', 'Banca di Imola SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (330, 'ABI06285', 'Banca CARIM - Cassa di Risparmio di Rimini S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (331, 'ABI06195', 'Carilo SpA in a.s.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (332, 'ABI08382', 'BANCA DI CREDITO COOPERATIVO DI BENE VAGIENNA (CUNEO) S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (333, 'UNGCIT21', 'doBank', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (334, 'ICRAITRRFG0', 'Banca di Forli\'' Credito Cooperativo S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (335, 'ABI03589', 'Allianz Bank Financial Advisors S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (336, 'ABI07076', 'Banca Cremasca - Credito Cooperativo Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (338, 'CCRTIT2TAQU', 'BANCA CREDITO COOPERATIVO DI AQUARA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (339, 'BPPNIT2PXXX', 'Crédit Agricole Friuladria S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (340, 'ABI03393', 'Banca Sviluppo Economico Spa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (341, 'ABI08278', 'Raiffeisenkasse Tirol Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (342, 'ABI07062', 'Credito Cooperativo Mediocrati- soc.coop. a responsabilità limitata', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (343, 'ABI08508', 'BCC CORINALDO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (346, 'ICRAITRR9H0', 'BCC DI ALTOFONTE E CACCAMO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (347, 'ABI08356', 'Banca di Credito Cooperativo Pordenonese - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (348, 'ABI06315', 'Cassa di Risparmio dell\''Umbria', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (349, 'ABI08133', 'CASSA RAIFFEISEN MERANO SOC.COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (350, 'ABI03273', 'CREDITO DI ROMAGNA S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (351, 'ABI03599', 'CASSA CENTRALE BANCA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (352, 'ICRAITRR950', 'Banca di Credito Cooperativo Brianza e Laghi - Società Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (353, 'ICRAITRRMY0', 'BANCA DI PESARO CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (354, 'ABI08358', 'Banca di Pescia - Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (356, 'CCRTIT2T10B', 'CASSA RURALE DI RABBI E CALDES BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (358, 'RZSBIT21025', 'Raiffeisenkasse Partschins Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (360, 'CCRTIT2T97A', 'BANCA ADRIA CREDITO COOP DEL DELTA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (694, 'ICRAITRRAL0', 'BCC DI BELLEGRA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (361, 'ABI08735', 'BCC di Pompiano e della Franciacorta - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (362, 'ABI08375', 'Banca di Credito Cooperativo di Basiliano - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (363, 'ABI05372', 'Banca Popolare del Cassinate Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (364, 'CCRTIT2TVEL', 'BANCA DI CREDITO COOPERATIVO DEL VELINO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (365, 'ABI08503', 'BANCA DI CREDITO COOP.DI CONVERSANO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (366, 'ABI08899', 'Cassa Rurale - BCC di Treviglio - Societa\'' Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (369, 'ABI08322', 'Banca Veronese Credito Cooperativo di Concamarise - SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (370, 'ABI08968', 'BCC Sangro Taetina di Atessa Castiglione Giuliano -soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (371, 'IMCOIT2A', 'Banca di Imola SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (372, 'ABI07087', 'Banca di Taranto - Banca di Credito Cooperativo - Società coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (374, 'ABI08182', 'Raiffeisenkasse Wipptal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (375, 'ABI08331', 'Banca di Credito Cooperativo dell\''Alto Reno - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (377, 'ICRAITRRTV0', 'Bcc Umbria Credito Cooperativo Soc.Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (378, 'ABI08532', 'Banca di Credito Cooperativo di Doberdo e Savogna Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (379, 'ABI02008', 'Unicredit S.p.A', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (380, 'ABI08997', 'BCC SAN MARCO DEI CAVOTI E DEL SANNIO CALVI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (381, 'ABI08946', 'BANCA  DON RIZZO CREDITO COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (382, 'ABI08330', 'Banca di Credito Cooperativo della Bassa Friulana - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (384, 'ICRAITRR9I0', 'BANCA CREDITO COOPERATIVO DI AQUARA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (385, 'PAYTITM1', 'Paytipper S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (386, 'ICRAITRRMK0', 'BCC Formello e Trevignano Romano', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (387, 'IMPAITRR', 'imprebanca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (389, 'ABI08183', 'Raiffeisenkasse Prad-Taufers Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (390, 'ABI08157', 'Raiffeisenkasse Naturns Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (391, 'ABI08244', 'Raiffeisenkasse Schlanders Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (392, 'ABI07017', 'Banca di Credito Cooperativo del Circeo - Soc. Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (393, 'ABI08154', 'BCC del Cilento - Cilento Centrale - SCPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (394, 'ABI08952', 'Banca di Credito Cooperativo "G.Toniolo" di San Cataldo - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (395, 'ABI07101', 'BANCA DI ANDRIA DI CREDITO COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (400, 'RZSBIT21042', 'Raiffeisenkasse Etschtal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (401, 'ABI08030', 'Banca di Credito Cooperativo di Borghetto Lodigiano (Lodi) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (402, 'ABI08472', 'Banca di Credito Cooperativo di Castenaso (Bologna) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (404, 'ABI08726', 'Banca Versilia Lunigiana e Garfagnana', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (406, 'ABI08175', 'Raiffeisenkasse Partschins Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (407, 'ICRAITRRCU0', 'BCC DELLA ROMAGNA OCCIDENTALE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (408, 'ABI03124', 'Banca del Fucino - Società per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (411, 'ICRAITRRQW0', 'BCC SANGRO TEATINA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (412, 'BPCVIT2S', 'Credito Valtellinese SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (413, 'BCCTIT2T', 'Cassa Rurale-BCC di Treviglio-Societa\'' Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (414, 'ABI08397', 'Cassa Rurale ed Artigiana di Boves - BCC - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (415, 'ICRAITRRQA0', 'BANCA DON RIZZO CREDITO COOP', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (416, 'ABI08024', 'CASSA RURALE ADAMELLO-BRENTA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (417, 'ABI08969', 'Banca San Francesco - Credito Cooperativo - Società cooperativa-', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (418, 'ABI08362', 'Banca di Credito Cooperativo di Arborea Societa\''  Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (419, 'CCRTIT2TBEN', 'BENE BANCA CREDITO COOPERATIVO DI BENE VAGIENNA (CUNEO) S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (420, 'ICRAITRRCI0', 'BANCA ALPI MARITTIME SCPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (421, 'ABI06155', 'Cassa di Risparmio di Ferrara S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (422, 'ABI08844', 'Banca di Credito Cooperativo di Santeramo in Colle - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (423, 'ABI08713', 'Banca di Credito Cooperativo di Pachino Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (426, 'ABI05602', 'Banca Popolare Vesuviana - Società coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (427, 'RZSBIT21040', 'Raiffeisenkasse Tauferer-Ahrntal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (429, 'ABI08826', 'BANCA DI PESARO CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (430, 'ABI08904', 'B.C.C. DELLE PREALPI SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (431, 'ABI06220', 'Cassa di Risparmio di Orvieto S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (432, 'ABI08434', 'Banca di Credito Cooperativo Abruzzese Cappelle sul Tavo - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (434, 'ABI08922', 'BCC di Vignole e della Montagna Pistoiese - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (435, 'ABI07074', 'Banca di Monastier e del Sile Credito Cooperativo Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (436, 'ABI03110', 'Farbanca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (437, 'RZSBIT21006', 'Raiffeisenkasse Deutschnofen-Aldein Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (438, 'ICRAITRRLV0', 'BANCA DI CREDITO COOP DI RIANO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (440, 'ABI05496', 'Banca Popolare di Cortona - Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (441, 'ABI05038', 'Banca Popolare del Mediterraneo S.C.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (442, 'ABI07113', 'Banca di Credito Cooperativo di Putignano - Società Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (443, 'ABI06225', 'Cassa di Risparmio del Veneto', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (444, 'CCRTIT2TPRE', 'B.C.C. DELLE PREALPI SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (445, 'ABI08530', 'BANCA D ALBA CREDITO COOPERATIVO SC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (447, 'ABI08232', 'CASSA RURALE DI SAONE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (448, 'ABI08088', 'CASSA RURALE DON LORENZO GUETTI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (449, 'ABI03242', 'Banco di Lucca e del Tirreno SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (450, 'ABI08249', 'Raiffeisenkasse Freienfeld Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (451, 'CRFIIT2SXXX', 'Cassa di Risparmio della Spezia S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (452, 'ABI08509', 'Banca Centro Emilia Credito Cooperativo s.c.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (453, 'ABI05142', 'Banca di Credito Popolare Società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (454, 'ABI05704', 'Banca Popolare di Spoleto S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (455, 'ABI08669', 'BCC ALTO VICENTINO di Schio, Pedemonte e Roana-SCPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (456, 'ABI03111', 'UBI BANCA SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (457, 'ABI08003', 'CREDITO VALDINIEVOLE BANCA DI CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (458, 'ABI07090', 'BANCA MALATESTIANA CREDITO COOPERATIVO SOCIETA\'' COOPERATIVA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (459, 'ABI03051', 'Barclays Bank Plc - Sede in Italia', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (460, 'ABI03425', 'Banco di Credito P. Azzoaglio S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (461, 'ABI08393', 'Cassa Rurale ed Artigiana di Borgo San Giacomo Cred.Coop.-soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (462, 'ABI03032', 'CREDITO EMILIANO SPA - REGGIO EMILIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (464, 'ABI05030', 'Credito Salernitano - Banca Popolare della Provincia di Salerno SCPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (465, 'ABI08511', 'CRADI CORTINA E DOLOMITI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (466, 'CCRTIT2TIBL', 'BCC dei Castelli e degli Iblei', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (467, 'ABI08329', 'Banca di Credito Cooperativo Brianza e Laghi - Società Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (468, 'ABI08885', 'Banca CRAS - BCC Chianciano Terme - Costa Etrusca-Sovicille-soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (469, 'ABI06085', 'Cassa di Risparmio di Asti S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (471, 'ABI08976', 'Bcc San Giuseppe', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (472, 'ABI03043', 'Banca Intermobiliare S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (473, 'ABI08715', 'Banca di Udine Credito Cooperativo - Societa cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (474, 'ABI05104', 'Banca Popolare del Lazio Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (475, 'ABI08597', 'Banca di Credito Cooperativo di Laurenzana e Nova Siri - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (476, 'ABI07092', 'BCC dei Castelli Romani e del Tuscolo', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (477, 'ICRAITRRKC0', 'BCC VICENTINO - POJANA MAGGIORE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (478, 'ABI08995', 'BCC Valmarecchia nei Comuni di Rimini e Verucchio (Rimini) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (479, 'ABI01030', 'Banca Monte dei Paschi di Siena', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (481, 'CCRTIT2TVIT', 'BANCA DI VITERBO CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (482, 'ABI07012', 'Banca di Credito Cooperativo di Bari - Soc. Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (483, 'CCRTIT2TMPR', 'BANCA MONTE PRUNO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (484, 'RZSBIT21001', 'Raiffeisenkasse Algund Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (485, 'RZSBIT21045', 'Raiffeisenkasse Tisens Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (486, 'ICRAITRRF20', 'LA BCC RAVENNATE FORLIVESE E IMOL.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (487, 'RZSBIT21055', 'Raiffeisenkasse Gröden Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (488, 'ITBBITM1', 'Banca ITB S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (489, 'CCRTIT2T92A', 'BCC MARCON VENEZIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (490, 'ABI08081', 'Raiffeisenkasse Bozen Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (491, 'ABI08519', 'Banca di Credito Cooperativo di Fano Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (492, 'ABI03084', 'Banca Cesare Ponti S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (493, 'CCRTIT2T23A', 'CASSA RURALE DEGLI ALTIPIANI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (494, 'ABI05387', 'BPER', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (495, 'ABI08338', 'BCC di Alberobello e Sammichele di Bari - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (496, 'RZSBIT21024', 'Raiffeisenkasse Obervinschgau Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (498, 'ABI07117', 'BCC di Rivarolo Canavese, Rivara ed enti territoriali locali', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (499, 'ICRAITMMEQ0', 'BANCA D\''ALBA sc', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (500, 'ICRAITRRN50', 'BCC DI CASALGRASSO E S.ALBANO STURA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (501, 'ABI08454', 'CREDITO PADANO Banca di Credito Cooperativo', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (502, 'RZSBIT21011', 'Raiffeisenkasse Kastelruth - St. Ulrich Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (504, 'ABI06915', 'Banca del Monte di Lucca S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (505, 'RZSBIT2B', 'Cassa Centrale Raiffeisen dell\''Alto Adige SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (507, 'ICRAITRRPH0', 'CREDITO TREVIGIANO BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (508, 'CCRTIT2T57A', 'CASSA RURALE DI ROVERETO BCC S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (510, 'ABI08850', 'Banca di Credito Cooperativo di Sarsina - Societa\'' cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (511, 'ABI05787', 'Banca Apulia', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (512, 'ABI06160', 'CariFirenze', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (513, 'ABI08011', 'Cassa Rurale Bassa Vallagarina - BCC - Soc. coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (514, 'ABI08309', 'BASSANO BANCA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (516, 'ABI08255', 'Raiffeisenkasse Überetsch Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (517, 'CCRTIT2T73A', 'CASSA RURALE DI TUENNO VAL DI NON', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (519, 'ABI03069', 'Intesa Sanpaolo S.p.A', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (521, 'ABI08148', 'Raiffeisenkasse Welsberg-Gsies-Taisten Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (522, 'ABI08302', 'Raiffeisenkasse Niederdorf Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (523, 'ABI08035', 'Raiffeisenkasse Bruneck Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (524, 'ABI03323', 'GBM BANCA S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (526, 'ABI08693', 'Banca di Credito Cooperativo di Nettuno - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (527, 'BCVAIT2V', 'BANCA VALSABBINA S.C.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (528, 'ABI08743', 'BCC del Velino (Comune di Posta, Provincia di Rieti) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (529, 'ABI08282', 'CASSA RURALE DI TUENNO VAL DI NON', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (530, 'ABI05824', 'Cassa Sovvenzioni Risparmio fra il Personale della Banca d\''Italia SCPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (532, 'ABI08340', 'Cassa Padana Banca di Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (533, 'ABI08210', 'CASSA RURALE DI ROVERETO BCC S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (535, 'ABI08728', 'Banca Patavina Cred.  Coop. di Sant\''Elena e Pioce Sacco soc. coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (536, 'ABI08553', 'Banca di Credito Cooperativo di Flumeri - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (538, 'ICRAITRRRN0', 'RIMINIBANCA CREDITO COOPERATIVO SOC. COOP. ', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (539, 'CCRTIT2TC11', 'BANCA SAN GIORGIO QUINTO  epaymodric.VALLE AGNO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (540, 'ICRAITRRKR0', 'BCC PRATOLA PELIGNA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (541, 'BREUITM1', 'BANCA REG EUROPEA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (542, 'ABI08422', 'Banca di Credito Cooperativo di Vergato - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (545, 'ABI08149', 'Banca di Credito Cooperativo di Basciano (TE) - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (546, 'ABI08883', 'BANCA DI BOLOGNA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (547, 'ABI06300', 'Cassa di Risparmio di San Miniato S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (548, 'ABI03374', 'Alto Adige Banca S.p.A. – Sudtirol Bank AG', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (549, 'ABI03267', 'BANCA GALILEO SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (550, 'ABI08126', 'Banca di Credito Cooperativo di Montepaone - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (551, 'ABI08787', 'Banca di Credito Cooperativo di Riano - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (552, 'ABI08601', 'Banca di Credito Cooperativo Valle del Torto - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (553, 'CCRTIT2T72A', 'CASSA RURALE VALLI DI PRIMIERO E VANOI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (554, 'ABI03062', 'Banca Mediolanum SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (555, 'ABI05484', 'Banca di Cividale S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (556, '07601', 'Poste Italiane', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (557, 'CCRTIT2TN00', 'BancAnagni Credito Cooperativo', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (558, 'ABI08220', 'Raiffeisenkasse Salurn Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (560, 'RZSBIT21050', 'Raiffeisenkasse Vintl Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (561, 'ABI03367', 'Banca Santa Giulia S.p.a', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (562, 'CCRTIT2TFOB', 'FRIULOVEST BANCA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (563, 'ICRAITRRFE0', 'B.C.C. DI GAUDIANO DI LAVELLO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (564, 'ABI03317', 'BANCA DELLA PROVINCIA DI MACERATA SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (565, 'ABI03138', 'Banca Reale S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (567, 'ABI03015', 'FinecoBank Banca Fineco S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (568, 'ABI08561', 'Banca di Salerno Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (569, 'ABI06150', 'Cassa di Risparmio di Fermo S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (570, 'ABI08113', 'Raiffeisenkasse Untereisacktal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (571, 'RZSBIT21510', 'Raiffeisenkasse Schlern-Rosengarten Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (572, 'ABI08441', 'Credito Cooperativo di Caravaggio Adda e Cremasco', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (573, 'ICRAITRR900', 'BANCA CENTROPADANA CRED. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (574, 'CCRTIT2TG01', 'ROVIGO BANCA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (576, 'CCRTIT2TBCV', 'CENTROVENETO BASSANO BANCA - CREDITO COOPERATIVO SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (577, 'ABI06305', 'Banca Cassa di Risparmio di Savigliano S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (578, 'ABI08572', 'Banca di Credito Cooperativo di Gambatesa società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (580, 'CCRTIT2T05A', 'CASSA RURALE ADAMELLO-BRENTA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (583, 'ABI08747', 'BCC PRATOLA PELIGNA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (584, 'ABI07107', 'Banca del Fermano - Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (587, 'ABI08851', 'Banca di Saturnia e Costa d’Argento Credito Cooperativo', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (588, 'SATYGB21', 'Satispay Ltd', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (589, 'ABI03263', 'Istituto Bancario del Lavoro S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (590, 'ABI08770', 'CASSA RURALE ED ARTIGIANA DI RIVAROLO MANTOVANO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (591, 'CCRTIT2T50A', 'Cassa Rurale di Fiemme Bcc', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (592, 'ABI08805', 'FRIULOVEST BANCA - CREDITO COOPERATIVO SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (593, 'ALPEIT21', 'AlpenBank', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (594, 'ICRAITRRB10', 'CASSA RURALE E ARTIGIANA DI BOVES', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (596, 'ABI03139', 'Banca per lo Sviluppo della cooperazione di credito S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (597, 'RZSBIT21014', 'Raiffeisenkasse Lana Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (598, 'PASCITMM', 'Banca Monte dei Paschi di Siena', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (601, 'ABI08078', 'C.R.GIUDICARIE VALSABBIA PAGANELLA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (602, 'ABI01005', 'Banca Nazionale Del Lavoro S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (603, 'ABI08381', 'Banca di Credito Cooperativo di Bellegra - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (604, 'CCRTIT2TRMB', 'RomagnaBanca Credito Cooperativo s.c.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (605, 'ICRAITRREA0', 'BCC DELL\''OGLIO E DEL SERIO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (606, 'ABI08931', 'Banca di Viterbo Credito Cooperativo - SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (608, 'ABI08916', 'Banca di Ancona, Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (609, 'ICRAITRRK60', 'Banca Versilia Lunigiana e Garfagnana', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (611, 'CCRTIT2TCAR', 'BANCA DI CARAGLIO CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (612, 'ABI08998', 'Raiffeisenkasse Passeier Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (613, 'POCAIT3C', 'Banca Popolare del Cassinate', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (614, 'CCRTIT2T37A', 'CASSA RURALE MEZZOLOMBARDO E SM', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (617, 'ABI08732', 'BCC VICENTINO - POJANA MAGGIORE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (618, 'ICRAITRRFZ0', 'BCC AGROBRESCIANO SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (619, 'ABI08811', 'Banca del Valdarno Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (620, 'ABI36018', 'Cabel per i Pagamenti I.P. s.c.p.a.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (621, 'ABI07086', 'Banca di Credito Cooperativo dell\''Adriatico Teramano soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (622, 'ICRAITRRCL0', 'BANCA DI CRED. COOP. DI MILANO SC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (623, 'ABI05772', 'Banca Popolare Sant\''Angelo Soc. coop. Per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (627, 'ABI08378', 'Cassa Rurale Artigiana-BCC Battipaglia e Montecorvino Rovella-soc.coop', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (628, 'ABI08855', 'Banca di Credito Cooperativo di Scafati e Cetara - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (629, 'ICRAITRRM20', 'B.C.C.LAUDENSE LODI S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (630, 'ICRAITRRNO0', 'RomagnaBanca Credito Cooperativo s.c.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (632, 'ABI08639', 'Banca di Credito Cooperativo di Masiano - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (633, 'ICRAITRRP10', 'BCC VALLE DEL LAMBRO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (634, 'ABI03359', 'BANCA PROSSIMA S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (635, 'ABI08708', 'BCC di Oppido Lucano e Ripacandida (Provincia di Potenza)', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (636, 'ICRAITRRTQ0', 'CREDITO COOPERATIVO ROMAGNOLO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (637, 'CRBZIT2B', 'CASSA DI RISPARMIO DI BOLZANO SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (638, 'ABI03598', 'Banca UBAE S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (639, 'ABI08345', 'BCC DI ANGHIARI E STIA CREDITO COOP', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (641, 'ABI03440', 'BANCO DI DESIO E DELLA BRIANZA S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (642, 'ABI08057', 'Cassa Rurale Centrofiemme - Cavalese BCC - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (644, 'ABI08556', 'Banca di Forli\'' Credito Cooperativo S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (645, 'ABI08115', 'Raiffeisenkasse Lana Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (646, 'ABI08965', 'BANCA SAN BIAGIO VENETO ORIENTALE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (651, 'ABI03083', 'UBI BANCA PRIVATE INVESTMENT S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (652, 'ICRAITRRQG0', 'B.C.C. "G. TONIOLO" DI SAN CATALDO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (653, 'ICRAITRROL0', 'BANCA CRAS - CREDITO COOPERATIVO TOSCANO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (654, 'ABI08712', 'Banca di Credito Cooperativo "Sen. P. Grammatico" - Paceco - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (655, 'ABI08437', 'Banca di Credito Cooperativo del Basso Sebino Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (656, 'ICRAITRR3P0', 'CASSA RAIFFEISEN MERANO SOC.COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (657, 'ABI08307', 'Raiffeisenkasse Eisacktal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (658, 'ABI07075', 'Crediumbria Banca di Credito Cooperativo Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (659, 'ABI05116', 'BANCA VALSABBINA S.C.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (660, 'ICRAITRRGP0', 'BCC VALLE DEL TORTO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (661, 'ABI06270', 'Cassa di Risparmio di Ravenna SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (665, 'ABI08079', 'CASSA RURALE BASSA ANAUNIA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (666, 'ABI08134', 'Raiffeisenkasse Marling Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (667, 'ABI08399', 'CRA DI BRENDOLA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (668, 'CCRTIT2TH00', 'CREDITO COOPERATIVO DI BRESCIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (669, 'ABI03493', 'RAIFFEISEN ALTO ADIGE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (670, 'ABI08990', 'Banca S. Stefano - Credito cooperativo - Martellago-Venezia -soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (671, 'ABI08894', 'Banca di Carnia e Gemonese - Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (672, 'ABI03235', 'BANCA DI SCONTO E CONTI CORRENTI DI SANTA MARIA CAPUA VETERE SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (674, 'ABI08705', 'BCC DI OSTRAVETERE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (676, 'ICRAITRRJM0', 'BCC OSTUNI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (677, 'CCRTIT2T54A', 'CASSA RURALE NOVELLA E ALTA ANAUNIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (678, 'ABI08095', 'Credito Cooperativo Reggiano - Societa\'' Cooperativa - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (679, 'ABI03332', 'Banca Passadore e C. - Società per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (680, 'RZSBIT21034', 'Raiffeisenkasse Schlanders Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (681, 'CCRTIT2TBCE', 'BANCA CENTRO EMILIA CREDITO COOPERATIVO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (682, 'CRRNIT2R', 'BANCA CARIM - Cassa di Risparmio di Rimini Spa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (683, 'CCRTIT2T06A', 'CASSA RURALE DI LEDRO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (685, 'ABI08107', 'CASSA RURALE DI ISERA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (686, 'ABI08452', 'BANCA ANNIA S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (687, 'ABI08086', 'Banca di Credito Cooperativo di Falconara Marittima - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (688, 'ICRAITRRUS0', 'BCC AGRIGENTINO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (690, 'ABI08178', 'Cassa Rurale di Pergine - Banca di Credito Cooperativo - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (691, 'ABI08810', 'Banca di Credito Cooperativo di San Giovanni Rotondo - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (693, 'ABI08462', 'Banca di Credito Cooperativo della Romagna Occidentale soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (695, 'CCRTIT2T10A', 'CASSA RURALE DI RABBI E CALDES BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (696, 'ABI08139', 'CASSA RURALE MEZZOLOMBARDO E SM', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (700, 'ABI08491', 'BCC di Civitanova Marche e Montecosaro - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (701, 'POSOIT22XXX', 'Banca Popolare di Sondrio', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (702, 'ROMAITRRXXX', 'BANCA DI CREDITO COOPERATIVO ROMA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (703, 'ABI08365', 'BCC dell\''Alto Tirreno della Calabria - Verbicaro (Cosenza) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (704, 'ABI08679', 'Banca di Credito Cooperativo di Mozzanica (Bergamo) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (705, 'CCRTIT2T45A', 'CASSA RURALE OLLE-SAMONE-SCURELLE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (706, 'ABI08603', 'Banca di Credito Cooperativo di Leverano (Lecce) Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (707, 'ICRAITRRE20', 'BANCA MALATESTIANA SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (709, 'RZSBIT21026', 'Raiffeisenkasse Passeier Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (711, 'ICRAITRRTS0', 'EMIL BANCA CREDITO COOPERATIVO SOC. COOP. ', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (712, 'ICRAITRRF90', 'BANCA DI FILOTTRANO CREDITO COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (713, 'ABI08954', 'BCC LA RISCOSSA DI REGALBUTO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (714, 'RSANIT3P', 'Credito Siciliano', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (715, 'ABI08094', 'Raiffeisenkasse Villnöß Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (716, 'ABI08404', 'Banca di Credito Cooperativo di Busto Garolfo e Buguggiate - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (717, 'ABI08717', 'BANCA DI CREDITO COOP. CRA DI PALIANO SC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (718, 'SENVITT1', 'BANCA PATRIMONI SELLA E C. S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (721, 'PAYPAL03069', 'Intesa Sanpaolo S.p.A - PayPal', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (722, 'ABI07066', 'Banca di Credito Cooperativo dei Comuni Cilentani - Società coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (724, 'ABI08988', 'Banca di Credito Cooperativo degli Ulivi - Terra di Bari - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (725, 'ABI03268', 'BANCA SELLA S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (727, 'ABI08456', 'Banca Sibillini Cred. Coop. Casavecchia', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (729, 'ABI08551', 'BCC di Fiumicello ed Aiello del Friuli - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (730, 'ABI03134', 'AlpenBank', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (731, 'ABI05035', 'Veneto Banca SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (735, 'ABI08065', 'Raiffeisenkasse Schlern-Rosengarten Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (736, 'ABI08812', 'Banca di Formello e Trevignano Romano di Credito Coop.-soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (738, 'ABI08704', 'Banca di Credito Cooperativo di Ostra e Morro d\''Alba soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (740, 'ABI08778', 'BANCA DI CREDITO COOPERATIVO DI RONCIGLIONE E BARBARANO ROMANO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (742, 'CCRTIT2T60A', 'CASSA RURALE DI SAONE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (743, 'ABI08231', 'Raiffeisenkasse Ulten-St. Pankraz-Laurein Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (744, 'RZSBIT21022', 'Raiffeisenkasse Naturns Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (745, 'ABI08554', 'Banca di Credito Cooperativo di Gaudiano di Lavello - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (749, 'ABI08020', 'Raiffeisenkasse Hochpustertal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (751, 'ABI08080', 'Raiffeisenkasse Toblach Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (752, 'ABI06055', 'Nuova Banca delle Marche S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (753, 'ABI08200', 'CASSA RURALE NOVELLA E ALTA ANAUNIA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (754, 'ABI03159', 'Banca Consulia Spa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (755, 'ICRAITRRBD0', 'BANCA DI CRED. COOP. DI BUONABITACOLO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (756, 'ABI08189', 'BCC della Valle del Trigno (Mafalda - San Salvo) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (757, 'CCRTIT2TMAL', 'BANCA MALATESTIANA SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (758, 'ABI03158', 'Banca Sistema SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (759, 'ABI08796', 'Banca di Credito Cooperativo di Sambuca di Sicilia -soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (761, 'CCRTIT2TVAL', 'BCC Valdostana Cooperative de Credit Valdotaine Società Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (762, 'ABI06340', 'Cassa di Risparmio Friuli Venezia Giulia', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (763, 'ABI08869', 'Banca di Credito Cooperativo Bergamo e Valli S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (764, 'ABI03426', 'Banca di Credito Peloritano S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (765, 'ABI08110', 'Raiffeisenkasse Latsch Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (766, 'CCRTIT2T77A', 'CASSA RURALE ALTA VALLAGARINA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (767, 'ABI03442', 'Widiba', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (768, 'UNCR-COR', 'Unicredit S.p.A – UniWeb', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (769, 'ABI08901', 'BCC VALLE DEL LAMBRO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (771, 'ABI08492', 'Banca di Credito Cooperativo di Cittanova - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (772, 'ABI08606', 'Banca di Credito Cooperativo di Canosa-Loconia società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (773, 'ABI08514', 'BCC DELL\''OGLIO E DEL SERIO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (775, 'CCRTIT2T22A', 'CASSA RURALE DON LORENZO GUETTI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (776, 'ABI08117', 'Raiffeisenkasse Laas Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (777, 'ABI05792', 'Banca Popolare Valconca Società Cooperativa per Azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (778, 'ABI08016', 'CASSA RURALE ALTO GARDA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (783, 'ICRAITRRE50', 'Banca Centro Emilia Credito Cooperativo s.c.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (784, 'IFSPIT21', 'Istituto di Pagamento InfoCamere', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (787, 'CCRTIT2TSGR', 'BCC DI SAN GIOVANNI ROTONDO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (789, 'ABI05385', 'Banca Popolare di Puglia e Basilicata - società cooperativa per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (791, 'ABI01010', 'Banco di Napoli', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (792, 'CCRTIT2T47A', 'CASSA RURALE ALTA VALSUGANA - BANCA DI CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (793, 'BPVIIT22', 'Banca Popolare di Vicenza S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (795, 'ABI08341', 'Banca di Credito Cooperativo di Altofonte e Caccamo - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (796, 'ABI08549', 'BANCA DI FILOTTRANO CREDITO COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (797, 'ABI06115', 'Cassa di Risparmio di Cento S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (798, 'ABI03395', 'Banca Interprovinciale Spa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (799, 'ABI08100', 'Cassa Rurale di Giovo', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (801, 'ABI08473', 'BCC di Castiglione Messer Raimondo e Pianella - SCRL', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (802, 'ICRAITRRA20', 'Banca di Credito Cooperativo di Arborea Societa\''  Cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (804, 'RZSBIT21317', 'Raiffeisenkasse Unterland Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (806, 'ABI06370', 'Cassa di Risparmio di Volterra S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (807, 'SIGPITM1XXX', 'SISAL GROUP S.P.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (808, 'ABI05132', 'Banca Nuova S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (809, 'ABI05156', 'Banca di Piacenza soc. coop per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (811, 'ICRAITRRL90', 'Banca di Credito Cooperativo di Recanati e Colmurano - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (813, 'ABI08187', 'Cassa Rurale Renon Societa\'' Cooperativa - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (814, 'ABI05390', 'Banca Tirrenica S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (815, 'ICRAITRRNR0', 'BCC DI SCAFATI E CETARA SC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (816, 'BPPIITRRXXX', 'Poste Italiane', 'f');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (818, 'CCRTIT2TO00', 'BCC LA RISCOSSA DI REGALBUTO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (819, 'ABI08304', 'CASSA RURALE DI TRENTO BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (820, 'RZSBIT21012', 'Raiffeisenkasse Laas Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (821, 'ABI08985', 'Banca del Nisseno - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (822, 'ABI08706', 'Banca di Credito Cooperativo di Ostuni - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (824, 'ABI08440', 'Banca di Credito Cooperativo di Carate Brianza - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (825, 'ABI08344', 'BancAnagni Credito Cooperativo', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (826, 'ABI08865', 'Banca di Credito Cooperativo di Sesto San Giovanni - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (827, 'ABI08951', 'Banca di Credito Cooperativo Giuseppe Toniolo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (828, 'ABI03250', 'Banca Euromobiliare - Società per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (830, 'RZSBIT21005', 'Raiffeisenkasse Bruneck Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (831, 'ABI03151', 'Hypo Tirol Bank - Succursale Italia', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (832, 'ACARIT22XXX', 'CASSA RURALE ED ARTIGIANA DI CANTU\'' BCC SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (835, 'ICRAITRRAQ0', 'CASSA RURALE ED ARTIGIANA DI BINASCO - CREDITO COOPERATIVO S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (836, 'ABI08887', 'BCC di San Calogero e Maierato BCC del Vibonese - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (837, 'ABI08327', 'BANCA DI CREDITO COOPERATIVO ROMA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (838, 'ABI07091', 'Banca del Catanzarese Cred. Coop. S.C.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (839, 'ABI08800', 'Banca di Credito Cooperativo di San Biagio Platani - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (840, 'RZSBIT21015', 'Raiffeisenkasse Latsch Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (841, 'RZSBIT21138', 'Raiffeisenkasse Toblach Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (842, 'ABI07094', 'Banca di Credito Cooperativo di Massafra - Soc. coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (843, 'ABI08457', 'Credito Cooperativo Valdarno Fiorentino Banca di Cascia - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (845, 'ABI03185', 'Banca Ifigest Spa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (846, 'RZSBIT21003', 'Raiffeisenkasse Bozen Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (847, 'ABI08769', 'BCC di Ripatransone - Provincia di Ascoli Piceno - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (848, 'RZSBIT21032', 'Raiffeisenkasse Sarntal Genossenschaf', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (849, 'ABI03278', 'Banca Emilveneta - Società per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (850, 'ABI06095', 'Cassa di Risparmio di Bra S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (852, 'ABI08445', 'Banca di Credito Cooperativo di Terra d\''Otranto - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (853, 'ABI08833', 'BCC DI CASALGRASSO E S.ALBANO STURA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (855, 'ABI03019', 'Credito Siciliano s.p.a.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (856, 'ICRAITRRFW0', 'BCC di Gambatesa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (857, 'ABI08590', 'Banca del Centroveneto Credito Cooperativo - Longare - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (858, 'ABI01015', 'Banco di Sardegna SpA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (860, 'ABI07048', 'Banca dell''Elba Credito Cooperativo - Soc. Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (862, 'CRPPIT2PXXX', 'Cassa di Risparmio di Parma e Piacenza S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (863, 'ABI08315', 'Valpolicella Benaco Banca Credito Cooperativo Soc.Coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (864, 'ABI08903', 'Banca di Credito Cooperativo di Turriaco - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (865, 'ABI08744', 'Banca di Credito Cooperativo di Dovera e Postino (Cremona) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (866, 'ABI08264', 'CASSA RURALE DI TASSULLO E NANNO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (867, 'ABI08238', 'Raiffeisenkasse Gröden Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (869, 'ABI08374', 'Banca di Credito Cooperativo di Barlassina (Milano) - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (870, 'ABI08233', 'Raiffeisenkasse Sarntal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (871, 'ABI08430', 'CASSA RURALE ED ARTIGIANA DI CANTU\'' BCC SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (872, 'CCRTIT2T80A', 'BASSANO BANCA CREDITO COOPERATIVO', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (873, 'ABI08258', 'Credito Cooperativo Centro Calabria - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (874, 'RZSBIT21054', 'Raiffeisenkasse Wipptal Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (876, 'ABI08140', 'CASSA RURALE DOLOMITI DI FASSA PRIMIERO E BELLUNO BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (878, 'ABI05728', 'Banca Popolare di Vicenza S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (880, 'BIC32698', 'Lottomatica', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (882, 'ABI08120', 'Cassa Rurale Lavis - Valle di Cembra BCC - soc.coop.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (883, 'IT00348170101', 'UniCredit S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (884, 'ABI06050', 'NUOVA CARICHIETI SPA', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (886, 'ABI08610', 'BANCA DEI COLLI EUGANEI CRED. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (889, 'CCRTIT2T04A', 'CASSA RURALE ALTO GARDA BCC', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (890, 'ABI08324', 'Banca Centropadana - Credito Cooperativo - Società cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (891, 'ICRAITRRTI0', 'Credito Cooperativo Mediocrati', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (893, 'ABI08877', 'BCC di Staranzano e Villesse Societa\'' cooperativa', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (894, 'ABI06170', 'Cassa di Risparmio di Fossano S.p.A.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (895, 'UNCRITMM', 'UniCredit S.p.A', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (897, 'ABI08234', 'Raiffeisenkasse Schenna Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (901, 'BDCPITTT', 'BANCA DEL PIEMONTE', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (902, 'ABI08450', 'Banca Alpi Marittime Credito Cooperativo Carru\'' - soc.coop. per azioni', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (903, 'ICRAITRROUO', 'BANCA DI CREDITO COOP. DI BORGHETTO LODIGIANO SOC. COOP.', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (905, 'RZSBIT21031', 'Raiffeisenkasse Salurn Genossenschaft', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (906, '05000', 'ICBPI', 't');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (2, 'ABI08987', 'BCC \"S. Vincenzo de Paoli\" di Casagiove - SCPA', 'f');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (126, 'ABI08910', 'Cassa Rurale ed Artigiana di Vestenanova - Cred. Coop. - soc.coop.', 'f');
INSERT INTO epay.cbl_t_psp(id, identificativo_psp, denominazione_psp, flag_riconciliabile) VALUES (1000, '09999999999', 'Banca 09999999999', 't');
INSERT INTO epay.cbl_t_storico_flusso_dettaglio(id, identificativo_flusso, codice_versamento, iuv, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_singolo_versamento, dataora_flusso, anagrafica_pagatore, codicefiscale_pagatore, id_versamento, dati_specifici_riscossione) VALUES (51, '2018-05-11ITBBITM1-0000000000037485', 'T002', 'iuv', null, '2018-07-27 12:46:59.394', 'NGUEYE', null, null, null, null, null, null);
INSERT INTO epay.cbl_t_storico_flusso_dettaglio(id, identificativo_flusso, codice_versamento, iuv, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_singolo_versamento, dataora_flusso, anagrafica_pagatore, codicefiscale_pagatore, id_versamento, dati_specifici_riscossione) VALUES (52, '2018-05-11ITBBITM1-0000000000037485', 'T002', 'iuv', null, '2018-07-27 15:58:56.116', 'NGUEYE', null, null, null, null, null, null);
INSERT INTO epay.cbl_t_storico_flusso_dettaglio(id, identificativo_flusso, codice_versamento, iuv, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_singolo_versamento, dataora_flusso, anagrafica_pagatore, codicefiscale_pagatore, id_versamento, dati_specifici_riscossione) VALUES (53, '2018-05-11ITBBITM1-0000000000037485', 'T002', 'iuv', null, '2018-07-27 16:04:16.215', 'NGUEYE', null, null, null, null, null, null);
INSERT INTO epay.cbl_t_storico_flusso_dettaglio(id, identificativo_flusso, codice_versamento, iuv, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_singolo_versamento, dataora_flusso, anagrafica_pagatore, codicefiscale_pagatore, id_versamento, dati_specifici_riscossione) VALUES (54, '2018-05-11ITBBITM1-0000000000037485', 'T002', 'iuv', null, '2018-07-27 16:41:37.343', 'NGUEYE', null, null, null, null, null, null);
INSERT INTO epay.cbl_t_storico_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, stato_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, utente_ins_var, data_ins_var) VALUES (11, '2018-05-22BCITITMM-0000000037', '80087670016', 'BCITITMM', 186, null, null, '2018-05-23 07:53:41', '2018-05-23 08:01:02', '<?xml version="1.0" encoding="UTF-8"?><pay_i:FlussoRiversamento xmlns:pay_i="http://www.digitpa.gov.it/schemas/2011/Pagamenti/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="/opt/proctele/resources/FlussoRiversamento_1_0_4.xsd"><pay_i:versioneOggetto>1.0</pay_i:versioneOggetto><pay_i:identificativoFlusso>2018-05-22BCITITMM-0000000037</pay_i:identificativoFlusso><pay_i:dataOraFlusso>2018-05-23T07:53:41</pay_i:dataOraFlusso><pay_i:identificativoUnivocoRegolamento>0306928519791704489999999999IT</pay_i:identificativoUnivocoRegolamento><pay_i:dataRegolamento>2018-05-22</pay_i:dataRegolamento><pay_i:istitutoMittente><pay_i:identificativoUnivocoMittente><pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoMittente><pay_i:denominazioneMittente>Intesa Sanpaolo</pay_i:denominazioneMittente></pay_i:istitutoMittente><pay_i:istitutoRicevente><pay_i:identificativoUnivocoRicevente><pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoRicevente><pay_i:denominazioneRicevente>Regione Piemonte</pay_i:denominazioneRicevente></pay_i:istitutoRicevente><pay_i:numeroTotalePagamenti>186</pay_i:numeroTotalePagamenti><pay_i:importoTotalePagamenti>31083.48</pay_i:importoTotalePagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF04181400001T00200000319</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15268536044320304011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>199.29</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF09181410001T00200000579</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15269380416029253011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>22</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti></pay_i:FlussoRiversamento>', null, 0, 1, null, null, null, '2018-07-27 12:14:55.447');
INSERT INTO epay.cbl_t_storico_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, stato_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, utente_ins_var, data_ins_var) VALUES (12, '2018-05-22BCITITMM-0000000037', '80087670016', 'BCITITMM', 186, null, null, '2018-05-23 07:53:41', '2018-05-23 08:01:02', '<?xml version="1.0" encoding="UTF-8"?><pay_i:FlussoRiversamento xmlns:pay_i="http://www.digitpa.gov.it/schemas/2011/Pagamenti/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="/opt/proctele/resources/FlussoRiversamento_1_0_4.xsd"><pay_i:versioneOggetto>1.0</pay_i:versioneOggetto><pay_i:identificativoFlusso>2018-05-22BCITITMM-0000000037</pay_i:identificativoFlusso><pay_i:dataOraFlusso>2018-05-23T07:53:41</pay_i:dataOraFlusso><pay_i:identificativoUnivocoRegolamento>0306928519791704489999999999IT</pay_i:identificativoUnivocoRegolamento><pay_i:dataRegolamento>2018-05-22</pay_i:dataRegolamento><pay_i:istitutoMittente><pay_i:identificativoUnivocoMittente><pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoMittente><pay_i:denominazioneMittente>Intesa Sanpaolo</pay_i:denominazioneMittente></pay_i:istitutoMittente><pay_i:istitutoRicevente><pay_i:identificativoUnivocoRicevente><pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoRicevente><pay_i:denominazioneRicevente>Regione Piemonte</pay_i:denominazioneRicevente></pay_i:istitutoRicevente><pay_i:numeroTotalePagamenti>186</pay_i:numeroTotalePagamenti><pay_i:importoTotalePagamenti>31083.48</pay_i:importoTotalePagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF04181400001T00200000319</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15268536044320304011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>199.29</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF09181410001T00200000579</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15269380416029253011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>22</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti></pay_i:FlussoRiversamento>', null, 0, 1, null, null, null, '2018-07-27 12:38:24.318');
INSERT INTO epay.cbl_t_storico_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, stato_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, utente_ins_var, data_ins_var) VALUES (13, '2018-05-22BCITITMM-0000000037', '80087670016', 'BCITITMM', 186, null, null, '2018-05-23 07:53:41', '2018-05-23 08:01:02', '<?xml version="1.0" encoding="UTF-8"?><pay_i:FlussoRiversamento xmlns:pay_i="http://www.digitpa.gov.it/schemas/2011/Pagamenti/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="/opt/proctele/resources/FlussoRiversamento_1_0_4.xsd"><pay_i:versioneOggetto>1.0</pay_i:versioneOggetto><pay_i:identificativoFlusso>2018-05-22BCITITMM-0000000037</pay_i:identificativoFlusso><pay_i:dataOraFlusso>2018-05-23T07:53:41</pay_i:dataOraFlusso><pay_i:identificativoUnivocoRegolamento>0306928519791704489999999999IT</pay_i:identificativoUnivocoRegolamento><pay_i:dataRegolamento>2018-05-22</pay_i:dataRegolamento><pay_i:istitutoMittente><pay_i:identificativoUnivocoMittente><pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoMittente><pay_i:denominazioneMittente>Intesa Sanpaolo</pay_i:denominazioneMittente></pay_i:istitutoMittente><pay_i:istitutoRicevente><pay_i:identificativoUnivocoRicevente><pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoRicevente><pay_i:denominazioneRicevente>Regione Piemonte</pay_i:denominazioneRicevente></pay_i:istitutoRicevente><pay_i:numeroTotalePagamenti>186</pay_i:numeroTotalePagamenti><pay_i:importoTotalePagamenti>31083.48</pay_i:importoTotalePagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF04181400001T00200000319</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15268536044320304011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>199.29</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF09181410001T00200000579</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15269380416029253011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>22</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti></pay_i:FlussoRiversamento>', null, 0, 1, null, null, null, '2018-07-27 12:42:23.645');
INSERT INTO epay.cbl_t_storico_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, stato_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, utente_ins_var, data_ins_var) VALUES (14, '2018-05-22BCITITMM-0000000037', '80087670016', 'BCITITMM', 186, null, null, '2018-05-23 07:53:41', '2018-05-23 08:01:02', '<?xml version="1.0" encoding="UTF-8"?><pay_i:FlussoRiversamento xmlns:pay_i="http://www.digitpa.gov.it/schemas/2011/Pagamenti/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="/opt/proctele/resources/FlussoRiversamento_1_0_4.xsd"><pay_i:versioneOggetto>1.0</pay_i:versioneOggetto><pay_i:identificativoFlusso>2018-05-22BCITITMM-0000000037</pay_i:identificativoFlusso><pay_i:dataOraFlusso>2018-05-23T07:53:41</pay_i:dataOraFlusso><pay_i:identificativoUnivocoRegolamento>0306928519791704489999999999IT</pay_i:identificativoUnivocoRegolamento><pay_i:dataRegolamento>2018-05-22</pay_i:dataRegolamento><pay_i:istitutoMittente><pay_i:identificativoUnivocoMittente><pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoMittente><pay_i:denominazioneMittente>Intesa Sanpaolo</pay_i:denominazioneMittente></pay_i:istitutoMittente><pay_i:istitutoRicevente><pay_i:identificativoUnivocoRicevente><pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoRicevente><pay_i:denominazioneRicevente>Regione Piemonte</pay_i:denominazioneRicevente></pay_i:istitutoRicevente><pay_i:numeroTotalePagamenti>186</pay_i:numeroTotalePagamenti><pay_i:importoTotalePagamenti>31083.48</pay_i:importoTotalePagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF04181400001T00200000319</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15268536044320304011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>199.29</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF09181410001T00200000579</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15269380416029253011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>22</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti></pay_i:FlussoRiversamento>', null, 0, 1, null, null, null, '2018-07-27 12:46:22.674');
INSERT INTO epay.cbl_t_storico_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, stato_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, utente_ins_var, data_ins_var) VALUES (15, '2018-05-18BCITITMM-0000000034', '80087670016', 'BCITITMM', 6, null, null, '2018-05-20 03:19:20', '2018-05-20 08:21:55', '<?xml version="1.0"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:epay="http://epaymodric.interfacews.epaymodric.epaymodric.csi.it/" xmlns:ren="http://www.csi.it/epay/epaywso/rendicontazione-ext" xmlns:typ="http://www.csi.it/epay/epaywso/types">
  <soapenv:Header/>
  <soapenv:Body>
    <epay:acquisciFlussoRendicontazione>
      <!--Optional:-->
      <cf>80087670016</cf>
      <!--Optional:-->
      <core>
        <ren:Testata>
          <ren:IdentificativoFlusso>2018-05-18BCITITMM-0000000034</ren:IdentificativoFlusso>
          <ren:DataRegolamento>2018-05-18+02:00</ren:DataRegolamento>
          <ren:NumeroTotalePagamentiFlusso>6</ren:NumeroTotalePagamentiFlusso>
          <ren:NumeroTotalePagamentiIntermediati>6</ren:NumeroTotalePagamentiIntermediati>
          <ren:ImportoTotalePagamentiFlusso>798.90</ren:ImportoTotalePagamentiFlusso>
          <ren:ImportoTotalePagamentiIntermediati>798.90</ren:ImportoTotalePagamentiIntermediati>
        </ren:Testata>
        <ren:FlussoRiversamento>cid:355170514487</ren:FlussoRiversamento>
        <ren:PagamentiIntermediati>
          <ren:PagamentoIntermediato>
            <ren:DatiSingoliPagamenti>
              <ren:DatiSingoloPagamento>
                <ren:IUV>RF71181370001T00200000003</ren:IUV>
                <ren:IUR>15265118432491712011</ren:IUR>
                <ren:IndiceDatiPagamento>1</ren:IndiceDatiPagamento>
                <ren:SingoloImportoPagato>180.18</ren:SingoloImportoPagato>
                <ren:DataEsitoSingoloPagamento>2018-05-18+02:00</ren:DataEsitoSingoloPagamento>
                <ren:CodiceVersamento>T002</ren:CodiceVersamento>
                <ren:DatiSpecificiRiscossione>2/1210</ren:DatiSpecificiRiscossione>
                <ren:DescrizioneCausaleVersamento>/RFS/RF71181370001T00200000003/180.18</ren:DescrizioneCausaleVersamento>
                <ren:TransactionId>PRD000000002244898</ren:TransactionId>
                <ren:AnagraficaPagatore>
                  <typ:PersonaFisica>
                    <typ:Cognome>VALTER</typ:Cognome>
                    <typ:Nome>VALTER</typ:Nome>
                  </typ:PersonaFisica>
                </ren:AnagraficaPagatore>
                <ren:AnagraficaVersante/>
              </ren:DatiSingoloPagamento>
            </ren:DatiSingoliPagamenti>
          </ren:PagamentoIntermediato>
          <ren:PagamentoIntermediato>
            <ren:DatiSingoliPagamenti>
              <ren:DatiSingoloPagamento>
                <ren:IUV>15265313073334849011</ren:IUV>
                <ren:IUR>15265313073334849011</ren:IUR>
                <ren:IndiceDatiPagamento>1</ren:IndiceDatiPagamento>
                <ren:SingoloImportoPagato>68.7</ren:SingoloImportoPagato>
                <ren:DataEsitoSingoloPagamento>2018-05-18+02:00</ren:DataEsitoSingoloPagamento>
                <ren:CodiceVersamento>C001</ren:CodiceVersamento>
                <ren:DatiSpecificiRiscossione>9/30615/E.3.01.03.02.000</ren:DatiSpecificiRiscossione>
                <ren:DescrizioneCausaleVersamento>/RFB/15265313073334849011/68.7</ren:DescrizioneCausaleVersamento>
                <ren:TransactionId>PRD000000002025740</ren:TransactionId>
                <ren:AnagraficaPagatore>
                  <typ:PersonaFisica>
                    <typ:Cognome>VERDE</typ:Cognome>
                    <typ:Nome>CALORE</typ:Nome>
                  </typ:PersonaFisica>
                </ren:AnagraficaPagatore>
                <ren:AnagraficaVersante/>
              </ren:DatiSingoloPagamento>
            </ren:DatiSingoliPagamenti>
          </ren:PagamentoIntermediato>
          <ren:PagamentoIntermediato>
            <ren:DatiSingoliPagamenti>
              <ren:DatiSingoloPagamento>
                <ren:IUV>RF65181370001C00100000014</ren:IUV>
                <ren:IUR>15265321364821726011</ren:IUR>
                <ren:IndiceDatiPagamento>1</ren:IndiceDatiPagamento>
                <ren:SingoloImportoPagato>127.6</ren:SingoloImportoPagato>
                <ren:DataEsitoSingoloPagamento>2018-05-18+02:00</ren:DataEsitoSingoloPagamento>
                <ren:CodiceVersamento>C001</ren:CodiceVersamento>
                <ren:DatiSpecificiRiscossione>9/30615/E.3.01.03.02.000</ren:DatiSpecificiRiscossione>
                <ren:DescrizioneCausaleVersamento>/RFS/RF65181370001C00100000014/127.6</ren:DescrizioneCausaleVersamento>
                <ren:TransactionId>PRD000000002025740</ren:TransactionId>
                <ren:AnagraficaPagatore>
                  <typ:PersonaFisica>
                    <typ:Cognome>SRL</typ:Cognome>
                    <typ:Nome>CASALINGHI</typ:Nome>
                  </typ:PersonaFisica>
                </ren:AnagraficaPagatore>
                <ren:AnagraficaVersante/>
              </ren:DatiSingoloPagamento>
            </ren:DatiSingoliPagamenti>
          </ren:PagamentoIntermediato>
          <ren:PagamentoIntermediato>
            <ren:DatiSingoliPagamenti>
              <ren:DatiSingoloPagamento>
                <ren:IUV>RF16181370001T00200000023</ren:IUV>
                <ren:IUR>15265374475584919011</ren:IUR>
                <ren:IndiceDatiPagamento>1</ren:IndiceDatiPagamento>
                <ren:SingoloImportoPagato>32.43</ren:SingoloImportoPagato>
                <ren:DataEsitoSingoloPagamento>2018-05-18+02:00</ren:DataEsitoSingoloPagamento>
                <ren:CodiceVersamento>T002</ren:CodiceVersamento>
                <ren:DatiSpecificiRiscossione>2/1210</ren:DatiSpecificiRiscossione>
                <ren:DescrizioneCausaleVersamento>/RFS/RF16181370001T00200000023/32.43</ren:DescrizioneCausaleVersamento>
                <ren:TransactionId>PRD000000002244999</ren:TransactionId>
                <ren:AnagraficaPagatore>
                  <typ:PersonaFisica/>
                  <typ:IdentificativoUnivocoFiscale>SROLSN77S61C722O</typ:IdentificativoUnivocoFiscale>
                </ren:AnagraficaPagatore>
                <ren:AnagraficaVersante/>
              </ren:DatiSingoloPagamento>
            </ren:DatiSingoliPagamenti>
          </ren:PagamentoIntermediato>
          <ren:PagamentoIntermediato>
            <ren:DatiSingoliPagamenti>
              <ren:DatiSingoloPagamento>
                <ren:IUV>RF50181370001TF0000000410</ren:IUV>
                <ren:IUR>15265386422644965011</ren:IUR>
                <ren:IndiceDatiPagamento>1</ren:IndiceDatiPagamento>
                <ren:SingoloImportoPagato>266.63</ren:SingoloImportoPagato>
                <ren:DataEsitoSingoloPagamento>2018-05-18+02:00</ren:DataEsitoSingoloPagamento>
                <ren:CodiceVersamento>TF00</ren:CodiceVersamento>
                <ren:DatiSpecificiRiscossione>9/E.1.01.01.48.000</ren:DatiSpecificiRiscossione>
                <ren:DescrizioneCausaleVersamento>/RFS/RF50181370001TF0000000410/266.63</ren:DescrizioneCausaleVersamento>
                <ren:TransactionId>PRD000000002246928</ren:TransactionId>
                <ren:AnagraficaPagatore>
                  <typ:PersonaFisica>
                    <typ:Cognome>ROSSI</typ:Cognome>
                    <typ:Nome>Enrico</typ:Nome>
                  </typ:PersonaFisica>
                  <typ:IdentificativoUnivocoFiscale>RSSNRC83B24D205L</typ:IdentificativoUnivocoFiscale>
                </ren:AnagraficaPagatore>
                <ren:AnagraficaVersante/>
              </ren:DatiSingoloPagamento>
            </ren:DatiSingoliPagamenti>
          </ren:PagamentoIntermediato>
          <ren:PagamentoIntermediato>
            <ren:DatiSingoliPagamenti>
              <ren:DatiSingoloPagamento>
                <ren:IUV>181170560898637</ren:IUV>
                <ren:IUR>15265388788634976011</ren:IUR>
                <ren:IndiceDatiPagamento>1</ren:IndiceDatiPagamento>
                <ren:SingoloImportoPagato>123.36</ren:SingoloImportoPagato>
                <ren:DataEsitoSingoloPagamento>2018-05-18+02:00</ren:DataEsitoSingoloPagamento>
                <ren:CodiceVersamento>TF00</ren:CodiceVersamento>
                <ren:DatiSpecificiRiscossione>9/E.1.01.01.48.000</ren:DatiSpecificiRiscossione>
                <ren:DescrizioneCausaleVersamento>/RFS/181170560898637/123.36</ren:DescrizioneCausaleVersamento>
                <ren:TransactionId>PRD000000002246928</ren:TransactionId>
                <ren:AnagraficaPagatore>
                  <typ:PersonaFisica>
                    <typ:Cognome>Daniele</typ:Cognome>
                    <typ:Nome>Luca</typ:Nome>
                  </typ:PersonaFisica>
                </ren:AnagraficaPagatore>
                <ren:AnagraficaVersante/>
              </ren:DatiSingoloPagamento>
            </ren:DatiSingoliPagamenti>
          </ren:PagamentoIntermediato>
        </ren:PagamentiIntermediati>
      </core>
    </epay:acquisciFlussoRendicontazione>
  </soapenv:Body>
</soapenv:Envelope>
', 'ELABORATO', 1, 1, null, 4, null, '2018-07-27 15:58:56.064');
INSERT INTO epay.cbl_t_storico_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, stato_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, utente_ins_var, data_ins_var) VALUES (16, '2018-05-22BCITITMM-0000000037', '80087670016', 'BCITITMM', 186, null, null, '2018-05-23 07:53:41', '2018-05-23 08:01:02', '<?xml version="1.0" encoding="UTF-8"?><pay_i:FlussoRiversamento xmlns:pay_i="http://www.digitpa.gov.it/schemas/2011/Pagamenti/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="/opt/proctele/resources/FlussoRiversamento_1_0_4.xsd"><pay_i:versioneOggetto>1.0</pay_i:versioneOggetto><pay_i:identificativoFlusso>2018-05-22BCITITMM-0000000037</pay_i:identificativoFlusso><pay_i:dataOraFlusso>2018-05-23T07:53:41</pay_i:dataOraFlusso><pay_i:identificativoUnivocoRegolamento>0306928519791704489999999999IT</pay_i:identificativoUnivocoRegolamento><pay_i:dataRegolamento>2018-05-22</pay_i:dataRegolamento><pay_i:istitutoMittente><pay_i:identificativoUnivocoMittente><pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoMittente><pay_i:denominazioneMittente>Intesa Sanpaolo</pay_i:denominazioneMittente></pay_i:istitutoMittente><pay_i:istitutoRicevente><pay_i:identificativoUnivocoRicevente><pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoRicevente><pay_i:denominazioneRicevente>Regione Piemonte</pay_i:denominazioneRicevente></pay_i:istitutoRicevente><pay_i:numeroTotalePagamenti>186</pay_i:numeroTotalePagamenti><pay_i:importoTotalePagamenti>31083.48</pay_i:importoTotalePagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF04181400001T00200000319</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15268536044320304011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>199.29</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF09181410001T00200000579</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15269380416029253011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>22</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti></pay_i:FlussoRiversamento>', null, 0, 1, null, null, null, '2018-07-27 16:04:16.197');
INSERT INTO epay.cbl_t_storico_flusso_origine(id, identificativo_flusso, identificativo_istituto_ricevente, identificativo_psp, numero_totale_pagamenti, importo_totale_pagamenti, iban_riversamento_flusso, dataora_flusso, data_inserimento, xml_flusso, stato_flusso, contatore_tentativi, id_istituto_ricevente, id_elaborazione, id_stato_flusso, utente_ins_var, data_ins_var) VALUES (17, '2018-05-22BCITITMM-0000000037', '80087670016', 'BCITITMM', 186, null, null, '2018-05-23 07:53:41', '2018-05-23 08:01:02', '<?xml version="1.0" encoding="UTF-8"?><pay_i:FlussoRiversamento xmlns:pay_i="http://www.digitpa.gov.it/schemas/2011/Pagamenti/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="/opt/proctele/resources/FlussoRiversamento_1_0_4.xsd"><pay_i:versioneOggetto>1.0</pay_i:versioneOggetto><pay_i:identificativoFlusso>2018-05-22BCITITMM-0000000037</pay_i:identificativoFlusso><pay_i:dataOraFlusso>2018-05-23T07:53:41</pay_i:dataOraFlusso><pay_i:identificativoUnivocoRegolamento>0306928519791704489999999999IT</pay_i:identificativoUnivocoRegolamento><pay_i:dataRegolamento>2018-05-22</pay_i:dataRegolamento><pay_i:istitutoMittente><pay_i:identificativoUnivocoMittente><pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoMittente><pay_i:denominazioneMittente>Intesa Sanpaolo</pay_i:denominazioneMittente></pay_i:istitutoMittente><pay_i:istitutoRicevente><pay_i:identificativoUnivocoRicevente><pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoRicevente><pay_i:denominazioneRicevente>Regione Piemonte</pay_i:denominazioneRicevente></pay_i:istitutoRicevente><pay_i:numeroTotalePagamenti>186</pay_i:numeroTotalePagamenti><pay_i:importoTotalePagamenti>31083.48</pay_i:importoTotalePagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF04181400001T00200000319</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15268536044320304011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>199.29</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:datiSingoliPagamenti><pay_i:identificativoUnivocoVersamento>RF09181410001T00200000579</pay_i:identificativoUnivocoVersamento><pay_i:identificativoUnivocoRiscossione>15269380416029253011</pay_i:identificativoUnivocoRiscossione><pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento><pay_i:singoloImportoPagato>22</pay_i:singoloImportoPagato><pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-21</pay_i:dataEsitoSingoloPagamento></pay_i:datiSingoliPagamenti></pay_i:FlussoRiversamento>', null, 0, 1, null, null, null, '2018-07-27 16:41:37.287');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (31, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'IN ERRORE', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (32, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'IN ERRORE', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (33, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'IN ERRORE', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (34, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'IN ERRORE', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (35, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'IN ERRORE', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (36, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'BOZZA', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (37, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'IN ERRORE', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_flusso_sintesi(id, identificativo_flusso, codice_versamento, progressivo_elaborazione, data_storicizzazione, utente_modifica, importo_quota_aggregazione, dataora_flusso, descrizione_versamento, stato_flusso, accertamento_anno, accertamento_nro, provvisorio_anno, provvisorio_nro, id_elaborazione, identificativo_istituto_ricevente, identificativo_psp, denominazione_psp, numero_totale_pagamenti, importo_totale_pagamenti, codice_fiscale_ente, descrizione_accertamento, capitolo, articolo, piano_dei_conti, id_catalogo, id_lista, dati_specifici_riscossione) VALUES (38, '2018-05-11ITBBITM1-0000000000037485', 'T002', null, null, 'NGUEYE', 2884.59, null, 'T002', 'IN ERRORE', 2018, null, null, null, null, null, null, null, null, 0.00, null, null, null, null, null, null, null, '2/1210');
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (1, 1, 'USERID', '2018-07-26 14:47:57.958', '2018-07-26 14:47:57.958');
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (2, 1, 'USERID', '2018-07-26 14:47:58.067', null);
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (3, 1, 'USERID', '2018-07-26 14:47:58.067', '2018-07-26 14:48:07.516');
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (4, 1, 'USERID', '2018-07-26 14:47:58.067', '2018-07-26 14:48:07.516');
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (5, 1, 'USERID', '2018-07-26 15:31:57.685', null);
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (6, 1, 'USERID', '2018-07-26 15:31:57.685', '2018-07-26 15:31:58.093');
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (7, 1, 'USERID', '2018-07-26 15:32:31.03', null);
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (8, 1, 'USERID', '2018-07-26 15:32:31.03', '2018-07-26 15:32:31.328');
INSERT INTO epay.cbl_t_storico_lock_applicativo(id, id_ente, id_utente, data_inizio, data_fine) VALUES (9, 1, 'USERID', '2018-07-26 15:35:13.173', null);
INSERT INTO epay.test(id, descrizione) VALUES (1, 'test1');
INSERT INTO epay.test(id, descrizione) VALUES (150, 'saveTest');
INSERT INTO epay.test(id, descrizione) VALUES (151, 'saveTest1');
INSERT INTO epay.test(id, descrizione) VALUES (4, 'TEST_DESCRIPTION_DO_NOT_DELETE');
INSERT INTO epay.test(id, descrizione) VALUES (5, 'TEST_DESCRIPTION_INSERT_DO_NOT_DELETE');
ALTER TABLE epay.cbl_r_stato_flusso_errore
	ADD FOREIGN KEY (id_errore) 
	REFERENCES cbl_d_errore (id);

ALTER TABLE epay.cbl_r_stato_flusso_errore
	ADD FOREIGN KEY (id_flusso_origine) 
	REFERENCES cbl_t_flusso_origine (id);


ALTER TABLE epay.cbl_t_elaborazione
	ADD FOREIGN KEY (id_errore) 
	REFERENCES cbl_d_errore (id);


ALTER TABLE epay.cbl_t_flusso_dettaglio
	ADD FOREIGN KEY (id_flusso_sintesi) 
	REFERENCES cbl_t_flusso_sintesi (id);


ALTER TABLE epay.cbl_t_flusso_origine
	ADD FOREIGN KEY (id_stato_flusso) 
	REFERENCES cbl_d_stato_flusso (id);

ALTER TABLE epay.cbl_t_flusso_origine
	ADD FOREIGN KEY (id_elaborazione) 
	REFERENCES cbl_t_elaborazione (id);

ALTER TABLE epay.cbl_t_flusso_origine
	ADD FOREIGN KEY (id_istituto_ricevente) 
	REFERENCES cbl_t_ente (id);


ALTER TABLE epay.cbl_t_flusso_sintesi
	ADD FOREIGN KEY (id_errore) 
	REFERENCES cbl_r_stato_flusso_errore (id);

ALTER TABLE epay.cbl_t_flusso_sintesi
	ADD FOREIGN KEY (id_istituto_ricevente) 
	REFERENCES cbl_t_ente (id);

ALTER TABLE epay.cbl_t_flusso_sintesi
	ADD FOREIGN KEY (id_flusso_origine) 
	REFERENCES cbl_t_flusso_origine (id);


ALTER TABLE epay.cbl_t_lock_applicativo
	ADD FOREIGN KEY (id_ente) 
	REFERENCES cbl_t_ente (id);


