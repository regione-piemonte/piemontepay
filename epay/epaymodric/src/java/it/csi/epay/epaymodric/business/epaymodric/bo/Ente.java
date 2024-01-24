/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.ModalitaAcquisizioneProvvisoriEnum;


/**
 *
 * @ author vsgro
 */
public class Ente extends BaseBO implements Serializable, Comparable<Ente> {

    private static final long serialVersionUID = 2043148139021148641L;

    private Long id;

    private String codiceFiscale;

    private String denominazione;

    private String emailEnte;

    private Boolean entePlurintermediato;

    private Boolean flagAccertamento;

    private Boolean flagRicezioneErrori;

    private Boolean flagRiconciliazione;

    private int giornoSchedulazione;

    private String ibanTesoreria;

    private String idEnte;

    private ModalitaAcquisizioneProvvisoriEnum modalitaAcquisizioneProvvisori;

    private int periodicitaSchedulazione;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione () {
        return denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public Boolean isFlagAccertamento () {
        return flagAccertamento;
    }

    public void setFlagAccertamento ( Boolean flagAccertamento ) {
        this.flagAccertamento = flagAccertamento;
    }

    public Boolean isFlagRiconciliazione () {
        return flagRiconciliazione;
    }

    public void setFlagRiconciliazione ( Boolean flagRiconciliazione ) {
        this.flagRiconciliazione = flagRiconciliazione;
    }

    public String getIbanTesoreria () {
        return ibanTesoreria;
    }

    public void setIbanTesoreria ( String ibanTesoreria ) {
        this.ibanTesoreria = ibanTesoreria;
    }

    public Boolean isEntePlurintermediato () {
        return entePlurintermediato;
    }

    public void setEntePlurintermediato ( Boolean entePlurintermediato ) {
        this.entePlurintermediato = entePlurintermediato;
    }

    public int getPeriodicitaSchedulazione () {
        return periodicitaSchedulazione;
    }

    public void setPeriodicitaSchedulazione ( int periodicitaSchedulazione ) {
        this.periodicitaSchedulazione = periodicitaSchedulazione;
    }

    public int getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    public void setGiornoSchedulazione ( int giornoSchedulazione ) {
        this.giornoSchedulazione = giornoSchedulazione;
    }

    public ModalitaAcquisizioneProvvisoriEnum getModalitaAcquisizioneProvvisori () {
        return modalitaAcquisizioneProvvisori;
    }

    public void setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisoriEnum modalitaAcquisizioneProvvisori ) {
        this.modalitaAcquisizioneProvvisori = modalitaAcquisizioneProvvisori;
    }

    public Boolean isFlagRicezioneErrori () {
        return flagRicezioneErrori;
    }

    public void setFlagRicezioneErrori ( Boolean flagRicezioneErrori ) {
        this.flagRicezioneErrori = flagRicezioneErrori;
    }

    public String getEmailEnte () {
        return emailEnte;
    }

    public void setEmailEnte ( String emailEnte ) {
        this.emailEnte = emailEnte;
    }

