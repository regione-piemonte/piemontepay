/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.math.BigDecimal;
import java.util.List;

import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;


/**
 *
 */
public class FlussoSintesiPagopaOutputDTOBuilder {

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

    private List<FlussoDettaglioPagopaOutputDTO> listFlussoDettaglioPagopaOutputDTO;

    private FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO;

    public FlussoSintesiPagopaOutputDTOBuilder () {
    }

    public FlussoSintesiPagopaOutputDTOBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withArticolo ( BigDecimal articolo ) {
        this.articolo = articolo;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withDescrizioneDatiSpecifici ( String descrizioneDatiSpecifici ) {
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withImportoQuotaAggregazione ( BigDecimal importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withMacrotipo ( String macrotipo ) {
        this.macrotipo = macrotipo;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withNumeroPagamentiAggregazione ( BigDecimal numeroPagamentiAggregazione ) {
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withPdc ( String pdc ) {
        this.pdc = pdc;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withProgressivoFlussoSintetico ( Integer progressivoFlussoSintetico ) {
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withTematica ( String tematica ) {
        this.tematica = tematica;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder
    withListFlussoDettaglioPagopaOutputDTO ( List<FlussoDettaglioPagopaOutputDTO> listFlussoDettaglioPagopaOutputDTO ) {
        this.listFlussoDettaglioPagopaOutputDTO = listFlussoDettaglioPagopaOutputDTO;
        return this;
    }

    public FlussoSintesiPagopaOutputDTOBuilder withFlussoOriginePagopaOutputDTO ( FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO ) {
        this.flussoOriginePagopaOutputDTO = flussoOriginePagopaOutputDTO;
        return this;
    }

    public FlussoSintesiPagopaOutputDTO build () {
        return new FlussoSintesiPagopaOutputDTO ( id, accertamentoAnno, accertamentoNumero, articolo, capitolo, codiceVersamento, datiSpecificiDiRiscossione,
            descrizioneCodiceVersamento, descrizioneDatiSpecifici, importoQuotaAggregazione, macrotipo, numeroPagamentiAggregazione, pdc,
            progressivoFlussoSintetico, tematica, listFlussoDettaglioPagopaOutputDTO, flussoOriginePagopaOutputDTO );
    }
}
