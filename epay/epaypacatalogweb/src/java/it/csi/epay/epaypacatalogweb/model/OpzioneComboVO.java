/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OpzioneComboVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codice;

	private String descrizione;

    public static List<OpzioneComboVO> getOpzioni ( List<? extends GenericVO> raw ) {
        List<OpzioneComboVO> output = new ArrayList<> ();

        if ( raw != null ) {
            for ( GenericVO o: raw ) {
                output.add ( new OpzioneComboVO ( o ) );
            }
        }

        return output;
    }

    public OpzioneComboVO() {
        id = null;
        codice = null;
        descrizione = null;
    }

    public OpzioneComboVO(GenericVO obj) {
        id = obj.getId ();
        codice = obj.getCodice ();
        descrizione = obj.getDescrizione ();
    }

	public OpzioneComboVO(Long id, String codice, String descrizione) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public OpzioneComboVO(String codice, String descrizione) {
		id = null;
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public OpzioneComboVO(String descrizione) {
		id = null;
		this.descrizione = descrizione;
		codice = descrizione.toUpperCase()
				.replaceAll("[^A-Z0-9_]", "_")
				.replaceAll("_{2,}", "_");
	}

	public String getCodiceDescrizione() {
	    String s = "";
	    if (this.getCodice () != null && !this.getCodice ().isEmpty ()) {
	        s += this.getCodice ();
	    }

	    if (this.getDescrizione () != null && !this.getDescrizione ().isEmpty ()) {
	        if (s.length () > 0) {
	            s += " - ";
	        }
	        s += this.getDescrizione ();
	    }

        return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
