/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.math.BigDecimal;
import java.util.Date;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOrigineErrore;


/**
 * builder per la classe {@link SimTFlussoOrigineErrore}
 */
public class SimTFlussoOrigineErroreBuilder extends AbstractBuilder<SimTFlussoOrigineErrore> {

    private Long id;

    private String codiceErrore;

    private String codiceVersamento;

    private Integer contatoreTentativi;

    private Date dataInserimento;

    private Date dataRegolamento;

    private Date dataoraFlusso;

    private String descrizioneErrore;

    private String ibanRiversamentoFlusso;

    private Long idElaborazione;

    private String idMessaggio;

    private Long idStatoFlusso;

    private String identificativoFlusso;

    private String identificativoPsp;

    private BigDecimal importoTotalePagamenti;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamenti;

    private Integer numeroTotalePagamentiIntermediati;

    private String xmlFlusso;

    private String identificativoUnivocoRegolamento;

    private String cfEnteRicevente;

    @Override
    public SimTFlussoOrigineErrore build () {
        SimTFlussoOrigineErrore simTFlussoOrigineErrore
        = new SimTFlussoOrigineErrore ( id, codiceErrore, codiceVersamento, contatoreTentativi, id == null ? dataInserimento : null, dataModifica,
                        dataRegolamento, dataoraFlusso, descrizioneErrore, ibanRiversamentoFlusso, idElaborazione, cfEnteRicevente, idMessaggio, idStatoFlusso,
                        identificativoFlusso, identificativoPsp, identificativoUnivocoRegolamento, importoTotalePagamenti, importoTotalePagamentiIntermediati,
                        numeroTotalePagamenti, numeroTotalePagamentiIntermediati, id == null ? utenteInserimento : null, utenteModifica, xmlFlusso );
        return simTFlussoOrigineErrore;
    }

    public SimTFlussoOrigineErroreBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
        return this;
    }

    @Override
    public SimTFlussoOrigineErroreBuilder withDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withDataoraFlusso ( Date dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withIdStatoFlusso ( Long idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withXmlFlusso ( String xmlFlusso ) {
        this.xmlFlusso = xmlFlusso;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        return this;
    }

    public SimTFlussoOrigineErroreBuilder withCfEnteRicevente ( String cfEnteRicevente ) {
        this.cfEnteRicevente = cfEnteRicevente;
        return this;
    }

}
