/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epaycat_t_storico_riferimento_contabile database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_storico_riferimento_contabile" )
@NamedQuery ( name = "StoricoRiferimentoContabile.findAll", query = "SELECT r FROM StoricoRiferimentoContabile r" )
public class StoricoRiferimentoContabile extends AbstractPropagableParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_riferimento_contabile" )
    private RiferimentoContabile riferimentoContabile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_storico_riferimento_contabile" )
    private StoricoRiferimentoContabile storicoRiferimentoContabile;

    @Column ( name = "anno_accertamento" )
    private Integer annoAccertamento;

    @Column ( name = "anno_esercizio" )
    private Integer annoEsercizio;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "data_fine_validita" )
    private Date dataFineValidita;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "data_inizio_validita" )
    private Date dataInizioValidita;

    @Column ( name = "dato_specifico_riscossione" )
    private String datoSpecificoRiscossione;

    @Column ( name = "descrizione_dato_specifico_riscossione" )
    private String descrizioneDatoSpecificoRiscossione;

    @Column ( name = "livello_pdc" )
    private String livelloPdc;

    @Column ( name = "numero_accertamento" )
    private Integer numeroAccertamento;

    @Column ( name = "numero_articolo" )
    private Integer numeroArticolo;

    @Column ( name = "capitolo" )
    private String numeroCapitolo;
    
    @Column ( name = "flag_mb_secondario" )
    private Boolean flagMbSecondario;
    
    @Column ( name = "flag_mb_primario" )
    private Boolean flagMbPrimario;


    private String titolo;

    private String categoria;

    private String tipologia;

    //uni-directional many-to-one association to TipologiaDatoSpecificoRiscossione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_tipologia_dato_specifico_riscossione", referencedColumnName = "codice" )
    private TipologiaDatoSpecificoRiscossione tipologiaDatoSpecificoRiscossione;

    //uni-directional many-to-one association to CodiceVersamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_codice_versamento" )
    private CodiceVersamento codiceVersamento;

    @Column ( name = "chiave_intersistema" )
    private String chiaveIntersistema;

    @Column ( name = "flag_posizione_precedente" )
    private Boolean flagPosizionePrecedente;

    @OneToMany ( mappedBy = "storicoRiferimentoContabile" )
    private List<StoricoRiferimentoContabile> storico;

    public StoricoRiferimentoContabile () {
    }

    public List<StoricoRiferimentoContabile> getStorico () {
        return storico;
    }

    public void setStorico ( List<StoricoRiferimentoContabile> storico ) {
        this.storico = storico;
    }

    public StoricoRiferimentoContabile getStoricoRiferimentoContabile () {
        return storicoRiferimentoContabile;
    }

    public void setStoricoRiferimentoContabile ( StoricoRiferimentoContabile storicoRiferimentoContabile ) {
        this.storicoRiferimentoContabile = storicoRiferimentoContabile;
    }

    public Boolean getFlagPosizionePrecedente () {
        return flagPosizionePrecedente;
    }

    public void setFlagPosizionePrecedente ( Boolean flagPosizionePrecedente ) {
        this.flagPosizionePrecedente = flagPosizionePrecedente;
    }

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public RiferimentoContabile getRiferimentoContabile () {
        return riferimentoContabile;
    }

    public void setRiferimentoContabile ( RiferimentoContabile riferimentoContabile ) {
        this.riferimentoContabile = riferimentoContabile;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDatoSpecificoRiscossione () {
        return datoSpecificoRiscossione;
    }

    public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
        this.datoSpecificoRiscossione = datoSpecificoRiscossione;
    }

    public String getDescrizioneDatoSpecificoRiscossione () {
        return descrizioneDatoSpecificoRiscossione;
    }

    public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
        this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
    }

    public String getLivelloPdc () {
        return livelloPdc;
    }

    public void setLivelloPdc ( String livelloPdc ) {
        this.livelloPdc = livelloPdc;
    }

    public Integer getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( Integer numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public Integer getNumeroArticolo () {
        return numeroArticolo;
    }

    public void setNumeroArticolo ( Integer numeroArticolo ) {
        this.numeroArticolo = numeroArticolo;
    }

    public String getNumeroCapitolo () {
        return numeroCapitolo;
    }

    public void setNumeroCapitolo ( String numeroCapitolo ) {
        this.numeroCapitolo = numeroCapitolo;
    }

    public String getTitolo () {
        return titolo;
    }

    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

    public String getCategoria () {
        return categoria;
    }

    public void setCategoria ( String categoria ) {
        this.categoria = categoria;
    }

    public TipologiaDatoSpecificoRiscossione getTipologiaDatoSpecificoRiscossione () {
        return tipologiaDatoSpecificoRiscossione;
    }

    public void setTipologiaDatoSpecificoRiscossione ( TipologiaDatoSpecificoRiscossione tipologiaDatoSpecificoRiscossione ) {
        this.tipologiaDatoSpecificoRiscossione = tipologiaDatoSpecificoRiscossione;
    }

    public String getTipologia () {
        return tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    public CodiceVersamento getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( CodiceVersamento codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	@Override
	public String toString() {
		return "StoricoRiferimentoContabile [id=" + id + "]";
	}
    
    

}
