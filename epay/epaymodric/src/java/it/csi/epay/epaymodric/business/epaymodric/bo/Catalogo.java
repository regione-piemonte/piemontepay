/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 *
 * @ author vsgro
 */
public class Catalogo extends BaseBO implements Serializable, Comparable<Catalogo> {

    private static final long serialVersionUID = 2838652486966533694L;

    private Long id;

    private Integer accertamentoAnno;

    private String accertamentoDesc;

    private Integer accertamentoNro;

    private Integer annoEsercizio;

    private BigDecimal articolo;

    private String capitolo;

    private String categoria;

    private String codiceFiscaleEnte;

    private String codiceVersamento;

    private Timestamp dataFineValidita;

    private Timestamp dataInizioValidita;

    private String datiSpecificiRiscossione;

    private String descrizioneVersamento;

    private String idEnte;

    private String pianoDeiConti;

    private String sottovoce;

    private String tipologia;

    private String titolo;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAccertamentoAnno () {
        return accertamentoAnno;
    }

    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public String getAccertamentoDesc () {
        return accertamentoDesc;
    }

    public void setAccertamentoDesc ( String accertamentoDesc ) {
        this.accertamentoDesc = accertamentoDesc;
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

    public BigDecimal getArticolo () {
        return articolo;
    }

    public void setArticolo ( BigDecimal articolo ) {
        this.articolo = articolo;
    }

    public String getCapitolo () {
        return capitolo;
    }

    public void setCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
    }

    public String getCategoria () {
        return categoria;
    }

    public void setCategoria ( String categoria ) {
        this.categoria = categoria;
    }

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public Timestamp getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Timestamp dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public Timestamp getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Timestamp dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public String getDescrizioneVersamento () {
        return descrizioneVersamento;
    }

