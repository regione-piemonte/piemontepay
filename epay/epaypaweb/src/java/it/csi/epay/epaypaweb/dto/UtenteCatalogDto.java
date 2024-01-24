/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.util.Date;


public class UtenteCatalogDto extends UtenteDto {

    private static final long serialVersionUID = 1L;

    private String nomeCompleto;

    private String cognome;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    private Date dataInizioValiditaCurrent;

    private Date dataFineValiditaCurrent;

    public UtenteCatalogDto ( Long id, String cod ) {
        super ( id, cod );
    }

    public void rimappaId ( Long nuovoId ) {
        id = nuovoId;
    }

    @Override
    public String getNomeCompleto () {
        return nomeCompleto;
    }

    public void setNomeCompleto ( String nomeCompleto ) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
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

    /**
     * @return the dataInizioValiditaCurrent
     */
    public Date getDataInizioValiditaCurrent () {
        return dataInizioValiditaCurrent;
    }

    /**
     * @param dataInizioValiditaCurrent the dataInizioValiditaCurrent to set
     */
    public void setDataInizioValiditaCurrent ( Date dataInizioValiditaCurrent ) {
        this.dataInizioValiditaCurrent = dataInizioValiditaCurrent;
    }

    /**
     * @return the dataFineValiditaCurrent
     */
    public Date getDataFineValiditaCurrent () {
        return dataFineValiditaCurrent;
    }

    /**
     * @param dataFineValiditaCurrent the dataFineValiditaCurrent to set
     */
    public void setDataFineValiditaCurrent ( Date dataFineValiditaCurrent ) {
        this.dataFineValiditaCurrent = dataFineValiditaCurrent;
    }

    @Override
    public String toString () {
        final String COMMA = ", ";
        StringBuilder s = new StringBuilder ();
        s.append ( "{ " );
        s.append ( "id:" ).append ( getId () ).append ( COMMA );
        s.append ( "cod:" ).append ( quote ( getCod () ) ).append ( COMMA );
        s.append ( "nome:" ).append ( quote ( getNome () ) ).append ( COMMA );
        s.append ( "cognome:" ).append ( quote ( cognome ) ).append ( COMMA );
        s.append ( "nomeCompleto:" ).append ( quote ( nomeCompleto ) ).append ( COMMA );
        s.append ( "dataInizioValidita:" ).append ( quote ( dataInizioValidita != null ? dataInizioValidita.toString () : "" ) ).append ( COMMA );
        s.append ( "dataFineValidita:" ).append ( quote ( dataFineValidita != null ? dataFineValidita.toString () : "" ) );
        s.append ( "dataInizioValiditaCurrent:" ).append ( quote ( dataInizioValiditaCurrent != null ? dataInizioValiditaCurrent.toString () : "" ) )
            .append ( COMMA );
        s.append ( "dataFineValiditaCurrent:" ).append ( quote ( dataFineValiditaCurrent != null ? dataFineValiditaCurrent.toString () : "" ) );
        s.append ( " }" );
        return s.toString ();
    }

}
