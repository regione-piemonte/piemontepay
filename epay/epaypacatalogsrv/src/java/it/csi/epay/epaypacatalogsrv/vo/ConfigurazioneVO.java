/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.vo;

import java.io.Serializable;


public class ConfigurazioneVO implements Serializable, Comparable<ConfigurazioneVO> {

    private static final long serialVersionUID = -8632080607351435058L;

    private Integer id;

    private Long idEnte;

    private String codice;

    private String valore;

    public boolean isEmpty () {
        return ( valore == null || valore.trim ().isEmpty () );
    }

    public Long asLong ( Long defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return Long.valueOf ( valore );
        }
    }

    public Integer asInteger ( Integer defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return Integer.valueOf ( valore );
        }
    }

    public Boolean asBoolean ( Boolean defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return Boolean.valueOf ( valore );
        }
    }

    public String asString ( String defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return valore;
        }
    }

    public String asString () {
        return asString ( null );
    }

    public Long asLong () {
        return asLong ( null );
    }

    public Integer asInteger () {
        return asInteger ( null );
    }

    public Boolean asBoolean () {
        return asBoolean ( null );
    }

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public Long getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( Long idEnte ) {
        this.idEnte = idEnte;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getValore () {
        return valore;
    }

    public void setValore ( String valore ) {
        this.valore = valore;
    }

    @Override
    public int compareTo ( ConfigurazioneVO toCompare ) {
        int compare = ( id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
            + idEnte.compareTo ( toCompare.getIdEnte () )
            + codice.compareTo ( toCompare.getCodice () )
            + valore.compareTo ( toCompare.getValore () );
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "idEnte: [" + idEnte + "]" );
        stringBuffer.append ( "chiave: [" + codice + "]" );
        stringBuffer.append ( "valore: [" + valore + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( codice == null ) ? 0 : codice.hashCode () );
        result = prime * result + ( ( valore == null ) ? 0 : valore.hashCode () );
        return result;
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
        ConfigurazioneVO other = (ConfigurazioneVO) obj;
        if ( id == null ) {
            if ( other.id != null ) {
                return false;
            }
        } else if ( !id.equals ( other.id ) ) {
            return false;
        }
        if ( idEnte == null ) {
            if ( other.idEnte != null ) {
                return false;
            }
        } else if ( !idEnte.equals ( other.idEnte ) ) {
            return false;
        }
        if ( codice == null ) {
            if ( other.codice != null ) {
                return false;
            }
        } else if ( !codice.equals ( other.codice ) ) {
            return false;
        }
        if ( valore == null ) {
            if ( other.valore != null ) {
                return false;
            }
        } else if ( !valore.equals ( other.valore ) ) {
            return false;
        }
        return true;
    }

}
