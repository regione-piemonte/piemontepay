/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import it.csi.epay.epaymodric.business.epaymodric.model.DatiTecniciParentEntity;


/**
 *
 * @ author vsgro
 */
public class FlussoOrigine extends Flusso {

    private static final long serialVersionUID = -5207901778751393812L;

    private String identificativoIstitutoRicevente;

    private String identificativoIstitutoRiceventeDescrizione;

    private String identificativoPsp;

    private String identificativoPspDescrizione;

    private BigDecimal importoTotalePagamenti;

    private Integer numeroTotalePagamenti;

    private String ibanRiversamentoFlusso;

    private Timestamp dataOraFlusso;

    private Timestamp dataInserimento;

    private String xmlFlusso;

    private Integer contatoreTentativi;

    //Nuru 2.2.12
    private Integer idStatoFlusso;

    private Integer numeroTotalePagamentiIntermediati;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Date dataRegolamento;

    private String identificativoUnivocoRegolamento;

    private Date dataPagamentoCalcolata;

    private TipoAcqusizioneDTO tipoAcqusizione;

    private BigDecimal importoTotalePagamentiSconosciuti;

    private BigDecimal importoTotalePagamentiNonIntermediati;

    private Integer numeroTotalePagamentiSconosciuti;

    private Integer numeroTotalePagamentiNonIntermediati;
    
    
    public String getIdentificativoIstitutoRicevente () {
        return identificativoIstitutoRicevente;
    }

    public void setIdentificativoIstitutoRicevente ( String identificativoIstitutoRicevente ) {
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public Timestamp getDataOraFlusso () {
        return dataOraFlusso;
    }

    public void setDataOraFlusso ( Timestamp dataOraFlusso ) {
        this.dataOraFlusso = dataOraFlusso;
    }

    @Override
    public Timestamp getDataInserimento () {
        return dataInserimento;
    }

    @Override
    public void setDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public Integer getIdStatoFlusso () {
        return idStatoFlusso;
    }

    public void setIdStatoFlusso ( Integer idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public String getXmlFlusso () {
        return xmlFlusso;
    }

    public void setXmlFlusso ( String xmlFlusso ) {
        this.xmlFlusso = xmlFlusso;
    }

    public Integer getContatoreTentativi () {
        return contatoreTentativi;
    }

    public void setContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }

    @Override
    public int compareTo ( Flusso toCompare ) {
        int compare = super.compareTo ( toCompare );
        FlussoOrigine origineToCompare = (FlussoOrigine) toCompare;
        compare = compare
                        + this.identificativoIstitutoRicevente.compareTo ( origineToCompare.getIdentificativoIstitutoRicevente () )
                        + this.identificativoPsp.compareTo ( origineToCompare.getIdentificativoPsp () )
                        + this.importoTotalePagamenti.compareTo ( origineToCompare.getImportoTotalePagamenti () )
                        + this.numeroTotalePagamenti.compareTo ( origineToCompare.getNumeroTotalePagamenti () )
                        + this.ibanRiversamentoFlusso.compareTo ( origineToCompare.getIbanRiversamentoFlusso () )
                        + this.dataOraFlusso.compareTo ( origineToCompare.getDataOraFlusso () )
                        + this.dataInserimento.compareTo ( origineToCompare.getDataInserimento () )
                        + this.xmlFlusso.compareTo ( origineToCompare.getXmlFlusso () )
                        + this.contatoreTentativi.compareTo ( origineToCompare.getContatoreTentativi () );
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "\n " );
        stringBuffer.append ( "identificativoIstitutoRicevente: [" + identificativoIstitutoRicevente + "]" );
        stringBuffer.append ( "identificativoPsp: [" + identificativoPsp + "]" );
        stringBuffer.append ( "importoTotalePagamenti: [" + importoTotalePagamenti + "]" );
        stringBuffer.append ( "ibanRiversamentoFlusso: [" + ibanRiversamentoFlusso + "]" );
        if ( dataOraFlusso != null ) {
            stringBuffer.append ( "dataOraFlusso: [" + dataOraFlusso.getTime () + "]" );
        }
        if ( dataInserimento != null ) {
            stringBuffer.append ( "dataInserimento: [" + dataInserimento.getTime () + "]" );
        }
        if ( xmlFlusso != null ) {
            stringBuffer.append ( "xmlFlusso: [" + xmlFlusso.substring ( 0, 50 ) + "]" );
        }
        stringBuffer.append ( "contatoreTentativi: [" + contatoreTentativi + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public void mapBaseFields ( DatiTecniciParentEntity entity ) {
        super.mapBaseFields ( entity );
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Date getDataRegolamento () {
        return dataRegolamento;
    }

    public void setDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public String getIdentificativoIstitutoRiceventeDescrizione () {
        return identificativoIstitutoRiceventeDescrizione;
    }

    public void setIdentificativoIstitutoRiceventeDescrizione ( String identificativoIstitutoRiceventeDescrizione ) {
        this.identificativoIstitutoRiceventeDescrizione = identificativoIstitutoRiceventeDescrizione;
    }

    public String getIdentificativoPspDescrizione () {
        return identificativoPspDescrizione;
    }

    public void setIdentificativoPspDescrizione ( String identificativoPspDescrizione ) {
        this.identificativoPspDescrizione = identificativoPspDescrizione;
    }

    public Date getDataPagamentoCalcolata () {
        return dataPagamentoCalcolata;
    }

    public void setDataPagamentoCalcolata ( Date dataPagamentoCalcolata ) {
        this.dataPagamentoCalcolata = dataPagamentoCalcolata;
    }

    public TipoAcqusizioneDTO getTipoAcqusizione () {
        return tipoAcqusizione;
    }

    public void setTipoAcqusizione ( TipoAcqusizioneDTO tipoAcqusizione ) {
        this.tipoAcqusizione = tipoAcqusizione;
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

}
