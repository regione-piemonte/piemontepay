/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class FlussoSintesiPagopaDTO extends ParentInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal articolo;

    private String capitolo;

    private String codiceVersamento;

    private String datiSpecificiDiRiscossione;

    private Long idIstitutoRicevente;

    private BigDecimal importoQuotaAggregazione;

    private BigDecimal numeroVersQuotaAggregazione;

    private String pianoDeiConti;

    private Integer provvisorioAnno;

    private Integer provvisorioNro;

    private Integer accertamentoNumero;

    private Integer accertamentoAnno;

    private List<FlussoDettaglioPagopaDTO> simTFlussoDettaglioPagopas;

    private FlussoOriginePagopaDTO simTFlussoOriginePagopa;

    public FlussoSintesiPagopaDTO () {
    }

    public FlussoSintesiPagopaDTO ( Long id, BigDecimal articolo, String capitolo, String codiceVersamento, String datiSpecificiDiRiscossione,
        Long idIstitutoRicevente, BigDecimal importoQuotaAggregazione, BigDecimal numeroVersQuotaAggregazione, String pianoDeiConti, Integer provvisorioAnno,
        Integer provvisorioNro, Integer accertamentoNumero, Integer accertamentoAnno, List<FlussoDettaglioPagopaDTO> simTFlussoDettaglioPagopas,
        FlussoOriginePagopaDTO simTFlussoOriginePagopa ) {
        super ();
        this.id = id;
        this.articolo = articolo;
        this.capitolo = capitolo;
        this.codiceVersamento = codiceVersamento;
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
        this.idIstitutoRicevente = idIstitutoRicevente;
        this.importoQuotaAggregazione = importoQuotaAggregazione;
        this.numeroVersQuotaAggregazione = numeroVersQuotaAggregazione;
        this.pianoDeiConti = pianoDeiConti;
        this.provvisorioAnno = provvisorioAnno;
        this.provvisorioNro = provvisorioNro;
        this.accertamentoNumero = accertamentoNumero;
        this.accertamentoAnno = accertamentoAnno;
        this.simTFlussoDettaglioPagopas = simTFlussoDettaglioPagopas;
        this.simTFlussoOriginePagopa = simTFlussoOriginePagopa;
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public BigDecimal getArticolo () {
        return this.articolo;
    }

    public void setArticolo ( BigDecimal articolo ) {
        this.articolo = articolo;
    }

    public String getCapitolo () {
        return this.capitolo;
    }

    public void setCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
    }

    public String getCodiceVersamento () {
        return this.codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return this.datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public Long getIdIstitutoRicevente () {
        return this.idIstitutoRicevente;
    }

    public void setIdIstitutoRicevente ( Long idIstitutoRicevente ) {
        this.idIstitutoRicevente = idIstitutoRicevente;
    }

    public BigDecimal getImportoQuotaAggregazione () {
        return this.importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione ( BigDecimal importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public BigDecimal getNumeroVersQuotaAggregazione () {
        return this.numeroVersQuotaAggregazione;
    }

    public void setNumeroVersQuotaAggregazione ( BigDecimal numeroVersQuotaAggregazione ) {
        this.numeroVersQuotaAggregazione = numeroVersQuotaAggregazione;
    }

    public String getPianoDeiConti () {
        return this.pianoDeiConti;
    }

    public void setPianoDeiConti ( String pianoDeiConti ) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public Integer getProvvisorioAnno () {
        return this.provvisorioAnno;
    }

    public void setProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public Integer getProvvisorioNro () {
        return this.provvisorioNro;
    }

    public void setProvvisorioNro ( Integer provvisorioNro ) {
        this.provvisorioNro = provvisorioNro;
    }

    public List<FlussoDettaglioPagopaDTO> getSimTFlussoDettaglioPagopas () {
        return this.simTFlussoDettaglioPagopas;
    }

    public void setSimTFlussoDettaglioPagopas ( List<FlussoDettaglioPagopaDTO> simTFlussoDettaglioPagopas ) {
        this.simTFlussoDettaglioPagopas = simTFlussoDettaglioPagopas;
    }

    public FlussoDettaglioPagopaDTO addSimTFlussoDettaglioPagopa ( FlussoDettaglioPagopaDTO simTFlussoDettaglioPagopa ) {
        getSimTFlussoDettaglioPagopas ().add ( simTFlussoDettaglioPagopa );
        simTFlussoDettaglioPagopa.setSimTFlussoSintesiPagopa ( this );

        return simTFlussoDettaglioPagopa;
    }

    public FlussoDettaglioPagopaDTO removeSimTFlussoDettaglioPagopa ( FlussoDettaglioPagopaDTO simTFlussoDettaglioPagopa ) {
        getSimTFlussoDettaglioPagopas ().remove ( simTFlussoDettaglioPagopa );
        simTFlussoDettaglioPagopa.setSimTFlussoSintesiPagopa ( null );

        return simTFlussoDettaglioPagopa;
    }

    public FlussoOriginePagopaDTO getSimTFlussoOriginePagopa () {
        return this.simTFlussoOriginePagopa;
    }

    public void setSimTFlussoOriginePagopa ( FlussoOriginePagopaDTO simTFlussoOriginePagopa ) {
        this.simTFlussoOriginePagopa = simTFlussoOriginePagopa;
    }

    public Integer getAccertamentoNumero () {
        return accertamentoNumero;
    }

    public void setAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
    }

    public Integer getAccertamentoAnno () {
        return accertamentoAnno;
    }

    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

}
