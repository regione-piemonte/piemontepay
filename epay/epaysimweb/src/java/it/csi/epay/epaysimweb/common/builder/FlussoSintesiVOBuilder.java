/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.common.builder;

import java.math.BigDecimal;

import it.csi.epay.epaysimweb.model.flussi.FlussoSintesiVO;


/**
 *
 */
public class FlussoSintesiVOBuilder implements AbstractBuilder<FlussoSintesiVO> {

    private Long id;

    private Integer accertamentoAnno;

    private Integer accertamentoNumero;

    private Integer articolo;

    private Integer capitolo;

    private String codiceVersamento;

    private String datiSpecificiDiRiscossione;

    private String descrizioneCodiceVersamento;

    private String descrizioneDatiSpecifici;

    private Double importoQuotaAggregazione;

    private String macrotipo;

    private Integer numeroPagamentiAggregazione;

    private String pdc;

    private Integer progressivoFlussoSintetico;

    private String tematica;

    public FlussoSintesiVOBuilder () {
    }

    public FlussoSintesiVOBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public FlussoSintesiVOBuilder withAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
        return this;
    }

    public FlussoSintesiVOBuilder withAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
        return this;
    }

    public FlussoSintesiVOBuilder withArticolo ( Integer articolo ) {
        this.articolo = articolo;
        return this;
    }

    public FlussoSintesiVOBuilder withArticolo ( BigDecimal articolo ) {
        this.articolo = ( null != articolo ? articolo.intValue () : null );
        return this;
    }

    public FlussoSintesiVOBuilder withCapitolo ( Integer capitolo ) {
        this.capitolo = capitolo;
        return this;
    }

    public FlussoSintesiVOBuilder withCapitolo ( BigDecimal capitolo ) {
        this.capitolo = ( null != capitolo ? capitolo.intValue () : null );
        return this;
    }

    public FlussoSintesiVOBuilder withCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
        return this;
    }

    public FlussoSintesiVOBuilder withDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
        return this;
    }

    public FlussoSintesiVOBuilder withDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
        return this;
    }

    public FlussoSintesiVOBuilder withDescrizioneDatiSpecifici ( String descrizioneDatiSpecifici ) {
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
        return this;
    }

    public FlussoSintesiVOBuilder withImportoQuotaAggregazione ( Double importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
        return this;
    }


    public FlussoSintesiVOBuilder withMacrotipo ( String macrotipo ) {
        this.macrotipo = macrotipo;
        return this;
    }

    public FlussoSintesiVOBuilder withNumeroPagamentiAggregazione ( Integer numeroPagamentiAggregazione ) {
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
        return this;
    }

    public FlussoSintesiVOBuilder withNumeroPagamentiAggregazione ( BigDecimal numeroPagamentiAggregazione ) {
        this.numeroPagamentiAggregazione = ( null != numeroPagamentiAggregazione ? numeroPagamentiAggregazione.intValue () : null );;
        return this;
    }

    public FlussoSintesiVOBuilder withPdc ( String pdc ) {
        this.pdc = pdc;
        return this;
    }

    public FlussoSintesiVOBuilder withProgressivoFlussoSintetico ( Integer progressivoFlussoSintetico ) {
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
        return this;
    }

    public FlussoSintesiVOBuilder withTematica ( String tematica ) {
        this.tematica = tematica;
        return this;
    }

    @Override
    public FlussoSintesiVO build () {
        return new FlussoSintesiVO ( id, accertamentoAnno, accertamentoNumero, articolo, capitolo, codiceVersamento, datiSpecificiDiRiscossione,
            descrizioneCodiceVersamento, descrizioneDatiSpecifici, importoQuotaAggregazione, macrotipo, numeroPagamentiAggregazione, pdc,
            progressivoFlussoSintetico, tematica );
    }
}
