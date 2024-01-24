DROP TABLE IF EXISTS sim_t_provvisorio CASCADE;

CREATE TABLE sim_t_provvisorio
(
   id                     integer         NOT NULL,
   identificativo_flusso  varchar(35)     NOT NULL,
   causale                varchar(85)     NOT NULL,
   data_movimento         timestamp       NOT NULL,
   anno_esercizio         integer         NOT NULL,
   anno_provvisorio       integer         NOT NULL,
   numero_provvisorio     integer         NOT NULL,
   importo_provvisorio    numeric(18,2)   NOT NULL,
   codice_fiscale_ente    varchar(16)     NOT NULL,
   stato                  varchar(20)     NOT NULL,
   id_giornale_di_cassa   integer         NOT NULL,
   utente_inserimento     varchar(16),
   data_inserimento       timestamp,
   utente_modifica        varchar(16),
   data_modifica          timestamp
);

ALTER TABLE sim_t_provvisorio
   ADD CONSTRAINT sim_t_provvisorio_pk
   PRIMARY KEY (id);

ALTER TABLE sim_t_provvisorio
   ADD CONSTRAINT sim_t_provvisorio_un UNIQUE (identificativo_flusso);


DROP SEQUENCE IF EXISTS sim_t_provvisorio_id_seq;
CREATE SEQUENCE SIM_T_PROVVISORIO_ID_SEQ;

