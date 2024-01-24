/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento;

import it.csi.epay.epaypacatalogweb.model.GenericVO;


public class OpzioniCodiceVersamentoVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private String codiceVersamentoSecondario;
    
    private String descrizioneCodiceVersamentoSecondario;
    
    private Long idCodiceVersamentoSecondario;

    private String enteSecondario;

    private Boolean flagMbPrimario;

    private Boolean flagMbSecondario;
    
    private String tipoPagamento;

    public OpzioniCodiceVersamentoVO ( Long id, String codice, String descrizione, String tipoPagamento ) {
        super ( id, codice, descrizione );
        this.tipoPagamento = tipoPagamento;
    }
   

    public String getTipoPagamento () {
        return tipoPagamento;
    }

    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getCodiceVersamentoSecondario () {
        return codiceVersamentoSecondario;
    }

    public void setCodiceVersamentoSecondario ( String codiceVersamentoSecondario ) {
        this.codiceVersamentoSecondario = codiceVersamentoSecondario;
    }

    public String getEnteSecondario () {
        return enteSecondario;
    }

    public void setEnteSecondario ( String enteSecondario ) {
        this.enteSecondario = enteSecondario;
    }

    public Boolean getFlagMbPrimario () {
        return flagMbPrimario;
    }

    public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
        this.flagMbPrimario = flagMbPrimario;
    }

    public Boolean getFlagMbSecondario () {
        return flagMbSecondario;
    }

    public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
        this.flagMbSecondario = flagMbSecondario;
    }

    
    /**
     * @return the descrizioneCodiceVersamentoSecondario
     */
    public String getDescrizioneCodiceVersamentoSecondario () {
        return descrizioneCodiceVersamentoSecondario;
    }


    
    /**
     * @param descrizioneCodiceVersamentoSecondario the descrizioneCodiceVersamentoSecondario to set
     */
    public void setDescrizioneCodiceVersamentoSecondario ( String descrizioneCodiceVersamentoSecondario ) {
        this.descrizioneCodiceVersamentoSecondario = descrizioneCodiceVersamentoSecondario;
    }


    
    /**
     * @return the idCodiceVersamentoSecondario
     */
    public Long getIdCodiceVersamentoSecondario () {
        return idCodiceVersamentoSecondario;
    }


    
    /**
     * @param idCodiceVersamentoSecondario the idCodiceVersamentoSecondario to set
     */
    public void setIdCodiceVersamentoSecondario ( Long idCodiceVersamentoSecondario ) {
        this.idCodiceVersamentoSecondario = idCodiceVersamentoSecondario;
    }


    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "OpzioniCodiceVersamentoVO [codiceVersamentoSecondario=" );
        builder.append ( codiceVersamentoSecondario );
        builder.append ( ", enteSecondario=" );
        builder.append ( enteSecondario );
        builder.append ( ", flagMbPrimario=" );
        builder.append ( flagMbPrimario );
        builder.append ( ", flagMbSecondario=" );
        builder.append ( flagMbSecondario );
        builder.append ( ", tipoPagamento=" );
        builder.append ( tipoPagamento );
        builder.append ( "]" );
        return builder.toString ();
    }

}
