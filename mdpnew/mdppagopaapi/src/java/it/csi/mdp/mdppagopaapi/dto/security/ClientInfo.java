/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.dto.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Generated;


/**
 * Classe contenente le informazioni di profilazione sull'utente corrente.
 *
 * Nota: per evitare manipolazioni indesiderate, questa classe e' immutabile e puo' essere costruita attraverso l'apposito Builder.
 */
public class ClientInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String nome = null;

    private String codice = null;

    private Boolean anonimo;

    private List<ScopeDTO> scopes = new LinkedList<> ();

    @Generated ( "SparkTools" )
    private ClientInfo ( Builder builder ) {
        nome = builder.nome;
        codice = builder.codice;
        anonimo = builder.anonimo;
        scopes = builder.scopes != null ? new ArrayList<> ( builder.scopes ) : Collections.emptyList ();
    }

    public boolean hasScope ( String name ) {
        if ( scopes != null ) {
            for ( ScopeDTO candidate: scopes ) {
                if ( candidate.getCodice ().equals ( name ) ) {
                    return true;
                }
            }
        }

        return false;
    }

    public Boolean getAnonimo () {
        return anonimo;
    }

    public String getNome () {
        return nome;
    }

    public String getCodice () {
        return codice;
    }

    public List<ScopeDTO> getScopes () {
        return scopes;
    }

    @Override
    public String toString () {
        return "ClientInfo [nome=" + nome + ", codice=" + codice + "]";
    }

    /**
     * Creates builder to build {@link ClientInfo}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link ClientInfo}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private String nome;

        private String codice;

        private Boolean anonimo;

        private List<ScopeDTO> scopes = Collections.emptyList ();

        private Builder () {
        }

        public Builder withNome ( String nome ) {
            this.nome = nome;
            return this;
        }

        public Builder withCodice ( String codice ) {
            this.codice = codice;
            return this;
        }

        public Builder withAnonimo ( Boolean anonimo ) {
            this.anonimo = anonimo;
            return this;
        }

        public Builder withScopes ( List<ScopeDTO> scopes ) {
            this.scopes = scopes;
            return this;
        }

        public ClientInfo build () {
            return new ClientInfo ( this );
        }
    }

}