DROP TABLE IF EXISTS sim_t_giornale_di_cassa CASCADE;
CREATE TABLE sim_t_giornale_di_cassa (
   id                  serial        NOT NULL,
   data_inserimento    timestamp     NOT NULL,
   xml                 text          NOT NULL,
   utente_inserimento  varchar(16),
   utente_modifica     varchar(16),
   data_modifica       timestamp,
	PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS sim_t_giornale_di_cassa_id_seq;
CREATE SEQUENCE  SIM_T_GIORNALE_DI_CASSA_ID_SEQ;

ALTER TABLE sim_t_provvisorio
  ADD CONSTRAINT sim_t_provvisorio_fk FOREIGN KEY (id_giornale_di_cassa)
  REFERENCES sim_t_giornale_di_cassa (id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION; 
       
DROP TABLE IF EXISTS sim_t_flusso_origine_errore CASCADE;

CREATE TABLE sim_t_flusso_origine_errore
(
   id                                     bigint          NOT NULL,
   identificativo_flusso                  text,
   identificativo_psp                     text,
   numero_totale_pagamenti                integer,
   importo_totale_pagamenti               numeric(18,2),
   iban_riversamento_flusso               text,
   dataora_flusso                         timestamp,
   data_inserimento                       timestamp,
   xml_flusso                             text,
   contatore_tentativi                    integer,
   id_elaborazione                        bigint,
   id_stato_flusso                        bigint,
   importo_totale_pagamenti_intermediati  numeric(18,2)   DEFAULT 0 NOT NULL,
   numero_totale_pagamenti_intermediati   integer         DEFAULT 0 NOT NULL,
   data_regolamento                       date,
   codice_versamento                      varchar(4),
   codice_errore                          varchar(30)     NOT NULL,
   descrizione_errore                     varchar(500),
   id_messaggio                           varchar(35)     NOT NULL,
   identificativo_univoco_regolamento     varchar(35)     NOT NULL,
   utente_inserimento                     varchar(16),
   utente_modifica                        varchar(16),
   data_modifica                          timestamp,
   cf_ente_ricevente                      varchar(16)
);

ALTER TABLE sim_t_flusso_origine_errore
   ADD CONSTRAINT sim_t_flusso_origine_errore_pkey
   PRIMARY KEY (id);

DROP SEQUENCE IF EXISTS sim_t_flusso_origine_errore_id_seq;

CREATE SEQUENCE sim_t_flusso_origine_errore_id_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;
   
DROP SEQUENCE IF EXISTS sim_t_flusso_origine_errore_id_seq;

CREATE SEQUENCE sim_t_flusso_origine_errore_id_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;

DROP SEQUENCE IF EXISTS sim_t_flusso_sintesi_pagopa_id_seq;

CREATE SEQUENCE sim_t_flusso_sintesi_pagopa_id_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;

DROP TABLE IF EXISTS sim_t_flusso_origine_pagopa CASCADE;

CREATE TABLE sim_t_flusso_origine_pagopa
(
      id                                     bigint          NOT NULL,
   identificativo_flusso                  text,
   identificativo_psp                     text,
   numero_totale_pagamenti                integer,
   importo_totale_pagamenti               numeric(18,2),
   iban_riversamento_flusso               text,
   dataora_flusso                         timestamp,
   data_inserimento                       timestamp,
   xml_flusso                             text,
   contatore_tentativi                    integer,
   id_elaborazione                        bigint,
   id_stato_flusso                        bigint,
   importo_totale_pagamenti_intermediati  numeric(18,2)   DEFAULT 0 NOT NULL,
   numero_totale_pagamenti_intermediati   integer         DEFAULT 0 NOT NULL,
   id_messaggio                           varchar(35)     NOT NULL,
   data_regolamento                       date,
   identificativo_univoco_regolamento     varchar(35)     NOT NULL,
   stato_elaborazione_flusso              boolean,
   utente_inserimento                     varchar(16),
   utente_modifica                        varchar(16),
   data_modifica                          timestamp,
   cf_ente_ricevente                      varchar(16)
);

ALTER TABLE sim_t_flusso_origine_pagopa
   ADD CONSTRAINT sim_t_flusso_origine_pagopa_pkey
   PRIMARY KEY (id);
  
DROP SEQUENCE IF EXISTS sim_t_flusso_origine_pagopa_id_seq;

CREATE SEQUENCE sim_t_flusso_origine_pagopa_id_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;

COMMIT;

DROP TABLE IF EXISTS sim_t_flusso_sintesi_pagopa CASCADE;

CREATE TABLE sim_t_flusso_sintesi_pagopa
(
   id                              bigint          NOT NULL,
   codice_versamento               text,
   importo_quota_aggregazione      numeric(18,2)   DEFAULT 0 NOT NULL,
   numero_vers_quota_aggregazione  numeric(10)     DEFAULT 0 NOT NULL,
   dati_specifici_di_riscossione   text,
   provvisorio_anno                integer,
   provvisorio_nro                 integer,
   id_flusso_origine               bigint,
   capitolo                        numeric,
   articolo                        numeric,
   piano_dei_conti                 text,
   accertamento_numero             integer,
   accertamento_anno               integer,
   utente_inserimento              varchar(16),
   data_inserimento                timestamp,
   utente_modifica                 varchar(16),
   data_modifica                   timestamp,
   cf_ente_ricevente               varchar(16)
);

ALTER TABLE sim_t_flusso_sintesi_pagopa
   ADD CONSTRAINT sim_t_flusso_sintesi_pagopa_pkey
   PRIMARY KEY (id);

ALTER TABLE sim_t_flusso_sintesi_pagopa
  ADD CONSTRAINT sim_t_flusso_sintesi_pagopa_fk1 FOREIGN KEY (id_flusso_origine)
  REFERENCES sim_t_flusso_origine_pagopa (id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
   
DROP SEQUENCE IF EXISTS sim_t_flusso_sintesi_pagopa_id_seq;

CREATE SEQUENCE sim_t_flusso_sintesi_pagopa_id_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;

DROP TABLE IF EXISTS sim_t_flusso_dettaglio_pagopa CASCADE;

CREATE TABLE sim_t_flusso_dettaglio_pagopa
(
  id                                bigint          NOT NULL,
   codice_versamento                 text,
   dati_specifici_di_riscossione     text,
   id_flusso_sintesi                 bigint,
   identificativo_unico_versamento   text,
   identificativo_unico_riscossione  text,
   importo_singolo_versamento        numeric(18,2)   DEFAULT 0 NOT NULL,
   data_pagamento                    timestamp,
   esito_pagamento                   char(1),
   descrizione_causale_versamento    text,
   transactionid                     text,
   stato_invio_fruitore              char(1),
   anagrafica_pagatore               text,
   codicefiscale_pagatore            text,
   indice_singolo_versamento         integer,
   causale                           varchar(135)    NOT NULL,
   accertamento_numero               integer,
   accertamento_anno                 integer,
   tipo_anagrafica_pagatore          char(1),
   utente_inserimento                varchar(16),
   data_inserimento                  timestamp,
   utente_modifica                   varchar(16),
   data_modifica                     timestamp,
   CHECK ((tipo_anagrafica_pagatore = 'F') OR (tipo_anagrafica_pagatore = 'G'))
);

ALTER TABLE sim_t_flusso_dettaglio_pagopa
   ADD CONSTRAINT sim_t_flusso_dettaglio_pagopa_pkey
   PRIMARY KEY (id);

ALTER TABLE sim_t_flusso_dettaglio_pagopa
  ADD CONSTRAINT sim_t_flusso_dettaglio_pagopa_fk1 FOREIGN KEY (id_flusso_sintesi)
  REFERENCES sim_t_flusso_sintesi_pagopa (id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
DROP SEQUENCE IF EXISTS sim_t_flusso_dettaglio_pagopa_id_seq;

CREATE SEQUENCE sim_t_flusso_dettaglio_pagopa_id_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;
       