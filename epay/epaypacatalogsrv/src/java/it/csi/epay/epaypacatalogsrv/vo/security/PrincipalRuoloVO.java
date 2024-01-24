/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.vo.security;

import java.security.InvalidParameterException;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.vo.ParentVO;


public class PrincipalRuoloVO extends ParentVO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private List<PrincipalCduVO> cdu;

    public boolean hasUseCase ( String uc ) {
        if ( uc == null || uc.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        if ( cdu != null ) {
            for ( PrincipalCduVO candidate: cdu ) {
                if ( uc.equalsIgnoreCase ( candidate.getCodice () ) ) {
                    return true;
                }
            }
        }

        return false;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public List<PrincipalCduVO> getCdu () {
        return cdu;
    }

    public void setCdu ( List<PrincipalCduVO> cdu ) {
        this.cdu = cdu;
    }

    @Override
    public String toString () {
        return "PrincipalRuoloVO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", cdu=" + cdu + "]";
    }

}
