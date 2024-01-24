/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class PosizioneDebitoriaCaricaRequestDto extends PrincipalDto {

	private static final long serialVersionUID = -155373424466027588L;

	private String posizioneDebitoriaEsterna;

	private String iuv;

	private Integer idEnte;

	private Integer idCov;

	public PosizioneDebitoriaCaricaRequestDto ( String ipAddress, Long idUtente, String codiceFiscaleUtente, String codiceApplicazione ) {
		super ( ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione );
	}

	public PosizioneDebitoriaCaricaRequestDto ( String ipAddress, Long idUtente, String codiceFiscaleUtente, String codiceApplicazione,
					String posizioneDebitoriaEsterna, String iuv, Integer idEnte, Integer idCov ) {
		super ( ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione );
		this.posizioneDebitoriaEsterna = posizioneDebitoriaEsterna;
		this.iuv = iuv;
		this.idEnte = idEnte;
		this.idCov = idCov;
	}

	public String getPosizioneDebitoriaEsterna () {
		return posizioneDebitoriaEsterna;
	}

	public void setPosizioneDebitoriaEsterna ( String posizioneDebitoriaEsterna ) {
		this.posizioneDebitoriaEsterna = posizioneDebitoriaEsterna;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Integer getIdEnte () {
		return idEnte;
	}

	public void setIdEnte ( Integer idEnte ) {
		this.idEnte = idEnte;
	}

	public Integer getIdCov () {
		return idCov;
	}

	public void setIdCov ( Integer idCov ) {
		this.idCov = idCov;
	}
}
