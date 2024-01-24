/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;

/**
 *
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsSalvaListaDiCarico" )
public class DTOInputWsSalvaListaDiCarico extends DTOInputBase {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String codiceVersamento;

    private String datiSpecificiRiscossione;

    private Integer annoEsercizio;

    private Integer accertamentoAnno;

    private Integer accertamentoNro;

    private String descrizioneCausale;

    private Integer rifPosizioneDebitoria;

    private BigDecimal importoVersamento;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    private String capitolo;

    private Integer articolo;

    private String pianoDeiConti;

    public DTOInputWsSalvaListaDiCarico () {

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

    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
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

    public String getDescrizioneCausale () {
        return descrizioneCausale;
    }

    public void setDescrizioneCausale ( String descrizioneCausale ) {
        this.descrizioneCausale = descrizioneCausale;
    }

    public Integer getRifPosizioneDebitoria () {
        return rifPosizioneDebitoria;
    }

    public void setRifPosizioneDebitoria ( Integer rifPosizioneDebitoria ) {
        this.rifPosizioneDebitoria = rifPosizioneDebitoria;
    }

    public BigDecimal getImportoVersamento () {
        return importoVersamento;
    }

    public void setImportoVersamento ( BigDecimal importoVersamento ) {
        this.importoVersamento = importoVersamento;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }


    public String getCapitolo () {
        return capitolo;
    }


    public void setCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
    }


    public Integer getArticolo () {
        return articolo;
    }


    public void setArticolo ( Integer articolo ) {
        this.articolo = articolo;
    }

    public String getPianoDeiConti () {
        return pianoDeiConti;
    }

    public void setPianoDeiConti ( String pianoDeiConti ) {
        this.pianoDeiConti = pianoDeiConti;
    }


}
