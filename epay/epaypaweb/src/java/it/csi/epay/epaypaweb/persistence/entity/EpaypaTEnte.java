/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_ente database table.
 * 
 */
@Entity
@Table(name="epaypa_t_ente")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTEnte.findCodFiscaleEnteByIdEnte",
			query = "SELECT e.codFiscaleEnte "
					+ "FROM EpaypaTEnte e "
					+ "WHERE e.idEnte = :idEnte"),
	@NamedQuery(
			name = "EpaypaTEnte.findIdEnteByCodFiscaleEnte",
			query = "SELECT e.idEnte "
					+ "FROM EpaypaTEnte e "
					+ "WHERE e.codFiscaleEnte = :codFiscaleEnte"),
	@NamedQuery(
			name = "EpaypaTEnte.findOneByCodFiscaleEnte",
			query = "SELECT e "
					+ "FROM EpaypaTEnte e "
					+ "WHERE e.codFiscaleEnte = :codFiscaleEnte"),
	@NamedQuery(
			name = "EpaypaTEnte.findAllByCodUtente",
			query = "SELECT e "
					+ "FROM EpaypaTUtente u, EpaypaTEnte e "
					+ "JOIN u.epaypaTEntes rel "
					+ "WHERE u.codUtente = :codUtente "
					+ "AND e.idEnte = rel.idEnte"),
	@NamedQuery(
			name="EpaypaTEnte.findAll",
			query="SELECT e FROM EpaypaTEnte e"),

	@NamedQuery(
	    name="EpaypaTEnte.findIdEnteMax",
	    query = "select u from EpaypaTEnte u order by u.idEnte desc"),
	
})
public class EpaypaTEnte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name="EPAYPA_T_ENTE_ID_GENERATOR", sequenceName="epaypa.EPAYPA_T_ENTE_SEQ" ,schema="epaypa", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYPA_T_ENTE_ID_GENERATOR")
	@Column(name="id_ente")
	private Integer idEnte;

	@Column(name="cod_fiscale_ente")
	private String codFiscaleEnte;

	private String denominazione;

	private String email;

	@Column ( name = "cod_gs1_gln" )
    private String codGs1Gln;

    @Column ( name = "cod_interbancario" )
    private String codInterbancario;

    private byte[] logo;
    	
	public EpaypaTEnte() {
	}

	public Integer getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getCodFiscaleEnte() {
		return this.codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    
    public String getCodGs1Gln () {
        return codGs1Gln;
    }

    
    public void setCodGs1Gln ( String codGs1Gln ) {
        this.codGs1Gln = codGs1Gln;
    }

    
    public String getCodInterbancario () {
        return codInterbancario;
    }

    
    public void setCodInterbancario ( String codInterbancario ) {
        this.codInterbancario = codInterbancario;
    }

    
    public byte [] getLogo () {
        return logo;
    }

    
    public void setLogo ( byte [] logo ) {
        this.logo = logo;
    }

}
