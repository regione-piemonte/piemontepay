/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.security;

import java.io.Serializable;
import java.security.InvalidParameterException;

import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.GetProfilazioneUtenteCorrenteCduOutputDto;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.GetProfilazioneUtenteCorrenteEnteOutputDto;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.GetProfilazioneUtenteCorrenteOutputDto;


public class PrincipalVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected GetProfilazioneUtenteCorrenteEnteOutputDto ente;

    protected GetProfilazioneUtenteCorrenteOutputDto utente;

    protected String ipOrigine;

    protected String codiceApplicativoChiamante;

    public boolean hasUseCase ( String uc ) {
        if ( uc == null || uc.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        if ( utente == null || utente.getCdu () == null ) {
            return false;
        }

        for ( GetProfilazioneUtenteCorrenteCduOutputDto candidate: utente.getCdu () ) {
            if ( uc.equalsIgnoreCase ( candidate.getCodice () ) ) {
                return true;
            }
        }

        return false;
    }

    public String getIpOrigine () {
        return ipOrigine;
    }

    public void setIpOrigine ( String ipOrigine ) {
        this.ipOrigine = ipOrigine;
    }

    public String getCodiceApplicativoChiamante () {
        return codiceApplicativoChiamante;
    }

    public void setCodiceApplicativoChiamante ( String codiceApplicativoChiamante ) {
        this.codiceApplicativoChiamante = codiceApplicativoChiamante;
    }

    public GetProfilazioneUtenteCorrenteEnteOutputDto getEnte () {
        return ente;
    }

    public void setEnte ( GetProfilazioneUtenteCorrenteEnteOutputDto ente ) {
        this.ente = ente;
    }

    public GetProfilazioneUtenteCorrenteOutputDto getUtente () {
        return utente;
    }

    public void setUtente ( GetProfilazioneUtenteCorrenteOutputDto utente ) {
        this.utente = utente;
    }

    @Override
    public String toString () {
        return "PrincipalVO [ente=" + ente + ", utente=" + utente + ", ipOrigine=" + ipOrigine + ", codiceApplicativoChiamante="
                        + codiceApplicativoChiamante + "]";
    }

    public PrincipalVO ( GetProfilazioneUtenteCorrenteOutputDto utente, GetProfilazioneUtenteCorrenteEnteOutputDto ente, String ipOrigine,
        String codiceApplicativoChiamante ) {
        super ();
        this.ente = ente;
        this.utente = utente;
        this.ipOrigine = ipOrigine;
        this.codiceApplicativoChiamante = codiceApplicativoChiamante;
    }
}
