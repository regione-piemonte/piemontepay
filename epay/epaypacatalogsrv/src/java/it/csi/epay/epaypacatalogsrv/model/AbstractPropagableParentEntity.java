/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractPropagableParentEntity extends AbstractChangeTrackedParentEntity {

    //uni-directional many-to-one association to StatoAggiornamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_stato_aggiornamento", referencedColumnName = "codice" )
    private StatoAggiornamento statoAggiornamento;

    @Column ( name = "descrizione_errore_aggiornamento" )
    private String descrizioneErroreAggiornamento;

    public String getDescrizioneEsitoAggiornamento() {
        StatoAggiornamento stat = getStatoAggiornamento ();
        String descrStato = "NONE";

        if(stat != null) {
            descrStato = getStatoAggiornamento ().getDescrizione ();
        }
        
        if ( descrizioneErroreAggiornamento != null && !descrizioneErroreAggiornamento.trim ().isEmpty () ) {
            return descrStato + " - " + getDescrizioneErroreAggiornamento () ;
        } else {
            return descrStato;
        }

    }

    public StatoAggiornamento getStatoAggiornamento () {
        return statoAggiornamento;
    }

    public void setStatoAggiornamento ( StatoAggiornamento statoAggiornamento ) {
        this.statoAggiornamento = statoAggiornamento;
    }

    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

	@Override
	public String toString() {
		return "AbstractPropagableParentEntity [statoAggiornamento=" + statoAggiornamento
				+ ", descrizioneErroreAggiornamento=" + descrizioneErroreAggiornamento + "]";
	}

}
