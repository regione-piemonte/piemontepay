/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import javax.ejb.EJB;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.SingolaRevocaDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameSingolaRevocaEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;



@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
    @Result(name = SUCCESS, location = "pages/dettaglio-revoca.jsp")
//@formatter:on
})
@SuppressWarnings("unchecked")
public class DettaglioRevocaAction extends CursoreAction<ColumnNameSingolaRevocaEnum, SingolaRevocaDto>{
    static private final long serialVersionUID = 1L;
    static private final String CLASSNAME = DettaglioPagamentoAction.class.getSimpleName();
    @EJB
    private GestioneFlussiBusiness gestioneFlussiBusiness;
    
    Long idRr;
    SingolaRevocaDto singolaRevoca;
    


    


    @Action("entry-dettaglio-revoca")
    @Authorization(cdu = "DET_ESP")
    public String entryDettagliRevoca() {
        return entryDettagliRevocaLocal(true);
    }
    
    public String entryDettagliRevocaLocal(boolean firstCall) {
        String methodName = "entryDettagliRevoca";
        

        String result = null;
        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );


            singolaRevoca = gestioneFlussiBusiness.getDettaglioRevoca(idRr).get ( 0 );

            
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
    
    public Long getIdRr () {
        return idRr;
    }

    
    public void setIdRr ( Long idRr ) {
        this.idRr = idRr;
    }
    
    public SingolaRevocaDto getSingolaRevoca () {
        return singolaRevoca;
    }

    
    public void setSingolaRevoca ( SingolaRevocaDto singolaRevoca ) {
        this.singolaRevoca = singolaRevoca;
    }

    @Override
    protected TotalSizeAndLightListDto<SingolaRevocaDto> getTotalSizeAndLightList ( Long idFlusso,
        List<CriterioOrdinamentoDto<ColumnNameSingolaRevocaEnum>> ord, PaginazioneDto pag ) throws BusinessException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
