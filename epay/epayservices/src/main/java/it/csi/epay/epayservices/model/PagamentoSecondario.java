/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class PagamentoSecondario implements Serializable {

    private static final long serialVersionUID = -520928713011664413L;

    private Long idPagamento;

    private Long idPagamentoSecondario;

    private BigDecimal importoComplessivo;

    private TipoPagamento tipoPagamento;

    private String identificativoDominio;

    private List<PagamentoComponenti> componenti;

    private String causale;

    /**
     * @return the id
     */
    public Long getIdPagamentoSecondario () {
        return idPagamentoSecondario;
    }

    /**
     * @param id the id to set
     */

    public void setIdPagamentoSecondario ( Long idPagamentoSecondario ) {
        this.idPagamentoSecondario = idPagamentoSecondario;
    }

    /**
     * @return the idPagamento
     */
    public Long getIdPagamento () {
        return idPagamento;
    }

    /**
     * @param idPagamento the idPagamento to set
     */

    public void setIdPagamento ( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }

    /**
     * @return the causale
     */
    public String getCausale () {
        return causale;
    }

    /**
     * @param causale the causale to set
     */
    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    /**
     * @return the importo
     */
    public BigDecimal getImportoComplessivo () {
        return importoComplessivo;
    }

    /**
     * @param importo the importo to set
     */
    public void setImportoComplessivo ( BigDecimal importoComplessivo ) {
        this.importoComplessivo = importoComplessivo;
    }

    /**
     * @return the tipoPagamento
     */
    public TipoPagamento getTipoPagamento () {
        return tipoPagamento;
    }

    /**
     * @param tipoPagamento the tipoPagamento to set
     */
    public void setTipoPagamento ( TipoPagamento tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }

    /**
     * @return the componenti
     */
    public List<PagamentoComponenti> getComponenti () {
        if ( componenti == null ) {
            componenti = new ArrayList<> ();
        }
        return componenti;
    }

    /**
     * @param componenti the componenti to set
     */
    public void setComponenti ( List<PagamentoComponenti> componenti ) {
        this.componenti = componenti;
    }
}
