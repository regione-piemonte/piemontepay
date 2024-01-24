/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoCollegatoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RicercaCodiceVersamentoFiltroVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RisultatoRicercaCodiceVersamentoVO;


public interface CodiceVersamentoManager {

    CodiceVersamentoVO istanziaNuovo ();

    CodiceVersamentoVO istanziaNuovo ( String codiceVoceEntrata ) throws OperationFailedException;

    CodiceVersamentoVO mergeInputUtente ( Long id, ModificaCodiceVersamentoVO input ) throws OperationFailedException;

    CodiceVersamentoVO mergeInputUtente ( Long id, ModificaCodiceVersamentoVO input, String codiceVoceEntrata ) throws OperationFailedException;

    List<RisultatoRicercaCodiceVersamentoVO> ricercaCodiciVersamento ( RicercaCodiceVersamentoFiltroVO filtro ) throws OperationFailedException;

    CodiceVersamentoVO getCodiceVersamento ( Long id ) throws OperationFailedException;

    InserisciCodiceVersamentoOutput inserisciCodiceVersamento ( ModificaCodiceVersamentoVO userInput ) throws OperationFailedException;

    AggiornaCodiceVersamentoOutput aggiornaVersamento ( ModificaCodiceVersamentoVO modelSubmitted ) throws OperationFailedException;

    void eliminaCodiceVersamento ( Long id ) throws OperationFailedException;

    void eliminaCodiceVersamentoCollegato ( Long idCodiceVersamento, Long id ) throws OperationFailedException;

    void aggiornaCodiceVersamentoCollegato ( Long idCodiceVersamento, ModificaCodiceVersamentoCollegatoVO userInput ) throws OperationFailedException;

    InserisciCodiceVersamentoOutput inserisciCodiceVersamentoCollegato ( Long idCodiceVersamento, ModificaCodiceVersamentoCollegatoVO userInput ) throws OperationFailedException;

    CodiceVersamentoVO istanziaNuovoCollegato ( Long idCodiceVersamento ) throws OperationFailedException;

    CodiceVersamentoVO mergeInputUtenteCollegato ( Long idCodiceVersamento, Long id, ModificaCodiceVersamentoCollegatoVO modelCorrente )
                    throws OperationFailedException;

    CodiceVersamentoVO getCodiceVersamentoCollegato ( Long idCodiceVersamento, Long id ) throws OperationFailedException;
    
   
}
