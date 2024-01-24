/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.vo.security;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.vo.ParentVO;


public class PrincipalFruitoreVO extends ParentVO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    private List<PrincipalScopeVO> scope = new ArrayList<> ();

    public boolean hasScope ( String required ) {
        if ( required == null || required.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        if ( scope != null ) {
            for ( PrincipalScopeVO candidate: scope ) {
                if ( required.equalsIgnoreCase ( candidate.getCodice () ) ) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValid () {
        if ( dataInizioValidita == null ) {
            return false;
        }

        LocalDate dataInizioValiditaLD;
        LocalDate dataFineValiditaLD;

        if ( dataInizioValidita instanceof java.sql.Date ) {
            dataInizioValiditaLD = ( (java.sql.Date) dataInizioValidita ).toLocalDate ();
        } else {
            dataInizioValiditaLD = dataInizioValidita.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
        }

        if ( dataFineValidita == null ) {
            dataFineValiditaLD = null;
        } else if ( dataFineValidita instanceof java.sql.Date ) {
            dataFineValiditaLD = ( (java.sql.Date) dataFineValidita ).toLocalDate ();
        } else {
            dataFineValiditaLD = dataFineValidita.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
        }

        LocalDate now = LocalDate.now ();

        if ( !now.isBefore ( dataInizioValiditaLD ) &&
            ( dataFineValiditaLD == null || dataFineValiditaLD.isAfter ( now ) ) ) {
            return true;
        }

        return false;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public List<PrincipalScopeVO> getScope () {
        return scope;
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

    @Override
    public String toString () {
        return "PrincipalFruitoreVO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", scope=" + scope + "]";
    }

}
