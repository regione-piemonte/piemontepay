/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_flusso_sintesi database table.
 *
 */
@Entity
@Table ( name = "cbl_t_flusso_sintesi", schema="epaymodric" )
@NamedQuery ( name = "CblTFlussoSintesi.findAll", query = "SELECT c FROM CblTFlussoSintesi c" )
public class CblTFlussoSintesi extends DatiTecniciParentEntity implements Serializable {

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CblTFlussoSintesi other = (CblTFlussoSintesi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "CBL_T_FLUSSO_SINTESI_ID_GENERATOR", sequenceName="epaymodric.CBL_T_FLUSSO_SINTESI_ID_SEQ", schema="epaymodric", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CBL_T_FLUSSO_SINTESI_ID_GENERATOR" )
    private Long id;

    @Column ( name = "codice_versamento" )
    private String codiceVersamento;

    @Column ( name = "dati_specifici_di_riscossione" )
    private String datiSpecificiDiRiscossione;

    @Column ( name = "importo_quota_aggregazione" )
    private BigDecimal importoQuotaAggregazione;

    @Column ( name = "numero_vers_quota_aggregazione" )
    private BigDecimal numeroVersQuotaAggregazione;

    @Column ( name = "piano_dei_conti" )
    private String pianoDeiConti;

    @Column ( name = "provvisorio_anno" )
    private Integer provvisorioAnno;

    @Column ( name = "provvisorio_nro" )
    private Integer provvisorioNro;

    //bi-directional many-to-one association to CblTFlussoDettaglio
    @OneToMany ( mappedBy = "cblTFlussoSintesi" )
    private List<CblTFlussoDettaglio> cblTFlussoDettaglios;

    //bi-directional many-to-one association to CblRStatoFlussoErrore
    @ManyToOne
    @JoinColumn ( name = "id_errore" )
    private CblRStatoFlussoErrore cblRStatoFlussoErrore;

    //bi-directional many-to-one association to CblTEnte
    @ManyToOne
    @JoinColumn ( name = "id_istituto_ricevente" )
    private CblTEnte cblTEnte;

    //bi-directional many-to-one association to CblTFlussoOrigine
    @ManyToOne
    @JoinColumn ( name = "id_flusso_origine" )
    private CblTFlussoOrigine cblTFlussoOrigine;


    @Column(name="articolo")
    private Integer articolo;

    @Column(name="accertamento_numero")
    private Integer accertamentoNumero;

    @Column(name="accertamento_anno")
    private Integer accertamentoAnno;

    @Column(name="descrizione_codice_versamento")
    private String descrizioneCodiceVersamento;

    @Column ( name = "capitolo" )
    private String capitolo;

    @Column ( name = "data_pagamento_calcolata" )
    private Date dataPagamentoCalcolata;
    
//  //bi-directional many-to-one association to CblTCodiceVersamento
//    @ManyToOne
//    @JoinColumns({
//        @JoinColumn ( name = "codice_versamento", referencedColumnName = "codice_versamento", insertable = false, updatable = false ),
//        @JoinColumn ( name = "id_istituto_ricevente", referencedColumnName = "id_ente", insertable = false, updatable = false )
//        })
//    private CblTCodiceVersamento cblTCodiceVersamento;
    
//    @ManyToMany 
//    @JoinTable ( name = "cbl_t_ente",
//                    joinColumns = @JoinColumn ( name = "id", referencedColumnName = "id_istituto_ricevente" ),
//                    inverseJoinColumns = @JoinColumn ( name = "id_ente", referencedColumnName = "id_ente" ) )
//    @JoinColumn ( name = "codice_versamento", referencedColumnName = "codice_versamento", insertable = false, updatable = false )
//    private List<CblTCodiceVersamento> codiciVersamento;

    
//    @ManyToMany ( targetEntity = EpayDAutorizzazioneChiamante.class )
//    @JoinTable ( name = "epay_r_chiamante_autorizzazione_chiamante",
//                    joinColumns = @JoinColumn ( name = "codice_chiamante", referencedColumnName = "codice_chiamante" ),
//                    inverseJoinColumns = @JoinColumn ( name = "codice_autorizzazione_chiamante", referencedColumnName = "codice" ) )
//    private List<EpayDAutorizzazioneChiamante> autorizzazioni;


    public CblTFlussoSintesi () {
    }

    public Integer getAccertamentoAnno () {
        return accertamentoAnno;
    }

    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public Integer getAccertamentoNumero () {
        return accertamentoNumero;
    }

    public void setAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
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

    public List<CblTFlussoDettaglio> getCblTFlussoDettaglios () {
        return cblTFlussoDettaglios;
    }

    public void setCblTFlussoDettaglios ( List<CblTFlussoDettaglio> cblTFlussoDettaglios ) {
        this.cblTFlussoDettaglios = cblTFlussoDettaglios;
    }

    public CblTFlussoDettaglio addCblTFlussoDettaglio ( CblTFlussoDettaglio cblTFlussoDettaglio ) {
        getCblTFlussoDettaglios ().add ( cblTFlussoDettaglio );
        cblTFlussoDettaglio.setCblTFlussoSintesi ( this );

        return cblTFlussoDettaglio;
    }

    public CblTFlussoDettaglio removeCblTFlussoDettaglio ( CblTFlussoDettaglio cblTFlussoDettaglio ) {
        getCblTFlussoDettaglios ().remove ( cblTFlussoDettaglio );
        cblTFlussoDettaglio.setCblTFlussoSintesi ( null );

        return cblTFlussoDettaglio;
    }

    public CblRStatoFlussoErrore getCblRStatoFlussoErrore () {
        return cblRStatoFlussoErrore;
    }

    public void setCblRStatoFlussoErrore ( CblRStatoFlussoErrore cblRStatoFlussoErrore ) {
        this.cblRStatoFlussoErrore = cblRStatoFlussoErrore;
    }

    public CblTEnte getCblTEnte () {
        return cblTEnte;
    }

    public void setCblTEnte ( CblTEnte cblTEnte ) {
        this.cblTEnte = cblTEnte;
    }

    public CblTFlussoOrigine getCblTFlussoOrigine () {
        return cblTFlussoOrigine;
    }

    public void setCblTFlussoOrigine ( CblTFlussoOrigine cblTFlussoOrigine ) {
        this.cblTFlussoOrigine = cblTFlussoOrigine;
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



    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento(String descrizioneCodiceVersamento) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public Date getDataPagamentoCalcolata () {
        return dataPagamentoCalcolata;
    }

    public void setDataPagamentoCalcolata ( Date dataPagamentoCalcolata ) {
        this.dataPagamentoCalcolata = dataPagamentoCalcolata;
    }

//	public List<CblTCodiceVersamento> getCodiciVersamento() {
//		return codiciVersamento;
//	}
//
//	public void setCodiciVersamento(List<CblTCodiceVersamento> codiciVersamento) {
//		this.codiciVersamento = codiciVersamento;
//	}
    
    

//	public CblTCodiceVersamento getCblTCodiceVersamento() {
//		return cblTCodiceVersamento;
//	}
//
//	public void setCblTCodiceVersamento(CblTCodiceVersamento cblTCodiceVersamento) {
//		this.cblTCodiceVersamento = cblTCodiceVersamento;
//	}
    
    

}
