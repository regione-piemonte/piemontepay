/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoFlussoSintesi" )
public class DTOFlussoSintesi implements Serializable {

    private static final long serialVersionUID = 5336564340601629130L;

    private String id;

    private String identificativoFlusso;

    private String codiceVersamento;

    private String descrizioneVersamento;

    private String importoQuotaAggregazione;

    private String numeroVersamentoQuotaAggregazione;

    private String datiSpecificiDiRiscossione;

    private String provvisorioAnno;

    private String provvisorioNumero;
    
    private String annoAccertamento;
    
    private String numeroAccertamento;
    

    public String getAnnoAccertamento() {
		return annoAccertamento;
	}

	public void setAnnoAccertamento(String annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getNumeroAccertamento() {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento(String numeroAccertamento) {
		this.numeroAccertamento = numeroAccertamento;
	}

	private ArrayList<DTOFlussoDettaglio> listaVersamenti
    = new ArrayList<DTOFlussoDettaglio> ();

    private DTOFlussoOrigine flussoOrigine = new DTOFlussoOrigine ();

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getImportoQuotaAggregazione () {
        return importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione ( String importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public String getNumeroVersamentoQuotaAggregazione () {
        return numeroVersamentoQuotaAggregazione;
    }

    public void setNumeroVersamentoQuotaAggregazione ( String numeroVersamentoQuotaAggregazione ) {
        this.numeroVersamentoQuotaAggregazione = numeroVersamentoQuotaAggregazione;
    }

    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public String getProvvisorioAnno () {
        return provvisorioAnno;
    }

    public void setProvvisorioAnno ( String provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public String getProvvisorioNumero () {
        return provvisorioNumero;
    }

    public void setProvvisorioNumero ( String provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
    }

    public ArrayList<DTOFlussoDettaglio> getListaVersamenti () {
        return listaVersamenti;
    }

    public void setListaVersamenti ( ArrayList<DTOFlussoDettaglio> listaVersamenti ) {
        this.listaVersamenti = listaVersamenti;
    }

    public DTOFlussoOrigine getFlussoOrigine () {
        return flussoOrigine;
    }

    public void setFlussoOrigine ( DTOFlussoOrigine flussoOrigine ) {
        this.flussoOrigine = flussoOrigine;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "identificativoFlusso: [" + identificativoFlusso + "]" );
        stringBuffer.append ( "codiceVersamento: [" + codiceVersamento + "]" );
        stringBuffer.append ( "importoQuotaAggregazione: [" + importoQuotaAggregazione + "]" );
        stringBuffer.append ( "numeroVersamentoQuotaAggregazione: [" + numeroVersamentoQuotaAggregazione + "]" );
        stringBuffer.append ( "datiSpecificiDiRiscossione: [" + datiSpecificiDiRiscossione + "]" );
        stringBuffer.append ( "provvisorioAnno: [" + provvisorioAnno + "]" );
        stringBuffer.append ( "provvisorioNumero: [" + provvisorioNumero + "]" );
        stringBuffer.append ( "numeroAccertamento: [" + numeroAccertamento + "]" );
        stringBuffer.append ( "annoAccertamento: [" + annoAccertamento + "]" );
        
        if ( listaVersamenti != null && listaVersamenti.size () > 0 ) {
            stringBuffer.append ( "listaFlussi: " );
            for ( int i = 0; i < listaVersamenti.size (); i++ ) {
                stringBuffer.append ( "\n [" + listaVersamenti.get ( i ) + "]" );
            }
        }
        if ( flussoOrigine != null ) {
            stringBuffer.append ( "flussoOrigine: [" + flussoOrigine + "]" );
        }
        return stringBuffer.toString ();
    }

    public String getDescrizioneVersamento () {
        return descrizioneVersamento;
    }

    public void setDescrizioneVersamento ( String descrizioneVersamento ) {
        this.descrizioneVersamento = descrizioneVersamento;
    }
}
