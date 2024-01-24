/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoStoricoFlussoSintesi" )
public class DTOStoricoFlussoSintesi extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer accertamentoAnno;

    private Integer accertamentoNro;

    private Integer articolo;

    private String capitolo;

    private String codiceVersamento;

    private String datiSpecificiRiscossione;

    private String descrizioneCodiceVersamento;

    private Long idErrore;

    private Long idFlussoOrigine;

    private Long idIstitutoRicevente;

    private String identificativoFlusso;

    private BigDecimal importoQuotaAggregazione;

    private BigDecimal numeroVersQuotaAggregazione;

    private String pianoDeiConti;

    private Integer provvisorioAnno;

    private Integer provvisorioNro;

    public DTOStoricoFlussoSintesi () {

    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAccertamentoAnno () {
        return accertamentoAnno;
    }

    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public Integer getAccertamentoNro () {
        return accertamentoNro;
    }

    public void setAccertamentoNro ( Integer accertamentoNro ) {
        this.accertamentoNro = accertamentoNro;
    }

    public Integer getArticolo () {
        return articolo;
    }

    public void setArticolo ( Integer articolo ) {
        this.articolo = articolo;
    }

    public String getCapitolo () {
        return capitolo;
    }

    public void setCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
    }


    public String getCodiceVersamento () {
        return codiceVersamento;
    }


    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }


    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }


    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public Long getIdErrore () {
        return idErrore;
    }

    public void setIdErrore ( Long idErrore ) {
        this.idErrore = idErrore;
    }

    public Long getIdFlussoOrigine () {
        return idFlussoOrigine;
    }

    public void setIdFlussoOrigine ( Long idFlussoOrigine ) {
        this.idFlussoOrigine = idFlussoOrigine;
    }

    public Long getIdIstitutoRicevente () {
        return idIstitutoRicevente;
    }

    public void setIdIstitutoRicevente ( Long idIstitutoRicevente ) {
        this.idIstitutoRicevente = idIstitutoRicevente;
    }


    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }


    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public BigDecimal getImportoQuotaAggregazione () {
        return importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione ( BigDecimal importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public BigDecimal getNumeroVersQuotaAggregazione () {
        return numeroVersQuotaAggregazione;
    }

    public void setNumeroVersQuotaAggregazione ( BigDecimal numeroVersQuotaAggregazione ) {
        this.numeroVersQuotaAggregazione = numeroVersQuotaAggregazione;
    }


    public String getPianoDeiConti () {
        return pianoDeiConti;
    }


    public void setPianoDeiConti ( String pianoDeiConti ) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public Integer getProvvisorioAnno () {
        return provvisorioAnno;
    }

    public void setProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public Integer getProvvisorioNro () {
        return provvisorioNro;
    }

    public void setProvvisorioNro ( Integer provvisorioNro ) {
        this.provvisorioNro = provvisorioNro;
    }



}
