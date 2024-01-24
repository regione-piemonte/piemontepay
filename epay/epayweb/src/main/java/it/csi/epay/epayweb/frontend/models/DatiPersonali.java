/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import it.csi.epay.epayweb.frontend.models.validators.annotation.CodiceFiscale_PartitaIva;
import it.csi.epay.epayweb.frontend.models.validators.annotation.FieldMatch;
import it.csi.epay.epayweb.frontend.models.validators.annotation.Privacy;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliSenzaIuvPrivateGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliStampaAvvisoSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliStampaAvvisoSenzaIuvPrivateGroup;

@Component
@FieldMatch(first = "email", second = "confirmEmail", message = "'E-mail' e 'Ripeti E-mail' non coincidono.")
public class DatiPersonali extends FlagModel implements Serializable {
	
	private static final long serialVersionUID = 5669810792758318190L;

	@Autowired
	HttpSession session;
	
	@NumberFormat(style=NumberFormat.Style.NUMBER, pattern = "#,##0.00" )
	@NotNull(message="Dato obbligatorio.", groups={DatiPersonaliSenzaIuvGroup.class, DatiPersonaliSenzaIuvPrivateGroup.class,
			DatiPersonaliStampaAvvisoSenzaIuvGroup.class, DatiPersonaliStampaAvvisoSenzaIuvPrivateGroup.class})
	@Digits(integer=9, fraction=2, message="Importo non valido.")
//	@DecimalMax(value = "999999.99", message = "Importo massimo euro 999.999,99"  , groups={DatiPersonaliStampaAvvisoSenzaIuvGroup.class, DatiPersonaliStampaAvvisoSenzaIuvPrivateGroup.class})
//	@Min(value=0, message="Importo deve maggiore di zero.")
	@DecimalMin( value= "0.01", message="Importo deve essere maggiore di zero.")
	private BigDecimal importo;
	
	@Pattern(regexp = "^[\\p{L}- ]*$", message="Caratteri non validi. Solo lettere, spazio e '-'.", 
			groups={DatiPersonaliSenzaIuvGroup.class, DatiPersonaliSenzaIuvPrivateGroup.class,
					DatiPersonaliStampaAvvisoSenzaIuvGroup.class, DatiPersonaliStampaAvvisoSenzaIuvPrivateGroup.class})
	private String nome;
	
	@Pattern(regexp = "^[\\p{L}- ]*$", message="Caratteri non validi. Solo lettere, spazio e '-'.", 
			groups={DatiPersonaliSenzaIuvGroup.class, DatiPersonaliSenzaIuvPrivateGroup.class,
					DatiPersonaliStampaAvvisoSenzaIuvGroup.class, DatiPersonaliStampaAvvisoSenzaIuvPrivateGroup.class})
	private String cognome;
	
	//@NotEmpty(message="Dato obbligatorio.", groups=DatiPersonaliSenzaIuvGroup.class)
	//@SafeHtml(groups=DatiPersonaliSenzaIuvGroup.class)
	//private String ragioneSociale;
	
	@NotEmpty(message="Dato obbligatorio.")
//	@Email(regexp = "[A-Za-z0-9_]+([\\-\\+\\.'][A-Za-z0-9_]+)*@[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*\\.[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*",  message="E-mail non valida.")
	@Email(regexp = "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*",  message="E-mail non valida.")
	private String email;
	
	@NotEmpty(message="Dato obbligatorio.")
//	@Email(regexp = "[A-Za-z0-9_]+([\\-\\+\\.'][A-Za-z0-9_]+)*@[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*\\.[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*",  message="E-mail non valida.")
	@Email(regexp = "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*",  message="E-mail non valida.")
	private String confirmEmail;
	
	@NotEmpty(message="Dato obbligatorio.", groups={DatiPersonaliSenzaIuvGroup.class,DatiPersonaliStampaAvvisoSenzaIuvGroup.class })
	@Size(min=11, max=35, message="Deve essere lungo tra 11 e 35 caratteri.", groups={DatiPersonaliSenzaIuvGroup.class,DatiPersonaliStampaAvvisoSenzaIuvGroup.class })
	@CodiceFiscale_PartitaIva(value=CodiceFiscale_PartitaIva.CfPi.CODICEFISCALE_OR_PARTITAIVA,groups={DatiPersonaliSenzaIuvGroup.class,DatiPersonaliStampaAvvisoSenzaIuvGroup.class })
	@Pattern(regexp = "^[A-Za-z0-9]*$", message="Caratteri non validi. Solo numeri e lettere.",groups={DatiPersonaliSenzaIuvGroup.class,DatiPersonaliStampaAvvisoSenzaIuvGroup.class })
	private String codiceFiscale;
	
	//@Pattern(regexp = "^[0-9\\p{L}\\s]*$", message="Caratteri non validi. Solo numeri e lettere.")
	//@Pattern(regexp = "^[^<>\\\\&]*$", message="Caratteri non validi. Tuii i caratteri tranne <>\\&.")
	private String note;
	
	@NotNull
	@Privacy
	private Boolean flagPrivacy;
	
