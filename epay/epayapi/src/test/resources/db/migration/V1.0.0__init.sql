create schema EPAY;

create table epay.epay_d_chiamante_esterno
(
    codice_chiamante      varchar(100) not null
        primary key,
    descrizione_chiamante varchar(200),
    data_inizio_validita  timestamp    not null,
    data_fine_validita    timestamp,
    passphrase            varchar(200) not null,
    url                   varchar(200) not null
);

-- alter table epay.epay_d_chiamante_esterno
--     owner to epay;
--
-- grant delete, insert, select, update on epay.epay_d_chiamante_esterno to epay_rw;
--
