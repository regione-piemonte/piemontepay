/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.ArrayList;
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
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.common.NotificaPagamentoRequestDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.StatoCursore;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = SUCCESS, location = "pages/dettaglio-pagamento.jsp")
//@formatter:on
})
@SuppressWarnings("unchecked")
public class DettaglioPagamentoAction extends CursoreAction<ColumnNameNotificaPagamentoEnum, NotificaPagamentoLightDto> {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = DettaglioPagamentoAction.class.getSimpleName();

	private NotificaPagamentoDto notificaPagamento;
	
    private Long idNotificaPagamento;
	private FlussoLightDto flussoSelezionato;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

    @Action("entry-dettaglio-pagamento")
    @Authorization(cdu = "DET_ESP")
    public String entryDettaglioPagamento() {
        return entryDettaglioPagamentoLocal(true);
    }
	
	public String entryDettaglioPagamentoLocal(boolean firstCall) {
		String methodName = "entryDettaglioPagamento";
		

		String result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			//--------------------------------//
	        //RDI-048 - START
	        //--------------------------------//
			NotificaPagamentoRequestDto notificaPagamentoRequestDto = new NotificaPagamentoRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idNotificaPagamento);
			notificaPagamento = gestioneFlussiBusiness.getNotificaPagamento(notificaPagamentoRequestDto);
			//notificaPagamento = gestioneFlussiBusiness.getNotificaPagamento(idNotificaPagamento);
	 	    flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(notificaPagamento.getIdFlusso());
			
		    if(firstCall) {
		        initCursore(flussoSelezionato.getId (), idNotificaPagamento);
		    }
            //--------------------------------//
            //RDI-048 - STOP
            //--------------------------------// 
			
			result = SUCCESS;
			
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}
	
	private void initCursore(Long idFlusso,Long idNotifica) throws BusinessException {
        List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> sorting = new ArrayList<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ();
        CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum> ();

        sortingItem.setColumnNameEnum ( ColumnNameNotificaPagamentoEnum.DATA_ESITO_PAGAMENTO );
        sortingItem.setSortTypeEnum ( SortTypeEnum.ASC );
        sorting.add ( sortingItem );

        PaginazioneDto pag = new PaginazioneDto(1, 1);

        TotalSizeAndLightListDto<NotificaPagamentoLightDto> totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndNotificaPagamentoLightList ( idFlusso, sorting, pag );

        int maxPag = totalSizeAndLightList.getTotalSize ().intValue ();

        

        StatoCursore<ColumnNameNotificaPagamentoEnum> statoCursore;
        List<Long> lightList = new ArrayList<Long>();
        lightList.add ( idNotifica );
        statoCursore = new StatoCursore<ColumnNameNotificaPagamentoEnum> ( idFlusso, lightList, pag.getNumeroPagina (), pag.getNumeroRighePerPagina (), maxPag, sorting );
        
        getSessionContext ().setStatoCursore(statoCursore);
	}
	
	//@formatter:off
	@Action("vai-al-primo-pagamento")
	public String vaiAlPrimaPagamento() throws BusinessException {
		idNotificaPagamento = super.vaiAllaPosizione(Cursore.INIZIO, (StatoCursore<ColumnNameNotificaPagamentoEnum>) getSessionContext().getStatoCursore(), idNotificaPagamento);
		return entryDettaglioPagamentoLocal(false);
	}

	@Action("vai-al-precedente-pagamento")
	public String vaiAlPrecedentePagamento() throws BusinessException {
		idNotificaPagamento = super.vaiAllaPosizione(Cursore.INDIETRO, (StatoCursore<ColumnNameNotificaPagamentoEnum>) getSessionContext().getStatoCursore(), idNotificaPagamento);
		return entryDettaglioPagamentoLocal(false);
	}

	@Action("vai-al-successivo-pagamento")
	public String vaiAlSuccessivoPagamento() throws BusinessException {
		idNotificaPagamento = super.vaiAllaPosizione(Cursore.AVANTI, (StatoCursore<ColumnNameNotificaPagamentoEnum>) getSessionContext().getStatoCursore(), idNotificaPagamento);
		return entryDettaglioPagamentoLocal(false);
	}

	@Action("vai-all-ultimo-pagamento")
	public String vaiAllUltimoPagamento() throws BusinessException {
		idNotificaPagamento = super.vaiAllaPosizione(Cursore.FINE, (StatoCursore<ColumnNameNotificaPagamentoEnum>) getSessionContext().getStatoCursore(), idNotificaPagamento);
		return entryDettaglioPagamentoLocal(false);
	}

	@Override
	protected TotalSizeAndLightListDto<NotificaPagamentoLightDto> getTotalSizeAndLightList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag)
	throws BusinessException
	{
		return gestioneFlussiBusiness.getTotalSizeAndNotificaPagamentoLightList(idFlusso, ord, pag);
	}
	//@formatter:on

	public Long getIdNotificaPagamento() {
		return idNotificaPagamento;
	}

	public void setIdNotificaPagamento(Long idNotificaPagamento) {
		this.idNotificaPagamento = idNotificaPagamento;
	}

	
	public NotificaPagamentoDto getNotificaPagamento() {
		return notificaPagamento;
	}

    public FlussoLightDto getFlussoSelezionato() {
		return flussoSelezionato;
	}

	public boolean isPrimo() {
		boolean val = super.isPrimo((StatoCursore<ColumnNameNotificaPagamentoEnum>) getSessionContext().getStatoCursore(), idNotificaPagamento);
		return val;
	}

	public boolean isUltimo() {
	    boolean val = super.isUltimo((StatoCursore<ColumnNameNotificaPagamentoEnum>) getSessionContext().getStatoCursore(), idNotificaPagamento);
        return val;
	}

}
