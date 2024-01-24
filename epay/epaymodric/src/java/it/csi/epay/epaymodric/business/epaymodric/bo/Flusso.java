/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;


/**
 *
 * @ author vsgro
 */
public class Flusso extends BaseBO implements Serializable, Comparable<Flusso> {

    private static final long serialVersionUID = -3880169228631618872L;

    private Long id;

    private String identificativoFlusso;

    private Elaborazione elaborazione;

    private StatoFlussoErrore statoFlussoErrore;

    private Ente istitutoRicevente;

    private Psp istitutoMittente;

    private StatoFlusso statoFlusso;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Elaborazione getElaborazione () {
        return elaborazione;
    }

    public void setElaborazione ( Elaborazione elaborazione ) {
        this.elaborazione = elaborazione;
    }

    public StatoFlussoErrore getStatoFlussoErrore () {
        return statoFlussoErrore;
    }

    public void setStatoFlussoErrore ( StatoFlussoErrore statoFlussoErrore ) {
        this.statoFlussoErrore = statoFlussoErrore;
    }

    public Ente getIstitutoRicevente () {
        return istitutoRicevente;
    }

    public void setIstitutoRicevente ( Ente istitutoRicevente ) {
        this.istitutoRicevente = istitutoRicevente;
    }

    public Psp getIstitutoMittente () {
        return istitutoMittente;
    }

    public void setIstitutoMittente ( Psp istitutoMittente ) {
        this.istitutoMittente = istitutoMittente;
    }

    public StatoFlusso getStatoFlusso () {
        return statoFlusso;
    }

    public void setStatoFlusso ( StatoFlusso statoFlusso ) {
        this.statoFlusso = statoFlusso;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    @Override
    public int compareTo ( Flusso toCompare ) {
        int compare =
                        (id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + identificativoFlusso.compareTo ( toCompare.getIdentificativoFlusso () );
        if ( elaborazione != null && toCompare.getElaborazione () != null ) {
            compare = compare
                            + elaborazione.compareTo ( toCompare.getElaborazione () );
        } else {
            compare = compare -1;
        }
        if ( statoFlussoErrore != null && toCompare.getStatoFlussoErrore () != null ) {
            compare = compare
                            + statoFlussoErrore.compareTo ( toCompare.getStatoFlussoErrore () );
        } else {
            compare = compare -1;
        }
        if ( istitutoRicevente != null && toCompare.getIstitutoRicevente () != null ) {
            compare = compare
                            + istitutoRicevente.compareTo ( toCompare.getIstitutoRicevente () );
        } else {
            compare = compare -1;
        }
        if ( istitutoMittente != null && toCompare.getIstitutoMittente () != null ) {
            compare = compare
                            + istitutoMittente.compareTo ( toCompare.getIstitutoMittente () );
        } else {
            compare = compare -1;
        }
        if ( statoFlusso != null && toCompare.getStatoFlusso () != null ) {
            compare = compare
                            + statoFlusso.compareTo ( toCompare.getStatoFlusso () );
        } else {
            compare = compare -1;
        }

        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "identificativoFlusso: [" + identificativoFlusso + "]" );
        if ( elaborazione != null ) {
            stringBuffer.append ( "elaborazione: [" + elaborazione + "]" );
        }
        if ( statoFlussoErrore != null ) {
            stringBuffer.append ( "statoFlussoErrore: [" + statoFlussoErrore + "]" );
        }
        if ( istitutoRicevente != null ) {
            stringBuffer.append ( "istitutoRicevente: [" + istitutoRicevente + "]" );
        }
        if ( istitutoMittente != null ) {
            stringBuffer.append ( "istitutoMittente: [" + istitutoMittente + "]" );
        }
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( elaborazione == null ) ? 0 : elaborazione.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( identificativoFlusso == null ) ? 0 : identificativoFlusso.hashCode () );
        result = prime * result + ( ( istitutoMittente == null ) ? 0 : istitutoMittente.hashCode () );
        result = prime * result + ( ( istitutoRicevente == null ) ? 0 : istitutoRicevente.hashCode () );
        result = prime * result + ( ( statoFlusso == null ) ? 0 : statoFlusso.hashCode () );
        result = prime * result + ( ( statoFlussoErrore == null ) ? 0 : statoFlussoErrore.hashCode () );
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
        Flusso other = (Flusso) obj;
        if ( elaborazione == null ) {
            if ( other.elaborazione != null ) {
                return false;
            }
        } else if ( !elaborazione.equals ( other.elaborazione ) ) {
            return false;
        }
        if ( id == null ) {
            if ( other.id != null ) {
                return false;
            }
        } else if ( !id.equals ( other.id ) ) {
            return false;
        }
        if ( identificativoFlusso == null ) {
            if ( other.identificativoFlusso != null ) {
                return false;
            }
        } else if ( !identificativoFlusso.equals ( other.identificativoFlusso ) ) {
            return false;
        }
        if ( istitutoMittente == null ) {
            if ( other.istitutoMittente != null ) {
                return false;
            }
        } else if ( !istitutoMittente.equals ( other.istitutoMittente ) ) {
            return false;
        }
        if ( istitutoRicevente == null ) {
            if ( other.istitutoRicevente != null ) {
                return false;
            }
        } else if ( !istitutoRicevente.equals ( other.istitutoRicevente ) ) {
            return false;
        }
        if ( statoFlusso == null ) {
            if ( other.statoFlusso != null ) {
                return false;
            }
        } else if ( !statoFlusso.equals ( other.statoFlusso ) ) {
            return false;
        }
        if ( statoFlussoErrore == null ) {
            if ( other.statoFlussoErrore != null ) {
                return false;
            }
        } else if ( !statoFlussoErrore.equals ( other.statoFlussoErrore ) ) {
            return false;
        }
        return true;
    }

}
