/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.math.BigDecimal;
import java.util.List;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoDettaglioPagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;


/**
 *
 */

public class SimTFlussoSintesiPagopaBuilder extends AbstractBuilder<SimTFlussoSintesiPagopa> {

    private Long id;

    private Integer accertamentoAnno;

    private Integer accertamentoNumero;

    private BigDecimal articolo;

    private String capitolo;

    private String codiceVersamento;

    private String datiSpecificiDiRiscossione;

    private String descrizioneCodiceVersamento;

    private String descrizioneDatiSpecifici;

    private BigDecimal importoQuotaAggregazione;

    private String macrotipo;

    private BigDecimal numeroPagamentiAggregazione;

    private String pdc;

    private Integer progressivoFlussoSintetico;

    private String tematica;

    private List<SimTFlussoDettaglioPagopa> simTFlussoDettaglioPagopas;

    private SimTFlussoOriginePagopa simTFlussoOriginePagopa;

    public SimTFlussoSintesiPagopaBuilder () {
    }

    public SimTFlussoSintesiPagopaBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withArticolo ( BigDecimal articolo ) {
        this.articolo = articolo;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withDescrizioneDatiSpecifici ( String descrizioneDatiSpecifici ) {
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withImportoQuotaAggregazione ( BigDecimal importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withMacrotipo ( String macrotipo ) {
        this.macrotipo = macrotipo;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withNumeroPagamentiAggregazione ( BigDecimal numeroPagamentiAggregazione ) {
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withPdc ( String pdc ) {
        this.pdc = pdc;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withProgressivoFlussoSintetico ( Integer progressivoFlussoSintetico ) {
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withTematica ( String tematica ) {
        this.tematica = tematica;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withSimTFlussoDettaglioPagopas ( List<SimTFlussoDettaglioPagopa> simTFlussoDettaglioPagopas ) {
        this.simTFlussoDettaglioPagopas = simTFlussoDettaglioPagopas;
        return this;
    }

    public SimTFlussoSintesiPagopaBuilder withSimTFlussoOriginePagopa ( SimTFlussoOriginePagopa simTFlussoOriginePagopa ) {
        this.simTFlussoOriginePagopa = simTFlussoOriginePagopa;
        return this;
    }

    @Override
    public SimTFlussoSintesiPagopa build () {
        return new SimTFlussoSintesiPagopa ( id, accertamentoAnno, accertamentoNumero, articolo, capitolo, codiceVersamento, datiSpecificiDiRiscossione,
            descrizioneCodiceVersamento, descrizioneDatiSpecifici, importoQuotaAggregazione, macrotipo, numeroPagamentiAggregazione, pdc,
            progressivoFlussoSintetico, tematica, simTFlussoDettaglioPagopas, simTFlussoOriginePagopa, null == id ? dataInserimento : null, dataModifica,
            null == id ? utenteInserimento : null, utenteModifica );
    }
}
