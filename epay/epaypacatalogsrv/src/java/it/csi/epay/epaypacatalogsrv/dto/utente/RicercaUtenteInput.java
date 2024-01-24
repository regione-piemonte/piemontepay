/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class RicercaUtenteInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private String codiceFiscale;

    private String nome;

    private String cognome;

    private String email;

    private Boolean soloUtentiInVita;

    private String codiceCategoriaCdu;

    private String codiceCdu;

    private Long idCodiceVersamento;

    private String codiceTematica;

    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getCodiceTematica () {
        return codiceTematica;
    }

    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    public String getCodiceCategoriaCdu () {
        return codiceCategoriaCdu;
    }

    public void setCodiceCategoriaCdu ( String codiceCategoriaCdu ) {
        this.codiceCategoriaCdu = codiceCategoriaCdu;
    }

    public String getCodiceCdu () {
        return codiceCdu;
    }

    public void setCodiceCdu ( String codiceCdu ) {
        this.codiceCdu = codiceCdu;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    public Boolean getSoloUtentiInVita () {
        return soloUtentiInVita;
    }

    public void setSoloUtentiInVita ( Boolean soloUtentiInVita ) {
        this.soloUtentiInVita = soloUtentiInVita;
    }

    @Override
    public String toString () {
        return "RicercaUtenteInput [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", soloUtentiInVita="
            + soloUtentiInVita + ", codiceCategoriaCdu=" + codiceCategoriaCdu + ", codiceCdu=" + codiceCdu + ", idCodiceVersamento=" + idCodiceVersamento
            + ", codiceTematica=" + codiceTematica + "]";
    }
    
    public List<String> getAudit()
    {
    	List<String> auditFilter = new ArrayList<String>();
    	
    	auditFilter.add("FILTRO= ");
    	if (null!= getNome() && !"".equals(getNome()))
    	{
    		auditFilter.add("nome: "+getNome());
    	}
    	if (null!= getCognome() && !"".equals(getCognome()))
    	{
    		auditFilter.add("cognome: "+getCognome());
    	}
    	if (null!= getCodiceFiscale() && !"".equals(getCodiceFiscale()))
    	{
    		auditFilter.add("codice fiscale: "+getCodiceFiscale());
    	}
    	if (null!= getEmail() && !"".equals(getEmail()))
    	{
    		auditFilter.add("email: "+getEmail());
    	}
    	if (null!= getSoloUtentiInVita() && getSoloUtentiInVita())
    	{
    		auditFilter.add("solo utenti in vita: opzione selezionata");
    	}
    	
    	
    	return auditFilter;
    }

}
