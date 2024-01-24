/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the cbl_t_catalogo database table.
 *
 */
@Entity
@Table(name="cbl_t_catalogo",schema="epaymodric")
@NamedQuery(name="CblTCatalogo.findAll", query="SELECT c FROM CblTCatalogo c")
public class CblTCatalogo extends DatiTecniciParentEntity implements Serializable, Comparable<CblTCatalogoTemp> {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CBL_T_CATALOGO_ID_GENERATOR", sequenceName="epaymodric.CBL_T_CATALOGO_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_CATALOGO_ID_GENERATOR")
    private Long id;

    @Column(name="accertamento_anno")
    private Integer accertamentoAnno;

    @Column(name="accertamento_desc")
    private String accertamentoDesc;

    @Column(name="accertamento_nro")
    private Integer accertamentoNro;

    @Column(name="anno_esercizio")
    private Integer annoEsercizio;

    private BigDecimal articolo;

    private String capitolo;

    private String categoria;

    @Column(name="codice_fiscale_ente")
    private String codiceFiscaleEnte;

    @Column(name="codice_versamento")
    private String codiceVersamento;

    @Column(name="data_fine_validita")
    private Timestamp dataFineValidita;

    @Column(name="data_inizio_validita")
    private Timestamp dataInizioValidita;

    @Column(name="dati_specifici_riscossione")
    private String datiSpecificiRiscossione;

    @Column(name="descrizione_versamento")
    private String descrizioneVersamento;

    @Column(name="id_ente")
    private String idEnte;

    @Column(name="piano_dei_conti")
    private String pianoDeiConti;

    private String sottovoce;

    private String tipologia;

    private String titolo;

    @Column ( name = "flag_annullato" )
    private Boolean flagAnnullato;

    @Column ( name = "chiave_intersistema" )
    private String chiaveIntersistema;

    //bi-directional many-to-one association to CblTCodiceVersamento
    @ManyToOne
    @JoinColumns({
        @JoinColumn ( name = "codice_versamento", referencedColumnName = "codice_versamento", insertable = false, updatable = false ),
        @JoinColumn ( name = "id_ente", referencedColumnName = "id_ente", insertable = false, updatable = false )
        })
    private CblTCodiceVersamento cblTCodiceVersamento;

	@Column ( name = "codice_tributo" )
	private String codiceTributo;

    public CblTCatalogo() {
    }
    
    @Override
    public int compareTo ( CblTCatalogoTemp o ) {
        return this.chiaveIntersistema == null ? ( o.getChiaveIntersistema () == null ? 0 : -1 )
                        : ( o.getChiaveIntersistema () == null ? +1 : this.chiaveIntersistema.compareToIgnoreCase ( o.getChiaveIntersistema () ) );
    }

    /*
     * (non-Javadoc)
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
        CblTCatalogo other = (CblTCatalogo) obj;
        if ( id == null ) {
			return other.id == null;
        } else
			return id.equals ( other.id );
	}

    public Integer getAccertamentoAnno() {
        return accertamentoAnno;
    }

    public String getAccertamentoDesc() {
        return accertamentoDesc;
    }

    public Integer getAccertamentoNro() {
        return accertamentoNro;
    }

    public Integer getAnnoEsercizio() {
        return annoEsercizio;
    }

    public BigDecimal getArticolo() {
        return articolo;
    }

    public String getCapitolo() {
        return capitolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public CblTCodiceVersamento getCblTCodiceVersamento() {
        return this.cblTCodiceVersamento;
    }

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    public Timestamp getDataFineValidita() {
        return dataFineValidita;
    }

    public Timestamp getDataInizioValidita() {
        return dataInizioValidita;
    }

    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    public String getDescrizioneVersamento() {
        return descrizioneVersamento;
    }

    public Boolean getFlagAnnullato () {
        return flagAnnullato;
    }

    public Long getId() {
        return id;
    }

    public String getIdEnte() {
        return idEnte;
    }

    public String getPianoDeiConti() {
        return pianoDeiConti;
    }

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    public String getSottovoce() {
        return sottovoce;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getTitolo() {
        return titolo;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        return result;
    }

    public void setAccertamentoAnno(Integer accertamentoAnno) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public void setAccertamentoDesc(String accertamentoDesc) {
        this.accertamentoDesc = accertamentoDesc;
    }

    public void setAccertamentoNro(Integer accertamentoNro) {
        this.accertamentoNro = accertamentoNro;
    }

    public void setAnnoEsercizio(Integer annoEsercizio) {
        this.annoEsercizio = annoEsercizio;
    }

    public void setArticolo(BigDecimal articolo) {
        this.articolo = articolo;
    }

    public void setCapitolo(String capitolo) {
        this.capitolo = capitolo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCblTCodiceVersamento(CblTCodiceVersamento cblTCodiceVersamento) {
        this.cblTCodiceVersamento = cblTCodiceVersamento;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public void setCodiceVersamento(String codiceVersamento) {
        this.codiceVersamento = codiceVersamento;
    }

    public void setDataFineValidita(Timestamp dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

    public void setDataInizioValidita(Timestamp dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public void setDescrizioneVersamento(String descrizioneVersamento) {
        this.descrizioneVersamento = descrizioneVersamento;
    }

    public void setFlagAnnullato ( Boolean flagAnnullato ) {
        this.flagAnnullato = flagAnnullato;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }

    public void setPianoDeiConti(String pianoDeiConti) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public void setSottovoce(String sottovoce) {
        this.sottovoce = sottovoce;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}
}
