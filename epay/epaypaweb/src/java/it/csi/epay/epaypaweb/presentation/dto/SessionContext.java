/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

import it.csi.epay.epaypaweb.dto.CategoriaCduDto;
import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfilazioneUtenteDto;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.dto.UtenteDto;


public class SessionContext implements Serializable {

    private static final String CODICE_CATEGORIA_CDU_TRASVERSALI = "TRASVERSALE";

    private static final String CODICE_CATEGORIA_CDU_VIRTUALI = "VIRTUALI";

    private static final long serialVersionUID = 1L;

    private EnteDto ente;

    private RuoloDto ruolo;

    private UtenteDto utente;

    private ProfiloDto profilo;

    private List<CduDto> listaCdu;

    private List<CategoriaCduDto> categorieCdu;

    private List<CodiceVersamentoDto> codiciVersamento;

    private List<CodiceVersamentoDto> codiciVersamentoVisibili;
    
    private List<CodiceVersamentoDto> codiciVersamentoListeDiCarico;

    private List<TipoFormatoFileDto> formatiFile;

    private StatoCursore<?> statoCursore;

    private Map<String, ActionScope> scopeMap;

    public ProfilazioneUtenteDto generaProfilazioneUtenteDto () {
        ProfilazioneUtenteDto output = new ProfilazioneUtenteDto ();

        output.setCategorieCdu ( categorieCdu == null ? null : new ArrayList<> ( categorieCdu ) );
        output.setCodiciVersamentoVisibili ( codiciVersamentoVisibili == null ? null : new ArrayList<> ( codiciVersamentoVisibili ) );
        output.setEnte ( ente );
        output.setListaCdu ( listaCdu == null ? null : new ArrayList<> ( listaCdu ) );
        output.setUtente ( utente );

        return output;
    }

    public SessionContext () {
        scopeMap = new HashMap<> ();
    }

    public List<CodiceVersamentoDto> getCodiciVersamentoVisibili () {
        return codiciVersamentoVisibili;
    }

    public void setCodiciVersamentoVisibili ( List<CodiceVersamentoDto> codiciVersamentoVisibili ) {
        this.codiciVersamentoVisibili = codiciVersamentoVisibili;
    }
    

    public List<CodiceVersamentoDto> getCodiciVersamentoListeDiCarico() {
		return codiciVersamentoListeDiCarico;
	}

	public void setCodiciVersamentoListeDiCarico(List<CodiceVersamentoDto> codiciVersamentoListeDiCarico) {
		this.codiciVersamentoListeDiCarico = codiciVersamentoListeDiCarico;
	}

	public UtenteDto getUtente () {
        return utente;
    }

    public void setUtente ( UtenteDto utente ) {
        this.utente = utente;
    }

    public ProfiloDto getProfilo () {
        return profilo;
    }

    public void setProfilo ( ProfiloDto profilo ) {
        this.profilo = profilo;
    }

    public RuoloDto getRuolo () {
        return ruolo;
    }

    public void setRuolo ( RuoloDto ruolo ) {
        this.ruolo = ruolo;
    }

    public EnteDto getEnte () {
        return ente;
    }

    public void setEnte ( EnteDto ente ) {
        this.ente = ente;
    }

    public List<CduDto> getListaCdu () {
        return listaCdu;
    }

    public void setListaCdu ( List<CduDto> listaCdu ) {
        this.listaCdu = listaCdu;

        //        categorieCdu = new HashSet<CategoriaCduDto> ();
        //		for (CduDto cdu : listaCdu) {
        //			if (!categorieCdu.contains(cdu.getCategoria())) {
        //				categorieCdu.add(cdu.getCategoria());
        //			}
        //		}
    }

    public void setCategorieCdu ( List<CategoriaCduDto> categorieCdu ) {
        this.categorieCdu = categorieCdu;
    }

    public List<CategoriaCduDto> getCategorieCdu () {
        return categorieCdu;
    }

    public List<CodiceVersamentoDto> getCodiciVersamento () {
        return codiciVersamento;
    }

    public void setCodiciVersamento ( List<CodiceVersamentoDto> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }

    public List<TipoFormatoFileDto> getFormatiFile () {
        return formatiFile;
    }

    public void setFormatiFile ( List<TipoFormatoFileDto> formatiFile ) {
        this.formatiFile = formatiFile;
    }

    public StatoCursore<?> getStatoCursore () {
        return statoCursore;
    }

    public void setStatoCursore ( StatoCursore<?> statoCursore ) {
        this.statoCursore = statoCursore;
    }

    public void resetStatoCursore () {
        statoCursore = null;
    }

    public ActionScope getActionScope () {
        ActionProxy invocationProxy = ActionContext.getContext ().getActionInvocation ().getProxy ();

        String scopeName = invocationProxy.getNamespace ().concat ( "-" ).concat ( invocationProxy.getAction ().getClass ().getName () );

        ActionScope actionScope = scopeMap.get ( scopeName );

        if ( actionScope == null ) {
            actionScope = new ActionScope ();
            scopeMap.put ( scopeName, actionScope );
        }

        return actionScope;
    }

    public List<CduDto> getListaCduNonVirtuali () {
        if ( listaCdu == null || listaCdu.isEmpty () ) {
            return Collections.emptyList ();
        } else {
            List<CduDto> output = new ArrayList<> ();

            for ( CduDto cdu: listaCdu ) {
                if ( cdu.getCategoria () != null &&
                    !cdu.getCategoria ().getCod ().equals ( CODICE_CATEGORIA_CDU_TRASVERSALI ) &&
                    !cdu.getCategoria ().getCod ().equals ( CODICE_CATEGORIA_CDU_VIRTUALI ) ) {
                    output.add ( cdu );
                }
            }

            return output;
        }

    }

}
