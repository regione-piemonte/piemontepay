/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

import static it.csi.epay.epaypaweb.util.Util.quote;


public class CodiceVersamentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String cod;

    private String des;

    private EnteDto enteDto;

    private Integer idTipoPagamento;

    private Boolean flagBollettinoPostale;

	private Boolean flagMbPrimario;

	private Boolean flagMbSecondario;

    /**
	 * @return the flagBollettinoPostale
	 */
	public Boolean getFlagBollettinoPostale() {
		return flagBollettinoPostale;
	}

	/**
	 * @param flagBollettinoPostale the flagBollettinoPostale to set
	 */
	public void setFlagBollettinoPostale(Boolean flagBollettinoPostale) {
		this.flagBollettinoPostale = flagBollettinoPostale;
	}

    public CodiceVersamentoDto ( Integer id ) {
        this.id = id;
    }

    public Integer getIdTipoPagamento () {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento ( Integer idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public Integer getId () {
        return id;
    }

    public String getCod () {
        return cod;
    }

    public void setCod ( String cod ) {
        this.cod = cod;
    }

    public String getDes () {
        return des;
    }

    public void setDes ( String des ) {
        this.des = des;
    }

    public String getCodDes () {
        return ( cod != null ) ? cod.concat ( " - " ).concat ( des ) : des;
    }

    public EnteDto getEnteDto () {
        return enteDto;
    }

    public void setEnteDto ( EnteDto enteDto ) {
        this.enteDto = enteDto;
    }

    @Override
    public String toString () {
        final String COMMA = ", ";
        StringBuilder s = new StringBuilder ();
        s.append ( "{ " );
        s.append ( "id:" ).append ( id ).append ( COMMA );
        s.append ( "cod:" ).append ( quote ( cod ) ).append ( COMMA );
        s.append ( "des:" ).append ( quote ( des ) ).append ( COMMA );
        s.append ( "enteDto:" ).append ( enteDto );
        s.append ( " }" );
        return s.toString ();
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass () != obj.getClass () ) {
            return false;
        }
        CodiceVersamentoDto other = (CodiceVersamentoDto) obj;
        if ( cod == null ) {
	        return other.cod == null;
        } else return cod.equalsIgnoreCase(other.cod);
    }

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}
}
