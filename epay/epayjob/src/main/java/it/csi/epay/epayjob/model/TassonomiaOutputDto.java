/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TassonomiaOutputDto implements Serializable {

    private static final long serialVersionUID = -5712363380867251870L;

    @JsonProperty ( "id" )
    private Long id;

    @JsonProperty ( "codTipoEnteCreditore" )
    private String codTipoEnteCreditore;

    @JsonProperty ( "tipoEnteCreditore" )
    private String tipoEnteCreditore;

    @JsonProperty ( "macroArea" )
    private String macroArea;

    @JsonProperty ( "nomeMacroArea" )
    private String nomeMacroArea;

    @JsonProperty ( "descrMacroArea" )
    private String descrMacroArea;

    @JsonProperty ( "codTipologiaServizio" )
    private String codTipologiaServizio;

    @JsonProperty ( "tipoServizio" )
    private String tipoServizio;

    @JsonProperty ( "motivoGiuridicoRiscossione" )
    private String motivoGiuridicoRiscossione;

    @JsonProperty ( "descrTipoServizio" )
    private String descrTipoServizio;

    @JsonProperty ( "nVersioneTassonomia" )
    private String nVersioneTassonomia;

    @JsonProperty ( "datiSpecificiIncasso" )
    private String datiSpecificiIncasso;

    @JsonProperty ( "dataInizioValidita" )
    private Date dataInizioValidita;

    @JsonProperty ( "dataFineValidita" )
    private Date dataFineValidita;

    @JsonProperty ( "flagAggiornato" )
    private Boolean flagAggiornato;

    @JsonProperty ( "toBeUpdate" )
    private Boolean toBeUpdate;

    @JsonProperty ( "toBeInsert" )
    private Boolean toBeInsert;

    @JsonProperty ( "toBeDelete" )
    private Boolean toBeDelete;

    @JsonProperty ( "utenteInserimento" )
    private String utenteInserimento;

    @JsonProperty ( "dataInserimento" )
    private Date dataInserimento;

    @JsonProperty ( "utenteModifica" )
    private String utenteModifica;

    @JsonProperty ( "dataModifica" )
    private Date dataModifica;

    @JsonProperty ( "flagCancellato" )
    private boolean flagCancellato;

    @JsonProperty ( "dataCancellazione" )
    private Date dataCancellazione;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodTipoEnteCreditore () {
        return codTipoEnteCreditore;
    }

    public void setCodTipoEnteCreditore ( String codTipoEnteCreditore ) {
        this.codTipoEnteCreditore = codTipoEnteCreditore;
    }

    public String getTipoEnteCreditore () {
        return tipoEnteCreditore;
    }

    public void setTipoEnteCreditore ( String tipoEnteCreditore ) {
        this.tipoEnteCreditore = tipoEnteCreditore;
    }

    public String getMacroArea () {
        return macroArea;
    }

    public void setMacroArea ( String macroArea ) {
        this.macroArea = macroArea;
    }

    public String getNomeMacroArea () {
        return nomeMacroArea;
    }

    public void setNomeMacroArea ( String nomeMacroArea ) {
        this.nomeMacroArea = nomeMacroArea;
    }

    public String getDescrMacroArea () {
        return descrMacroArea;
    }

    public void setDescrMacroArea ( String descrMacroArea ) {
        this.descrMacroArea = descrMacroArea;
    }

    public String getCodTipologiaServizio () {
        return codTipologiaServizio;
    }

    public void setCodTipologiaServizio ( String codTipologiaServizio ) {
        this.codTipologiaServizio = codTipologiaServizio;
    }

    public String getTipoServizio () {
        return tipoServizio;
    }

    public void setTipoServizio ( String tipoServizio ) {
        this.tipoServizio = tipoServizio;
    }

    public String getMotivoGiuridicoRiscossione () {
        return motivoGiuridicoRiscossione;
    }

    public void setMotivoGiuridicoRiscossione ( String motivoGiuridicoRiscossione ) {
        this.motivoGiuridicoRiscossione = motivoGiuridicoRiscossione;
    }

    public String getDescrTipoServizio () {
        return descrTipoServizio;
    }

    public void setDescrTipoServizio ( String descrTipoServizio ) {
        this.descrTipoServizio = descrTipoServizio;
    }

    public String getnVersioneTassonomia () {
        return nVersioneTassonomia;
    }

    public void setnVersioneTassonomia ( String nVersioneTassonomia ) {
        this.nVersioneTassonomia = nVersioneTassonomia;
    }

    public String getDatiSpecificiIncasso () {
        return datiSpecificiIncasso;
    }

    public void setDatiSpecificiIncasso ( String datiSpecificiIncasso ) {
        this.datiSpecificiIncasso = datiSpecificiIncasso;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public Boolean getFlagAggiornato () {
        return flagAggiornato;
    }

    public void setFlagAggiornato ( Boolean flagAggiornato ) {
        this.flagAggiornato = flagAggiornato;
    }

    public Boolean getToBeUpdate () {
        return toBeUpdate;
    }

    public void setToBeUpdate ( Boolean toBeUpdate ) {
        this.toBeUpdate = toBeUpdate;
    }

    public Boolean getToBeInsert () {
        return toBeInsert;
    }

    public void setToBeInsert ( Boolean toBeInsert ) {
        this.toBeInsert = toBeInsert;
    }

    public Boolean getToBeDelete () {
        return toBeDelete;
    }

    public void setToBeDelete ( Boolean toBeDelete ) {
        this.toBeDelete = toBeDelete;
    }

    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    public Date getDataInserimento () {
        return dataInserimento;
    }

    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public String getUtenteModifica () {
        return utenteModifica;
    }

    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

    public Date getDataModifica () {
        return dataModifica;
    }

    public void setDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public boolean isFlagCancellato () {
        return flagCancellato;
    }

    public void setFlagCancellato ( boolean flagCancellato ) {
        this.flagCancellato = flagCancellato;
    }

    public Date getDataCancellazione () {
        return dataCancellazione;
    }

    public void setDataCancellazione ( Date dataCancellazione ) {
        this.dataCancellazione = dataCancellazione;
    }

    @Override
    public int hashCode () {
        return Objects.hash ( codTipoEnteCreditore, codTipologiaServizio, macroArea );
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( obj instanceof TassonomiaOutputDto ) {
            TassonomiaOutputDto other = (TassonomiaOutputDto) obj;
            return Objects.equals ( codTipoEnteCreditore, other.codTipoEnteCreditore ) && Objects.equals ( codTipologiaServizio, other.codTipologiaServizio )
                            && Objects.equals ( macroArea, other.macroArea );
        }
        if ( obj instanceof TassonomiaModel ) {
            TassonomiaModel other = (TassonomiaModel) obj;
            return codTipoEnteCreditore.equalsIgnoreCase ( other.getCodiceTipoEnteCreditore () ) &&
                            codTipologiaServizio.equalsIgnoreCase ( other.getCodiceTipologiaServizio () ) &&
                            macroArea.equalsIgnoreCase ( other.getProgressivoMacroAreaPerEnteCreditore () );
        }
        return false;
    }
}
