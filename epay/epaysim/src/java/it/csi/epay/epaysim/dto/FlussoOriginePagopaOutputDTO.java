/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *
 */
@XmlAccessorType ( XmlAccessType.FIELD )
public class FlussoOriginePagopaOutputDTO extends ParentOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String cfEnteCreditore;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataInserimento;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataModifica;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataOraMessaggio;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataRegolamento;

    private String denominazioneEnte;

    private String denominazionePsp;

    private String idMessaggio;

    private String identificativoFlusso;

    private String identificativoPsp;

    private String identificativoUnivocoRegolamento;

    private BigDecimal importoTotalePagamenti;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamenti;

    private Integer numeroTotalePagamentiIntermediati;

    private Integer provvisorioAnno;

    private Integer provvisorioNumero;

    private Boolean statoElaborazioneFlusso;

    private String utenteInserimento;

    private String utenteModifica;

    private List<FlussoSintesiPagopaOutputDTO> listFlussoSintesiPagopaOutputDTO;

    public FlussoOriginePagopaOutputDTO () {
    }

    public FlussoOriginePagopaOutputDTO ( Long id, String cfEnteCreditore, XMLGregorianCalendar dataInserimento, XMLGregorianCalendar dataModifica,
        XMLGregorianCalendar dataOraMessaggio, XMLGregorianCalendar dataRegolamento, String denominazioneEnte, String denominazionePsp, String idMessaggio,
        String identificativoFlusso, String identificativoPsp, String identificativoUnivocoRegolamento, BigDecimal importoTotalePagamenti,
        BigDecimal importoTotalePagamentiIntermediati, Integer numeroTotalePagamenti, Integer numeroTotalePagamentiIntermediati, Integer provvisorioAnno,
        Integer provvisorioNumero, Boolean statoElaborazioneFlusso, String utenteInserimento, String utenteModifica,
        List<FlussoSintesiPagopaOutputDTO> listFlussoSintesiPagopaOutputDTO ) {
        super ();
        this.id = id;
        this.cfEnteCreditore = cfEnteCreditore;
        this.dataInserimento = dataInserimento;
        this.dataModifica = dataModifica;
        this.dataOraMessaggio = dataOraMessaggio;
        this.dataRegolamento = dataRegolamento;
        this.denominazioneEnte = denominazioneEnte;
        this.denominazionePsp = denominazionePsp;
        this.idMessaggio = idMessaggio;
        this.identificativoFlusso = identificativoFlusso;
        this.identificativoPsp = identificativoPsp;
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        this.importoTotalePagamenti = importoTotalePagamenti;
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        this.provvisorioAnno = provvisorioAnno;
        this.provvisorioNumero = provvisorioNumero;
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
        this.utenteInserimento = utenteInserimento;
        this.utenteModifica = utenteModifica;
        this.listFlussoSintesiPagopaOutputDTO = listFlussoSintesiPagopaOutputDTO;
    }

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the cfEnteCreditore
     */
    public String getCfEnteCreditore () {
        return cfEnteCreditore;
    }

    /**
     * @param cfEnteCreditore the cfEnteCreditore to set
     */
    public void setCfEnteCreditore ( String cfEnteCreditore ) {
        this.cfEnteCreditore = cfEnteCreditore;
    }

    /**
     * @return the dataInserimento
     */
    public XMLGregorianCalendar getDataInserimento () {
        return dataInserimento;
    }

    /**
     * @param dataInserimento the dataInserimento to set
     */
    public void setDataInserimento ( XMLGregorianCalendar dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    /**
     * @return the dataModifica
     */
    public XMLGregorianCalendar getDataModifica () {
        return dataModifica;
    }

    /**
     * @param dataModifica the dataModifica to set
     */
    public void setDataModifica ( XMLGregorianCalendar dataModifica ) {
        this.dataModifica = dataModifica;
    }

    /**
     * @return the dataOraMessaggio
     */
    public XMLGregorianCalendar getDataOraMessaggio () {
        return dataOraMessaggio;
    }

    /**
     * @param dataOraMessaggio the dataOraMessaggio to set
     */
    public void setDataOraMessaggio ( XMLGregorianCalendar dataOraMessaggio ) {
        this.dataOraMessaggio = dataOraMessaggio;
    }

    /**
     * @return the dataRegolamento
     */
    public XMLGregorianCalendar getDataRegolamento () {
        return dataRegolamento;
    }

    /**
     * @param dataRegolamento the dataRegolamento to set
     */
    public void setDataRegolamento ( XMLGregorianCalendar dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    /**
     * @return the denominazioneEnte
     */
    public String getDenominazioneEnte () {
        return denominazioneEnte;
    }

    /**
     * @param denominazioneEnte the denominazioneEnte to set
     */
    public void setDenominazioneEnte ( String denominazioneEnte ) {
        this.denominazioneEnte = denominazioneEnte;
    }

    /**
     * @return the denominazionePsp
     */
    public String getDenominazionePsp () {
        return denominazionePsp;
    }

    /**
     * @param denominazionePsp the denominazionePsp to set
     */
    public void setDenominazionePsp ( String denominazionePsp ) {
        this.denominazionePsp = denominazionePsp;
    }

    /**
     * @return the idMessaggio
     */
    public String getIdMessaggio () {
        return idMessaggio;
    }

    /**
     * @param idMessaggio the idMessaggio to set
     */
    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
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
     * @return the identificativoPsp
     */
    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    /**
     * @param identificativoPsp the identificativoPsp to set
     */
    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    /**
     * @return the identificativoUnivocoRegolamento
     */
    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    /**
     * @param identificativoUnivocoRegolamento the identificativoUnivocoRegolamento to set
     */
    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    /**
     * @return the importoTotalePagamenti
     */
    public BigDecimal getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    /**
     * @param importoTotalePagamenti the importoTotalePagamenti to set
     */
    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    /**
     * @return the importoTotalePagamentiIntermediati
     */
    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return importoTotalePagamentiIntermediati;
    }

    /**
     * @param importoTotalePagamentiIntermediati the importoTotalePagamentiIntermediati to set
     */
    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    /**
     * @return the numeroTotalePagamenti
     */
    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    /**
     * @param numeroTotalePagamenti the numeroTotalePagamenti to set
     */
    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    /**
     * @return the numeroTotalePagamentiIntermediati
     */
    public Integer getNumeroTotalePagamentiIntermediati () {
        return numeroTotalePagamentiIntermediati;
    }

    /**
     * @param numeroTotalePagamentiIntermediati the numeroTotalePagamentiIntermediati to set
     */
    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    /**
     * @return the provvisorioAnno
     */
    public Integer getProvvisorioAnno () {
        return provvisorioAnno;
    }

    /**
     * @param provvisorioAnno the provvisorioAnno to set
     */
    public void setProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    /**
     * @return the provvisorioNumero
     */
    public Integer getProvvisorioNumero () {
        return provvisorioNumero;
    }

    /**
     * @param provvisorioNumero the provvisorioNumero to set
     */
    public void setProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
    }

    /**
     * @return the statoElaborazioneFlusso
     */
    public Boolean getStatoElaborazioneFlusso () {
        return statoElaborazioneFlusso;
    }

    /**
     * @param statoElaborazioneFlusso the statoElaborazioneFlusso to set
     */
    public void setStatoElaborazioneFlusso ( Boolean statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
    }

    /**
     * @return the utenteInserimento
     */
    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    /**
     * @param utenteInserimento the utenteInserimento to set
     */
    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    /**
     * @return the utenteModifica
     */
    public String getUtenteModifica () {
        return utenteModifica;
    }

    /**
     * @param utenteModifica the utenteModifica to set
     */
    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

    /**
     * @return the simTFlussoSintesiPagopas
     */
    public List<FlussoSintesiPagopaOutputDTO> getListFlussoSintesiPagopaOutputDTO () {
        return listFlussoSintesiPagopaOutputDTO;
    }

    /**
     * @param simTFlussoSintesiPagopas the simTFlussoSintesiPagopas to set
     */
    public void setListFlussoSintesiPagopaOutputDTO ( List<FlussoSintesiPagopaOutputDTO> listFlussoSintesiPagopaOutputDTO ) {
        this.listFlussoSintesiPagopaOutputDTO = listFlussoSintesiPagopaOutputDTO;
    }

}
