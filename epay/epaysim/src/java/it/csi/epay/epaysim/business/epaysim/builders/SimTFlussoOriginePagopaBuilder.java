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

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;

/**
 * Builder to build {@link SimTFlussoOriginePagopa}.
 */

public final class SimTFlussoOriginePagopaBuilder extends AbstractBuilder<SimTFlussoOriginePagopa> {

    private Long id;

    private String cfEnteCreditore;

    private Timestamp dataOraMessaggio;

    private Date dataRegolamento;

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

    private List<SimTFlussoSintesiPagopa> simTFlussoSintesiPagopas;

    public SimTFlussoOriginePagopaBuilder () {
    }

    public SimTFlussoOriginePagopaBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withCfEnteCreditore ( String cfEnteCreditore ) {
        this.cfEnteCreditore = cfEnteCreditore;
        return this;
    }
    /*
     * public SimTFlussoOriginePagopaBuilder withDataInserimento ( Timestamp dataInserimento ) { this.dataInserimento = dataInserimento; return this; } public
     * SimTFlussoOriginePagopaBuilder withDataModifica ( Timestamp dataModifica ) { this.dataModifica = dataModifica; return this; }
     */

    public SimTFlussoOriginePagopaBuilder withDataOraMessaggio ( Timestamp dataOraMessaggio ) {
        this.dataOraMessaggio = dataOraMessaggio;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withDataOraMessaggio ( XMLGregorianCalendar dataOraMessaggio ) {
        this.dataOraMessaggio = getTimestamp ( getDate ( dataOraMessaggio ) );
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withDataRegolamento ( XMLGregorianCalendar dataRegolamento ) {
        this.dataRegolamento = getDate ( dataRegolamento );
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withDenominazioneEnte ( String denominazioneEnte ) {
        this.denominazioneEnte = denominazioneEnte;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withDenominazionePsp ( String denominazionePsp ) {
        this.denominazionePsp = denominazionePsp;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
        return this;
    }

    public SimTFlussoOriginePagopaBuilder withStatoElaborazioneFlusso ( Boolean statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
        return this;
    }

    //    @Override
    //    public SimTFlussoOriginePagopaBuilder withUtenteInserimento ( String utenteInserimento ) {
    //        this.utenteInserimento = utenteInserimento;
    //        return this;
    //    }
    //
    //    @Override
    //    public SimTFlussoOriginePagopaBuilder withUtenteModifica ( String utenteModifica ) {
    //        this.utenteModifica = utenteModifica;
    //        return this;
    //    }

    public SimTFlussoOriginePagopaBuilder withSimTFlussoSintesiPagopas ( List<SimTFlussoSintesiPagopa> simTFlussoSintesiPagopas ) {
        this.simTFlussoSintesiPagopas = simTFlussoSintesiPagopas;
        return this;
    }

    @Override
    public SimTFlussoOriginePagopa build () {
        return new SimTFlussoOriginePagopa ( id, cfEnteCreditore, dataOraMessaggio, dataRegolamento, denominazioneEnte, denominazionePsp, idMessaggio,
            identificativoFlusso, identificativoPsp, identificativoUnivocoRegolamento, importoTotalePagamenti, importoTotalePagamentiIntermediati,
            numeroTotalePagamenti, numeroTotalePagamentiIntermediati, provvisorioAnno, provvisorioNumero, statoElaborazioneFlusso, simTFlussoSintesiPagopas,
            null == id ? dataInserimento : null, dataModifica, null == id ? utenteInserimento : null, utenteModifica );
    }
}
