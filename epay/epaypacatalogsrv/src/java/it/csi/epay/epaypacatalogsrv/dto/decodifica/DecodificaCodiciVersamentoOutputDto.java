/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

public class DecodificaCodiciVersamentoOutputDto extends DecodificaOutputDto {

    private static final long serialVersionUID = 1L;

    private Boolean flagMbPrimario;

    private Boolean flagMbSecondario;

    private String enteSecondario;

    private String codiceVersamentoSecondario;

    private String tipoPagamento;
    
    private String descrizioneCodiceVersamentoSecondario;
    
    private Long idCodiceVersamentoSecondario;


    public DecodificaCodiciVersamentoOutputDto () {
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

    public String getEnteSecondario () {
        return enteSecondario;
    }

    public void setEnteSecondario ( String enteSecondario ) {
        this.enteSecondario = enteSecondario;
    }

    public DecodificaCodiciVersamentoOutputDto ( Long id, String codice, String descrizione, String tipoPagamento ) {
        super ( id, codice, descrizione );
        this.tipoPagamento = tipoPagamento;
    }

    public String getCodiceVersamentoSecondario () {
        return codiceVersamentoSecondario;
    }

    public String getTipoPagamento () {
        return tipoPagamento;
    }

    public void setCodiceVersamentoSecondario ( String codiceVersamentoSecondario ) {
        this.codiceVersamentoSecondario = codiceVersamentoSecondario;
    }

    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
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
    
    

}
