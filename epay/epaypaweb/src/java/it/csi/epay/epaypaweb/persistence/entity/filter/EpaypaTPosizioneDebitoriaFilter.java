/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.filter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table ( name = "epaypa_t_posizione_debitoria" )
@NamedQueries ( {
				@NamedQuery (
								name = "EpaypaTPosizioneDebitoria.findAllByIdFlussoFilter",
								query = "SELECT e FROM EpaypaTPosizioneDebitoriaFilter e WHERE idFlusso.idFlusso = :idFlusso" ),
} )
public class EpaypaTPosizioneDebitoriaFilter implements Serializable {

	private static final long serialVersionUID = 1548211947332018118L;

	@Id
	@Column ( name = "id_posizione_debitoria" )
	private Long idPosizioneDebitoria;

	@ManyToOne
	@JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
	private EpaypaTFlussoFilter idFlusso;

	@Column ( name = "iuv" )
	private String iuv;

	@Column ( name = "importo_totale" )
	private BigDecimal importoTotale;

	@Column ( name = "des_causale_versamento" )
	private String desCausaleVersamento;

	@Column ( name = "dt_scadenza" )
	private Timestamp dtScadenza;

	@ManyToOne
	@JoinColumn ( name = "id_soggetto_debitore" )
	private EpaypaTSoggettoFilter epaypaTSoggettoDebitore;

	@Column ( name = "codice_avviso", insertable = false, updatable = false )
	private String codiceAvviso;

	@Column ( name = "id_posizione_debitoria_est" )
	private String idPosizioneDebitoriaEst;

	@OneToMany
	@JoinColumn ( name = "codice_avviso", referencedColumnName = "codice_avviso"/*, updatable = false, insertable = false, nullable = false*/ )
	private List<EpaypaTAggPosizioneDebitoriaFilter> epaypaTAggPosizioneDebitoriaFilter;

	public EpaypaTPosizioneDebitoriaFilter () {
	}

	public List<EpaypaTAggPosizioneDebitoriaFilter> getEpaypaTAggPosizioneDebitoriaFilter () {
		return epaypaTAggPosizioneDebitoriaFilter;
	}

	public void setEpaypaTAggPosizioneDebitoriaFilter (
					List<EpaypaTAggPosizioneDebitoriaFilter> epaypaTAggPosizioneDebitoriaFilter ) {
		this.epaypaTAggPosizioneDebitoriaFilter = epaypaTAggPosizioneDebitoriaFilter;
	}

	public Long getIdPosizioneDebitoria () {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria ( Long idPosizioneDebitoria ) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public EpaypaTFlussoFilter getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( EpaypaTFlussoFilter idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public BigDecimal getImportoTotale () {
		return importoTotale;
	}

	public void setImportoTotale ( BigDecimal importoTotale ) {
		this.importoTotale = importoTotale;
	}

	public String getDesCausaleVersamento () {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento ( String desCausaleVersamento ) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public Timestamp getDtScadenza () {
		return dtScadenza;
	}

	public void setDtScadenza ( Timestamp dtScadenza ) {
		this.dtScadenza = dtScadenza;
	}

	public EpaypaTSoggettoFilter getEpaypaTSoggettoDebitore () {
		return epaypaTSoggettoDebitore;
	}

	public void setEpaypaTSoggettoDebitore ( EpaypaTSoggettoFilter epaypaTSoggettoDebitore ) {
		this.epaypaTSoggettoDebitore = epaypaTSoggettoDebitore;
	}

	public String getCodiceAvviso () {
		return codiceAvviso;
	}

	public void setCodiceAvviso ( String codiceAvviso ) {
		this.codiceAvviso = codiceAvviso;
	}

	public String getIdPosizioneDebitoriaEst () {
		return idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst ( String idPosizioneDebitoriaEst ) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}
}
