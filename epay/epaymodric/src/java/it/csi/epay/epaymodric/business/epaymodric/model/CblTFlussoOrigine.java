/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the cbl_t_flusso_origine database table.
 *
 */
@Entity
@Table ( name = "cbl_t_flusso_origine", schema="epaymodric" )
@NamedQuery ( name = "CblTFlussoOrigine.findAll", query = "SELECT c FROM CblTFlussoOrigine c" )
public class CblTFlussoOrigine extends DatiTecniciParentEntity implements Serializable {

	/* necessaria per i metodi di audit */
	@Override
	public String getPrimaryKeyRepresentation () {
		if ( id == null ) {
			return "null";
		} else {
			return id.toString ();
		}
	}


	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator ( name = "CBL_T_FLUSSO_ORIGINE_ID_GENERATOR", sequenceName="epaymodric.CBL_T_FLUSSO_ORIGINE_ID_SEQ", schema="epaymodric", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CBL_T_FLUSSO_ORIGINE_ID_GENERATOR" )
	private Long id;


	@Column(name="contatore_tentativi")
	private Integer contatoreTentativi;

	@Column(name="dataora_flusso")
	private Timestamp dataoraFlusso;

	@Column(name="identificativo_flusso")
	private String identificativoFlusso;

	@Column(name="identificativo_istituto_ricevente")
	private String identificativoIstitutoRicevente;

	//bi-directional many-to-one association to CblTPsp
	@ManyToOne
	@JoinColumn(name="identificativo_psp", referencedColumnName="identificativo_psp")
	private CblTPsp cblTPsp;

	//bi-directional many-to-one association to CblTFlussoSintesi
	@OneToMany(mappedBy="cblTFlussoOrigine")
	private List<CblTFlussoSintesi> cblTFlussoSintesis;

	//bi-directional many-to-one association to CblRStatoFlussoErrore
	@OneToMany(mappedBy="cblTFlussoOrigine")
	private List<CblRStatoFlussoErrore> cblRStatoFlussoErrores;

	@Column(name="importo_totale_pagamenti")
	private BigDecimal importoTotalePagamenti;

    @Column ( name = "importo_totale_pagamenti_intermediati" )
    private BigDecimal importoTotalePagamentiIntermediati;

    @Column ( name = "importo_totale_pagamenti_sconosciuti" )
    private BigDecimal importoTotalePagamentiSconosciuti;

    @Column ( name = "importo_totale_pagamenti_non_intermediati" )
    private BigDecimal importoTotalePagamentiNonIntermediati;

    @Column ( name = "numero_totale_pagamenti" )
    private Integer numeroTotalePagamenti;

    @Column ( name = "numero_totale_pagamenti_intermediati" )
    private Integer numeroTotalePagamentiIntermediati;

    @Column ( name = "numero_totale_sconosciuti" )
    private Integer numeroTotalePagamentiSconosciuti;

    @Column ( name = "numero_totale_pagamenti_non_intermediati" )
    private Integer numeroTotalePagamentiNonIntermediati;

    @Column ( name = "xml_flusso" )
    private String xmlFlusso;

	@Column ( name = "data_regolamento" )
	private Date dataRegolamento;

	@Column ( name = "identificativo_univoco_regolamento" )
	private String identificativoUnivocoRegolamento;
	    
	@Column(name="flag_flusso_intermediato")
    private Boolean flagFlussoIntermediato;

	//bi-directional many-to-one association to CblDStatoFlusso
	@ManyToOne
	@JoinColumn(name="id_stato_flusso")
	private CblDStatoFlusso cblDStatoFlusso;

	//bi-directional many-to-one association to CblTElaborazione
	@ManyToOne
	@JoinColumn(name="id_elaborazione")
	private CblTElaborazione cblTElaborazione;

	//bi-directional many-to-one association to CblTEnte
	@ManyToOne
	@JoinColumn(name="id_istituto_ricevente")
	private CblTEnte cblTEnte;

	//bi-directional many-to-one association to CblDTipoAcquisizione
	@ManyToOne
	@JoinColumn ( name = "tipo_acquisizione" )
	private CblDTipoAcquisizione cblDTipoAcquisizione;

	public CblTFlussoOrigine() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getContatoreTentativi() {
		return this.contatoreTentativi;
	}

	public void setContatoreTentativi(Integer contatoreTentativi) {
		this.contatoreTentativi = contatoreTentativi;
	}

	@Override
	public Timestamp getDataInserimento() {
		return this.dataInserimento;
	}

	@Override
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Timestamp getDataoraFlusso() {
		return this.dataoraFlusso;
	}

	public void setDataoraFlusso(Timestamp dataoraFlusso) {
		this.dataoraFlusso = dataoraFlusso;
	}

	public String getIdentificativoFlusso() {
		return this.identificativoFlusso;
	}

	public void setIdentificativoFlusso(String identificativoFlusso) {
		this.identificativoFlusso = identificativoFlusso;
	}

	public String getIdentificativoIstitutoRicevente() {
		return this.identificativoIstitutoRicevente;
	}

	public void setIdentificativoIstitutoRicevente(String identificativoIstitutoRicevente) {
		this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
	}

	public BigDecimal getImportoTotalePagamenti() {
		return this.importoTotalePagamenti;
	}

	public void setImportoTotalePagamenti(BigDecimal importoTotalePagamenti) {
		this.importoTotalePagamenti = importoTotalePagamenti;
	}

	public BigDecimal getImportoTotalePagamentiIntermediati() {
		return this.importoTotalePagamentiIntermediati;
	}

	public void setImportoTotalePagamentiIntermediati(BigDecimal importoTotalePagamentiIntermediati) {
		this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
	}

	public Integer getNumeroTotalePagamenti() {
		return this.numeroTotalePagamenti;
	}

	public void setNumeroTotalePagamenti(Integer numeroTotalePagamenti) {
		this.numeroTotalePagamenti = numeroTotalePagamenti;
	}

	public Integer getNumeroTotalePagamentiIntermediati() {
		return this.numeroTotalePagamentiIntermediati;
	}

	public void setNumeroTotalePagamentiIntermediati(Integer numeroTotalePagamentiIntermediati) {
		this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
	}

	public String getXmlFlusso() {
		return this.xmlFlusso;
	}

	public void setXmlFlusso(String xmlFlusso) {
		this.xmlFlusso = xmlFlusso;
	}

	public List<CblRStatoFlussoErrore> getCblRStatoFlussoErrores() {
		return this.cblRStatoFlussoErrores;
	}

	public void setCblRStatoFlussoErrores(List<CblRStatoFlussoErrore> cblRStatoFlussoErrores) {
		this.cblRStatoFlussoErrores = cblRStatoFlussoErrores;
	}

	public CblRStatoFlussoErrore addCblRStatoFlussoErrore(CblRStatoFlussoErrore cblRStatoFlussoErrore) {
		getCblRStatoFlussoErrores().add(cblRStatoFlussoErrore);
		cblRStatoFlussoErrore.setCblTFlussoOrigine(this);

		return cblRStatoFlussoErrore;
	}

	public CblRStatoFlussoErrore removeCblRStatoFlussoErrore(CblRStatoFlussoErrore cblRStatoFlussoErrore) {
		getCblRStatoFlussoErrores().remove(cblRStatoFlussoErrore);
		cblRStatoFlussoErrore.setCblTFlussoOrigine(null);

		return cblRStatoFlussoErrore;
	}

	public CblDStatoFlusso getCblDStatoFlusso() {
		return this.cblDStatoFlusso;
	}

	public void setCblDStatoFlusso(CblDStatoFlusso cblDStatoFlusso) {
		this.cblDStatoFlusso = cblDStatoFlusso;
	}

	public CblTElaborazione getCblTElaborazione() {
		return this.cblTElaborazione;
	}

	public void setCblTElaborazione(CblTElaborazione cblTElaborazione) {
		this.cblTElaborazione = cblTElaborazione;
	}

	public CblTEnte getCblTEnte() {
		return this.cblTEnte;
	}

	public void setCblTEnte(CblTEnte cblTEnte) {
		this.cblTEnte = cblTEnte;
	}

	public Date getDataRegolamento () {
		return dataRegolamento;
	}

	public void setDataRegolamento ( Date dataRegolamento ) {
		this.dataRegolamento = dataRegolamento;
	}



	public CblTPsp getCblTPsp() {
		return this.cblTPsp;
	}

	public void setCblTPsp(CblTPsp cblTPsp) {
		this.cblTPsp = cblTPsp;
	}

	public List<CblTFlussoSintesi> getCblTFlussoSintesis() {
		return this.cblTFlussoSintesis;
	}

	public void setCblTFlussoSintesis(List<CblTFlussoSintesi> cblTFlussoSintesis) {
		this.cblTFlussoSintesis = cblTFlussoSintesis;
	}

	public CblTFlussoSintesi addCblTFlussoSintesi(CblTFlussoSintesi cblTFlussoSintesi) {
		getCblTFlussoSintesis().add(cblTFlussoSintesi);
		cblTFlussoSintesi.setCblTFlussoOrigine(this);

		return cblTFlussoSintesi;
	}

	public CblTFlussoSintesi removeCblTFlussoSintesi(CblTFlussoSintesi cblTFlussoSintesi) {
		getCblTFlussoSintesis().remove(cblTFlussoSintesi);
		cblTFlussoSintesi.setCblTFlussoOrigine(null);

		return cblTFlussoSintesi;
	}

	public CblDTipoAcquisizione getCblDTipoAcquisizione () {
		return this.cblDTipoAcquisizione;
	}

	public void setCblDTipoAcquisizione ( CblDTipoAcquisizione cblDTipoAcquisizione ) {
		this.cblDTipoAcquisizione = cblDTipoAcquisizione;
	}

	public Boolean getFlagFlussoIntermediato() {
		return flagFlussoIntermediato;
	}

	public void setFlagFlussoIntermediato(Boolean flagFlussoIntermediato) {
		this.flagFlussoIntermediato = flagFlussoIntermediato;
	}

	public String getIdentificativoUnivocoRegolamento () {
		return identificativoUnivocoRegolamento;
	}

	public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
		this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
	}

