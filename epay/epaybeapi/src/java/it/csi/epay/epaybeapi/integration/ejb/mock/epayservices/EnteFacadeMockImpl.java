/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.ejb.mock.epayservices;

import java.util.List;

import org.springframework.stereotype.Service;

import it.csi.epay.epayservices.model.Ente;


@Service
public class EnteFacadeMockImpl implements it.csi.epay.epayservices.interfaces.ejb.EnteFacade {

	@Override
	public List<Ente> listaEntiConTasse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ente> listaEntiConTasse(String nomeEnte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ente> listaEntiConPagamentiSpontanei() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ente> listaEntiConPagamentiConIuvPagabili() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ente ricerca(Long Id) throws IllegalArgumentException {
		Ente ente  = new Ente();
		ente.setIdEnte(Id);
		ente.setNome("Comune di Alessandria");
		ente.setCodiceFiscale("00429440068");
		ente.setFlagInvioPagamenti(Boolean.TRUE);
		return ente;
	}

	@Override
	public Ente ricerca(String Name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ente inserisci(Ente ente) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ente aggiorna(Ente ente) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
