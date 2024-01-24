/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.model.provvisorio;

import java.math.BigDecimal;
import java.util.Date;

import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoProvvisorio;
import it.csi.epay.epaymodricweb.util.DateUtils;


/**
 *
 */

public class DTOProvvisorioBuilder {

    private Long id;

    private String identificativoFlusso;

    private String descrizione;

    private String stato;

    private Integer annoProvvisorio;

    private String causale;

    private Date dataMovimento;

    private Integer annoEsercizio;

    private Date dataFine;

    private BigDecimal importoProvvisorio;

    private Integer numeroProvvisorio;

    private String tipoMovimento;

    public DTOProvvisorioBuilder () {
    }

    public DTOProvvisorioBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public DTOProvvisorioBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public DTOProvvisorioBuilder withDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
        return this;
    }

    public DTOProvvisorioBuilder withStato ( String stato ) {
        this.stato = stato;
        return this;
    }

    public DTOProvvisorioBuilder withAnnoProvvisorio ( Integer annoProvvisorio ) {
        this.annoProvvisorio = annoProvvisorio;
        return this;
    }

    public DTOProvvisorioBuilder withCausale ( String causale ) {
        this.causale = causale;
        return this;
    }

    public DTOProvvisorioBuilder withDataMovimento ( Date dataMovimento ) {
        this.dataMovimento = dataMovimento;
        return this;
    }

    public DTOProvvisorioBuilder withAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
        return this;
    }

    public DTOProvvisorioBuilder withDataFine ( Date dataFine ) {
        this.dataFine = dataFine;
        return this;
    }

    public DTOProvvisorioBuilder withImportoProvvisorio ( BigDecimal importoProvvisorio ) {
        this.importoProvvisorio = importoProvvisorio;
        return this;
    }

    public DTOProvvisorioBuilder withNumeroProvvisorio ( Integer numeroProvvisorio ) {
        this.numeroProvvisorio = numeroProvvisorio;
        return this;
    }

    public DTOProvvisorioBuilder withTipoMovimento ( String tipoMovimento ) {
        this.tipoMovimento = tipoMovimento;
        return this;
    }

    public DtoProvvisorio build () {
        DtoProvvisorio prov = new DtoProvvisorio ();
        prov.setAnnoEsercizio ( annoEsercizio );
        prov.setAnnoProvvisorio ( annoProvvisorio );
        prov.setCausale ( causale );
        prov.setDataFine ( DateUtils.getXmlGregorianCalendarDate ( dataFine ) );
        prov.setDataMovimento ( DateUtils.getXmlGregorianCalendarDate ( dataMovimento ) );
        prov.setDescrizione ( descrizione );
        prov.setId ( id );
        prov.setIdentificativoFlusso ( identificativoFlusso );
        prov.setImportoProvvisorio ( importoProvvisorio );
        prov.setNumeroProvvisorio ( numeroProvvisorio );
        prov.setStato ( stato );
        prov.setTipoMovimento ( tipoMovimento );
        return prov;
    }
}
