/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;

import java.io.Serializable;

public class DownloadData  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2394889420505354840L;
	private String contentType;
    private String contentDisposition;
    private String filename;
    private byte[] bytes;


    /**
     * 
     */
    public DownloadData()
    {
        this.contentDisposition = "attachment"; 
    }
    
    /**
     * @param contentType
     * @param filename
     * @param bytes
     */
    public DownloadData(String contentType, String filename, byte[] bytes)
    {
    	this();
    	
        this.contentType = contentType;
        this.filename = filename;
        this.bytes = bytes;
    }

    /**
     * @return
     */
    public String getContentType()
    {
        return contentType;
    }

    /**
     * @param contentType
     */
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    /**
     * @return
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * @param filename
     */
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    /**
     * @return
     */
    public byte[] getBytes()
    {
        return bytes;
    }

    /**
     * @param bytes
     */
    public void setBytes(byte[] bytes)
    {
        this.bytes = bytes;
    }

	/**
	 * @param contentDisposition
	 */
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	/**
	 * @return
	 */
	public String getContentDisposition() {
		return contentDisposition;
	}
}
