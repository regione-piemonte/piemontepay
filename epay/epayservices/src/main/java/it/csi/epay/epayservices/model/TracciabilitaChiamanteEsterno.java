/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;


public class TracciabilitaChiamanteEsterno implements Serializable {

	private static final long serialVersionUID = 8839266041558609910L;

	private Long idChiamata;

	private ChiamanteEsterno chiamanteEsterno;

	private String digest;

	private String iuv;

	private String codiceFiscale;

	private String idTransazione;

	private String identificativoPagamento;

	private Date timestampChiamata;

	private String remoteHost;

	private String descrizioneChiamante;

	public TracciabilitaChiamanteEsterno () {
		super ();
	}

	@Generated ( "SparkTools" )
	private TracciabilitaChiamanteEsterno ( Builder builder ) {
		this.idChiamata = builder.idChiamata;
		this.chiamanteEsterno = builder.chiamanteEsterno;
		this.digest = builder.digest;
		this.iuv = builder.iuv;
		this.codiceFiscale = builder.codiceFiscale;
		this.idTransazione = builder.idTransazione;
		this.identificativoPagamento = builder.identificativoPagamento;
		this.timestampChiamata = builder.timestampChiamata;
		this.remoteHost = builder.remoteHost;
		this.descrizioneChiamante = builder.descrizioneChiamante;
	}

	public Long getIdChiamata () {
		return idChiamata;
	}

	public void setIdChiamata ( Long idChiamata ) {
		this.idChiamata = idChiamata;
	}

	public ChiamanteEsterno getChiamanteEsterno () {
		return chiamanteEsterno;
	}

	public void setChiamanteEsterno ( ChiamanteEsterno chiamanteEsterno ) {
		this.chiamanteEsterno = chiamanteEsterno;
	}

	public String getDigest () {
		return digest;
	}

	public void setDigest ( String digest ) {
		this.digest = digest;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIdTransazione () {
		return idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public Date getTimestampChiamata () {
		return timestampChiamata;
	}

	public void setTimestampChiamata ( Date timestampChiamata ) {
		this.timestampChiamata = timestampChiamata;
	}

	public String getRemoteHost () {
		return remoteHost;
	}

	public void setRemoteHost ( String remoteHost ) {
		this.remoteHost = remoteHost;
	}

	public String getIdentificativoPagamento () {
		return identificativoPagamento;
	}

	public void setIdentificativoPagamento ( String identificativoPagamento ) {
		this.identificativoPagamento = identificativoPagamento;
	}

	public String getDescrizioneChiamante () {
		return descrizioneChiamante;
	}

	public void setDescrizioneChiamante ( String descrizioneChiamante ) {
		this.descrizioneChiamante = descrizioneChiamante;
	}

	/**
	 * Creates builder to build {@link TracciabilitaChiamanteEsterno}.
	 * 
	 * @return created builder
	 */
	@Generated ( "SparkTools" )
	public static Builder builder () {
		return new Builder ();
	}

	/**
	 * Builder to build {@link TracciabilitaChiamanteEsterno}.
	 */
	@Generated ( "SparkTools" )
	public static final class Builder {

		private Long idChiamata;

		private ChiamanteEsterno chiamanteEsterno;

		private String digest;

		private String iuv;

		private String codiceFiscale;

		private String idTransazione;

		private String identificativoPagamento;

		private Date timestampChiamata;

		private String remoteHost;

		private String descrizioneChiamante;

		private Builder () {
		}

		public Builder withIdChiamata ( Long idChiamata ) {
			this.idChiamata = idChiamata;
			return this;
		}

		public Builder withChiamanteEsterno ( ChiamanteEsterno chiamanteEsterno ) {
			this.chiamanteEsterno = chiamanteEsterno;
			return this;
		}

		public Builder withDigest ( String digest ) {
			this.digest = digest;
			return this;
		}

		public Builder withIuv ( String iuv ) {
			this.iuv = iuv;
			return this;
		}

		public Builder withCodiceFiscale ( String codiceFiscale ) {
			this.codiceFiscale = codiceFiscale;
			return this;
		}

		public Builder withIdTransazione ( String idTransazione ) {
			this.idTransazione = idTransazione;
			return this;
		}

		public Builder withIdentificativoPagamento ( String identificativoPagamento ) {
			this.identificativoPagamento = identificativoPagamento;
			return this;
		}

		public Builder withTimestampChiamata ( Date timestampChiamata ) {
			this.timestampChiamata = timestampChiamata;
			return this;
		}

		public Builder withRemoteHost ( String remoteHost ) {
			this.remoteHost = remoteHost;
			return this;
		}

		public Builder withDescrizioneChiamante ( String descrizioneChiamante ) {
			this.descrizioneChiamante = descrizioneChiamante;
			return this;
		}

		public TracciabilitaChiamanteEsterno build () {
			return new TracciabilitaChiamanteEsterno ( this );
		}
	}
}
