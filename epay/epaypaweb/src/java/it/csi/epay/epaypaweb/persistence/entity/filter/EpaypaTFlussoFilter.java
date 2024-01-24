/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.filter;

import it.csi.epay.epaypaweb.persistence.entity.EpaypaDStatoFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table ( name = "epaypa_t_flusso" )
public class EpaypaTFlussoFilter implements Serializable {

	private static final long serialVersionUID = 5598589921250999482L;

	@Id
	@Column ( name = "id_flusso" )
	private Long idFlusso;

	@ManyToOne
	@JoinColumn ( name = "id_tipo_flusso" )
	private EpaypaDTipoFlusso epaypaDTipoFlusso;

	@ManyToOne
	@JoinColumn ( name = "id_stato_flusso" )
	private EpaypaDStatoFlusso epaypaDStatoFlusso;

	@Column ( name = "id_messaggio" )
	private String idMessaggio;

	@ManyToOne
	@JoinColumn ( name = "id_ente" )
	private EpaypaTEnte epaypaTEnte;

	@ManyToOne
	@JoinColumn ( name = "id_codice_versamento" )
	private EpaypaTCodiceVersamento epaypaTCodiceVersamento;

	@Column ( name = "numero_elementi" )
	private Integer numeroElementi;

	@Column ( name = "importo_totale" )
	private BigDecimal importoTotale;

	@Column ( name = "pagamenti_spontanei" )
	private Boolean pagamentiSpontanei;

	@Column ( name = "dt_inserimento" )
	private Timestamp dtInserimento;

	@Column ( name = "dt_ultima_variazione" )
	private Timestamp dtUltimaVariazione;

	@Column ( name = "utente_ultima_variazione" )
	private String utenteUltimaVariazione;

	@Column ( name = "cod_esito" )
	private String codEsito;

	@Column ( name = "dettaglio_esito" )
	private String dettaglioEsito;

	@OneToOne ( fetch = FetchType.EAGER )
	@JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = true )
	private EpaypaTRendicontazioneFilter epaypaTRendicontazione;

	@OneToMany ( cascade = CascadeType.ALL )
	@JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
	private List<EpaypaTPosizioneDebitoriaFilter> epaypaTPosizioneDebitoriaLight;

	@OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
	private List<EpaypaTAggPosizioneDebitoriaFilter> epaypaTAggPosizioneDebitoriaFilter;

	public EpaypaTFlussoFilter () {
		//empty constructor
	}

	public List<EpaypaTAggPosizioneDebitoriaFilter> getEpaypaTAggPosizioneDebitoriaFilter () {
		return epaypaTAggPosizioneDebitoriaFilter;
	}

	public void setEpaypaTAggPosizioneDebitoriaFilter ( List<EpaypaTAggPosizioneDebitoriaFilter> epaypaTAggPosizioneDebitoriaFilter ) {
		this.epaypaTAggPosizioneDebitoriaFilter = epaypaTAggPosizioneDebitoriaFilter;
	}

	public Long getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( Long idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public EpaypaDTipoFlusso getEpaypaDTipoFlusso () {
		return epaypaDTipoFlusso;
	}

	public void setEpaypaDTipoFlusso ( EpaypaDTipoFlusso epaypaDTipoFlusso ) {
		this.epaypaDTipoFlusso = epaypaDTipoFlusso;
	}

	public EpaypaDStatoFlusso getEpaypaDStatoFlusso () {
		return epaypaDStatoFlusso;
	}

	public void setEpaypaDStatoFlusso ( EpaypaDStatoFlusso epaypaDStatoFlusso ) {
		this.epaypaDStatoFlusso = epaypaDStatoFlusso;
	}

	public String getIdMessaggio () {
		return idMessaggio;
	}

	public void setIdMessaggio ( String idMessaggio ) {
		this.idMessaggio = idMessaggio;
	}

	public EpaypaTEnte getEpaypaTEnte () {
		return epaypaTEnte;
	}

	public void setEpaypaTEnte ( EpaypaTEnte epaypaTEnte ) {
		this.epaypaTEnte = epaypaTEnte;
	}

	public EpaypaTCodiceVersamento getEpaypaTCodiceVersamento () {
		return epaypaTCodiceVersamento;
	}

	public void setEpaypaTCodiceVersamento ( EpaypaTCodiceVersamento epaypaTCodiceVersamento ) {
		this.epaypaTCodiceVersamento = epaypaTCodiceVersamento;
	}

	public Integer getNumeroElementi () {
		return numeroElementi;
	}

	public void setNumeroElementi ( Integer numeroElementi ) {
		this.numeroElementi = numeroElementi;
	}

	public BigDecimal getImportoTotale () {
		return importoTotale;
	}

	public void setImportoTotale ( BigDecimal importoTotale ) {
		this.importoTotale = importoTotale;
	}

	public Boolean getPagamentiSpontanei () {
		return pagamentiSpontanei;
	}

	public void setPagamentiSpontanei ( Boolean pagamentiSpontanei ) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}

	public Timestamp getDtInserimento () {
		return dtInserimento;
	}

	public void setDtInserimento ( Timestamp dtInserimento ) {
		this.dtInserimento = dtInserimento;
	}

	public Timestamp getDtUltimaVariazione () {
		return dtUltimaVariazione;
	}

	public void setDtUltimaVariazione ( Timestamp dtUltimaVariazione ) {
		this.dtUltimaVariazione = dtUltimaVariazione;
	}

	public String getUtenteUltimaVariazione () {
		return utenteUltimaVariazione;
	}

	public void setUtenteUltimaVariazione ( String utenteUltimaVariazione ) {
		this.utenteUltimaVariazione = utenteUltimaVariazione;
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

	public EpaypaTRendicontazioneFilter getEpaypaTRendicontazione () {
		return epaypaTRendicontazione;
	}

	public void setEpaypaTRendicontazione ( EpaypaTRendicontazioneFilter epaypaTRendicontazione ) {
		this.epaypaTRendicontazione = epaypaTRendicontazione;
	}

	public List<EpaypaTPosizioneDebitoriaFilter> getEpaypaTPosizioneDebitoriaLight () {
		return epaypaTPosizioneDebitoriaLight;
	}

	public void setEpaypaTPosizioneDebitoriaLight (
					List<EpaypaTPosizioneDebitoriaFilter> epaypaTPosizioneDebitoriaLight ) {
		this.epaypaTPosizioneDebitoriaLight = epaypaTPosizioneDebitoriaLight;
	}
}
