/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import it.csi.epay.epaypaweb.enumeration.NomeFiltroReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoCampoFiltroEnum;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/** dto facade <-> business <-> persistence */
public class ReportPagamentiDto extends ReportBaseDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6401456816619787791L;

    //private Long id;

    private String codiceFiscale;

	private String cognome;

    private Date dataPagamentoFine;

    private Date dataPagamentoInizio;

    private Date dataScadenzaPagamentoInizio;

    private Date dataScadenzaPagamentoFine;

    private Integer idCodiceVersamento;

	private String iuv;

    //private String nominativoExport;

    private Integer tipoPagamento;

	private TipoReportDto tipoReportDto;
	
	private Boolean costiNotifica;

    @Generated ( "SparkTools" )
    private ReportPagamentiDto ( Builder builder ) {
        this.id = builder.id;
        this.codiceFiscale = builder.codiceFiscale;
        this.codiceFiscaleEnte = builder.codiceFiscaleEnte;
        this.codiceFiscaleUtente = builder.codiceFiscaleUtente;
        this.cognome = builder.cognome;
        this.dataPagamentoFine = builder.dataPagamentoFine;
        this.dataPagamentoInizio = builder.dataPagamentoInizio;
        this.idCodiceVersamento = builder.idCodiceVersamento;
        this.idEnte = builder.idEnte;
        this.idUtente = builder.idUtente;
        this.iuv = builder.iuv;
        this.nominativoExport = builder.nominativoExport;
        this.tipoPagamento = builder.tipoPagamento;
        this.statoReport = builder.statoReport;
        this.tipoFileReport = builder.tipoFileReport;
        this.idFile = builder.idFile;
        this.nomeFile = builder.nomeFile;
        this.dataInserimento = builder.dataInserimento;
        this.dataModifica = builder.dataModifica;
        this.tipoReportDto = builder.tipoReportDto;
        this.dataScadenzaPagamentoInizio = builder.dataScadenzaPagamentoInizio;
        this.dataScadenzaPagamentoFine = builder.dataScadenzaPagamentoFine;
        this.costiNotifica = builder.costiNotifica;
    }

    /**
     *
     */
    public ReportPagamentiDto () {
        super ();
    }

    @Override
    public void loadDatiFiltroReportDto( List<DatiFiltroReportDto>  datiFiltroReportDtoList ) {
        for ( DatiFiltroReportDto datiFiltroReportDto : datiFiltroReportDtoList ) {
            switch ( datiFiltroReportDto.getNomeFiltro() ) {
            case IUV :
                setIuv( datiFiltroReportDto.getValore() );
                break;
            case CODICE_FISCALE :
                setCodiceFiscale( datiFiltroReportDto.getValore() );
                break;
            case COGNOME :
                setCognome( datiFiltroReportDto.getValore() );
                break;
            case ID_CODICE_VERSAMENTO :
                setIdCodiceVersamento( StringUtils.isNotEmpty( datiFiltroReportDto.getValore() ) ? new Integer ( datiFiltroReportDto.getValore () ) : null ) ;
                break;
            case TIPO_PAGAMENTO :
                setTipoPagamento( StringUtils.isNotEmpty( datiFiltroReportDto.getValore() ) ? new Integer ( datiFiltroReportDto.getValore () ) : null );
                break;
            case DATA_PAGAMENTO_INIZIO :
                setDataPagamentoInizio( StringUtils.isNotEmpty( datiFiltroReportDto.getValore() ) ? new Date ( Long.parseLong( datiFiltroReportDto.getValore () ) ) : null );
                break;
            case DATA_PAGAMENTO_FINE:
                setDataPagamentoFine( StringUtils.isNotEmpty( datiFiltroReportDto.getValore() ) ? new Date ( Long.parseLong( datiFiltroReportDto.getValore () ) ) : null );
                break;
            case COSTI_NOTIFICA:
            	setCostiNotifica(StringUtils.isNotEmpty( datiFiltroReportDto.getValore() ) ? Boolean.valueOf(datiFiltroReportDto.getValore()) : null );
            	break;
            default:
                break;
            }
        }
    }


    @Override
    public List<DatiFiltroReportDto> buildDatiFiltroReportDto() {
        List<DatiFiltroReportDto> datiFiltroReportDtoList = new ArrayList<DatiFiltroReportDto>();

        //IUV
        if( StringUtils.isNotEmpty(getIuv()) ) {
            datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
                .withNomeFiltro( NomeFiltroReportEnum.IUV )
                .withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
                .withValore( getIuv() )
                .build());
        }

        //CODICE FISCALE
        if( StringUtils.isNotEmpty(getCodiceFiscale()) ) {
            datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
                .withNomeFiltro( NomeFiltroReportEnum.CODICE_FISCALE )
                .withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
                .withValore( getCodiceFiscale() )
                .build());
        }

        //COGNOME
        if ( StringUtils.isNotEmpty(getCognome()) ) {
            datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
                .withNomeFiltro( NomeFiltroReportEnum.COGNOME )
                .withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
                .withValore( getCognome() )
                .build());
        }

        //ID CODICE VERSAMENTO
        if( null != getIdCodiceVersamento() ) {
            datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
                .withNomeFiltro( NomeFiltroReportEnum.ID_CODICE_VERSAMENTO )
                .withTipoCampoFiltro( TipoCampoFiltroEnum.INTEGER )
                .withValore( String.valueOf ( getIdCodiceVersamento() ) )
                .build());
        }

        //ID TIPO PAGAMENTO
        if ( null != getTipoPagamento() ) {
            datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
                .withNomeFiltro( NomeFiltroReportEnum.TIPO_PAGAMENTO )
                .withTipoCampoFiltro( TipoCampoFiltroEnum.INTEGER )
                .withValore( String.valueOf ( getTipoPagamento() ) )
                .build());
        }

        // DATA PAGAMENTO INIZIO
        if ( null != getDataPagamentoInizio() ) {
            datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
                .withNomeFiltro( NomeFiltroReportEnum.DATA_PAGAMENTO_INIZIO )
                .withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
                .withValore( String.valueOf( getDataPagamentoInizio ().getTime () ) )
                .build());
        }

        // DATA PAGAMENTO FINE
        if ( null != getDataPagamentoFine() ) {
            datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
                .withNomeFiltro( NomeFiltroReportEnum.DATA_PAGAMENTO_FINE )
                .withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
                .withValore( String.valueOf( getDataPagamentoFine ().getTime () ) )
                .build());
        }
        
        // COSTI NOTIFICA
        if ( null != getCostiNotifica() ) {
        	datiFiltroReportDtoList.add(DatiFiltroReportDto.builder()
        			.withNomeFiltro( NomeFiltroReportEnum.COSTI_NOTIFICA )
        			.withTipoCampoFiltro( TipoCampoFiltroEnum.INTEGER )
        			.withValore( String.valueOf( getCostiNotifica() ) )
        			.build());
        }

        return datiFiltroReportDtoList;
    }

	/**
     * @return the codiceFiscale
     */
    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    /**
     * @param codiceFiscale the codiceFiscale to set
     */
    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * @return the cognome
     */
    public String getCognome () {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    /**
     * @return the dataPagamentoFine
     */
    public Date getDataPagamentoFine () {
        return dataPagamentoFine;
    }

    /**
     * @param dataPagamentoFine the dataPagamentoFine to set
     */
    public void setDataPagamentoFine ( Date dataPagamentoFine ) {
        this.dataPagamentoFine = dataPagamentoFine;
    }

    /**
     * @return the dataPagamentoInizio
     */
    public Date getDataPagamentoInizio () {
        return dataPagamentoInizio;
    }

    /**
     * @param dataPagamentoInizio the dataPagamentoInizio to set
     */
    public void setDataPagamentoInizio ( Date dataPagamentoInizio ) {
        this.dataPagamentoInizio = dataPagamentoInizio;
    }

    /**
     * @return the idCodiceVersamento
     */
    public Integer getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    /**
     * @param idCodiceVersamento the idCodiceVersamento to set
     */
    public void setIdCodiceVersamento ( Integer idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

	public Date getDataScadenzaPagamentoInizio () {
        return dataScadenzaPagamentoInizio;
    }

    public void setDataScadenzaPagamentoInizio ( Date dataScadenzaPagamentoInizio ) {
        this.dataScadenzaPagamentoInizio = dataScadenzaPagamentoInizio;
    }

    public Date getDataScadenzaPagamentoFine () {
        return dataScadenzaPagamentoFine;
    }

    public void setDataScadenzaPagamentoFine ( Date dataScadenzaPagamentoFine ) {
        this.dataScadenzaPagamentoFine = dataScadenzaPagamentoFine;
    }

    /**
     * @return the iuv
     */
    public String getIuv () {
        return iuv;
    }

    /**
     * @param iuv the iuv to set
     */
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

	/**
     * @return the tipoPagamento
     */
    public Integer getTipoPagamento () {
        return tipoPagamento;
    }

    /**
     * @param tipoPagamento the tipoPagamento to set
     */
    public void setTipoPagamento ( Integer tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    /**
     * @return the statoReport
     */

	@Override
    public TipoReportDto getTipoReportDto() {
        return tipoReportDto;
    }

    @Override
    public void setTipoReportDto(TipoReportDto tipoReportDto) {
        this.tipoReportDto = tipoReportDto;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder2 = new StringBuilder ();
        builder2.append ( "ReportDto [id=" );
        builder2.append ( id );
        builder2.append ( ", codiceFiscale=" );
        builder2.append ( codiceFiscale );
        builder2.append ( ", codiceFiscaleEnte=" );
        builder2.append ( codiceFiscaleEnte );
        builder2.append ( ", codiceFiscaleUtente=" );
        builder2.append ( codiceFiscaleUtente );
        builder2.append ( ", cognome=" );
        builder2.append ( cognome );
        builder2.append ( ", dataPagamentoFine=" );
        builder2.append ( dataPagamentoFine );
        builder2.append ( ", dataPagamentoInizio=" );
        builder2.append ( dataPagamentoInizio );
        builder2.append ( ", idCodiceVersamento=" );
        builder2.append ( idCodiceVersamento );
        builder2.append ( ", idEnte=" );
        builder2.append ( idEnte );
        builder2.append ( ", idUtente=" );
        builder2.append ( idUtente );
        builder2.append ( ", iuv=" );
        builder2.append ( iuv );
        builder2.append ( ", nominativoExport=" );
        builder2.append ( nominativoExport );
        builder2.append ( ", tipoPagamento=" );
        builder2.append ( tipoPagamento );
        builder2.append ( ", statoReport=" );
        builder2.append ( statoReport );
        builder2.append ( ", tipoFileReport=" );
        builder2.append ( tipoFileReport );
        builder2.append ( ", idFile=" );
        builder2.append ( idFile );
        builder2.append ( ", nomeFile=" );
        builder2.append ( nomeFile );
        builder2.append ( ", dataInserimento=" );
        builder2.append ( dataInserimento );
        builder2.append ( ", dataModifica=" );
        builder2.append ( dataModifica );
        builder2.append ( "]" );
        return builder2.toString ();
    }

    /**
     * Creates builder to build {@link ReportPagamentiDto}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link ReportPagamentiDto}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long id;

        private String codiceFiscale;

        private String codiceFiscaleEnte;

        private String codiceFiscaleUtente;

        private String cognome;

        private Date dataPagamentoFine;

        private Date dataPagamentoInizio;

        private Integer idCodiceVersamento;

        private Integer idEnte;

        private Long idUtente;

        private String iuv;

        private String nominativoExport;

        private Integer tipoPagamento;

        private StatoReportDto statoReport;

        private TipoFileReportDto tipoFileReport;

        private Long idFile;

        private String nomeFile;

        private Date dataInserimento;

        private Date dataModifica;

        private TipoReportDto tipoReportDto;

        private Integer idTipoFile;

        private Date dataScadenzaPagamentoInizio;

        private Date dataScadenzaPagamentoFine;
        
        private Boolean costiNotifica;

        private Builder () {
        }

        public Builder withId ( Long id ) {
            this.id = id;
            return this;
        }

        public Builder withCodiceFiscale ( String codiceFiscale ) {
            this.codiceFiscale = codiceFiscale;
            return this;
        }

        public Builder withCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
            this.codiceFiscaleEnte = codiceFiscaleEnte;
            return this;
        }

        public Builder withCodiceFiscaleUtente ( String codiceFiscaleUtente ) {
            this.codiceFiscaleUtente = codiceFiscaleUtente;
            return this;
        }

        public Builder withCognome ( String cognome ) {
            this.cognome = cognome;
            return this;
        }

        public Builder withDataPagamentoFine ( Date dataPagamentoFine ) {
            this.dataPagamentoFine = dataPagamentoFine;
            return this;
        }

        public Builder withDataPagamentoInizio ( Date dataPagamentoInizio ) {
            this.dataPagamentoInizio = dataPagamentoInizio;
            return this;
        }

        public Builder withIdCodiceVersamento ( Integer idCodiceVersamento ) {
            this.idCodiceVersamento = idCodiceVersamento;
            return this;
        }

        public Builder withIdEnte ( Integer idEnte ) {
            this.idEnte = idEnte;
            return this;
        }

        public Builder withIdUtente ( Long idUtente ) {
            this.idUtente = idUtente;
            return this;
        }

        public Builder withIuv ( String iuv ) {
            this.iuv = iuv;
            return this;
        }

        public Builder withNominativoExport ( String nominativoExport ) {
            this.nominativoExport = nominativoExport;
            return this;
        }

        public Builder withTipoPagamento ( Integer tipoPagamento ) {
            this.tipoPagamento = tipoPagamento;
            return this;
        }

        public Builder withStatoReport ( StatoReportDto statoReport ) {
            this.statoReport = statoReport;
            return this;
        }

        public Builder withTipoFileReport ( TipoFileReportDto tipoFileReport ) {
            this.tipoFileReport = tipoFileReport;
            return this;
        }

        public Builder withIdFile ( Long idFile ) {
            this.idFile = idFile;
            return this;
        }

        public Builder withNomeFile ( String nomeFile ) {
            this.nomeFile = nomeFile;
            return this;
        }

        public Builder withDataInserimento ( Date dataInserimento ) {
            this.dataInserimento = dataInserimento;
            return this;
        }

        public Builder withDataModifica ( Date dataModifica ) {
            this.dataModifica = dataModifica;
            return this;
        }

        public Builder withTipoReportDto ( TipoReportDto tipoReportDto) {
            this.tipoReportDto = tipoReportDto;
            return this;
        }

        public Builder withDataScadenzaPagamentoInizio ( Date dataScadenzaPagamentoInizio ) {
            this.dataScadenzaPagamentoInizio = dataScadenzaPagamentoInizio;
            return this;
        }

        public Builder withDataScadenzaPagamentoFine ( Date dataScadenzaPagamentoFine ) {
            this.dataScadenzaPagamentoFine = dataPagamentoFine;
            return this;
        }
        public Builder withCostiNotifica ( Integer idCosiNotifica ) {
        	if (idCosiNotifica == null || idCosiNotifica.equals(2) || idCosiNotifica.equals(0))
        		this.costiNotifica = null;
        	else
        		this.costiNotifica = Boolean.TRUE;
        	return this;
        }

        public ReportPagamentiDto build () {
            return new ReportPagamentiDto ( this );
        }
    }
    
    

	public Boolean getCostiNotifica() {
		return costiNotifica;
	}

	public void setCostiNotifica(Boolean costiNotifica) {
		this.costiNotifica = costiNotifica;
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

		if(!StringUtils.isEmpty(getIuv())) {
			sb.append(", ").append("iuv:'").append(getIuv()).append("'");
		}
		if(!StringUtils.isEmpty(getCodiceFiscale())) {
			sb.append(", ").append("codiceFiscale:'").append(getCodiceFiscale()).append("'");
		}
		if(!StringUtils.isEmpty(getCognome())) {
			sb.append(", ").append("cognome:'").append(getCognome()).append("'");
		}
		if( null != getIdCodiceVersamento() ) {
			sb.append(", ").append("codiceVersamento:'").append(  getIdCodiceVersamento()).append("'");
		}
		if ( null != getTipoPagamento() ){
			sb.append(", ").append("tipoPagamento:'").append(  getTipoPagamento()).append("'");
		}
		
		if ( null != getDataPagamentoInizio() ){
			sb.append(", ").append("dataPagamentoInizio:'").append(  getDataPagamentoInizio()).append("'");
		}
		
		if ( null != getDataPagamentoFine() ){
			sb.append(", ").append("dataPagamentoFine:'").append(  getDataPagamentoFine()).append("'");
		}

		String message= sb.toString();
		if (!StringUtils.isEmpty(message) && message.length()>1)
		{
			message= message.substring(1);
		}
		return message;
	}
}
