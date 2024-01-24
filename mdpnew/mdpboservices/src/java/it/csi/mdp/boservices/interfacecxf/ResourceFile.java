/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.interfacecxf;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * Classe per rappresenta l'oggetto di comunicazione tra client e server
 *
 * @author DP-ISP
 */
@XmlType
public class ResourceFile {

	private String fileName;
	private String fileType;
	private DataHandler fileData;


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	@XmlMimeType("application/octet-stream")
	public DataHandler getFileData() {
		return fileData;
	}

	public void setFileData(DataHandler fileData) {
		this.fileData = fileData;
	}

}

