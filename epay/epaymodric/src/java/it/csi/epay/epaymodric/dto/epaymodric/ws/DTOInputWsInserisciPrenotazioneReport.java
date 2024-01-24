/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsInserisciPrenotazioneReport" )
public class DTOInputWsInserisciPrenotazioneReport extends DTOInputDate {

    private static final long serialVersionUID = 422088937398221477L;

    //	private Long id;

    private String idEnte;

    private String codiceFiscaleEnte;

    private String idUtente;

    private String codiceFiscaleUtente;

    private String idStato;

    private String idFile;

    private String idTipoFile;

    private String idTipoReport;

    private String nominativoExport;

    private String identificativoFlusso;

    private String causaleProvvisorio;

    private String iuv;

    private String idCodiceVersamento;

    private String idStatoFlusso;

    private String psp;

    /**
     * @return the idEnte
     */
    public String getIdEnte () {
        return idEnte;
    }

    /**
     * @param idEnte the idEnte to set
     */
    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    /**
     * @return the codiceFiscaleEnte
     */
    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    /**
     * @param codiceFiscaleEnte the codiceFiscaleEnte to set
     */
    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    /**
     * @return the idUtente
     */
    public String getIdUtente () {
        return idUtente;
    }

    /**
     * @param idUtente the idUtente to set
     */
    public void setIdUtente ( String idUtente ) {
        this.idUtente = idUtente;
    }

    /**
     * @return the codiceFiscaleUtente
     */
    public String getCodiceFiscaleUtente () {
        return codiceFiscaleUtente;
    }

    /**
     * @param codiceFiscaleUtente the codiceFiscaleUtente to set
     */
    public void setCodiceFiscaleUtente ( String codiceFiscaleUtente ) {
        this.codiceFiscaleUtente = codiceFiscaleUtente;
    }

    /**
     * @return the idStato
     */
    public String getIdStato () {
        return idStato;
    }

    /**
     * @param idStato the idStato to set
     */
    public void setIdStato ( String idStato ) {
        this.idStato = idStato;
    }

    /**
     * @return the idFile
     */
    public String getIdFile () {
        return idFile;
    }

    /**
     * @param idFile the idFile to set
     */
    public void setIdFile ( String idFile ) {
        this.idFile = idFile;
    }

    /**
     * @return the idTipoFile
     */
    public String getIdTipoFile () {
        return idTipoFile;
    }

    /**
     * @param idTipoFile the idTipoFile to set
     */
    public void setIdTipoFile ( String idTipoFile ) {
        this.idTipoFile = idTipoFile;
    }

    /**
     * @return the idTipoReport
     */
    public String getIdTipoReport () {
        return idTipoReport;
    }

    /**
     * @param idTipoReport the idTipoReport to set
     */
    public void setIdTipoReport ( String idTipoReport ) {
        this.idTipoReport = idTipoReport;
    }

    /**
     * @return the nominativoExport
     */
    public String getNominativoExport () {
        return nominativoExport;
    }

    /**
     * @param nominativoExport the nominativoExport to set
     */
    public void setNominativoExport ( String nominativoExport ) {
        this.nominativoExport = nominativoExport;
    }

    /**
     * @return the identificativoFlusso
     */
    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    /**
     * @param identificativoFlusso the identificativoFlusso to set
     */
    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    /**
     * @return the causaleProvvisorio
     */
    public String getCausaleProvvisorio () {
        return causaleProvvisorio;
    }

