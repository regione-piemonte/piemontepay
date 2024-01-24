/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.common.builder;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysimweb.model.flussi.FlussoOrigineVO;


/**
 * builder per la classe {@link FlussoOrigineVO}
 */
public class FlussoOrigineVOBuilder implements AbstractBuilder<FlussoOrigineVO> {

    private Long id;

    private String cfEnteCreditore;

    private Date dataInserimento;

    private Date dataModifica;

    private Date dataOraMessaggio;

    private Date dataRegolamento;

    private String denominazioneEnte;

    private String denominazionePsp;

    private String idMessaggio;

    private String identificativoFlusso;

    private String identificativoPsp;

    private String identificativoUnivocoRegolamento;

    private Double importoTotalePagamenti;

    private Double importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamenti;

    private Integer numeroTotalePagamentiIntermediati;

    private Integer provvisorioAnno;

    private Integer provvisorioNumero;

    private String statoElaborazioneFlusso;

    private String utenteInserimento;

    private String utenteModifica;

    public FlussoOrigineVOBuilder () {
    }

    public FlussoOrigineVOBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }

    public FlussoOrigineVOBuilder withCfEnteCreditore ( String cfEnteCreditore ) {
        this.cfEnteCreditore = cfEnteCreditore;
        return this;
    }

    public FlussoOrigineVOBuilder withDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
        return this;
    }

    public FlussoOrigineVOBuilder withDataInserimento ( XMLGregorianCalendar dataInserimento ) {
        this.dataInserimento = getDate ( dataInserimento );
        return this;
    }

    public FlussoOrigineVOBuilder withDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
        return this;
    }

    public FlussoOrigineVOBuilder withDataModifica ( XMLGregorianCalendar dataModifica ) {
        this.dataModifica = getDate ( dataModifica );
        return this;
    }

    public FlussoOrigineVOBuilder withDataOraMessaggio ( Date dataOraMessaggio ) {
        this.dataOraMessaggio = dataOraMessaggio;
        return this;
    }

    public FlussoOrigineVOBuilder withDataOraMessaggio ( XMLGregorianCalendar dataOraMessaggio ) {
        this.dataOraMessaggio = getDate ( dataOraMessaggio );
        return this;
    }

    public FlussoOrigineVOBuilder withDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
        return this;
    }

    public FlussoOrigineVOBuilder withDataRegolamento ( XMLGregorianCalendar dataRegolamento ) {
        this.dataRegolamento = getDate ( dataRegolamento );;
        return this;
    }

    public FlussoOrigineVOBuilder withDenominazioneEnte ( String denominazioneEnte ) {
        this.denominazioneEnte = denominazioneEnte;
        return this;
    }

    public FlussoOrigineVOBuilder withDenominazionePsp ( String denominazionePsp ) {
        this.denominazionePsp = denominazionePsp;
        return this;
    }

    public FlussoOrigineVOBuilder withIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
        return this;
    }

    public FlussoOrigineVOBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public FlussoOrigineVOBuilder withIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
        return this;
    }

    public FlussoOrigineVOBuilder withIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        return this;
    }

    public FlussoOrigineVOBuilder withImportoTotalePagamenti ( Double importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
        return this;
    }

    public FlussoOrigineVOBuilder withImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = ( null != importoTotalePagamenti ? importoTotalePagamenti.doubleValue () : null );
        return this;
    }

    public FlussoOrigineVOBuilder withImportoTotalePagamentiIntermediati ( Double importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        return this;
    }

    public FlussoOrigineVOBuilder withImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = ( null != importoTotalePagamentiIntermediati ? importoTotalePagamentiIntermediati.doubleValue () : null );
        return this;
    }

    public FlussoOrigineVOBuilder withNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        return this;
    }

    public FlussoOrigineVOBuilder withNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        return this;
    }

    public FlussoOrigineVOBuilder withProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
        return this;
    }

    public FlussoOrigineVOBuilder withProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
        return this;
    }

    public FlussoOrigineVOBuilder withStatoElaborazioneFlusso ( String statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
        return this;
    }

    public FlussoOrigineVOBuilder withUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
        return this;
    }

    public FlussoOrigineVOBuilder withUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
        return this;
    }

    @Override
    public FlussoOrigineVO build () {
        return new FlussoOrigineVO ( id, cfEnteCreditore, dataInserimento, dataModifica, dataOraMessaggio, dataRegolamento, denominazioneEnte, denominazionePsp,
            idMessaggio, identificativoFlusso, identificativoPsp, identificativoUnivocoRegolamento, importoTotalePagamenti, importoTotalePagamentiIntermediati,
            numeroTotalePagamenti, numeroTotalePagamentiIntermediati, provvisorioAnno, provvisorioNumero, statoElaborazioneFlusso, utenteInserimento,
            utenteModifica );
    }

}
