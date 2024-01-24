/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile;

import java.io.Serializable;


public class RicercaRiferimentoContabileFiltroVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long idCodiceVersamento;

    private String descrizioneCodiceVersamento;

    private String descrizioneCompletaCodiceVersamentoSelezionato;

    private String codiceMacrotipo;

    private String codiceTematica;

    private String codiceVoceEntrata;

    private Boolean ancheRiferimentiNonInVita;

    private String descrizioneVoceEntrata;

    private Integer annoEsercizio;

    private Boolean multibeneficiarioCheckbox;

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @return the idCodiceVersamento
     */
    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    /**
     * @return the descrizioneCodiceVersamento
     */
    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    /**
     * @return the descrizioneCompletaCodiceVersamentoSelezionato
     */
    public String getDescrizioneCompletaCodiceVersamentoSelezionato () {
        return descrizioneCompletaCodiceVersamentoSelezionato;
    }

    /**
     * @return the codiceMacrotipo
     */
    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    /**
     * @return the codiceTematica
     */
    public String getCodiceTematica () {
        return codiceTematica;
    }

    /**
     * @return the codiceVoceEntrata
     */
    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    /**
     * @return the ancheRiferimentiNonInVita
     */
    public Boolean getAncheRiferimentiNonInVita () {
        return ancheRiferimentiNonInVita;
    }

    /**
     * @return the descrizioneVoceEntrata
     */
    public String getDescrizioneVoceEntrata () {
        return descrizioneVoceEntrata;
    }

    /**
     * @return the annoEsercizio
     */
    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    /**
     * @return the multibeneficiarioCheckbox
     */
    public Boolean getMultibeneficiarioCheckbox () {
        return multibeneficiarioCheckbox;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @param idCodiceVersamento the idCodiceVersamento to set
     */
    public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    /**
     * @param descrizioneCodiceVersamento the descrizioneCodiceVersamento to set
     */
    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    /**
     * @param descrizioneCompletaCodiceVersamentoSelezionato the descrizioneCompletaCodiceVersamentoSelezionato to set
     */
    public void setDescrizioneCompletaCodiceVersamentoSelezionato ( String descrizioneCompletaCodiceVersamentoSelezionato ) {
        this.descrizioneCompletaCodiceVersamentoSelezionato = descrizioneCompletaCodiceVersamentoSelezionato;
    }

    /**
     * @param codiceMacrotipo the codiceMacrotipo to set
     */
    public void setCodiceMacrotipo ( String codiceMacrotipo ) {
        this.codiceMacrotipo = codiceMacrotipo;
    }

    /**
     * @param codiceTematica the codiceTematica to set
     */
    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    /**
     * @param codiceVoceEntrata the codiceVoceEntrata to set
     */
    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    /**
     * @param ancheRiferimentiNonInVita the ancheRiferimentiNonInVita to set
     */
    public void setAncheRiferimentiNonInVita ( Boolean ancheRiferimentiNonInVita ) {
        this.ancheRiferimentiNonInVita = ancheRiferimentiNonInVita;
    }

    /**
     * @param descrizioneVoceEntrata the descrizioneVoceEntrata to set
     */
    public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
        this.descrizioneVoceEntrata = descrizioneVoceEntrata;
    }

    /**
     * @param annoEsercizio the annoEsercizio to set
     */
    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    /**
     * @param multibeneficiarioCheckbox the multibeneficiarioCheckbox to set
     */
    public void setMultibeneficiarioCheckbox ( Boolean multibeneficiarioCheckbox ) {
        this.multibeneficiarioCheckbox = multibeneficiarioCheckbox;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "RicercaRiferimentoContabileFiltroVO [id=" );
        builder.append ( id );
        builder.append ( ", idCodiceVersamento=" );
        builder.append ( idCodiceVersamento );
        builder.append ( ", descrizioneCodiceVersamento=" );
        builder.append ( descrizioneCodiceVersamento );
        builder.append ( ", descrizioneCompletaCodiceVersamentoSelezionato=" );
        builder.append ( descrizioneCompletaCodiceVersamentoSelezionato );
        builder.append ( ", codiceMacrotipo=" );
        builder.append ( codiceMacrotipo );
        builder.append ( ", codiceTematica=" );
        builder.append ( codiceTematica );
        builder.append ( ", codiceVoceEntrata=" );
        builder.append ( codiceVoceEntrata );
        builder.append ( ", ancheRiferimentiNonInVita=" );
        builder.append ( ancheRiferimentiNonInVita );
        builder.append ( ", descrizioneVoceEntrata=" );
        builder.append ( descrizioneVoceEntrata );
        builder.append ( ", annoEsercizio=" );
        builder.append ( annoEsercizio );
        builder.append ( ", multibeneficiarioCheckbox=" );
        builder.append ( multibeneficiarioCheckbox );
        builder.append ( "]" );
        return builder.toString ();
    }

}
