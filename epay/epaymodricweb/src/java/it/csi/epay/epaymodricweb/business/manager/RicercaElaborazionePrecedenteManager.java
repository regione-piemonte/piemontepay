/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.business.manager;

import java.util.List;

import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.model.GenericVO;
import it.csi.epay.epaymodricweb.model.elaborazione.ElaborazioneVO;
import it.csi.epay.epaymodricweb.model.elaborazione.RicercaElaborazionePrecedenteFiltroVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoOrigineVO;

/**
 *
 *
 * @author Nouroudine Gueye
 *
 */
public interface RicercaElaborazionePrecedenteManager {

    //	public List<ElaborazioneVO> ricercaElaborazionePrecedente(String idEnte,String utenteElaborazione,
    //			String statoElaborazione, String dataInizio, String dataFine);

    public List<ElaborazioneVO> ricercaElaborazionePrecedente ( RicercaElaborazionePrecedenteFiltroVO filtro ) throws OperationFailedException;

    public List<GenericVO> elencaStatiElaborazione () throws OperationFailedException;

    public List<FlussoOrigineVO> ricercaFlussiOrigineAssociatiAElaborazione ( String listaFlussi ) throws OperationFailedException;

}
