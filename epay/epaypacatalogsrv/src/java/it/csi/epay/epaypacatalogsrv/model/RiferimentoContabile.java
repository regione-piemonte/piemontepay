/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the epaycat_t_riferimento_contabile database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_riferimento_contabile" )
@NamedQuery ( name = "RiferimentoContabile.findAll", query = "SELECT r FROM RiferimentoContabile r" )
@SuppressWarnings ( "unused" )
public class RiferimentoContabile extends AbstractPropagableParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

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

    private String titolo;

    @Column ( name = "flag_annullato" )
    private Boolean flagAnnullato;

    @Column ( name = "flag_mb_secondario" )
    private Boolean flagMbSecondario;

    @Column ( name = "flag_mb_primario" )
    private Boolean flagMbPrimario;
    
    @Column( name = "codice_tributo")
    private String codiceTributo;

    private String categoria;

    private String tipologia;


	@Column ( name = "codice_tipologia_dato_specifico_riscossione" )
    private String codiceTipologiaDatoSpecificoRiscossione;

    //uni-directional many-to-one association to CodiceVersamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_codice_versamento" )
    private CodiceVersamento codiceVersamento;

    //uni-directional many-to-one association to Tassonomia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_tassonomia" , referencedColumnName = "id" )
    private Tassonomia tassonomia;

    @Column ( name = "chiave_intersistema" )
    private String chiaveIntersistema;

    //bi-directional many-to-one association to CodiceVersamento
    @OneToMany ( mappedBy = "riferimentoContabile" )
    private List<StoricoRiferimentoContabile> storico;

    @ManyToMany
    @JoinTable (
        name = "epaycat_r_riferimento_contabile_mb",
        joinColumns = { @JoinColumn ( name = "id_riferimento_contabile_primario", referencedColumnName = "id" ) },
        inverseJoinColumns = { @JoinColumn ( name = "id_riferimento_contabile_secondario", referencedColumnName = "id" ) } )
    List <RiferimentoContabile> riferimentiContabiliSecondariCollegati = new LinkedList<> ();


    public RiferimentoContabile () {
    }

    public List<StoricoRiferimentoContabile> getStorico () {
        return storico;
    }

    public void setStorico ( List<StoricoRiferimentoContabile> storico ) {
        this.storico = storico;
    }

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public Boolean getFlagAnnullato () {
        return flagAnnullato;
    }

    public void setFlagAnnullato ( Boolean flagAnnullato ) {
        this.flagAnnullato = flagAnnullato;
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

    public List<RiferimentoContabile> getRiferimentiContabiliSecondariCollegati() {
        return riferimentiContabiliSecondariCollegati;
    }

    public void setRiferimentiContabiliSecondariCollegati(
        List<RiferimentoContabile> riferimentiContabiliSecondariCollegati) {
        this.riferimentiContabiliSecondariCollegati = riferimentiContabiliSecondariCollegati;
    }
    /**
     * @return the tassonomia
     */
    public Tassonomia getTassonomia() {
        return tassonomia;
    }

    /**
     * @param tassonomia the tassonomia to set
     */
    public void setTassonomia(Tassonomia tassonomia) {
        this.tassonomia = tassonomia;
    }

    /**
     * @return the codiceTipologiaDatoSpecificoRiscossione
     */
    public String getCodiceTipologiaDatoSpecificoRiscossione() {
        return codiceTipologiaDatoSpecificoRiscossione;
    }

    /**
     * @param codiceTipologiaDatoSpecificoRiscossione the codiceTipologiaDatoSpecificoRiscossione to set
     */
    public void setCodiceTipologiaDatoSpecificoRiscossione(String codiceTipologiaDatoSpecificoRiscossione) {
        this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
    }

	
    public String getCodiceTributo () {
        return codiceTributo;
    }

    
    public void setCodiceTributo ( String codiceTributo ) {
        this.codiceTributo = codiceTributo;
    }

	@Override public String toString () {
		return "RiferimentoContabile{" +
						"id=" + id +
						", annoAccertamento=" + annoAccertamento +
						", annoEsercizio=" + annoEsercizio +
						", dataFineValidita=" + dataFineValidita +
						", dataInizioValidita=" + dataInizioValidita +
						", datoSpecificoRiscossione='" + datoSpecificoRiscossione + '\'' +
						", descrizioneDatoSpecificoRiscossione='" + descrizioneDatoSpecificoRiscossione + '\'' +
						", livelloPdc='" + livelloPdc + '\'' +
						", numeroAccertamento=" + numeroAccertamento +
						", numeroArticolo=" + numeroArticolo +
						", numeroCapitolo='" + numeroCapitolo + '\'' +
						", titolo='" + titolo + '\'' +
						", flagAnnullato=" + flagAnnullato +
						", flagMbSecondario=" + flagMbSecondario +
						", flagMbPrimario=" + flagMbPrimario +
						", codiceTributo='" + codiceTributo + '\'' +
						", categoria='" + categoria + '\'' +
						", tipologia='" + tipologia + '\'' +
						", codiceTipologiaDatoSpecificoRiscossione='" + codiceTipologiaDatoSpecificoRiscossione + '\'' +
						", codiceVersamento=" + codiceVersamento +
						", tassonomia=" + tassonomia +
						", chiaveIntersistema='" + chiaveIntersistema + '\'' +
						", storico=" + storico +
						", riferimentiContabiliSecondariCollegati=" + riferimentiContabiliSecondariCollegati +
						'}';
	}
}
