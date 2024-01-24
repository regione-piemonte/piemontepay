/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ProfilazioneEpayPaCatalogSrvDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private EnteDto ente;

    private RuoloDto ruolo;

    private UtenteCatalogDto utente;

    private ProfiloDto profilo;

    private List<CduDto> listaCdu;

    private List<CategoriaCduDto> categorieCdu;

    private List<CodiceVersamentoDto> codiciVersamento;

    private List<CodiceVersamentoDto> codiciVersamentoVisibili;
    
    private List<CodiceVersamentoDto> codiciVersamentoListeDiCarico;

    private List<TematicaDto> tematiche;

    public ProfilazioneEpayPaCatalogSrvDto () {
        super ();
        listaCdu = new ArrayList<> ();
        categorieCdu = new ArrayList<> ();
        codiciVersamento = new ArrayList<> ();
        codiciVersamentoVisibili = new ArrayList<> ();
        codiciVersamentoListeDiCarico = new ArrayList<> ();
        tematiche = new ArrayList<> ();
    }

    public List<CodiceVersamentoDto> getCodiciVersamentoVisibili () {
        return codiciVersamentoVisibili;
    }

    public List<TematicaDto> getTematiche () {
        return tematiche;
    }

    public EnteDto getEnte () {
        return ente;
    }

    public void setEnte ( EnteDto ente ) {
        this.ente = ente;
    }

    public RuoloDto getRuolo () {
        return ruolo;
    }

    public void setRuolo ( RuoloDto ruolo ) {
        this.ruolo = ruolo;
    }

    public UtenteCatalogDto getUtente () {
        return utente;
    }

    public void setUtente ( UtenteCatalogDto utente ) {
        this.utente = utente;
    }

    public ProfiloDto getProfilo () {
        return profilo;
    }

    public void setProfilo ( ProfiloDto profilo ) {
        this.profilo = profilo;
    }

    public List<CduDto> getListaCdu () {
        return listaCdu;
    }

    public List<CategoriaCduDto> getCategorieCdu () {
        return categorieCdu;
    }

    public List<CodiceVersamentoDto> getCodiciVersamento () {
        return codiciVersamento;
    }
    

    public List<CodiceVersamentoDto> getCodiciVersamentoListeDiCarico() {
		return codiciVersamentoListeDiCarico;
	}

	@Override
    public String toString () {
        final String COMMA = ", ";
        StringBuilder s = new StringBuilder ();
        s.append ( "{ " );
        s.append ( "utente:" ).append ( utente ).append ( COMMA );
        s.append ( "ente:" ).append ( ente ).append ( COMMA );
        s.append ( "ruolo:" ).append ( ruolo ).append ( COMMA );
        s.append ( "profilo:" ).append ( profilo ).append ( COMMA );
        s.append ( "listaCdu:" ).append ( listaCdu ).append ( COMMA );
        s.append ( "categorieCdu:" ).append ( categorieCdu ).append ( COMMA );
        s.append ( "codiciVersamento:" ).append ( codiciVersamento ).append ( COMMA );
        s.append ( "tematiche:" ).append ( tematiche );
        s.append ( " }" );
        return s.toString ();
    }

}
