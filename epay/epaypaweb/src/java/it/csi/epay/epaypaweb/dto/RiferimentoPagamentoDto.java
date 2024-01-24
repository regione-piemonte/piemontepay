/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;


public class RiferimentoPagamentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String valore;

    public RiferimentoPagamentoDto () {
    }

    public RiferimentoPagamentoDto ( Long id ) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getValore () {
        return valore;
    }

    public void setValore ( String valore ) {
        this.valore = valore;
    }

    @Override
    public String toString () {
        final String COMMA = ", ";
        StringBuilder s = new StringBuilder ();
        s.append ( "{ " );
        s.append ( "id:" ).append ( id ).append ( COMMA );
        s.append ( "nome:" ).append ( quote ( nome ) ).append ( COMMA );
        s.append ( "valore:" ).append ( quote ( valore ) );
        s.append ( " }" );
        return s.toString ();
    }

}
