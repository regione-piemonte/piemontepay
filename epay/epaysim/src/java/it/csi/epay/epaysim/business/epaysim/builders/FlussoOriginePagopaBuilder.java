/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysim.dto.FlussoOriginePagopaDTO;


/**
 *
 */

public class FlussoOriginePagopaBuilder extends AbstractBuilder<FlussoOriginePagopaDTO> {

    private Long id;

    private XMLGregorianCalendar dataInserimento;

    private XMLGregorianCalendar dataRegolamento;

    private XMLGregorianCalendar dataoraFlusso;

    private String ibanRiversamentoFlusso;

    private Long idElaborazione;

    private String idMessaggio;

    private String identificativoFlusso;

    private String identificativoPsp;

    private String identificativoUnivocoRegolamento;

    private BigDecimal importoTotalePagamenti;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamenti;

    private Integer numeroTotalePagamentiIntermediati;

    private String codiceFiscaleEnte;

    private Boolean statoElaborazioneFlusso;

    @Override
    public FlussoOriginePagopaDTO build () {
        return new FlussoOriginePagopaDTO ( id, dataInserimento, dataRegolamento, dataoraFlusso, ibanRiversamentoFlusso, idElaborazione,
            idMessaggio, identificativoFlusso, identificativoPsp, identificativoUnivocoRegolamento, importoTotalePagamenti,
            importoTotalePagamentiIntermediati, numeroTotalePagamenti, numeroTotalePagamentiIntermediati, codiceFiscaleEnte, statoElaborazioneFlusso );
    }

    public FlussoOriginePagopaBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public FlussoOriginePagopaBuilder withDataInserimento ( XMLGregorianCalendar dataInserimento ) {
        this.dataInserimento = dataInserimento;
        return this;
    }

    public FlussoOriginePagopaBuilder withDataRegolamento ( XMLGregorianCalendar dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
        return this;
    }

    public FlussoOriginePagopaBuilder withDataoraFlusso ( XMLGregorianCalendar dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
        return this;
    }

    public FlussoOriginePagopaBuilder withIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
        return this;
    }

    public FlussoOriginePagopaBuilder withIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
        return this;
    }

    public FlussoOriginePagopaBuilder withIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
        return this;
    }

    public FlussoOriginePagopaBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public FlussoOriginePagopaBuilder withIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
        return this;
    }

    public FlussoOriginePagopaBuilder withIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        return this;
    }

    public FlussoOriginePagopaBuilder withImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
        return this;
    }

    public FlussoOriginePagopaBuilder withImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        return this;
    }

    public FlussoOriginePagopaBuilder withNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        return this;
    }

    public FlussoOriginePagopaBuilder withNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        return this;
    }

    public FlussoOriginePagopaBuilder withCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
        return this;
    }

    public FlussoOriginePagopaBuilder withStatoElaborazioneFlusso ( Boolean statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
        return this;
    }

}
