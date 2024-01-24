/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/** dto facade <-> business <-> persistence */
public class DettaglioPagamentiDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private DettaglioPosizioneDebitoriaDto dettaglioPosizioneDebitoria = new DettaglioPosizioneDebitoriaDto ();

    private DettaglioSoggettoDebitoreDto dettaglioSoggettoDebitore = new DettaglioSoggettoDebitoreDto ();

    private List<DettaglioComponenteImportoDto> dettaglioComponentiImporto = new LinkedList<> ();
    
    //RDI-23
    private String causale;

    public DettaglioSoggettoDebitoreDto getDettaglioSoggettoDebitore () {
        return dettaglioSoggettoDebitore;
    }

    public List<DettaglioComponenteImportoDto> getDettaglioComponentiImporto () {
        return dettaglioComponentiImporto;
    }

    public DettaglioPosizioneDebitoriaDto getDettaglioPosizioneDebitoria () {
        return dettaglioPosizioneDebitoria;
    }

    @Override
    public String toString () {
        return "DettaglioPagamentiDto [" + ( dettaglioPosizioneDebitoria != null ? "dettaglioPosizioneDebitoria=" + dettaglioPosizioneDebitoria + ", " : "" )
            + ( dettaglioSoggettoDebitore != null ? "dettaglioSoggettoDebitore=" + dettaglioSoggettoDebitore + ", " : "" )
            + ( dettaglioComponentiImporto != null ? "dettaglioComponentiImporto=" + dettaglioComponentiImporto : "" ) + "]";
    }

    public static class DettaglioPosizioneDebitoriaDto implements Serializable {

        private static final long serialVersionUID = 1L;

        private String iuv;

        private BigDecimal importoDovuto;

        private BigDecimal importoPagato;

        private String descrizioneCausaleVersamento;

        private Date dataScadenza;

        private Date dataPagamento;

        private String descrizioneStatoPagamento;

        private Integer idStatoPagamento;

        private Boolean pagamentoSpontaneo;

        private String descrizionePagamentoSpontaneo;

        private String identificativoUnivocoDebitore;

        private String posizioneDebitoriaEsterna;

        private String descrizioneRata;

        private Integer annoRiferimento;

        private String notePagatore;
        
        private Boolean costiDiNotifica; 

        public String getIuv () {
            return iuv;
        }

        public void setIuv ( String iuv ) {
            this.iuv = iuv;
        }

        public BigDecimal getImportoDovuto () {
            return importoDovuto;
        }

        public void setImportoDovuto ( BigDecimal importoDovuto ) {
            this.importoDovuto = importoDovuto;
        }

        public BigDecimal getImportoPagato () {
            return importoPagato;
        }

        public void setImportoPagato ( BigDecimal importoPagato ) {
            this.importoPagato = importoPagato;
        }

        public String getDescrizioneCausaleVersamento () {
            return descrizioneCausaleVersamento;
        }

        public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
            this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
        }

        public Date getDataScadenza () {
            return dataScadenza;
        }

        public void setDataScadenza ( Date dataScadenza ) {
            this.dataScadenza = dataScadenza;
        }

        public Date getDataPagamento () {
            return dataPagamento;
        }

        public void setDataPagamento ( Date dataPagamento ) {
            this.dataPagamento = dataPagamento;
        }

        public String getDescrizioneStatoPagamento () {
            return descrizioneStatoPagamento;
        }

        public void setDescrizioneStatoPagamento ( String descrizioneStatoPagamento ) {
            this.descrizioneStatoPagamento = descrizioneStatoPagamento;
        }

        public Integer getIdStatoPagamento () {
            return idStatoPagamento;
        }

        public void setIdStatoPagamento ( Integer idStatoPagamento ) {
            this.idStatoPagamento = idStatoPagamento;
        }

        public Boolean getPagamentoSpontaneo () {
            return pagamentoSpontaneo;
        }

        public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
            this.pagamentoSpontaneo = pagamentoSpontaneo;
        }

        public String getDescrizionePagamentoSpontaneo () {
            return descrizionePagamentoSpontaneo;
        }

        public void setDescrizionePagamentoSpontaneo ( String descrizionePagamentoSpontaneo ) {
            this.descrizionePagamentoSpontaneo = descrizionePagamentoSpontaneo;
        }

        public String getIdentificativoUnivocoDebitore () {
            return identificativoUnivocoDebitore;
        }

        public void setIdentificativoUnivocoDebitore ( String identificativoUnivocoDebitore ) {
            this.identificativoUnivocoDebitore = identificativoUnivocoDebitore;
        }

        public String getPosizioneDebitoriaEsterna () {
            return posizioneDebitoriaEsterna;
        }

        public void setPosizioneDebitoriaEsterna ( String posizioneDebitoriaEsterna ) {
            this.posizioneDebitoriaEsterna = posizioneDebitoriaEsterna;
        }

        public String getDescrizioneRata () {
            return descrizioneRata;
        }

        public void setDescrizioneRata ( String descrizioneRata ) {
            this.descrizioneRata = descrizioneRata;
        }

        public Integer getAnnoRiferimento () {
            return annoRiferimento;
        }

        public void setAnnoRiferimento ( Integer annoRiferimento ) {
            this.annoRiferimento = annoRiferimento;
        }

        public String getNotePagatore () {
            return notePagatore;
        }

        public void setNotePagatore ( String notePagatore ) {
            this.notePagatore = notePagatore;
        }
        
        

        public Boolean getCostiDiNotifica() {
			return costiDiNotifica;
		}

		public void setCostiDiNotifica(Boolean costiDiNotifica) {
			this.costiDiNotifica = costiDiNotifica;
		}

		@Override
        public String toString () {
            return "DettaglioPosizioneDebitoriaDto [" + ( iuv != null ? "iuv=" + iuv + ", " : "" )
                + ( importoDovuto != null ? "importoDovuto=" + importoDovuto + ", " : "" )
                + ( importoPagato != null ? "importoPagato=" + importoPagato + ", " : "" )
                + ( descrizioneCausaleVersamento != null ? "descrizioneCausaleVersamento=" + descrizioneCausaleVersamento + ", " : "" )
                + ( dataScadenza != null ? "dataScadenza=" + dataScadenza + ", " : "" )
                + ( dataPagamento != null ? "dataPagamento=" + dataPagamento + ", " : "" )
                + ( descrizioneStatoPagamento != null ? "descrizioneStatoPagamento=" + descrizioneStatoPagamento + ", " : "" )
                + ( idStatoPagamento != null ? "idStatoPagamento=" + idStatoPagamento + ", " : "" )
                + ( pagamentoSpontaneo != null ? "pagamentoSpontaneo=" + pagamentoSpontaneo + ", " : "" )
                + ( descrizionePagamentoSpontaneo != null ? "descrizionePagamentoSpontaneo=" + descrizionePagamentoSpontaneo + ", " : "" )
                + ( identificativoUnivocoDebitore != null ? "identificativoUnivocoDebitore=" + identificativoUnivocoDebitore + ", " : "" )
                + ( posizioneDebitoriaEsterna != null ? "posizioneDebitoriaEsterna=" + posizioneDebitoriaEsterna + ", " : "" )
                + ( descrizioneRata != null ? "descrizioneRata=" + descrizioneRata + ", " : "" )
                + ( annoRiferimento != null ? "annoRiferimento=" + annoRiferimento + ", " : "" )
                + ( costiDiNotifica != null ? "costiDiNotifica=" + costiDiNotifica + ", " : "" )
                + ( notePagatore != null ? "notePagatore=" + notePagatore : "" ) + "]";
        }

    }

    public static class DettaglioComponenteImportoDto implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;

        private BigDecimal importo;

        private String causale;

        private String datiSpecificiRiscossione;

        private Integer annoAccertamento;

        private Integer numeroAccertamento;

        public Long getId () {
            return id;
        }

        public void setId ( Long id ) {
            this.id = id;
        }

        public BigDecimal getImporto () {
            return importo;
        }

        public void setImporto ( BigDecimal importo ) {
            this.importo = importo;
        }

        public String getCausale () {
            return causale;
        }

        public void setCausale ( String causale ) {
            this.causale = causale;
        }

        public String getDatiSpecificiRiscossione () {
            return datiSpecificiRiscossione;
        }

        public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
            this.datiSpecificiRiscossione = datiSpecificiRiscossione;
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

        @Override
        public String toString () {
            return "DettaglioComponenteImportoDto [" + ( id != null ? "id=" + id + ", " : "" ) + ( importo != null ? "importo=" + importo + ", " : "" )
                + ( causale != null ? "causale=" + causale + ", " : "" )
                + ( datiSpecificiRiscossione != null ? "datiSpecificiRiscossione=" + datiSpecificiRiscossione + ", " : "" )
                + ( annoAccertamento != null ? "annoAccertamento=" + annoAccertamento + ", " : "" )
                + ( numeroAccertamento != null ? "numeroAccertamento=" + numeroAccertamento : "" ) + "]";
        }

    }

    public static class DettaglioSoggettoDebitoreDto implements Serializable {

        private static final long serialVersionUID = 1L;

        private String cognome;

        private String nome;

        private String identificativoUnivoco;

        private String tipoSoggetto;

        private String indirizzo;

        private String civico;

        private String cap;

        private String localita;

        private String provincia;

        private String nazione;

        private String email;

        public String getCognome () {
            return cognome;
        }

        public void setCognome ( String cognome ) {
            this.cognome = cognome;
        }

        public String getNome () {
            return nome;
        }

        public void setNome ( String nome ) {
            this.nome = nome;
        }

        public String getIdentificativoUnivoco () {
            return identificativoUnivoco;
        }

        public void setIdentificativoUnivoco ( String identificativoUnivoco ) {
            this.identificativoUnivoco = identificativoUnivoco;
        }

        public String getTipoSoggetto () {
            return tipoSoggetto;
        }

        public void setTipoSoggetto ( String tipoSoggetto ) {
            this.tipoSoggetto = tipoSoggetto;
        }

        public String getIndirizzo () {
            return indirizzo;
        }

        public void setIndirizzo ( String indirizzo ) {
            this.indirizzo = indirizzo;
        }

        public String getCivico () {
            return civico;
        }

        public void setCivico ( String civico ) {
            this.civico = civico;
        }

        public String getCap () {
            return cap;
        }

        public void setCap ( String cap ) {
            this.cap = cap;
        }

        public String getLocalita () {
            return localita;
        }

        public void setLocalita ( String localita ) {
            this.localita = localita;
        }

        public String getProvincia () {
            return provincia;
        }

        public void setProvincia ( String provincia ) {
            this.provincia = provincia;
        }

        public String getNazione () {
            return nazione;
        }

        public void setNazione ( String nazione ) {
            this.nazione = nazione;
        }

        public String getEmail () {
            return email;
        }

        public void setEmail ( String email ) {
            this.email = email;
        }

        @Override
        public String toString () {
            return "DettaglioSoggettoDebitoreDto [" + ( cognome != null ? "cognome=" + cognome + ", " : "" ) + ( nome != null ? "nome=" + nome + ", " : "" )
                + ( identificativoUnivoco != null ? "identificativoUnivoco=" + identificativoUnivoco + ", " : "" )
                + ( tipoSoggetto != null ? "tipoSoggetto=" + tipoSoggetto + ", " : "" ) + ( indirizzo != null ? "indirizzo=" + indirizzo + ", " : "" )
                + ( civico != null ? "civico=" + civico + ", " : "" ) + ( cap != null ? "cap=" + cap + ", " : "" )
                + ( localita != null ? "localita=" + localita + ", " : "" ) + ( provincia != null ? "provincia=" + provincia + ", " : "" )
                + ( nazione != null ? "nazione=" + nazione + ", " : "" ) + ( email != null ? "email=" + email : "" ) + "]";
        }

    }

    
    public String getCausale () {
        return causale;
    }

    
    public void setCausale ( String causale ) {
        this.causale = causale;
    }
}
