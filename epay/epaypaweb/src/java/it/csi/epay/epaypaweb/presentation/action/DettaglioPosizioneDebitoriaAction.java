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
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaLightDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.dto.common.TotalSizeAndPosizioneDebitoriaLightListRequestDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.StatoCursore;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = SUCCESS, location = "pages/dettaglio-posizionedebitoria.jsp"),
//@formatter:on
})
@SuppressWarnings("unchecked")
public class DettaglioPosizioneDebitoriaAction extends CursoreAction<ColumnNamePosizioneDebitoriaEnum, PosizioneDebitoriaLightDto> {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = DettaglioPosizioneDebitoriaAction.class.getSimpleName ();

	private Long idPosizioneDebitoria;
	private PosizioneDebitoriaDto posdeb;
	private FlussoLightDto flussoSelezionato;

	private static final String SI = "SI";

	private static final String NO = "NO";

	private String multibeneficiario = NO;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Action("entry-dettaglio-posizionedebitoria")
	@Authorization(cdu = "DET_LDC")
	public String entryDettaglioPosizioneDebitoria() {
		String methodName = "entryDettaglioPosizioneDebitoria";
		

		String result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			PosizioneDebitoriaRequestDto posizioneDebitoriaRequestDto = new PosizioneDebitoriaRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idPosizioneDebitoria);
			posdeb = gestioneFlussiBusiness.getPosizioneDebitoria(posizioneDebitoriaRequestDto);
			flussoSelezionato = gestioneFlussiBusiness.getFlussoLight ( posdeb.getIdFlusso () );
			String cov = flussoSelezionato.getCodVersamento ();
			Integer idCov = getIdCodVersamento ( cov );
			Boolean flagMbPrimario = getCov ( idCov ).getFlagMbPrimario ();
			if ( null != flagMbPrimario && flagMbPrimario ) {
				multibeneficiario = SI;
			} else {
				multibeneficiario = NO;
			}
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
	@Action("vai-alla-prima-posizionedebitoria")
	public String vaiAllaPrimaPosizioneDebitoria() throws BusinessException {
		idPosizioneDebitoria = super.vaiAllaPosizione(Cursore.INIZIO, (StatoCursore<ColumnNamePosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoria);
		return entryDettaglioPosizioneDebitoria();
	}

	@Action("vai-alla-precedente-posizionedebitoria")
	public String vaiAllaPrecedentePosizioneDebitoria() throws BusinessException {
		idPosizioneDebitoria = super.vaiAllaPosizione(Cursore.INDIETRO, (StatoCursore<ColumnNamePosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoria);
		return entryDettaglioPosizioneDebitoria();
	}

	@Action("vai-alla-successiva-posizionedebitoria")
	public String vaiAllaSuccessivaPosizioneDebitoria() throws BusinessException {
		idPosizioneDebitoria = super.vaiAllaPosizione(Cursore.AVANTI, (StatoCursore<ColumnNamePosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoria);
		return entryDettaglioPosizioneDebitoria();
	}

	@Action("vai-alla-ultima-posizionedebitoria")
	public String vaiAllaUltimaPosizioneDebitoria() throws BusinessException {
		idPosizioneDebitoria = super.vaiAllaPosizione(Cursore.FINE, (StatoCursore<ColumnNamePosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoria);
		return entryDettaglioPosizioneDebitoria();
	}

	@Override
	protected TotalSizeAndLightListDto<PosizioneDebitoriaLightDto> getTotalSizeAndLightList(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord, PaginazioneDto pag)
	throws BusinessException
	{
		TotalSizeAndPosizioneDebitoriaLightListRequestDto totalSizeAndPosizioneDebitoriaLightListRequestDto = new TotalSizeAndPosizioneDebitoriaLightListRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idFlusso,ord,pag);
		return gestioneFlussiBusiness.getTotalSizeAndPosizioneDebitoriaLightList(totalSizeAndPosizioneDebitoriaLightListRequestDto);
	}
	//@formatter:on

	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(Long idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public PosizioneDebitoriaDto getPosizioneDebitoria() {
		return posdeb;
	}

	public FlussoLightDto getFlussoSelezionato() {
		return flussoSelezionato;
	}

	public boolean isPrimo() {
		return super.isPrimo((StatoCursore<ColumnNamePosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoria);
	}

	public boolean isUltimo() {
		return super.isUltimo((StatoCursore<ColumnNamePosizioneDebitoriaEnum>) getSessionContext().getStatoCursore(), idPosizioneDebitoria);
	}

	public String getMultibeneficiario () {
		return multibeneficiario;
	}

	public void setMultibeneficiario ( String multibeneficiario ) {
		this.multibeneficiario = multibeneficiario;
	}

}
