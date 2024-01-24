/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.io.Serializable;

public class ComponentiAccertamento implements Serializable {
	
	/**
     *
     */
    private static final long serialVersionUID = -256398724112512035L;

	private Integer progressivo;
	
	private Integer annoEsercizio;
	
	private Integer annoAccertamento;
	
	private String numeroAccertamento;

	public Integer getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	public Integer getAnnoEsercizio() {
		return annoEsercizio;
	}

	public void setAnnoEsercizio(Integer annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}

	public Integer getAnnoAccertamento() {
		return annoAccertamento;
	}

	public void setAnnoAccertamento(Integer annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getNumeroAccertamento() {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento(String numeroAccertamento) {
		this.numeroAccertamento = numeroAccertamento;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( annoAccertamento == null ) ? 0 : annoAccertamento.hashCode () );
        result = prime * result + ( ( numeroAccertamento == null ) ? 0 : numeroAccertamento.hashCode () );
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        ComponentiAccertamento other = (ComponentiAccertamento) obj;
        if ( annoAccertamento == null ) {
            if ( other.annoAccertamento != null )
                return false;
        } else if ( !annoAccertamento.equals ( other.annoAccertamento ) )
            return false;
        if ( numeroAccertamento == null ) {
            if ( other.numeroAccertamento != null )
                return false;
        } else if ( !numeroAccertamento.equals ( other.numeroAccertamento ) )
            return false;
        return true;
    }

}
