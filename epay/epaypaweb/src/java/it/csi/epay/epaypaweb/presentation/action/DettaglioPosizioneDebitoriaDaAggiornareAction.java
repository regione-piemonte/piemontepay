/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareLightDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.StatoCursore;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = SUCCESS, location = "pages/dettaglio-posizionedebitoriadaaggiornare.jsp")
//@formatter:on
})
@SuppressWarnings("unchecked")
public class DettaglioPosizioneDebitoriaDaAggiornareAction extends CursoreAction<ColumnNameAggPosizioneDebitoriaEnum, PosizioneDebitoriaDaAggiornareLightDto> {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = DettaglioPosizioneDebitoriaDaAggiornareAction.class.getSimpleName ();

	private Long idPosizioneDebitoriaDaAggiornare;
	private PosizioneDebitoriaDaAggiornareDto aggposdeb;
	private FlussoLightDto flussoSelezionato;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Action("entry-dettaglio-posizionedebitoriadaaggiornare")
	@Authorization(cdu = "DET_AGPD")
	public String entryDettaglioPosizioneDebitoriaDaAggiornare() {
		String methodName = "entryDettaglioPosizioneDebitoriaDaAggiornare";
		

		String result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			 PosizioneDebitoriaDaAggiornareRequestDto posizioneDebitoriaDaAggiornareRequestDto = new PosizioneDebitoriaDaAggiornareRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idPosizioneDebitoriaDaAggiornare);

			aggposdeb = gestioneFlussiBusiness.getPosizioneDebitoriaDaAggiornare(posizioneDebitoriaDaAggiornareRequestDto);
			flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(aggposdeb.getIdFlusso());
			result = SUCCESS;

		} catch (Exception e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	//@formatter:off
	@Action("vai-alla-prima-posizionedebitoriadaaggiornare")
	public String vaiAllaPrimaPosizioneDebitoriaDaAggiornare() throws BusinessException {
		idPosizioneDebitoriaDaAggiornare = super.vaiAllaPosizione(Cursore.INIZIO, (StatoCursore<ColumnNameAggPosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoriaDaAggiornare);
		return entryDettaglioPosizioneDebitoriaDaAggiornare();
	}

	@Action("vai-alla-precedente-posizionedebitoriadaaggiornare")
	public String vaiAllaPrecedentePosizioneDebitoriaDaAggiornare() throws BusinessException {
		idPosizioneDebitoriaDaAggiornare = super.vaiAllaPosizione(Cursore.INDIETRO, (StatoCursore<ColumnNameAggPosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoriaDaAggiornare);
		return entryDettaglioPosizioneDebitoriaDaAggiornare();
	}

	@Action("vai-alla-successiva-posizionedebitoriadaaggiornare")
	public String vaiAllaSuccessivaPosizioneDebitoriaDaAggiornare() throws BusinessException {
		idPosizioneDebitoriaDaAggiornare = super.vaiAllaPosizione(Cursore.AVANTI, (StatoCursore<ColumnNameAggPosizioneDebitoriaEnum>)getSessionContext().getStatoCursore(), idPosizioneDebitoriaDaAggiornare);
		return entryDettaglioPosizioneDebitoriaDaAggiornare();
	}

	@Action("vai-alla-ultima-posizionedebitoriadaaggiornare")
	public String vaiAllaUltimaPosizioneDebitoriaDaAggiornare() throws BusinessException {
		idPosizioneDebitoriaDaAggiornare = super.vaiAllaPosizione(Cursore.FINE, (StatoCursore<ColumnNameAggPosizioneDebitoriaEnum>)getSessionContext().getStatoCursore(), idPosizioneDebitoriaDaAggiornare);
		return entryDettaglioPosizioneDebitoriaDaAggiornare();
	}

	@Override
	protected TotalSizeAndLightListDto<PosizioneDebitoriaDaAggiornareLightDto> getTotalSizeAndLightList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag)
	throws BusinessException
	{
		return gestioneFlussiBusiness.getTotalSizeAndPosizioneDebitoriaDaAggiornareLightList(idFlusso, ord, pag);
	}
	//@formatter:on

	public Long getIdPosizioneDebitoriaDaAggiornare() {
		return idPosizioneDebitoriaDaAggiornare;
	}

	public void setIdPosizioneDebitoriaDaAggiornare(Long idPosizioneDebitoriaDaAggiornare) {
		this.idPosizioneDebitoriaDaAggiornare = idPosizioneDebitoriaDaAggiornare;
	}

	public PosizioneDebitoriaDaAggiornareDto getPosizioneDebitoriaDaAggiornare() {
		return aggposdeb;
	}

	public FlussoLightDto getFlussoSelezionato() {
		return flussoSelezionato;
	}

	public boolean isPrimo() {
		return super.isPrimo((StatoCursore<ColumnNameAggPosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoriaDaAggiornare);
	}

	public boolean isUltimo() {
		return super.isUltimo((StatoCursore<ColumnNameAggPosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoriaDaAggiornare);
	}

}
