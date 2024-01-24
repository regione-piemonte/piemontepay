/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import org.apache.struts2.convention.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.opensymphony.xwork2.Action.INPUT;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = INPUT, location = "pages/ricerca-flussi-posizionidebitoriedaaggiornare.jsp"),
	@Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" }),
	@Result(name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" }),
	@Result(name = "MODIFICA_FLUSSO_POSIZIONIDEBITORIE_DA_AGGIORNARE", type = "chain", location = "entry-visualizza-flusso-posizionidebitoriedaaggiornare")
//@formatter:on
})
public class RicercaFlussiPosizioniDebitorieDaAggiornareAction extends RicercaListeAggiornamentoPosizioniDebitorieEListedicaricoBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = RicercaFlussiPosizioniDebitorieDaAggiornareAction.class.getSimpleName();

	@Action("entry-ricerca-listeaggiornamentoposizionidebitorie")
	@Authorization(cdu = "RIC_AGPD")
	public String entryRicercaListeAggiornamentoPosizioniDebitorie() {
		return entryRicercaListe("entryRicercaListeAggiornamentoPosizioniDebitorie");
	}

	@Action("valida-filtro-ricerca-listeaggiornamentoposizionidebitorie-json")
	public String validaFiltroRicercaListeAggiornamentoPosizioniDebitorieJSON() {
		return validaFiltroRicercaJSON("validaFiltroRicercaListeAggiornamentoPosizioniDebitorieJSON");
	}

	@Action("ricerca-listeaggiornamentoposizionidebitorie-json")
	@Authorization(cdu = "RIC_AGPD")
	public String ricercaFlussiListedicaricoJSON() throws Exception {
		String methodName = "ricercaFlussiListedicaricoJSON";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		FlussoLightFilterDto filter = buildFlussoLightFilterDto(TipoFlussoEnum.AGGIORNAMENTO_POSIZIONI_DEBITORIE);

		List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> sorting = null;
		if (sortingCol != null && sortingDir != null) {
			sorting = new ArrayList<CriterioOrdinamentoDto<ColumnNameFlussoEnum>>();
			CriterioOrdinamentoDto<ColumnNameFlussoEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameFlussoEnum>();

			if ("idMessaggio".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.ID_MESSAGGIO);
			else if ("codVersamento".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.ID_CODICE_VERSAMENTO);
			else if ("numeroElementi".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.NUMERO_ELEMENTI);
			else if ("desStatoFlusso".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.DESCRIZIONE_STATO_FLUSSO);
			else if ("dataUltimaVariazione".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.DATA_ULTIMA_VARIAZIONE);

			sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
			sorting.add(sortingItem);
		}

		doSearch( filter, sorting);

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return "json-table-data";
	}

	@Action("modifica-flusso-posizionidebitoriedaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	public String modificaFlussoPosizioniDebitorieDaAggiornare() {
		String methodName = "modificaFlussoPosizioniDebitorieDaAggiornare";
		

		String result;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// cambio lo stato in bozza
			FlussoDto testataFlusso = new FlussoDto(idFlusso);
			testataFlusso.setStatoFlusso(StatoFlussoEnum.BOZZA);
			testataFlusso.setUtenteUltimaVariazione(getSessionContext().getUtente().getCod());
			gestioneFlussiBusiness.salvaTestataFlussoPosizioniDebitorieDaAggiornare(testataFlusso);
			
			result = "MODIFICA_FLUSSO_POSIZIONIDEBITORIE_DA_AGGIORNARE";
		
		} catch (Exception e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

}
