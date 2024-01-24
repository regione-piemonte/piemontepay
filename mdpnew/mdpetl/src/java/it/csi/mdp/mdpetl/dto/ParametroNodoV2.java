/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.util.ArrayList;
import java.util.List;


public class ParametroNodoV2 extends BaseDTO {

    private static final long serialVersionUID = -5337439484416182209L;

    private String portaDiDominio;

    private String identificativoDominio;

    private String identificativointermediarioPA;

    private String passwordDominioNodoSpc;

    private List<String> identificativiStazioneIntermediarioPA = new ArrayList<String> ();

    public List<String> getIdentificativiStazioneIntermediarioPA () {
        return identificativiStazioneIntermediarioPA;
    }

    public void setIdentificativiStazioneIntermediarioPA ( List<String> identificativiStazioneIntermediarioPA ) {
        this.identificativiStazioneIntermediarioPA = identificativiStazioneIntermediarioPA;
    }

    public String getPortaDiDominio () {
        return portaDiDominio;
    }

    public void setPortaDiDominio ( String portaDiDominio ) {
        this.portaDiDominio = portaDiDominio;
    }

    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }

    public String getIdentificativointermediarioPA () {
        return identificativointermediarioPA;
    }

    public void setIdentificativointermediarioPA ( String identificativointermediarioPA ) {
        this.identificativointermediarioPA = identificativointermediarioPA;
    }

    public String getPasswordDominioNodoSpc () {
        return passwordDominioNodoSpc;
    }

    public void setPasswordDominioNodoSpc ( String passwordDominioNodoSpc ) {
        this.passwordDominioNodoSpc = passwordDominioNodoSpc;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( identificativiStazioneIntermediarioPA == null ) ? 0 : identificativiStazioneIntermediarioPA.hashCode () );
        result = prime * result + ( ( identificativoDominio == null ) ? 0 : identificativoDominio.hashCode () );
        result = prime * result + ( ( identificativointermediarioPA == null ) ? 0 : identificativointermediarioPA.hashCode () );
        result = prime * result + ( ( passwordDominioNodoSpc == null ) ? 0 : passwordDominioNodoSpc.hashCode () );
        result = prime * result + ( ( portaDiDominio == null ) ? 0 : portaDiDominio.hashCode () );
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
        ParametroNodoV2 other = (ParametroNodoV2) obj;
        if ( identificativiStazioneIntermediarioPA == null ) {
            if ( other.identificativiStazioneIntermediarioPA != null ) {
                return false;
            }
        } else if ( !identificativiStazioneIntermediarioPA.equals ( other.identificativiStazioneIntermediarioPA ) ) {
            return false;
        }
        if ( identificativoDominio == null ) {
            if ( other.identificativoDominio != null ) {
                return false;
            }
        } else if ( !identificativoDominio.equals ( other.identificativoDominio ) ) {
            return false;
        }
        if ( identificativointermediarioPA == null ) {
            if ( other.identificativointermediarioPA != null ) {
                return false;
            }
        } else if ( !identificativointermediarioPA.equals ( other.identificativointermediarioPA ) ) {
            return false;
        }
        if ( passwordDominioNodoSpc == null ) {
            if ( other.passwordDominioNodoSpc != null ) {
                return false;
            }
        } else if ( !passwordDominioNodoSpc.equals ( other.passwordDominioNodoSpc ) ) {
            return false;
        }
        if ( portaDiDominio == null ) {
            if ( other.portaDiDominio != null ) {
                return false;
            }
        } else if ( !portaDiDominio.equals ( other.portaDiDominio ) ) {
            return false;
        }
        return true;
    }

}
