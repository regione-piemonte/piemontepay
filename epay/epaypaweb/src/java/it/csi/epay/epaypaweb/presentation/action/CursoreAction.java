/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.dto.StatoCursore;


import java.util.List;

public abstract class CursoreAction<ColumnNameEnum, T extends ElementoFlussoDto> extends EpaypawebBaseAction {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = CursoreAction.class.getSimpleName ();

	private boolean cursorClicked = false;

	protected enum Cursore {
		INIZIO, INDIETRO, AVANTI, FINE
	};

	public boolean isCursorClicked() {
		return cursorClicked;
	}

	protected Long vaiAllaPosizione(Cursore cursore, StatoCursore<ColumnNameEnum> stato, Long id) throws BusinessException {
		String methodName = "vaiAllaPosizione";
		
		
		
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		Long newId = id; // N.B. cambia valore solo se il cursore si muove

		int iRel = stato.getIdList().indexOf(id);
		int iAbs = iRel + stato.getNumeroRighePerPagina() * (stato.getNumeroPagina() - 1);
		int numeroTotalePagine = (int) Math.ceil(new Float(stato.getNumeroTotaleElementi()) / new Float(stato.getNumeroRighePerPagina()));

		switch (cursore) {
		case INIZIO: {
			updateStatoCursore(stato, 1);
			List<Long> newIdList = stato.getIdList();
			newId = newIdList.get(0);
			break;
		}
		case INDIETRO:
			if (iRel > 0)
				newId = getTotalSizeAndLightList(stato.getIdFlusso(), stato.getCriteriOrdinamento(), new PaginazioneDto(iAbs, 1)).getLightList().get(0).getId();

			else {
				if (stato.getNumeroPagina() > 1) {
					updateStatoCursore(stato, stato.getNumeroPagina() - 1);
					List<Long> newIdList = stato.getIdList();
					newId = newIdList.get(stato.getNumeroRighePerPagina() - 1);
				}
			}
			break;

		case AVANTI:
			if (iRel < stato.getNumeroRighePerPagina() - 1) {
				if (iAbs < stato.getNumeroTotaleElementi() - 1)
					newId = getTotalSizeAndLightList(stato.getIdFlusso(), stato.getCriteriOrdinamento(), new PaginazioneDto(iAbs + 2, 1)).getLightList().get(0).getId();

			} else {
				if (stato.getNumeroPagina() < numeroTotalePagine) {
					updateStatoCursore(stato, stato.getNumeroPagina() + 1);
					List<Long> newIdList = stato.getIdList();
					newId = newIdList.get(0);
				}
			}
			break;

		case FINE:
			updateStatoCursore(stato, numeroTotalePagine);
			List<Long> newIdList = stato.getIdList();
			newId = newIdList.get(getIndiceRelativoUltimo(stato));
			break;
		}

		cursorClicked = true;

		log.info ( "newId: " +  newId);
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return newId;
	}

	protected boolean isPrimo(StatoCursore<ColumnNameEnum> stato, Long id) {
		return stato.getNumeroPagina() == 1 && stato.getIdList().get(0).equals(id);
	}

	protected boolean isUltimo(StatoCursore<ColumnNameEnum> stato, Long id) {
		int numeroTotaleElementi = stato.getNumeroTotaleElementi();
		int numeroRighePerPagina = stato.getNumeroRighePerPagina();
		//
		int numeroTotalePagine = (int) Math.ceil(new Float(numeroTotaleElementi) / new Float(numeroRighePerPagina));
		boolean eval =  stato.getNumeroPagina() == numeroTotalePagine && stato.getIdList().get(getIndiceRelativoUltimo(stato)).equals(id);
		return eval;
	}

	//@formatter:off
	private List<Long> getNewIdList(StatoCursore<ColumnNameEnum> stato, Integer newNumPagina) throws BusinessException {
		TotalSizeAndLightListDto<T> newTotalSizeAndLightList = getTotalSizeAndLightList(
				stato.getIdFlusso(),
				stato.getCriteriOrdinamento(),
				new PaginazioneDto(newNumPagina, stato.getNumeroRighePerPagina())
		);
		List<T> newLightList = newTotalSizeAndLightList.getLightList();
		List<Long> newIdList = collectIds(newLightList);
		return newIdList;
	}

	private void updateStatoCursore(StatoCursore<ColumnNameEnum> stato, Integer newNumPagina) throws BusinessException {
		List<Long> newIdList = getNewIdList(stato, newNumPagina);
		stato.setNumeroPagina(newNumPagina);
		stato.setIdList(newIdList);
	}

	private int getIndiceRelativoUltimo(StatoCursore<ColumnNameEnum> stato) {
		int modulo = stato.getNumeroTotaleElementi() % stato.getNumeroRighePerPagina();
		return (modulo > 0 ? modulo : stato.getNumeroRighePerPagina()) - 1;
	}
	
	protected abstract TotalSizeAndLightListDto<T> getTotalSizeAndLightList ( Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameEnum>> ord,
		PaginazioneDto pag )
	throws BusinessException;
	//@formatter:on
}