    public void setDescrizioneVersamento ( String descrizioneVersamento ) {
        this.descrizioneVersamento = descrizioneVersamento;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getPianoDeiConti () {
        return pianoDeiConti;
    }

    public void setPianoDeiConti ( String pianoDeiConti ) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public String getSottovoce () {
        return sottovoce;
    }

    public void setSottovoce ( String sottovoce ) {
        this.sottovoce = sottovoce;
    }

    public String getTipologia () {
        return tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    public String getTitolo () {
        return titolo;
    }

    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

    @Override
    public int compareTo ( Catalogo toCompare ) {
        int compare =
                        ( this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + (this.accertamentoAnno.intValue () == toCompare.getAccertamentoAnno ().intValue () ? 0 : -1 )
                        + this.accertamentoDesc.compareTo ( toCompare.getAccertamentoDesc () )
                        + (this.accertamentoNro.intValue () == toCompare.getAccertamentoNro ().intValue () ? 0 : -1 )
                        + (this.annoEsercizio.intValue () == toCompare.getAnnoEsercizio ().intValue () ? 0 : -1 )
                        + (this.articolo.floatValue () == toCompare.getArticolo ().floatValue () ? 0 : -1 )
                        + this.capitolo.compareTo ( toCompare.getCapitolo () )
                        + this.categoria.compareTo ( toCompare.getCategoria () )
                        + this.codiceFiscaleEnte.compareTo ( toCompare.getCodiceFiscaleEnte () )
                        + this.codiceVersamento.compareTo ( toCompare.getCodiceVersamento () )
                        + this.datiSpecificiRiscossione.compareTo ( toCompare.getDatiSpecificiRiscossione () )
                        + this.descrizioneVersamento.compareTo ( toCompare.getDescrizioneVersamento () )
                        + this.idEnte.compareTo ( toCompare.getIdEnte () )
                        + this.pianoDeiConti.compareTo ( toCompare.getPianoDeiConti () )
                        + this.sottovoce.compareTo ( toCompare.getSottovoce () )
                        + this.tipologia.compareTo ( toCompare.getTipologia () )
                        + this.titolo.compareTo ( toCompare.getTitolo () )
                        ;
        if (dataFineValidita!=null) {
            compare = compare
                            + this.dataFineValidita.compareTo ( toCompare.getDataFineValidita () );
        }
        if (dataInizioValidita!=null) {
            compare = compare
                            + this.dataInizioValidita.compareTo ( toCompare.getDataInizioValidita () );
        }

        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "accertamentoAnno: [" + accertamentoAnno + "]" );
        stringBuffer.append ( "accertamentoDesc: [" + accertamentoDesc + "]" );
        stringBuffer.append ( "accertamentoNro: [" + accertamentoNro + "]" );
        stringBuffer.append ( "annoEsercizio: [" + annoEsercizio + "]" );
        stringBuffer.append ( "articolo: [" + articolo + "]" );
        stringBuffer.append ( "capitolo: [" + capitolo + "]" );
        stringBuffer.append ( "categoria: [" + categoria + "]" );
        stringBuffer.append ( "codiceFiscaleEnte: [" + codiceFiscaleEnte + "]" );
        stringBuffer.append ( "codiceVersamento: [" + codiceVersamento + "]" );
        if (dataFineValidita!=null) {
            stringBuffer.append ( "dataFineValidita: [" + dataFineValidita.getTime () + "]" );
        }
        if (dataInizioValidita!=null) {
            stringBuffer.append ( "dataInizioValidita: [" + dataInizioValidita.getTime ()+ "]" );
        }
        stringBuffer.append ( "datiSpecificiRiscossione: [" + datiSpecificiRiscossione + "]" );
        stringBuffer.append ( "descrizioneVersamento: [" + descrizioneVersamento + "]" );
        stringBuffer.append ( "idEnte: [" + idEnte + "]" );
        stringBuffer.append ( "pianoDeiConti: [" + pianoDeiConti + "]" );
        stringBuffer.append ( "sottovoce: [" + sottovoce + "]" );
        stringBuffer.append ( "tipologia: [" + tipologia + "]" );
        stringBuffer.append ( "titolo: [" + titolo + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( accertamentoAnno == null ) ? 0 : accertamentoAnno.hashCode () );
        result = prime * result + ( ( accertamentoDesc == null ) ? 0 : accertamentoDesc.hashCode () );
        result = prime * result + ( ( accertamentoNro == null ) ? 0 : accertamentoNro.hashCode () );
        result = prime * result + ( ( annoEsercizio == null ) ? 0 : annoEsercizio.hashCode () );
        result = prime * result + ( ( articolo == null ) ? 0 : articolo.hashCode () );
        result = prime * result + ( ( capitolo == null ) ? 0 : capitolo.hashCode () );
        result = prime * result + ( ( categoria == null ) ? 0 : categoria.hashCode () );
        result = prime * result + ( ( codiceFiscaleEnte == null ) ? 0 : codiceFiscaleEnte.hashCode () );
        result = prime * result + ( ( codiceVersamento == null ) ? 0 : codiceVersamento.hashCode () );
        result = prime * result + ( ( dataFineValidita == null ) ? 0 : dataFineValidita.hashCode () );
        result = prime * result + ( ( dataInizioValidita == null ) ? 0 : dataInizioValidita.hashCode () );
        result = prime * result + ( ( datiSpecificiRiscossione == null ) ? 0 : datiSpecificiRiscossione.hashCode () );
        result = prime * result + ( ( descrizioneVersamento == null ) ? 0 : descrizioneVersamento.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( pianoDeiConti == null ) ? 0 : pianoDeiConti.hashCode () );
        result = prime * result + ( ( sottovoce == null ) ? 0 : sottovoce.hashCode () );
        result = prime * result + ( ( tipologia == null ) ? 0 : tipologia.hashCode () );
        result = prime * result + ( ( titolo == null ) ? 0 : titolo.hashCode () );
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
        Catalogo other = (Catalogo) obj;
        if ( accertamentoAnno == null ) {
            if ( other.accertamentoAnno != null )
                return false;
        } else if ( !accertamentoAnno.equals ( other.accertamentoAnno ) )
            return false;
        if ( accertamentoDesc == null ) {
            if ( other.accertamentoDesc != null )
                return false;
        } else if ( !accertamentoDesc.equals ( other.accertamentoDesc ) )
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
        if ( categoria == null ) {
            if ( other.categoria != null )
                return false;
        } else if ( !categoria.equals ( other.categoria ) )
            return false;
        if ( codiceFiscaleEnte == null ) {
            if ( other.codiceFiscaleEnte != null )
                return false;
        } else if ( !codiceFiscaleEnte.equals ( other.codiceFiscaleEnte ) )
            return false;
        if ( codiceVersamento == null ) {
            if ( other.codiceVersamento != null )
                return false;
        } else if ( !codiceVersamento.equals ( other.codiceVersamento ) )
            return false;
        if ( dataFineValidita == null ) {
            if ( other.dataFineValidita != null )
                return false;
        } else if ( !dataFineValidita.equals ( other.dataFineValidita ) )
            return false;
        if ( dataInizioValidita == null ) {
            if ( other.dataInizioValidita != null )
                return false;
        } else if ( !dataInizioValidita.equals ( other.dataInizioValidita ) )
            return false;
        if ( datiSpecificiRiscossione == null ) {
            if ( other.datiSpecificiRiscossione != null )
                return false;
        } else if ( !datiSpecificiRiscossione.equals ( other.datiSpecificiRiscossione ) )
            return false;
        if ( descrizioneVersamento == null ) {
            if ( other.descrizioneVersamento != null )
                return false;
        } else if ( !descrizioneVersamento.equals ( other.descrizioneVersamento ) )
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
        if ( pianoDeiConti == null ) {
            if ( other.pianoDeiConti != null )
                return false;
        } else if ( !pianoDeiConti.equals ( other.pianoDeiConti ) )
            return false;
        if ( sottovoce == null ) {
            if ( other.sottovoce != null )
                return false;
        } else if ( !sottovoce.equals ( other.sottovoce ) )
            return false;
        if ( tipologia == null ) {
            if ( other.tipologia != null )
                return false;
        } else if ( !tipologia.equals ( other.tipologia ) )
            return false;
        if ( titolo == null ) {
            if ( other.titolo != null )
                return false;
        } else if ( !titolo.equals ( other.titolo ) )
            return false;
        return true;
    }

}
