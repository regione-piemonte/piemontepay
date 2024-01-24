/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_storico_flusso_sintesi database table.
 *
 */
@Entity
@Table(name="cbl_t_storico_flusso_sintesi",schema="epaymodric")
@NamedQuery(name="CblTStoricoFlussoSintesi.findAll", query="SELECT c FROM CblTStoricoFlussoSintesi c")
public class CblTStoricoFlussoSintesi extends DatiTecniciParentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    @Id
    @SequenceGenerator(name="CBL_T_STORICO_FLUSSO_SINTESI_ID_GENERATOR", sequenceName="epaymodric.CBL_T_STORICO_FLUSSO_SINTESI_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_STORICO_FLUSSO_SINTESI_ID_GENERATOR")
    private Long id;

    @Column(name="accertamento_anno")
    private Integer accertamentoAnno;

    @Column(name="accertamento_nro")
    private Integer accertamentoNro;

    private Integer articolo;

    private String capitolo;

    @Column(name="codice_versamento")
    private String codiceVersamento;

    @Column(name="dati_specifici_riscossione")
    private String datiSpecificiRiscossione;

    @Column ( name = "descrizione_codice_versamento" )
    private String descrizioneCodiceVersamento;

    @Column ( name = "id_errore" )
    private Long idErrore;

    @Column ( name = "id_flusso_origine" )
    private Long idFlussoOrigine;

    @Column ( name = "id_istituto_ricevente" )
    private Long idIstitutoRicevente;

    @Column(name="identificativo_flusso")
    private String identificativoFlusso;

    @Column(name="importo_quota_aggregazione")
    private BigDecimal importoQuotaAggregazione;

    @Column ( name = "numero_vers_quota_aggregazione" )
    private BigDecimal numeroVersQuotaAggregazione;

    @Column(name="piano_dei_conti")
    private String pianoDeiConti;

    @Column(name="provvisorio_anno")
    private Integer provvisorioAnno;

    @Column(name="provvisorio_nro")
    private Integer provvisorioNro;

    public CblTStoricoFlussoSintesi() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccertamentoAnno() {
        return accertamentoAnno;
    }

    public void setAccertamentoAnno(Integer accertamentoAnno) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public Integer getAccertamentoNro() {
        return accertamentoNro;
    }

    public void setAccertamentoNro(Integer accertamentoNro) {
        this.accertamentoNro = accertamentoNro;
    }

    public Integer getArticolo() {
        return articolo;
    }

    public void setArticolo(Integer articolo) {
        this.articolo = articolo;
    }

    public String getCapitolo() {
        return capitolo;
    }

    public void setCapitolo(String capitolo) {
        this.capitolo = capitolo;
    }

    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    public void setCodiceVersamento(String codiceVersamento) {
        this.codiceVersamento = codiceVersamento;
    }

    @Override
    public Timestamp getDataInserimento () {
        return dataInserimento;
    }

    @Override
    public void setDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    @Override
    public Timestamp getDataModifica () {
        return dataModifica;
    }

    @Override
    public void setDataModifica ( Timestamp dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public Long getIdErrore () {
        return idErrore;
    }

    public void setIdErrore ( Long idErrore ) {
        this.idErrore = idErrore;
    }

    public Long getIdFlussoOrigine () {
        return idFlussoOrigine;
    }

    public void setIdFlussoOrigine ( Long idFlussoOrigine ) {
        this.idFlussoOrigine = idFlussoOrigine;
    }

    public Long getIdIstitutoRicevente () {
        return idIstitutoRicevente;
    }

    public void setIdIstitutoRicevente ( Long idIstitutoRicevente ) {
        this.idIstitutoRicevente = idIstitutoRicevente;
    }

    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso(String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public BigDecimal getImportoQuotaAggregazione() {
        return importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione(BigDecimal importoQuotaAggregazione) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public BigDecimal getNumeroVersQuotaAggregazione () {
        return numeroVersQuotaAggregazione;
    }

    public void setNumeroVersQuotaAggregazione ( BigDecimal numeroVersQuotaAggregazione ) {
        this.numeroVersQuotaAggregazione = numeroVersQuotaAggregazione;
    }

    public String getPianoDeiConti() {
        return pianoDeiConti;
    }

    public void setPianoDeiConti(String pianoDeiConti) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public Integer getProvvisorioAnno() {
        return provvisorioAnno;
    }

    public void setProvvisorioAnno(Integer provvisorioAnno) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public Integer getProvvisorioNro() {
        return provvisorioNro;
    }

    public void setProvvisorioNro(Integer provvisorioNro) {
        this.provvisorioNro = provvisorioNro;
    }

    @Override
    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    @Override
    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    @Override
    public String getUtenteModifica() {
        return utenteModifica;
    }

    @Override
    public void setUtenteModifica(String utenteModifica) {
        this.utenteModifica = utenteModifica;
    }

}
