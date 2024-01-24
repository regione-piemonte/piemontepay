/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epaycat_t_tassonomia database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_tassonomia" )
@NamedQuery ( name = "Tassonomia.findAll", query = "SELECT t FROM Tassonomia t" )
public class Tassonomia extends AbstractChangeTrackedParentEntity implements Serializable {

    private static final long serialVersionUID = 7649953105685316533L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column ( name = "cod_tipo_ente_creditore" )
    private String codTipoEnteCreditore;

    @Column ( name = "tipo_ente_creditore" )
    private String tipoEnteCreditore;

    @Column ( name = "macro_area" )
    private String macroArea;

    @Column ( name = "nome_macro_area" )
    private String nomeMacroArea;

    @Column ( name = "descr_macro_area" )
    private String descrMacroArea;

    @Column ( name = "cod_tipologia_servizio" )
    private String codTipologiaServizio;

    @Column ( name = "tipo_servizio" )
    private String tipoServizio;

    @Column ( name = "motivo_giuridico_riscossione" )
    private String motivoGiuridicoRiscossione;

    @Column ( name = "descr_tipo_servizio" )
    private String descrTipoServizio;

    @Column ( name = "n_versione_tassonomia" )
    private String nVersioneTassonomia;

    @Column ( name = "dati_specifici_incasso" )
    private String datiSpecificiIncasso;

    @Temporal(TemporalType.TIMESTAMP)
    @Column ( name = "data_inizio_validita" )
    private Date dataInizioValidita;

    @Temporal(TemporalType.DATE)
    @Column ( name = "data_fine_validita" )
    private Date dataFineValidita;

    @Column ( name = "flag_aggiornato" )
    private Boolean flagAggiornato;

    @Column ( name = "flag_cancellato" )
    private Boolean flagCancellato;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "data_cancellazione" )
    private Date dataCancellazione;

    public Tassonomia () {
        // placeholder comment
    }



    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }



    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }



    /**
     * @return the codTipoEnteCreditore
     */
    public String getCodTipoEnteCreditore() {
        return codTipoEnteCreditore;
    }



    /**
     * @param codTipoEnteCreditore the codTipoEnteCreditore to set
     */
    public void setCodTipoEnteCreditore(String codTipoEnteCreditore) {
        this.codTipoEnteCreditore = codTipoEnteCreditore;
    }



    /**
     * @return the tipoEnteCreditore
     */
    public String getTipoEnteCreditore() {
        return tipoEnteCreditore;
    }



    /**
     * @param tipoEnteCreditore the tipoEnteCreditore to set
     */
    public void setTipoEnteCreditore(String tipoEnteCreditore) {
        this.tipoEnteCreditore = tipoEnteCreditore;
    }


    /**
     * @return the macroArea
     */
    public String getMacroArea() {
        return macroArea;
    }



    /**
     * @param macroArea the macroArea to set
     */
    public void setMacroArea(String macroArea) {
        this.macroArea = macroArea;
    }



    /**
     * @return the nomeMacroArea
     */
    public String getNomeMacroArea() {
        return nomeMacroArea;
    }



    /**
     * @param nomeMacroArea the nomeMacroArea to set
     */
    public void setNomeMacroArea(String nomeMacroArea) {
        this.nomeMacroArea = nomeMacroArea;
    }



    /**
     * @return the descrMacroArea
     */
    public String getDescrMacroArea() {
        return descrMacroArea;
    }



    /**
     * @param descrMacroArea the descrMacroArea to set
     */
    public void setDescrMacroArea(String descrMacroArea) {
        this.descrMacroArea = descrMacroArea;
    }



    /**
     * @return the codTipologiaServizio
     */
    public String getCodTipologiaServizio() {
        return codTipologiaServizio;
    }



    /**
     * @param codTipologiaServizio the codTipologiaServizio to set
     */
    public void setCodTipologiaServizio(String codTipologiaServizio) {
        this.codTipologiaServizio = codTipologiaServizio;
    }



    /**
     * @return the tipoServizio
     */
    public String getTipoServizio() {
        return tipoServizio;
    }



    /**
     * @param tipoServizio the tipoServizio to set
     */
    public void setTipoServizio(String tipoServizio) {
        this.tipoServizio = tipoServizio;
    }



    /**
     * @return the motivoGiuridicoRiscossione
     */
    public String getMotivoGiuridicoRiscossione() {
        return motivoGiuridicoRiscossione;
    }



    /**
     * @param motivoGiuridicoRiscossione the motivoGiuridicoRiscossione to set
     */
    public void setMotivoGiuridicoRiscossione(String motivoGiuridicoRiscossione) {
        this.motivoGiuridicoRiscossione = motivoGiuridicoRiscossione;
    }



    /**
     * @return the descrTipoServizio
     */
    public String getDescrTipoServizio() {
        return descrTipoServizio;
    }



    /**
     * @param descrTipoServizio the descrTipoServizio to set
     */
    public void setDescrTipoServizio(String descrTipoServizio) {
        this.descrTipoServizio = descrTipoServizio;
    }



    /**
     * @return the nVersioneTassonomia
     */
    public String getnVersioneTassonomia() {
        return nVersioneTassonomia;
    }



    /**
     * @param nVersioneTassonomia the nVersioneTassonomia to set
     */
    public void setnVersioneTassonomia(String nVersioneTassonomia) {
        this.nVersioneTassonomia = nVersioneTassonomia;
    }



    /**
     * @return the datiSpecificiIncasso
     */
    public String getDatiSpecificiIncasso() {
        return datiSpecificiIncasso;
    }



    /**
     * @param datiSpecificiIncasso the datiSpecificiIncasso to set
     */
    public void setDatiSpecificiIncasso(String datiSpecificiIncasso) {
        this.datiSpecificiIncasso = datiSpecificiIncasso;
    }



    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }



    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita(Date dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }



    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita() {
        return dataFineValidita;
    }



    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita(Date dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }



    /**
     * @return the flagAggiornato
     */
    public Boolean getFlagAggiornato() {
        return flagAggiornato;
    }



    /**
     * @param flagAggiornato the flagAggiornato to set
     */
    public void setFlagAggiornato(Boolean flagAggiornato) {
        this.flagAggiornato = flagAggiornato;
    }


    public Boolean getFlagCancellato () {
        return flagCancellato;
    }

    public void setFlagCancellato ( Boolean flagCancellato ) {
        this.flagCancellato = flagCancellato;
    }

    public Date getDataCancellazione () {
        return dataCancellazione;
    }

    public void setDataCancellazione ( Date dataCancellazione ) {
        this.dataCancellazione = dataCancellazione;
    }



	@Override
	public String toString() {
		return "Tassonomia [id=" + id + "]";
	}

}
