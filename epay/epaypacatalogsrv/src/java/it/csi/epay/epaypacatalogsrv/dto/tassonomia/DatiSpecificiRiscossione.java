/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;
import java.io.Serializable;


public class DatiSpecificiRiscossione implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codice;

    private String descrizione;

    private Integer annoEsercizio;
    
    private Integer annoAccertamento;
    
    private String numeroAccertamento;
    
    private String codiceTributo;

    /**
     * @return the codice
     */
    public String getCodice () {
        return codice;
    }

    /**
     * @param codice the codice to set
     */
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione () {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    
    /**
     * @return the annoEsercizio
     */
    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    
    /**
     * @param annoEsercizio the annoEsercizio to set
     */
    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    
    /**
     * @return the annoAccertamento
     */
    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    
    /**
     * @param annoAccertamento the annoAccertamento to set
     */
    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    
    /**
     * @return the numeroAccertamento
     */
    public String getNumeroAccertamento () {
        return numeroAccertamento;
    }

    
    /**
     * @param numeroAccertamento the numeroAccertamento to set
     */
    public void setNumeroAccertamento ( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    
    /**
     * @return the codiceTributo
     */
    public String getCodiceTributo () {
        return codiceTributo;
    }

    
    /**
     * @param codiceTributo the codiceTributo to set
     */
    public void setCodiceTributo ( String codiceTributo ) {
        this.codiceTributo = codiceTributo;
    }

    
    

}
