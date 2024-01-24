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
public class VistaDatiAccertamento implements Serializable, Comparable<VistaDatiAccertamento> {

    private static final long serialVersionUID = 5639754049011795210L;

    private Integer accertamentoAnno;

    private Integer accertamentoNro;

    private Integer annoEsercizio;

    private String codiceVersamento;

    private String datiSpecificiRiscossione;

    private Long id;

    private String idEnte;

    private String pdc;

    private Integer priorita;

    private String capitolo;

    private Integer articolo;

    private String pianoDeiConti;

    private String descrizioneCodiceVersamento;

    private Long idListaDiCarico;

    public Integer getAccertamentoAnno () {
        return accertamentoAnno;
    }

    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public Integer getAccertamentoNro () {
        return accertamentoNro;
    }

    public void setAccertamentoNro ( Integer accertamentoNro ) {
        this.accertamentoNro = accertamentoNro;
    }

    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getPdc () {
        return pdc;
    }

    public void setPdc ( String pdc ) {
        this.pdc = pdc;
    }

    public Integer getPriorita () {
        return priorita;
    }

    public void setPriorita ( Integer priorita ) {
        this.priorita = priorita;
    }

    @Override
    public int compareTo ( VistaDatiAccertamento arg0 ) {
        int compare = 0;
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        return stringBuffer.toString ();
    }

    public String getCapitolo() {
        return capitolo;
    }

    public void setCapitolo(String capitolo) {
        this.capitolo = capitolo;
    }

    public Integer getArticolo() {
        return articolo;
    }

    public void setArticolo(Integer articolo) {
        this.articolo = articolo;
    }

    public String getPianoDeiConti() {
        return pianoDeiConti;
    }

    public void setPianoDeiConti(String pianoDeiConti) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento(String descrizioneCodiceVersamento) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( accertamentoAnno == null ) ? 0 : accertamentoAnno.hashCode () );
        result = prime * result + ( ( accertamentoNro == null ) ? 0 : accertamentoNro.hashCode () );
        result = prime * result + ( ( annoEsercizio == null ) ? 0 : annoEsercizio.hashCode () );
        result = prime * result + ( ( articolo == null ) ? 0 : articolo.hashCode () );
        result = prime * result + ( ( capitolo == null ) ? 0 : capitolo.hashCode () );
        result = prime * result + ( ( codiceVersamento == null ) ? 0 : codiceVersamento.hashCode () );
        result = prime * result + ( ( datiSpecificiRiscossione == null ) ? 0 : datiSpecificiRiscossione.hashCode () );
        result = prime * result + ( ( descrizioneCodiceVersamento == null ) ? 0 : descrizioneCodiceVersamento.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( pdc == null ) ? 0 : pdc.hashCode () );
        result = prime * result + ( ( pianoDeiConti == null ) ? 0 : pianoDeiConti.hashCode () );
        result = prime * result + ( ( priorita == null ) ? 0 : priorita.hashCode () );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        VistaDatiAccertamento other = (VistaDatiAccertamento) obj;
        if ( accertamentoAnno == null ) {
            if ( other.accertamentoAnno != null )
                return false;
        } else if ( !accertamentoAnno.equals ( other.accertamentoAnno ) )
            return false;
        if ( accertamentoNro == null ) {
            if ( other.accertamentoNro != null )
                return false;
        } else if ( !accertamentoNro.equals ( other.accertamentoNro ) )
            return false;
        if ( annoEsercizio == null ) {
            if ( other.annoEsercizio != null )
                return false;
        } else if ( !annoEsercizio.equals ( other.annoEsercizio ) )
            return false;
        if ( articolo == null ) {
            if ( other.articolo != null )
                return false;
        } else if ( !articolo.equals ( other.articolo ) )
            return false;
        if ( capitolo == null ) {
            if ( other.capitolo != null )
                return false;
        } else if ( !capitolo.equals ( other.capitolo ) )
            return false;
        if ( codiceVersamento == null ) {
            if ( other.codiceVersamento != null )
                return false;
        } else if ( !codiceVersamento.equals ( other.codiceVersamento ) )
            return false;
        if ( datiSpecificiRiscossione == null ) {
            if ( other.datiSpecificiRiscossione != null )
                return false;
        } else if ( !datiSpecificiRiscossione.equals ( other.datiSpecificiRiscossione ) )
            return false;
        if ( descrizioneCodiceVersamento == null ) {
            if ( other.descrizioneCodiceVersamento != null )
                return false;
        } else if ( !descrizioneCodiceVersamento.equals ( other.descrizioneCodiceVersamento ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        if ( idEnte == null ) {
            if ( other.idEnte != null )
                return false;
        } else if ( !idEnte.equals ( other.idEnte ) )
            return false;
        if ( pdc == null ) {
            if ( other.pdc != null )
                return false;
        } else if ( !pdc.equals ( other.pdc ) )
            return false;
        if ( pianoDeiConti == null ) {
            if ( other.pianoDeiConti != null )
                return false;
        } else if ( !pianoDeiConti.equals ( other.pianoDeiConti ) )
            return false;
        if ( priorita == null ) {
            if ( other.priorita != null )
                return false;
        } else if ( !priorita.equals ( other.priorita ) )
            return false;
        return true;
    }

    public Long getIdListaDiCarico () {
        return idListaDiCarico;
    }

    public void setIdListaDiCarico ( Long idListaDiCarico ) {
        this.idListaDiCarico = idListaDiCarico;
    }

}
