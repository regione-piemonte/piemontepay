/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @ author
 */
public class FlussoSintesi extends Flusso {

    private static final long serialVersionUID = 6024907684664456910L;

    private String codiceVersamento;

    private BigDecimal importoQuotaAggregazione;

    private BigDecimal numeroVersamentoQuotaAggregazione;

    private String datiSpecificiDiRiscossione;

    private Integer provvisorioAnno;

    private Integer provvisorioNumero;

    private ArrayList<FlussoDettaglio> listaPagamenti;

    private BigDecimal importoTotaleDettagli = new BigDecimal(0);

    private FlussoOrigine flussoOrigine;

    private String capitolo;

    private Integer articolo;

    private String pianoDeiConti;

    private Integer accertamentoNumero;

    private Integer accertamentoAnno;

    private String descrizioneCodiceVersamento;

    private Date dataPagamentoCalcolata;

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public BigDecimal getImportoQuotaAggregazione () {
        return importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione ( BigDecimal importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public BigDecimal getNumeroVersamentoQuotaAggregazione () {
        return numeroVersamentoQuotaAggregazione;
    }

    public void setNumeroVersamentoQuotaAggregazione ( BigDecimal numeroVersamentoQuotaAggregazione ) {
        this.numeroVersamentoQuotaAggregazione = numeroVersamentoQuotaAggregazione;
    }

    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public Integer getProvvisorioAnno () {
        return provvisorioAnno;
    }

    public void setProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public Integer getProvvisorioNumero () {
        return provvisorioNumero;
    }

    public void setProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
    }

    public ArrayList<FlussoDettaglio> getListaPagamenti () {
        return listaPagamenti;
    }

    public void setListaPagamenti ( ArrayList<FlussoDettaglio> listaPagamenti ) {
        this.listaPagamenti = listaPagamenti;
    }

    public FlussoOrigine getFlussoOrigine () {
        return flussoOrigine;
    }


    public void setFlussoOrigine ( FlussoOrigine flussoOrigine ) {
        this.flussoOrigine = flussoOrigine;
    }

    public void setImportoTotaleDettagli ( BigDecimal importoTotaleDettagli ) {
        this.importoTotaleDettagli = importoTotaleDettagli;
    }

    public BigDecimal getImportoTotaleDettagli () {
        return importoTotaleDettagli;
    }

    @Override
    public int compareTo ( Flusso toCompare ) {
        int compare = super.compareTo ( toCompare );
        FlussoSintesi sintesiToCompare = (FlussoSintesi) toCompare;
        compare = compare
                        + (this.codiceVersamento.compareTo ( sintesiToCompare.getCodiceVersamento () ) )
                        + (this.importoQuotaAggregazione.compareTo ( sintesiToCompare.getImportoQuotaAggregazione () ) )
                        + (this.numeroVersamentoQuotaAggregazione.compareTo ( sintesiToCompare.getNumeroVersamentoQuotaAggregazione () ) )
                        + (this.datiSpecificiDiRiscossione.compareTo ( sintesiToCompare.getDatiSpecificiDiRiscossione () ) )
                        + (this.provvisorioAnno.intValue () == sintesiToCompare.getProvvisorioAnno ().intValue () ? 0 : -1)
                        + (this.provvisorioNumero.intValue () == sintesiToCompare.getProvvisorioNumero ().intValue () ? 0 : -1)
                        + (this.importoTotaleDettagli.floatValue () == sintesiToCompare.getImportoTotaleDettagli ().floatValue () ? 0 : -1)
                        ;
        if (listaPagamenti!=null && sintesiToCompare.getListaPagamenti () !=null) {
            if (listaPagamenti.size ()==sintesiToCompare.getListaPagamenti ().size ()) {
                for (int i=0; i<listaPagamenti.size (); i++) {
                    compare =  compare
                                    + listaPagamenti.get ( i ).compareTo ( sintesiToCompare.getListaPagamenti ().get ( i ) );
                }
            }
        }
        if (flussoOrigine!=null && sintesiToCompare.getFlussoOrigine () !=null) {
            compare =  compare
                            + this.flussoOrigine.compareTo ( sintesiToCompare.getFlussoOrigine () );
        } else {
            compare = compare -1;
        }

        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "codiceVersamento: [" + codiceVersamento + "]" );
        stringBuffer.append ( "importoQuotaAggregazione: [" + importoQuotaAggregazione + "]" );
        stringBuffer.append ( "numeroVersamentoQuotaAggregazione: [" + numeroVersamentoQuotaAggregazione + "]" );
        stringBuffer.append ( "datiSpecificiDiRiscossione: [" + datiSpecificiDiRiscossione + "]" );
        stringBuffer.append ( "provvisorioAnno: [" + provvisorioAnno + "]" );
        stringBuffer.append ( "provvisorioNumero: [" + provvisorioNumero + "]" );
        stringBuffer.append ( "importoTotaleDettagli: [" + importoTotaleDettagli + "]" );
        if (listaPagamenti!=null && listaPagamenti.size ()>0) {
            stringBuffer.append ( "listaFlussi: " );
            for (int i=0; i<listaPagamenti.size (); i++) {
                stringBuffer.append ( "\n ["+listaPagamenti.get ( i )+"]" );
            }
        }
        if (flussoOrigine!=null) {
            stringBuffer.append ( "flussoOrigine: [" + flussoOrigine + "]" );
        }
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

    public Integer getAccertamentoNumero() {
        return accertamentoNumero;
    }

    public void setAccertamentoNumero(Integer accertamentoNumero) {
        this.accertamentoNumero = accertamentoNumero;
    }

    public Integer getAccertamentoAnno() {
        return accertamentoAnno;
    }

    public void setAccertamentoAnno(Integer accertamentoAnno) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento(String descrizioneCodiceVersamento) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public Date getDataPagamentoCalcolata () {
        return dataPagamentoCalcolata;
    }

    public void setDataPagamentoCalcolata ( Date dataPagamentoCalcolata ) {
        this.dataPagamentoCalcolata = dataPagamentoCalcolata;
    }

}
