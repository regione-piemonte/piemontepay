/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.dto.security;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 * Classe contenente le informazioni di profilazione sull'utente corrente.
 *
 */
public class ClientInfoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String nome = null;

    private String codice = null;

    private Boolean anonimo;

    private List<ScopeDTO> scopes = new LinkedList<> ();

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public Boolean getAnonimo () {
        return anonimo;
    }

    public void setAnonimo ( Boolean anonimo ) {
        this.anonimo = anonimo;
    }

    public List<ScopeDTO> getScopes () {
        return scopes;
    }

    public void setScopes ( List<ScopeDTO> scopes ) {
        this.scopes = scopes;
    }

}
