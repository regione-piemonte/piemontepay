/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;

import it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche;

public class RicevuteTelematicheExt  implements  java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4470241619190814689L;

	private RicevuteTelematiche ricevuteTelematiche;
	private byte[] rtDataBin;

	/**
	 * @return the rtDataBin
	 */
	public byte[] getRtDataBin() {
		return rtDataBin;
	}

	/**
	 * @param rtDataBin the rtDataBin to set
	 */
	public void setRtDataBin(byte[] rtDataBin) {
		this.rtDataBin = rtDataBin;
	}

	/**
	 * @return the ricevuteTelematiche
	 */
	public RicevuteTelematiche getRicevuteTelematiche() {
		return ricevuteTelematiche;
	}

	/**
	 * @param ricevuteTelematiche the ricevuteTelematiche to set
	 */
	public void setRicevuteTelematiche(RicevuteTelematiche ricevuteTelematiche) {
		this.ricevuteTelematiche = ricevuteTelematiche;
	}
	
	
}
