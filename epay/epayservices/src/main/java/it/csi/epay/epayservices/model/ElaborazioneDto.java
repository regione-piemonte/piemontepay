/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings ( "unused" )
public class ElaborazioneDto implements Serializable {

	private static final long serialVersionUID = 9143667834560385355L;

	private Long idElaborazione;

	private Date dataAvvio;

	private Date dataTermine;

	private Integer documentiElaborati;

	private Integer documentiArchiviati;

	private Integer documentiNonArchiviati;

	public Long getIdElaborazione () {
		return idElaborazione;
	}

	public void setIdElaborazione ( Long idElaborazione ) {
		this.idElaborazione = idElaborazione;
	}

	public Date getDataAvvio () {
		return dataAvvio;
	}

	public void setDataAvvio ( Date dataAvvio ) {
		this.dataAvvio = dataAvvio;
	}

	public Date getDataTermine () {
		return dataTermine;
	}

	public void setDataTermine ( Date dataTermine ) {
		this.dataTermine = dataTermine;
	}

	public Integer getDocumentiElaborati () {
		return documentiElaborati;
	}

	public void setDocumentiElaborati ( Integer documentiElaborati ) {
		this.documentiElaborati = documentiElaborati;
	}

	public Integer getDocumentiArchiviati () {
		return documentiArchiviati;
	}

	public void setDocumentiArchiviati ( Integer documentiArchiviati ) {
		this.documentiArchiviati = documentiArchiviati;
	}

	public Integer getDocumentiNonArchiviati () {
		return documentiNonArchiviati;
	}

	public void setDocumentiNonArchiviati ( Integer documentiNonArchiviati ) {
		this.documentiNonArchiviati = documentiNonArchiviati;
	}
}