	@NotEmpty(message="Dato obbligatorio.", groups={DatiPersonaliSenzaIuvGroup.class,DatiPersonaliStampaAvvisoSenzaIuvGroup.class })
	private String soggettoGiuridico;  
	
	//@Pattern(regexp = "^[0-9\\p{L} ]*$", message="Caratteri non validi. Solo numeri e lettere.")
	@Size(min=0, max=70, message="Lunghezza massima 70 caratteri", groups={DatiPersonaliSenzaIuvGroup.class,DatiPersonaliStampaAvvisoSenzaIuvGroup.class })
	@Pattern(regexp = "^[^\\t\\n\\r<>\\\\;]*$", message="Caratteri non validi.")
	private String ragioneSociale;
	
	private List<ComponenteImporto> componentiImporto;

    private List<RiferimentoPagamento> riferimentiPagamento;
    
    private Boolean flagInitFromSession;
		
	/**
	 * @return the importo
	 */
	public BigDecimal getImporto() {
		return importo;
	}

	/**
	 * @param importo the importo to set
	 */
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the confirmEmail
	 */
	public String getConfirmEmail() {
		return confirmEmail;
	}

	/**
	 * @param confirmEmail the confirmEmail to set
	 */
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the flagPrivacy
	 */
	public Boolean getFlagPrivacy() {
		return flagPrivacy;
	}

	/**
	 * @param flagPrivacy the flagPrivacy to set
	 */
	public void setFlagPrivacy(Boolean flagPrivacy) {
		this.flagPrivacy = flagPrivacy;
	}
	

	/**
	 * @return the flagInitFromSession
	 */
	public Boolean getFlagInitFromSession() {
		return flagInitFromSession;
	}

	/**
	 * @param flagInitFromSession the flagInitFromSession to set
	 */
	public void setFlagInitFromSession(Boolean flagInitFromSession) {
		this.flagInitFromSession = flagInitFromSession;
	}

	public String getSoggettoGiuridico() {
		return soggettoGiuridico;
	}

	public void setSoggettoGiuridico(String soggettoGiuridico) {
		this.soggettoGiuridico = soggettoGiuridico;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	
	 /**
	 * @return the componentiImporto
	 */
	public List<ComponenteImporto> getComponentiImporto() {
		if (componentiImporto == null) {
			componentiImporto = new ArrayList<DatiPersonali.ComponenteImporto>();
		}
		return componentiImporto;
	}

	/**
	 * @param componentiImporto the componentiImporto to set
	 */
	public void setComponentiImporto(List<ComponenteImporto> componentiImporto) {
		this.componentiImporto = componentiImporto;
	}
	
	public void addComponentiImporto(ComponenteImporto componenteImporto) {
		this.getComponentiImporto().add(componenteImporto);
	}
	
	public void addComponentiImporto(BigDecimal importo, String causale) {
		this.addComponentiImporto(new ComponenteImporto(importo, causale));
	}
	
	public boolean hasComponentiImporto() {
		return this.getComponentiImporto() != null && !this.getComponentiImporto().isEmpty();
	}

    /**
     * @return the riferimentiPagamento
     */
    public List<RiferimentoPagamento> getRiferimentiPagamento () {
        if ( riferimentiPagamento == null ) {
            riferimentiPagamento = new ArrayList<RiferimentoPagamento> ();
        }
        return riferimentiPagamento;
    }

    /**
     * @param riferimentiPagamento the riferimentiPagamento to set
     */
    public void setRiferimentiPagamento ( List<RiferimentoPagamento> riferimentiPagamento ) {
        this.riferimentiPagamento = riferimentiPagamento;
    }

    public void addRiferimentiPagamento ( RiferimentoPagamento riferimentoPagamento ) {
        this.getRiferimentiPagamento ().add ( riferimentoPagamento );
    }

    public void addRiferimentiPagamento ( String nome, String valore ) {
        RiferimentoPagamento riferimentoPagamento = new RiferimentoPagamento ();
        riferimentoPagamento.setNome ( nome );
        riferimentoPagamento.setValore ( valore );
        this.addRiferimentiPagamento ( riferimentoPagamento );
        //        this.addRiferimentiPagamento ( new RiferimentoPagamento ( nome, valore ) );
    }

    public boolean hasRiferimentiPagamento () {
        return this.getRiferimentiPagamento () != null && !this.getRiferimentiPagamento ().isEmpty ();
    }

	public class ComponenteImporto implements Serializable {
		private static final long serialVersionUID = 5469810792758318190L;
		private BigDecimal importo;
		private String causale;
		
		public ComponenteImporto(BigDecimal importo, String causale) {
			super();
			this.importo = importo;
			this.causale = causale;
		}
		
		/**
		 * @return the importo
		 */
		public BigDecimal getImporto() {
			return importo;
		}
		/**
		 * @param importo the importo to set
		 */
		public void setImporto(BigDecimal importo) {
			this.importo = importo;
		}
		/**
		 * @return the causale
		 */
		public String getCausale() {
			return causale;
		}
		/**
		 * @param desc the causale to set
		 */
		public void setCausale(String causale) {
			this.causale = causale;
		} 
		
	 }
}
