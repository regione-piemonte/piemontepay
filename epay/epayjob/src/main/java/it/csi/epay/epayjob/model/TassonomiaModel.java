/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TassonomiaModel implements Serializable {

    private static final long serialVersionUID = -1587099353307584569L;

    @JsonProperty ( "CODICE TIPO ENTE CREDITORE" )
    private String codiceTipoEnteCreditore;

    @JsonProperty ( "TIPO ENTE CREDITORE" )
    private String tipoEnteCreditore;

    @JsonProperty ( "Progressivo Macro Area per Ente Creditore" )
    private String progressivoMacroAreaPerEnteCreditore;

    @JsonProperty ( "NOME MACRO AREA" )
    private String nomeMacroArea;

    @JsonProperty ( "DESCRIZIONE MACRO AREA" )
    private String descrizioneMacroArea;

    @JsonProperty ( "CODICE TIPOLOGIA SERVIZIO" )
    private String codiceTipologiaServizio;

    @JsonProperty ( "TIPO SERVIZIO" )
    private String tipoServizio;

    @JsonProperty ( "Motivo Giuridico della riscossione" )
    private String motivoGiuridicoDellaRiscossione;

    @JsonProperty ( "DESCRIZIONE TIPO SERVIZIO" )
    private String descrizioneTipoServizio;

    @JsonProperty ( "VERSIONE TASSONOMIA" )
    private String versioneTassonomia;

    @JsonProperty ( "DATI SPECIFICI DI INCASSO" )
    private String datiSpecificiDiIncasso;

    @JsonProperty ( "DATA INIZIO VALIDITA" )
    private String dataInizioValidita;

    @JsonProperty ( "DATA FINE VALIDITA" )
    private String dataFineValidita;

    public String getCodiceTipoEnteCreditore () {
        return codiceTipoEnteCreditore;
    }

    public void setCodiceTipoEnteCreditore ( String codiceTipoEnteCreditore ) {
        this.codiceTipoEnteCreditore = codiceTipoEnteCreditore;
    }

    public String getTipoEnteCreditore () {
        return tipoEnteCreditore;
    }

    public void setTipoEnteCreditore ( String tipoEnteCreditore ) {
        this.tipoEnteCreditore = tipoEnteCreditore;
    }

    public String getProgressivoMacroAreaPerEnteCreditore () {
        return progressivoMacroAreaPerEnteCreditore;
    }

    public void setProgressivoMacroAreaPerEnteCreditore ( String progressivoMacroAreaPerEnteCreditore ) {
        this.progressivoMacroAreaPerEnteCreditore = progressivoMacroAreaPerEnteCreditore;
    }

    public String getNomeMacroArea () {
        return nomeMacroArea;
    }

    public void setNomeMacroArea ( String nomeMacroArea ) {
        this.nomeMacroArea = nomeMacroArea;
    }

    public String getDescrizioneMacroArea () {
        return descrizioneMacroArea;
    }

    public void setDescrizioneMacroArea ( String descrizioneMacroArea ) {
        this.descrizioneMacroArea = descrizioneMacroArea;
    }

    public String getCodiceTipologiaServizio () {
        return codiceTipologiaServizio;
    }

    public void setCodiceTipologiaServizio ( String codiceTipologiaServizio ) {
        this.codiceTipologiaServizio = codiceTipologiaServizio;
    }

    public String getTipoServizio () {
        return tipoServizio;
    }

    public void setTipoServizio ( String tipoServizio ) {
        this.tipoServizio = tipoServizio;
    }

    public String getMotivoGiuridicoDellaRiscossione () {
        return motivoGiuridicoDellaRiscossione;
    }

    public void setMotivoGiuridicoDellaRiscossione ( String motivoGiuridicoDellaRiscossione ) {
        this.motivoGiuridicoDellaRiscossione = motivoGiuridicoDellaRiscossione;
    }

    public String getDescrizioneTipoServizio () {
        return descrizioneTipoServizio;
    }

    public void setDescrizioneTipoServizio ( String descrizioneTipoServizio ) {
        this.descrizioneTipoServizio = descrizioneTipoServizio;
    }

    public String getVersioneTassonomia () {
        return versioneTassonomia;
    }

    public void setVersioneTassonomia ( String versioneTassonomia ) {
        this.versioneTassonomia = versioneTassonomia;
    }

    public String getDatiSpecificiDiIncasso () {
        return datiSpecificiDiIncasso;
    }

    public void setDatiSpecificiDiIncasso ( String datiSpecificiDiIncasso ) {
        this.datiSpecificiDiIncasso = datiSpecificiDiIncasso;
    }

    public String getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( String dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( String dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    @Override
    public String toString () {
        return "TassonomiaModel [codiceTipoEnteCreditore=" + codiceTipoEnteCreditore + ", tipoEnteCreditore=" + tipoEnteCreditore
                        + ", progressivoMacroAreaPerEnteCreditore=" + progressivoMacroAreaPerEnteCreditore + ", nomeMacroArea=" + nomeMacroArea + ", descrizioneMacroArea="
                        + descrizioneMacroArea + ", codiceTipologiaServizio=" + codiceTipologiaServizio + ", tipoServizio=" + tipoServizio
                        + ", motivoGiuridicoDellaRiscossione=" + motivoGiuridicoDellaRiscossione + ", descrizioneTipoServizio=" + descrizioneTipoServizio
                        + ", versioneTassonomia=" + versioneTassonomia + ", datiSpecificiDiIncasso=" + datiSpecificiDiIncasso + ", dataInizioValidita=" + dataInizioValidita
                        + ", dataFineValidita=" + dataFineValidita + "]";
    }

    @Override
    public int hashCode () {
        return Objects.hash ( codiceTipoEnteCreditore, codiceTipologiaServizio, progressivoMacroAreaPerEnteCreditore );
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
            return Objects.equals ( codiceTipoEnteCreditore, other.getCodTipoEnteCreditore () )
                            && Objects.equals ( codiceTipologiaServizio, other.getCodTipologiaServizio () )
                            && Objects.equals ( progressivoMacroAreaPerEnteCreditore, other.getMacroArea () );
        }
        if ( obj instanceof TassonomiaModel ) {
            TassonomiaModel other = (TassonomiaModel) obj;
            return codiceTipoEnteCreditore.equalsIgnoreCase ( other.getCodiceTipoEnteCreditore () ) &&
                            codiceTipologiaServizio.equalsIgnoreCase ( other.getCodiceTipologiaServizio () ) &&
                            progressivoMacroAreaPerEnteCreditore.equalsIgnoreCase ( other.getProgressivoMacroAreaPerEnteCreditore () );
        }
        return false;
    }

}