    /**
     * @return the importoTotalePagamentiSconosciuti
     */
    public BigDecimal getImportoTotalePagamentiSconosciuti () {
        return importoTotalePagamentiSconosciuti;
    }

    /**
     * @param importoTotalePagamentiSconosciuti the importoTotalePagamentiSconosciuti to set
     */
    public void setImportoTotalePagamentiSconosciuti ( BigDecimal importoTotalePagamentiSconosciuti ) {
        this.importoTotalePagamentiSconosciuti = importoTotalePagamentiSconosciuti;
    }

    /**
     * @return the importoTotalePagamentiNonIntermediati
     */
    public BigDecimal getImportoTotalePagamentiNonIntermediati () {
        return importoTotalePagamentiNonIntermediati;
    }

    /**
     * @param importoTotalePagamentiNonIntermediati the importoTotalePagamentiNonIntermediati to set
     */
    public void setImportoTotalePagamentiNonIntermediati ( BigDecimal importoTotalePagamentiNonIntermediati ) {
        this.importoTotalePagamentiNonIntermediati = importoTotalePagamentiNonIntermediati;
    }

    /**
     * @return the numeroTotalePagamentiSconosciuti
     */
    public Integer getNumeroTotalePagamentiSconosciuti () {
        return numeroTotalePagamentiSconosciuti;
    }

    /**
     * @param numeroTotalePagamentiSconosciuti the numeroTotalePagamentiSconosciuti to set
     */
    public void setNumeroTotalePagamentiSconosciuti ( Integer numeroTotalePagamentiSconosciuti ) {
        this.numeroTotalePagamentiSconosciuti = numeroTotalePagamentiSconosciuti;
    }

    /**
     * @return the numeroTotalePagamentiNonIntermediati
     */
    public Integer getNumeroTotalePagamentiNonIntermediati () {
        return numeroTotalePagamentiNonIntermediati;
    }

    /**
     * @param numeroTotalePagamentiNonIntermediati the numeroTotalePagamentiNonIntermediati to set
     */
    public void setNumeroTotalePagamentiNonIntermediati ( Integer numeroTotalePagamentiNonIntermediati ) {
        this.numeroTotalePagamentiNonIntermediati = numeroTotalePagamentiNonIntermediati;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        CblTFlussoOrigine other = (CblTFlussoOrigine) obj;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        return true;
    }

}
