/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class GetDatiPagamentoChiamanteEsternoOutput extends AbstractOutput implements Serializable {

    private static final long serialVersionUID = -7050222655758291763L;

    @JsonProperty ( "importoPagatoTotale" )
    private BigDecimal importoPagatoTotale;

    @JsonProperty ( "enteBeneficiario" )
    private String enteBeneficiario;

    @JsonProperty ( "codiceFiscaleEnteBeneficiario" )
    private String codiceFiscaleEnteBeneficiario;

    @JsonProperty ( "tipologiaVersamento" )
    private String tipologiaVersamento;

    @JsonProperty ( "importoPagato" )
    private BigDecimal importoPagato;

    @JsonProperty ( "datiMultibeneficiario" )
    @JsonInclude ( JsonInclude.Include.NON_NULL )
    private List<GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput> datiMultibeneficiario;

    @JsonProperty ( "nomeECognomeRagioneSociale" )
    private String nomeECognomeRagioneSociale;

    @JsonProperty ( "codiceFiscalePIva" )
    private String codiceFiscalePIva;

    @JsonProperty ( "codiceAvviso" )
    private String codiceAvviso;

    @JsonProperty ( "identificativoUnicoVersamento" )
    private String identificativoUnicoVersamento;

    @JsonProperty ( "numeroTransazione" )
    private String numeroTransazione;

    @JsonProperty ( "prestatoreDiServiziDiPagamento" )
    private String prestatoreDiServiziDiPagamento;

    @JsonProperty ( "dataEOraOperazione" )
    private Date dataEOraOperazione;

    @JsonProperty ( "dataEsitoPagamento" )
    private Timestamp dataEsitoPagamento;

    @JsonProperty ( "identificativoUnicoRiscossione" )
    private String identificativoUnicoRiscossione;

    @JsonProperty ( "infoAggiuntive" )
    private String infoAggiuntive;

    

    public String getEnteBeneficiario () {
        return enteBeneficiario;
    }

    public void setEnteBeneficiario ( String enteBeneficiario ) {
        this.enteBeneficiario = enteBeneficiario;
    }

    public String getCodiceFiscaleEnteBeneficiario () {
        return codiceFiscaleEnteBeneficiario;
    }

    public void setCodiceFiscaleEnteBeneficiario ( String codiceFiscaleEnteBeneficiario ) {
        this.codiceFiscaleEnteBeneficiario = codiceFiscaleEnteBeneficiario;
    }

    public String getTipologiaVersamento () {
        return tipologiaVersamento;
    }

    public void setTipologiaVersamento ( String tipologiaVersamento ) {
        this.tipologiaVersamento = tipologiaVersamento;
    }

    
    public BigDecimal getImportoPagatoTotale () {
        return importoPagatoTotale;
    }

    
    public void setImportoPagatoTotale ( BigDecimal importoPagatoTotale ) {
        this.importoPagatoTotale = importoPagatoTotale;
    }

    
    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    
    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    public List<GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput> getDatiMultibeneficiario () {
        return datiMultibeneficiario;
    }

    public void setDatiMultibeneficiario (
        List<GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput> datiMultibeneficiario ) {
        this.datiMultibeneficiario = datiMultibeneficiario;
    }

    public String getNomeECognomeRagioneSociale () {
        return nomeECognomeRagioneSociale;
    }

    public void setNomeECognomeRagioneSociale ( String nomeECognomeRagioneSociale ) {
        this.nomeECognomeRagioneSociale = nomeECognomeRagioneSociale;
    }

    public String getCodiceFiscalePIva () {
        return codiceFiscalePIva;
    }

    public void setCodiceFiscalePIva ( String codiceFiscalePIva ) {
        this.codiceFiscalePIva = codiceFiscalePIva;
    }

    public String getCodiceAvviso () {
        return codiceAvviso;
    }

    public void setCodiceAvviso ( String codiceAvviso ) {
        this.codiceAvviso = codiceAvviso;
    }

    public String getIdentificativoUnicoVersamento () {
        return identificativoUnicoVersamento;
    }

    public void setIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    public String getNumeroTransazione () {
        return numeroTransazione;
    }

    public void setNumeroTransazione ( String numeroTransazione ) {
        this.numeroTransazione = numeroTransazione;
    }

    public String getPrestatoreDiServiziDiPagamento () {
        return prestatoreDiServiziDiPagamento;
    }

    public void setPrestatoreDiServiziDiPagamento ( String prestatoreDiServiziDiPagamento ) {
        this.prestatoreDiServiziDiPagamento = prestatoreDiServiziDiPagamento;
    }

    public Date getDataEOraOperazione () {
        return dataEOraOperazione;
    }

    public void setDataEOraOperazione ( Date dataEOraOperazione ) {
        this.dataEOraOperazione = dataEOraOperazione;
    }

    public Timestamp getDataEsitoPagamento () {
        return dataEsitoPagamento;
    }

    public void setDataEsitoPagamento ( Timestamp dataEsitoPagamento ) {
        this.dataEsitoPagamento = dataEsitoPagamento;
    }

    public String getIdentificativoUnicoRiscossione () {
        return identificativoUnicoRiscossione;
    }

    public void setIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    public String getInfoAggiuntive () {
        return infoAggiuntive;
    }

    public void setInfoAggiuntive ( String infoAggiuntive ) {
        this.infoAggiuntive = infoAggiuntive;
    }

    @Generated ( "SparkTools" )
    private GetDatiPagamentoChiamanteEsternoOutput ( GetDatiPagamentoChiamanteEsternoOutput.Builder builder ) {
        setResult ( builder.result );
    }

    /**
     * Creates builder to build {@link AggiornaPosizioneDebitoriaChiamanteEsternoOutput}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static GetDatiPagamentoChiamanteEsternoOutput.Builder builder () {
        return new GetDatiPagamentoChiamanteEsternoOutput.Builder ();
    }

    @Generated ( "SparkTools" )
    public static final class Builder {

        private Esito result;

        private Builder () {
        }

        public GetDatiPagamentoChiamanteEsternoOutput.Builder withResult ( Esito result ) {
            this.result = result;
            return this;
        }

        public GetDatiPagamentoChiamanteEsternoOutput build () {
            return new GetDatiPagamentoChiamanteEsternoOutput ( this );
        }
    }

    @Override
    public String toString () {
        return "GetDatiPagamentoChiamanteEsternoOutput{" +
            "importoPagatoTotale=" + importoPagatoTotale +
            ", enteBeneficiario='" + enteBeneficiario + '\'' +
            ", codiceFiscaleEnteBeneficiario='" + codiceFiscaleEnteBeneficiario + '\'' +
            ", tipologiaVersamento='" + tipologiaVersamento + '\'' +
            ", importoPagato=" + importoPagato +
            ", datiMultibeneficiario=" + datiMultibeneficiario +
            ", nomeECognomeRagioneSociale='" + nomeECognomeRagioneSociale + '\'' +
            ", codiceFiscalePIva='" + codiceFiscalePIva + '\'' +
            ", codiceAvviso='" + codiceAvviso + '\'' +
            ", identificativoUnicoVersamento='" + identificativoUnicoVersamento + '\'' +
            ", numeroTransazione='" + numeroTransazione + '\'' +
            ", prestatoreDiServiziDiPagamento='" + prestatoreDiServiziDiPagamento + '\'' +
            ", dataEOraOperazione='" + dataEOraOperazione + '\'' +
            ", dataEsitoPagamento='" + dataEsitoPagamento + '\'' +
            ", identificativoUnicoRiscossione='" + identificativoUnicoRiscossione + '\'' +
            ", infoAggiuntive='" + infoAggiuntive + '\'' +
            '}';
    }
}