    @Override
    public int compareTo ( Ente toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.codiceFiscale.compareTo ( toCompare.getCodiceFiscale () )
                        + this.denominazione.compareTo ( toCompare.getDenominazione () )
                        + this.emailEnte.compareTo ( toCompare.getEmailEnte () )
                        + this.entePlurintermediato.compareTo ( toCompare.isEntePlurintermediato () )
                        + this.flagAccertamento.compareTo ( toCompare.isFlagAccertamento () )
                        + this.flagRicezioneErrori.compareTo ( toCompare.isFlagRicezioneErrori () )
                        + this.flagRiconciliazione.compareTo ( toCompare.isFlagRiconciliazione () )
                        + ( this.giornoSchedulazione == toCompare.getGiornoSchedulazione () ? 0 : -1 )
                        + this.ibanTesoreria.compareTo ( toCompare.getIbanTesoreria () )
                        + this.idEnte.compareTo ( toCompare.getIdEnte () )
                        + ( ( modalitaAcquisizioneProvvisori != null && toCompare.getModalitaAcquisizioneProvvisori () != null )
                                        ? ( this.modalitaAcquisizioneProvvisori.getCodice ().intValue () == toCompare.getModalitaAcquisizioneProvvisori ().getCodice ()
                                        .intValue () ? 0 : -1 )
                                                        : -1 )
                        + ( this.periodicitaSchedulazione == toCompare.getPeriodicitaSchedulazione () ? 0 : -1 );
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "codiceFiscale: [" + codiceFiscale + "]" );
        stringBuffer.append ( "denominazione: [" + denominazione + "]" );
        stringBuffer.append ( "emailEnte: [" + emailEnte + "]" );
        stringBuffer.append ( "entePlurintermediato: [" + entePlurintermediato + "]" );
        stringBuffer.append ( "flagAccertamento: [" + flagAccertamento + "]" );
        stringBuffer.append ( "flagRicezioneErrori: [" + flagRicezioneErrori + "]" );
        stringBuffer.append ( "flagRiconciliazione: [" + flagRiconciliazione + "]" );
        stringBuffer.append ( "giornoSchedulazione: [" + giornoSchedulazione + "]" );
        stringBuffer.append ( "ibanTesoreria: [" + ibanTesoreria + "]" );
        stringBuffer.append ( "idEnte: [" + idEnte + "]" );
        if (modalitaAcquisizioneProvvisori!=null) {
            stringBuffer.append ( "modalitaAcquisizioneProvvisori: [" + modalitaAcquisizioneProvvisori.getCodice ()+ "]" );
        }
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codiceFiscale == null ) ? 0 : codiceFiscale.hashCode () );
        result = prime * result + ( ( denominazione == null ) ? 0 : denominazione.hashCode () );
        result = prime * result + ( ( emailEnte == null ) ? 0 : emailEnte.hashCode () );
        result = prime * result + ( ( entePlurintermediato == null ) ? 0 : entePlurintermediato.hashCode () );
        result = prime * result + ( ( flagAccertamento == null ) ? 0 : flagAccertamento.hashCode () );
        result = prime * result + ( ( flagRicezioneErrori == null ) ? 0 : flagRicezioneErrori.hashCode () );
        result = prime * result + ( ( flagRiconciliazione == null ) ? 0 : flagRiconciliazione.hashCode () );
        result = prime * result + giornoSchedulazione;
        result = prime * result + ( ( ibanTesoreria == null ) ? 0 : ibanTesoreria.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( modalitaAcquisizioneProvvisori == null ) ? 0 : modalitaAcquisizioneProvvisori.hashCode () );
        result = prime * result + periodicitaSchedulazione;
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        Ente other = (Ente) obj;
        if ( codiceFiscale == null ) {
            if ( other.codiceFiscale != null )
                return false;
        } else if ( !codiceFiscale.equals ( other.codiceFiscale ) )
            return false;
        if ( denominazione == null ) {
            if ( other.denominazione != null )
                return false;
        } else if ( !denominazione.equals ( other.denominazione ) )
            return false;
        if ( emailEnte == null ) {
            if ( other.emailEnte != null )
                return false;
        } else if ( !emailEnte.equals ( other.emailEnte ) )
            return false;
        if ( entePlurintermediato == null ) {
            if ( other.entePlurintermediato != null )
                return false;
        } else if ( !entePlurintermediato.equals ( other.entePlurintermediato ) )
            return false;
        if ( flagAccertamento == null ) {
            if ( other.flagAccertamento != null )
                return false;
        } else if ( !flagAccertamento.equals ( other.flagAccertamento ) )
            return false;
        if ( flagRicezioneErrori == null ) {
            if ( other.flagRicezioneErrori != null )
                return false;
        } else if ( !flagRicezioneErrori.equals ( other.flagRicezioneErrori ) )
            return false;
        if ( flagRiconciliazione == null ) {
            if ( other.flagRiconciliazione != null )
                return false;
        } else if ( !flagRiconciliazione.equals ( other.flagRiconciliazione ) )
            return false;
        if ( giornoSchedulazione != other.giornoSchedulazione )
            return false;
        if ( ibanTesoreria == null ) {
            if ( other.ibanTesoreria != null )
                return false;
        } else if ( !ibanTesoreria.equals ( other.ibanTesoreria ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        if ( idEnte == null ) {
            if ( other.idEnte != null )
                return false;
        } else if ( !idEnte.equals ( other.idEnte ) )
            return false;
        if ( modalitaAcquisizioneProvvisori != other.modalitaAcquisizioneProvvisori )
            return false;
        if ( periodicitaSchedulazione != other.periodicitaSchedulazione )
            return false;
        return true;
    }

}
