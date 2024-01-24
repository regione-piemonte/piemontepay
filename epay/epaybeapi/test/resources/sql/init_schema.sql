DROP ALL OBJECTS;
	
CREATE TABLE epaybeapi_t_anagrafica (
	id_anagrafica int auto_increment primary key,
	nome varchar(250) NULL,
	cognome varchar(250) NULL,
	ragione_sociale varchar(250) NULL,
	codice_fiscale varchar(35) NULL,
	indirizzo varchar(70) NULL,
	email varchar(250) NULL,
	telefono varchar(30) NULL,
	cellulare varchar(30) NULL,
	civico varchar(16) NULL,
	cap varchar(16) NULL,
	localita varchar(35) NULL,
	provincia varchar(35) NULL,
	nazione varchar(2) NULL,
	flag_persona_fisica bool NOT NULL DEFAULT true,
	PRIMARY KEY (id_anagrafica)
);