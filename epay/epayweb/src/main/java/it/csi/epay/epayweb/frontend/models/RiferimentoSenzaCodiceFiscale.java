/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import it.csi.epay.epayweb.frontend.models.validators.annotation.Iuv;
import it.csi.epay.epayweb.frontend.models.validators.annotation.RifPagamento;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoConIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoSenzaIuvGroup;


@RifPagamento ( groups = RiferimentoSenzaIuvGroup.class )
public class RiferimentoSenzaCodiceFiscale extends _Model implements Serializable {

    private static final long serialVersionUID = 549905853961949068L;

    @NotEmpty ( message = "Dato obbligatorio.", groups = RiferimentoConIuvGroup.class )
    //@Size(min=25, max=25, message="Deve essere lungo 25 caratteri.", groups=RiferimentoConIuvGroup.class)
    @Iuv ( groups = RiferimentoConIuvGroup.class )
    @Pattern ( regexp = "^[A-Za-z0-9]*$", message = "Caratteri non validi. Solo numeri e lettere.", groups = RiferimentoConIuvGroup.class )
    private String iuv;

    @NotNull ( message = "Dato obbligatorio.", groups = RiferimentoSenzaIuvGroup.class )
    private Long enteId;

    private String enteDesc;

    @NotNull ( message = "Dato obbligatorio.", groups = RiferimentoSenzaIuvGroup.class )
    private Long pagamentoId;

    private String pagamentoDesc;

    private Date dataOperazione;

    //datiPersonali.setCompilazioneNote(pagamento.getTipoPagamento().getCompilazioneNote());
    private String compilazioneNote;

    //TAG_PPU - 2019 - RDI 004 - RDI 005
    private Integer annoAccertamento;

    private Integer numeroAccertamento;
    //TAG_PPU - 2019 - RDI 004 - RDI 005

    private List<RiferimentoPagamento> riferimentiPagamento;

    public RiferimentoSenzaCodiceFiscale () {
        dataOperazione = new Date ();
    }

    /**
     * @return the iuv
     */
    public String getIuv () {
        return iuv;
    }

    /**
     * @param iuv the iuv to set
     */
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    /**
     * @return the enteId
     */
    public Long getEnteId () {
        return enteId;
    }

    /**
     * @param enteId the enteId to set
     */
    public void setEnteId ( Long enteId ) {
        this.enteId = enteId;
    }

    /**
     * @return the enteDesc
     */
    public String getEnteDesc () {
        return enteDesc;
    }

    /**
     * @param enteDesc the enteDesc to set
     */
    public void setEnteDesc ( String enteDesc ) {
        this.enteDesc = enteDesc;
    }

    /**
     * @return the pagamentoId
     */
    public Long getPagamentoId () {
        return pagamentoId;
    }

    /**
     * @param pagamentoId the pagamentoId to set
     */
    public void setPagamentoId ( Long pagamentoId ) {
        this.pagamentoId = pagamentoId;
    }

    /**
     * @return the pagamentoDesc
     */
    public String getPagamentoDesc () {
        return pagamentoDesc;
    }

    /**
     * @param pagamentoDesc the pagamentoDesc to set
     */
    public void setPagamentoDesc ( String pagamentoDesc ) {
        this.pagamentoDesc = pagamentoDesc;
    }

    /**
     * @return the dataOperazione
     */
    public Date getDataOperazione () {
        return dataOperazione;
    }

    /**
     * @param dataOperazione the dataOperazione to set
     */
    public void setDataOperazione ( Date dataOperazione ) {
        this.dataOperazione = dataOperazione;
    }

    /**
     * @return the compilazioneNote
     */
    public String getCompilazioneNote () {
        return compilazioneNote;
    }

    /**
     * @param compilazioneNote the compilazioneNote to set
     */
    public void setCompilazioneNote ( String compilazioneNote ) {
        this.compilazioneNote = compilazioneNote;
    }

    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public Integer getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( Integer numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public List<RiferimentoPagamento> getRiferimentiPagamento () {
        return riferimentiPagamento;
    }

    public void setRiferimentiPagamento ( List<RiferimentoPagamento> riferimentiPagamento ) {
        this.riferimentiPagamento = riferimentiPagamento;
    }

    public boolean equalIdEnteAndIdPagamento ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        RiferimentoSenzaCodiceFiscale other = (RiferimentoSenzaCodiceFiscale) obj;
        if ( enteId == null ) {
            if ( other.enteId != null )
                return false;
        } else if ( !enteId.equals ( other.enteId ) )
            return false;
        if ( pagamentoId == null ) {
            if ( other.pagamentoId != null )
                return false;
        } else if ( !pagamentoId.equals ( other.pagamentoId ) )
            return false;
        return true;
    }

    public boolean equalIuv ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        RiferimentoSenzaCodiceFiscale other = (RiferimentoSenzaCodiceFiscale) obj;
        if ( iuv == null ) {
            if ( other.iuv != null )
                return false;
        } else if ( !iuv.equals ( other.iuv ) )
            return false;
        return true;
    }

    public boolean equalRiferimentiPagamento ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        RiferimentoSenzaCodiceFiscale other = (RiferimentoSenzaCodiceFiscale) obj;
        if ( riferimentiPagamento == null ) {
            if ( other.riferimentiPagamento != null )
                return false;
        } else {
            if ( other.riferimentiPagamento == null )
                return false;
            else {
                if ( riferimentiPagamento.size () != other.riferimentiPagamento.size () ) {
                    return false;
                }
                for ( int i = 0; i < riferimentiPagamento.size (); i++ ) {
                    if ( !riferimentiPagamento.get ( i ).getNome ().equals ( other.riferimentiPagamento.get ( i ).getNome () )
                        || !riferimentiPagamento.get ( i ).getValore ().equals ( other.riferimentiPagamento.get ( i ).getValore () ) )
                        return false;
                }
            }
        }
        return true;
    }
}
