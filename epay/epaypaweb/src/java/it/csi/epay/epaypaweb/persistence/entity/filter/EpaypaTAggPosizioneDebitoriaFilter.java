/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.filter;

import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoAggPosizioneDebitoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table ( name = "epaypa_t_agg_posizione_debitoria" )
public class EpaypaTAggPosizioneDebitoriaFilter implements Serializable {

	private static final long serialVersionUID = -798268983066090399L;

	@Id
	@Column ( name = "id_agg_posizione_debitoria" )
	private Long idAggPosizioneDebitoria;

	@Column ( name = "id_flusso", insertable = false, updatable = false )
	private Long idFlusso;

	@Column ( name = "cod_esito" )
	private String codEsito;

	@Column ( name = "codice_avviso", insertable = false, updatable = false )
	private String codiceAvviso;

	@Column ( name = "dettaglio_esito" )
	private String dettaglioEsito;

	@Column ( name = "id_posizione_debitoria_est" )
	private String idPosizioneDebitoriaEst;

	private String motivazione;

	@Column ( name = "importo_principale" )
	private BigDecimal importoPrincipale;

	@Column ( name = "importo_secondario_altro_ente" )
	private BigDecimal importoSecondarioAltroEnte;

	@ManyToOne
	@JoinColumn ( name = "id_tipo_agg_posizione_debitoria" )
	private EpaypaDTipoAggPosizioneDebitoria epaypaDTipoAggPosizioneDebitoria;

	@ManyToOne ( fetch = FetchType.EAGER )
	@JoinColumn ( name = "codice_avviso", referencedColumnName = "codice_avviso", updatable = false, insertable = false, nullable = false )
	private EpaypaTPosizioneDebitoriaFilter epaypaTPosizioneDebitoriaFilter;

	public EpaypaTAggPosizioneDebitoriaFilter () {
		//empty constructor
	}

	public EpaypaTPosizioneDebitoriaFilter getEpaypaTPosizioneDebitoriaFilter () {
		return epaypaTPosizioneDebitoriaFilter;
	}

	public void setEpaypaTPosizioneDebitoriaFilter ( EpaypaTPosizioneDebitoriaFilter epaypaTPosizioneDebitoriaFilter ) {
		this.epaypaTPosizioneDebitoriaFilter = epaypaTPosizioneDebitoriaFilter;
	}

	public Long getIdAggPosizioneDebitoria () {
		return idAggPosizioneDebitoria;
	}

	public String getCodiceAvviso () {
		return codiceAvviso;
	}

	public void setCodiceAvviso ( String codiceAvviso ) {
		this.codiceAvviso = codiceAvviso;
	}

	public void setIdAggPosizioneDebitoria ( Long idAggPosizioneDebitoria ) {
		this.idAggPosizioneDebitoria = idAggPosizioneDebitoria;
	}

	public Long getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( Long idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public String getCodEsito () {
		return codEsito;
	}

	public void setCodEsito ( String codEsito ) {
		this.codEsito = codEsito;
	}

	public String getDettaglioEsito () {
		return dettaglioEsito;
	}

	public void setDettaglioEsito ( String dettaglioEsito ) {
		this.dettaglioEsito = dettaglioEsito;
	}

	public String getIdPosizioneDebitoriaEst () {
		return idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst ( String idPosizioneDebitoriaEst ) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}

	public String getMotivazione () {
		return motivazione;
	}

	public void setMotivazione ( String motivazione ) {
		this.motivazione = motivazione;
	}

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public BigDecimal getImportoSecondarioAltroEnte () {
		return importoSecondarioAltroEnte;
	}

	public void setImportoSecondarioAltroEnte ( BigDecimal importoSecondarioAltroEnte ) {
		this.importoSecondarioAltroEnte = importoSecondarioAltroEnte;
	}

	public EpaypaDTipoAggPosizioneDebitoria getEpaypaDTipoAggPosizioneDebitoria () {
		return epaypaDTipoAggPosizioneDebitoria;
	}

	public void setEpaypaDTipoAggPosizioneDebitoria ( EpaypaDTipoAggPosizioneDebitoria epaypaDTipoAggPosizioneDebitoria ) {
		this.epaypaDTipoAggPosizioneDebitoria = epaypaDTipoAggPosizioneDebitoria;
	}
}
