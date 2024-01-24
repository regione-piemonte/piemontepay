/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.builders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;


/**
 *
 */
public class FlussoOriginePagopaOutputDTOBuilder extends AbstractBuilder<FlussoOriginePagopaOutputDTO> {

    private Long id;

    private String cfEnteCreditore;

    private XMLGregorianCalendar dataInserimento;

    private XMLGregorianCalendar dataModifica;

    private XMLGregorianCalendar dataOraMessaggio;

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

    public FlussoOriginePagopaOutputDTOBuilder () {
    }

    public FlussoOriginePagopaOutputDTOBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withCfEnteCreditore ( String cfEnteCreditore ) {
        this.cfEnteCreditore = cfEnteCreditore;
        return this;
    }

    @Override
    public FlussoOriginePagopaOutputDTOBuilder withDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = getXMLGregorianCalendar ( dataInserimento );
        return this;
    }

    @Override
    public FlussoOriginePagopaOutputDTOBuilder withDataModifica ( Date dataModifica ) {
        this.dataModifica = getXMLGregorianCalendar ( dataModifica );;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withDataOraMessaggio ( XMLGregorianCalendar dataOraMessaggio ) {
        this.dataOraMessaggio = dataOraMessaggio;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withDataOraMessaggio ( Timestamp dataOraMessaggio ) {
        this.dataOraMessaggio = getXMLGregorianCalendar ( dataOraMessaggio );
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withDataRegolamento ( XMLGregorianCalendar dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = getXMLGregorianCalendar ( dataRegolamento );
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withDenominazioneEnte ( String denominazioneEnte ) {
        this.denominazioneEnte = denominazioneEnte;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withDenominazionePsp ( String denominazionePsp ) {
        this.denominazionePsp = denominazionePsp;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withStatoElaborazioneFlusso ( Boolean statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
        return this;
    }

    @Override
    public FlussoOriginePagopaOutputDTOBuilder withUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
        return this;
    }

    @Override
    public FlussoOriginePagopaOutputDTOBuilder withUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
        return this;
    }

    public FlussoOriginePagopaOutputDTOBuilder withListFlussoSintesiPagopaOutputDTO ( List<FlussoSintesiPagopaOutputDTO> listFlussoSintesiPagopaOutputDTO ) {
        this.listFlussoSintesiPagopaOutputDTO = listFlussoSintesiPagopaOutputDTO;
        return this;
    }

    @Override
    public FlussoOriginePagopaOutputDTO build () {
        return new FlussoOriginePagopaOutputDTO ( id, cfEnteCreditore, dataInserimento, dataModifica, dataOraMessaggio, dataRegolamento, denominazioneEnte,
            denominazionePsp, idMessaggio, identificativoFlusso, identificativoPsp, identificativoUnivocoRegolamento, importoTotalePagamenti,
            importoTotalePagamentiIntermediati, numeroTotalePagamenti, numeroTotalePagamentiIntermediati, provvisorioAnno, provvisorioNumero,
            statoElaborazioneFlusso, utenteInserimento, utenteModifica, listFlussoSintesiPagopaOutputDTO );
    }
}

