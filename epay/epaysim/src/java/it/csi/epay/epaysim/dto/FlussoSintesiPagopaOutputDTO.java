/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType ( XmlAccessType.FIELD )
public class FlussoSintesiPagopaOutputDTO extends ParentOutput implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public FlussoSintesiPagopaOutputDTO () {

    }

    public FlussoSintesiPagopaOutputDTO ( Long id, Integer accertamentoAnno, Integer accertamentoNumero, BigDecimal articolo, String capitolo,
        String codiceVersamento, String datiSpecificiDiRiscossione, String descrizioneCodiceVersamento, String descrizioneDatiSpecifici,
        BigDecimal importoQuotaAggregazione, String macrotipo, BigDecimal numeroPagamentiAggregazione, String pdc, Integer progressivoFlussoSintetico,
        String tematica, List<FlussoDettaglioPagopaOutputDTO> listFlussoDettaglioPagopaOutputDTO, FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO ) {
        super ();
        this.id = id;
        this.accertamentoAnno = accertamentoAnno;
        this.accertamentoNumero = accertamentoNumero;
        this.articolo = articolo;
        this.capitolo = capitolo;
        this.codiceVersamento = codiceVersamento;
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
        this.importoQuotaAggregazione = importoQuotaAggregazione;
        this.macrotipo = macrotipo;
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
        this.pdc = pdc;
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
        this.tematica = tematica;
        this.listFlussoDettaglioPagopaOutputDTO = listFlussoDettaglioPagopaOutputDTO;
        this.flussoOriginePagopaOutputDTO = flussoOriginePagopaOutputDTO;
    }

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the accertamentoAnno
     */
    public Integer getAccertamentoAnno () {
        return accertamentoAnno;
    }

    /**
     * @param accertamentoAnno the accertamentoAnno to set
     */
    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    /**
     * @return the accertamentoNumero
     */
    public Integer getAccertamentoNumero () {
        return accertamentoNumero;
    }

    /**
     * @param accertamentoNumero the accertamentoNumero to set
     */
    public void setAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
    }

    /**
     * @return the articolo
     */
    public BigDecimal getArticolo () {
        return articolo;
    }

    /**
     * @param articolo the articolo to set
     */
    public void setArticolo ( BigDecimal articolo ) {
        this.articolo = articolo;
    }

    /**
     * @return the capitolo
     */
    public String getCapitolo () {
        return capitolo;
    }

    /**
     * @param capitolo the capitolo to set
     */
    public void setCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
    }

    /**
     * @return the codiceVersamento
     */
    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    /**
     * @param codiceVersamento the codiceVersamento to set
     */
    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    /**
     * @return the datiSpecificiDiRiscossione
     */
    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    /**
     * @param datiSpecificiDiRiscossione the datiSpecificiDiRiscossione to set
     */
    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    /**
     * @return the descrizioneCodiceVersamento
     */
    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    /**
     * @param descrizioneCodiceVersamento the descrizioneCodiceVersamento to set
     */
    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    /**
     * @return the descrizioneDatiSpecifici
     */
    public String getDescrizioneDatiSpecifici () {
        return descrizioneDatiSpecifici;
    }

    /**
     * @param descrizioneDatiSpecifici the descrizioneDatiSpecifici to set
     */
    public void setDescrizioneDatiSpecifici ( String descrizioneDatiSpecifici ) {
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
    }

    /**
     * @return the importoQuotaAggregazione
     */
    public BigDecimal getImportoQuotaAggregazione () {
        return importoQuotaAggregazione;
    }

    /**
     * @param importoQuotaAggregazione the importoQuotaAggregazione to set
     */
    public void setImportoQuotaAggregazione ( BigDecimal importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    /**
     * @return the macrotipo
     */
    public String getMacrotipo () {
        return macrotipo;
    }

    /**
     * @param macrotipo the macrotipo to set
     */
    public void setMacrotipo ( String macrotipo ) {
        this.macrotipo = macrotipo;
    }

    /**
     * @return the numeroPagamentiAggregazione
     */
    public BigDecimal getNumeroPagamentiAggregazione () {
        return numeroPagamentiAggregazione;
    }

    /**
     * @param numeroPagamentiAggregazione the numeroPagamentiAggregazione to set
     */
    public void setNumeroPagamentiAggregazione ( BigDecimal numeroPagamentiAggregazione ) {
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
    }

    /**
     * @return the pdc
     */
    public String getPdc () {
        return pdc;
    }

    /**
     * @param pdc the pdc to set
     */
    public void setPdc ( String pdc ) {
        this.pdc = pdc;
    }

    /**
     * @return the progressivoFlussoSintetico
     */
    public Integer getProgressivoFlussoSintetico () {
        return progressivoFlussoSintetico;
    }

    /**
     * @param progressivoFlussoSintetico the progressivoFlussoSintetico to set
     */
    public void setProgressivoFlussoSintetico ( Integer progressivoFlussoSintetico ) {
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
    }

    /**
     * @return the tematica
     */
    public String getTematica () {
        return tematica;
    }

    /**
     * @param tematica the tematica to set
     */
    public void setTematica ( String tematica ) {
        this.tematica = tematica;
    }

    /**
     * @return the simTFlussoDettaglioPagopas
     */
    public List<FlussoDettaglioPagopaOutputDTO> getListFlussoDettaglioPagopaOutputDTO () {
        return listFlussoDettaglioPagopaOutputDTO;
    }

    /**
     * @param simTFlussoDettaglioPagopas the simTFlussoDettaglioPagopas to set
     */
    public void setListFlussoDettaglioPagopaOutputDTO ( List<FlussoDettaglioPagopaOutputDTO> listFlussoDettaglioPagopaOutputDTO ) {
        this.listFlussoDettaglioPagopaOutputDTO = listFlussoDettaglioPagopaOutputDTO;
    }


    /**
     * @return the flussoOriginePagopaOutputDTO
     */
    public FlussoOriginePagopaOutputDTO getFlussoOriginePagopaOutputDTO () {
        return flussoOriginePagopaOutputDTO;
    }


    /**
     * @param flussoOriginePagopaOutputDTO the flussoOriginePagopaOutputDTO to set
     */
    public void setFlussoOriginePagopaOutputDTO ( FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO ) {
        this.flussoOriginePagopaOutputDTO = flussoOriginePagopaOutputDTO;
    }

}