    /**
     * @param causaleProvvisorio the causaleProvvisorio to set
     */
    public void setCausaleProvvisorio ( String causaleProvvisorio ) {
        this.causaleProvvisorio = causaleProvvisorio;
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
     * @return the idCodiceVersamento
     */
    public String getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    /**
     * @param idCodiceVersamento the idCodiceVersamento to set
     */
    public void setIdCodiceVersamento ( String idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    /**
     * @return the idStatoFlusso
     */
    public String getIdStatoFlusso () {
        return idStatoFlusso;
    }

    /**
     * @param idStatoFlusso the idStatoFlusso to set
     */
    public void setIdStatoFlusso ( String idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    /**
     * @return the psp
     */
    public String getPsp () {
        return psp;
    }

    /**
     * @param psp the psp to set
     */
    public void setPsp ( String psp ) {
        this.psp = psp;
    }

    //	public List<DTODatiFiltroReport> buildDatiFiltroReportDto() {
    //    	List<DTODatiFiltroReport> datiFiltroReportDtoList = new ArrayList<DTODatiFiltroReport>();
    //    	
    //    	
    //    	//IDENTIFICATIVO_FLUSSO
    //    	if(null!= getIdentificativoFlusso() && !"".equals(getIdentificativoFlusso())) {
    //    		
    ////    		DTODatiFiltroReport filtro= new DTODatiFiltroReport();
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.IDENTIFICATIVO_FLUSSO ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
    //    								.withValore( getIdentificativoFlusso() )
    //    								.build());
    //    	}
    //    	
    //    	//IUV
    ////    	if( !StringUtils.isEmpty(getIuv()) ) {
    //    		if(null!= getIuv() && !"".equals(getIuv())) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.IUV ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
    //    								.withValore( getIuv() )
    //    								.build());
    //    	}
    //    	
    //    	//ID_CODICE_VERSAMENTO
    //    	if( null!= getIdCodiceVersamento() && !"".equals(getIdCodiceVersamento())) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.ID_CODICE_VERSAMENTO ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.INTEGER )
    //    								.withValore(  getIdCodiceVersamento()  )
    //    								.build());
    //    	}
    //    	
    //    	//ID_STATO_FLUSSO
    //    	if( null!= getIdCodiceVersamento() && !"".equals(getIdCodiceVersamento())) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.ID_STATO_FLUSSO ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.INTEGER )
    //    								.withValore( getIdStatoFlusso()  )
    //    								.build());
    //    	}
    //    	
    //    	//DATA_ELABORAZIONE_DA
    //    	if( null!= getDataElaborazioneDa()) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.DATA_ELABORAZIONE_DA ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
    //    								.withValore( String.valueOf ( getDataElaborazioneDa().getTime() )  )
    //    								.build());
    //    	}
    //    	
    //    	//DATA_ELABORAZIONE_A
    //    	if( null!= getDataElaborazioneA()) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.DATA_ELABORAZIONE_A ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
    //    								.withValore( String.valueOf ( getDataElaborazioneA().getTime() )  )
    //    								.build());
    //    	}
    //    	
    //    	//DATA_RICEZIONE_DA
    //    	if( null!= getDataRicezioneDa()) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.DATA_RICEZIONE_DA ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
    //    								.withValore( String.valueOf ( getDataRicezioneDa().getTime() )  )
    //    				
    //    								
    //    								.build());
    //    	}
    //    	
    //    	//DATA_RICEZIONE_A
    //    	if( null!= getDataRicezioneA()) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.DATA_RICEZIONE_A ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.DATE )
    //    								.withValore( String.valueOf ( getDataRicezioneA().getTime() )  )
    //    								.build());
    //    	}
    //    	
    //    	//CODICE_DESCRIZIONE_PSP
    ////    	if( !StringUtils.isEmpty(getPsp()) ) {
    //    		if(null!= getPsp() && !"".equals(getPsp())) {
    //    		
    //    		
    //    		datiFiltroReportDtoList.add(DTODatiFiltroReport.builder()
    //    								.withNomeFiltro( NomeFiltroReportEnum.CODICE_DESCRIZIONE_PSP ) 
    //    								.withTipoCampoFiltro( TipoCampoFiltroEnum.STRING )
    //    								.withValore( getPsp() )
    //    								.build());
    //    	}
    //    	
    //    	
    //    	return datiFiltroReportDtoList;
    //	}

    //	public Long getId() {
    //		return id;
    //	}
    //
    //	public void setId(Long id) {
    //		this.id = id;
    //	}

}
