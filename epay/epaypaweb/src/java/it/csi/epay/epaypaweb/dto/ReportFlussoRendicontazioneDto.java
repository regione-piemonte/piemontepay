/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaypaweb.enumeration.NomeFiltroReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoCampoFiltroEnum;
import javax.annotation.Generated;

public class ReportFlussoRendicontazioneDto extends ReportBaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date dataRicezioneInizio;
	private Date dataRicezioneFine;
	private String codiceDescrizionePSP;

	@Generated("SparkTools")
	private ReportFlussoRendicontazioneDto(Builder builder) {
		this.id = builder.id;
		this.codiceFiscaleEnte = builder.codiceFiscaleEnte;
		this.codiceFiscaleUtente = builder.codiceFiscaleUtente;
		this.idEnte = builder.idEnte;
		this.idUtente = builder.idUtente;
		this.nominativoExport = builder.nominativoExport;
		this.statoReport = builder.statoReport;
		this.tipoFileReport = builder.tipoFileReport;
		this.idFile = builder.idFile;
		this.nomeFile = builder.nomeFile;
		this.dataInserimento = builder.dataInserimento;
		this.dataModifica = builder.dataModifica;
		this.tipoReportDto = builder.tipoReportDto;
		this.dataRicezioneInizio = builder.dataRicezioneInizio;
		this.dataRicezioneFine = builder.dataRicezioneFine;
		this.codiceDescrizionePSP = builder.codiceDescrizionePSP;
	}
	
	public ReportFlussoRendicontazioneDto() {
		super ();
	}

	@Override
	public void loadDatiFiltroReportDto(List<DatiFiltroReportDto> datiFiltroReportDtoList) {

		for ( DatiFiltroReportDto datiFiltroReportDto : datiFiltroReportDtoList ) {
			switch ( datiFiltroReportDto.getNomeFiltro() ) {
			case CODICE_DESCRIZIONE_PSP :
				setCodiceDescrizionePSP( datiFiltroReportDto.getValore() );
				break;
			case DATA_RICEZIONE_INIZIO : 
    			setDataRicezioneInizio( StringUtils.isNotEmpty( datiFiltroReportDto.getValore() ) ? new Date ( Long.parseLong( datiFiltroReportDto.getValore () ) ) : null );
        		break;
        	case DATA_RICEZIONE_FINE:
        		setDataRicezioneFine( StringUtils.isNotEmpty( datiFiltroReportDto.getValore() ) ? new Date ( Long.parseLong( datiFiltroReportDto.getValore () ) ) : null );
        		break;
			default :
				break;
			}
		}

	}

	@Override
	public List<DatiFiltroReportDto> buildDatiFiltroReportDto() {

		List<DatiFiltroReportDto> datiFiltroReportDtoList = new ArrayList<DatiFiltroReportDto>();
		
		if ( null != getCodiceDescrizionePSP () ) {
			datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
					.withNomeFiltro( NomeFiltroReportEnum.CODICE_DESCRIZIONE_PSP ) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
					.withValore( String.valueOf ( getCodiceDescrizionePSP() ) )
					.build());
		}
		if ( null != getDataRicezioneInizio() ) {
			datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
					.withNomeFiltro( NomeFiltroReportEnum.DATA_RICEZIONE_INIZIO) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
					.withValore( String.valueOf ( getDataRicezioneInizio().getTime() ) )
					.build());
		}
		if ( null != getDataRicezioneFine() ) {
			datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
					.withNomeFiltro( NomeFiltroReportEnum.DATA_RICEZIONE_FINE) 
					.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
					.withValore( String.valueOf ( getDataRicezioneFine().getTime() ) )
					.build());
		}
		
		return datiFiltroReportDtoList;
	}

	public String getCodiceDescrizionePSP() {
		return codiceDescrizionePSP;
	}

	public void setCodiceDescrizionePSP(String codiceDescrizionePSP) {
		this.codiceDescrizionePSP = codiceDescrizionePSP;
	}

	public Date getDataRicezioneInizio() {
		return dataRicezioneInizio;
	}

	public void setDataRicezioneInizio(Date dataRicezioneInizio) {
		this.dataRicezioneInizio = dataRicezioneInizio;
	}

	public Date getDataRicezioneFine() {
		return dataRicezioneFine;
	}

	public void setDataRicezioneFine(Date dataRicezioneFine) {
		this.dataRicezioneFine = dataRicezioneFine;
	}

	/**
	 * Creates builder to build {@link ReportFlussoRendicontazioneDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ReportFlussoRendicontazioneDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private String codiceFiscaleEnte;
		private String codiceFiscaleUtente;
		private Integer idEnte;
		private Long idUtente;
		private String nominativoExport;
		private StatoReportDto statoReport;
		private TipoFileReportDto tipoFileReport;
		private Long idFile;
		private String nomeFile;
		private Date dataInserimento;
		private Date dataModifica;
		private TipoReportDto tipoReportDto;
		private Date dataRicezioneInizio;
		private Date dataRicezioneFine;
		private String codiceDescrizionePSP;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withCodiceFiscaleEnte(String codiceFiscaleEnte) {
			this.codiceFiscaleEnte = codiceFiscaleEnte;
			return this;
		}

		public Builder withCodiceFiscaleUtente(String codiceFiscaleUtente) {
			this.codiceFiscaleUtente = codiceFiscaleUtente;
			return this;
		}

		public Builder withIdEnte(Integer idEnte) {
			this.idEnte = idEnte;
			return this;
		}

		public Builder withIdUtente(Long idUtente) {
			this.idUtente = idUtente;
			return this;
		}

		public Builder withNominativoExport(String nominativoExport) {
			this.nominativoExport = nominativoExport;
			return this;
		}

		public Builder withStatoReport(StatoReportDto statoReport) {
			this.statoReport = statoReport;
			return this;
		}

		public Builder withTipoFileReport(TipoFileReportDto tipoFileReport) {
			this.tipoFileReport = tipoFileReport;
			return this;
		}

		public Builder withIdFile(Long idFile) {
			this.idFile = idFile;
			return this;
		}

		public Builder withNomeFile(String nomeFile) {
			this.nomeFile = nomeFile;
			return this;
		}

		public Builder withDataInserimento(Date dataInserimento) {
			this.dataInserimento = dataInserimento;
			return this;
		}

		public Builder withDataModifica(Date dataModifica) {
			this.dataModifica = dataModifica;
			return this;
		}

		public Builder withTipoReportDto(TipoReportDto tipoReportDto) {
			this.tipoReportDto = tipoReportDto;
			return this;
		}

		public Builder withDataRicezioneInizio(Date dataRicezioneInizio) {
			this.dataRicezioneInizio = dataRicezioneInizio;
			return this;
		}

		public Builder withDataRicezioneFine(Date dataRicezioneFine) {
			this.dataRicezioneFine = dataRicezioneFine;
			return this;
		}

		public Builder withCodiceDescrizionePSP(String codiceDescrizionePSP) {
			this.codiceDescrizionePSP = codiceDescrizionePSP;
			return this;
		}

		public ReportFlussoRendicontazioneDto build() {
			return new ReportFlussoRendicontazioneDto(this);
		}
	}
	
	@Override
	public String getAuditMessage() {

		StringBuilder sb = new StringBuilder();


        if(getIdUtente()!=null) {
        	sb.append(", ").append("idProfilo:'").append(getIdUtente()).append("'");
        }
        if(getIdEnte()!=null) {
        	sb.append(", ").append("idEnte:'").append(getIdEnte()).append("'");
        }

		if(!StringUtils.isEmpty(getCodiceDescrizionePSP())) {
			sb.append(", ").append("codiceDescrizionePSP:'").append(getCodiceDescrizionePSP()).append("'");
		}
		
		if ( null != getDataRicezioneInizio() ){
			sb.append(", ").append("dataRicezioneInizio:'").append(  getDataRicezioneInizio()).append("'");
		}
		
		if ( null != getDataRicezioneFine() ){
			sb.append(", ").append("dataRicezioneFine:'").append(  getDataRicezioneFine()).append("'");
		}
		String message= sb.toString();
		if (!StringUtils.isEmpty(message) && message.length()>1)
		{
			message= message.substring(1);
		}

		return message;
	}
	

}
