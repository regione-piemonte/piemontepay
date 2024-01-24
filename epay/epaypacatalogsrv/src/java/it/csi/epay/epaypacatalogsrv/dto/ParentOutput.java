/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;
import java.security.InvalidParameterException;

import it.csi.epay.epaypacatalogsrv.exception.CodedException;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


public class ParentOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings ( "unchecked" )
    public static <T extends ParentOutput> T fail ( Exception e, Class<? extends ParentOutput> clazz ) {

        ParentOutput output;
        try {
            output = clazz.newInstance ();
        } catch ( InstantiationException | IllegalAccessException e1 ) {
            throw new RuntimeException ( e1 );
        }

        if ( e instanceof CodedException ) {
            CodedException ec = (CodedException) e;
            output.setCodiceEsito ( ec.getResultCode () );
            output.setDescrizioneEsito ( ec.getMessage () );
            output.setCodiceStato ( ec.getStatusCode () );
            output.setCodiceMessaggio ( ec.getMessageKey () );
        } else if ( e instanceof InvalidParameterException ) {
            output.setCodiceEsito ( Constants.RESULT_CODES.BAD_REQUEST );
            output.setDescrizioneEsito ( e.getMessage () != null ? e.getMessage () : null );
            output.setCodiceStato ( 400 );
        } else {
            output.setCodiceEsito ( Constants.RESULT_CODES.INTERNAL_ERROR );
            output.setDescrizioneEsito ( null );
            output.setCodiceStato ( 500 );
        }

        return (T) output;
    }

    @SuppressWarnings ( "unchecked" )
    public static <T extends ParentOutput> T ok ( Class<? extends ParentOutput> T ) {
        T output;
        try {
            output = (T) T.newInstance ();
        } catch ( InstantiationException | IllegalAccessException e ) {
            throw new RuntimeException ( e );
        }

        output.setCodiceEsito ( Constants.RESULT_CODES.OK );
        output.setDescrizioneEsito ( null );
        output.setCodiceStato ( 200 );
        output.setCodiceMessaggio ( Constants.RESULT_CODES.OK );

        return output;
    }

    private Integer codiceStato;

    private String codiceEsito;

    private String codiceMessaggio;

    private String descrizioneEsito;

    public String getCodiceMessaggio () {
        return codiceMessaggio;
    }

    public void setCodiceMessaggio ( String codiceMessaggio ) {
        this.codiceMessaggio = codiceMessaggio;
    }

    public Integer getCodiceStato () {
        return codiceStato;
    }

    public void setCodiceStato ( Integer codiceStato ) {
        this.codiceStato = codiceStato;
    }

    public String getCodiceEsito () {
        return codiceEsito;
    }

    public void setCodiceEsito ( String codiceEsito ) {
        this.codiceEsito = codiceEsito;
    }

    public String getDescrizioneEsito () {
        return descrizioneEsito;
    }

    public void setDescrizioneEsito ( String descrizioneEsito ) {
        this.descrizioneEsito = descrizioneEsito;
    }

    @Override
    public String toString () {
        return "ParentOutput [codiceStato=" + codiceStato + ", codiceEsito=" + codiceEsito + ", codiceMessaggio=" + codiceMessaggio + ", descrizioneEsito="
            + descrizioneEsito + "]";
    }

}
