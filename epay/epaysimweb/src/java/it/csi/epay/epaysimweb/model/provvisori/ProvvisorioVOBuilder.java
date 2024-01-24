/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.model.provvisori;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 */

public class ProvvisorioVOBuilder {

    private Long id;

    private String identificativoFlusso;

    private String descrizione;

    private String stato;

    private Integer annoProvvisorio;

    private String causale;

    private Date dataMovimento;

    private Integer annoEsercizio;

    private Date dataFine;

    private String idEnte;

    private BigDecimal importoProvvisorio;

    private Integer numeroProvvisorio;

    public ProvvisorioVOBuilder () {
    }

    public ProvvisorioVOBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public ProvvisorioVOBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public ProvvisorioVOBuilder withDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
        return this;
    }

    public ProvvisorioVOBuilder withStato ( String stato ) {
        this.stato = stato;
        return this;
    }

    public ProvvisorioVOBuilder withAnnoProvvisorio ( Integer annoProvvisorio ) {
        this.annoProvvisorio = annoProvvisorio;
        return this;
    }

    public ProvvisorioVOBuilder withCausale ( String causale ) {
        this.causale = causale;
        return this;
    }

    public ProvvisorioVOBuilder withDataMovimento ( Date dataMovimento ) {
        this.dataMovimento = dataMovimento;
        return this;
    }

    public ProvvisorioVOBuilder withAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
        return this;
    }

    public ProvvisorioVOBuilder withDataFine ( Date dataFine ) {
        this.dataFine = dataFine;
        return this;
    }

    public ProvvisorioVOBuilder withIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
        return this;
    }

    public ProvvisorioVOBuilder withImportoProvvisorio ( BigDecimal importoProvvisorio ) {
        this.importoProvvisorio = importoProvvisorio;
        return this;
    }

    public ProvvisorioVOBuilder withNumeroProvvisorio ( Integer numeroProvvisorio ) {
        this.numeroProvvisorio = numeroProvvisorio;
        return this;
    }

    public ProvvisorioVO build () {
        return new ProvvisorioVO ( id, identificativoFlusso, descrizione, stato, annoProvvisorio, causale, dataMovimento, annoEsercizio, dataFine, idEnte,
            importoProvvisorio, numeroProvvisorio );
    }
}
