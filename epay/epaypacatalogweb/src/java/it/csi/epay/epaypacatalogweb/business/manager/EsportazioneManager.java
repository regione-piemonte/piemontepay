/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RisultatoRicercaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.TassonomiaVO;
import it.csi.epay.epaypacatalogweb.model.utente.RisultatoRicercaUtenteVO;

public interface EsportazioneManager {

	void esportaVociSpesaXlsx(HttpServletResponse response, List<VoceEntrataPPayVO> vociEntrata) throws IOException;

	void esportaVociSpesaCsv(HttpServletResponse response, List<VoceEntrataPPayVO> vociEntrata) throws IOException;

    void esportaCodiciVersamentoXlsx ( HttpServletResponse response, List<RisultatoRicercaCodiceVersamentoVO> list ) throws IOException;

    void esportaCodiciVersamentoCsv ( HttpServletResponse response, List<RisultatoRicercaCodiceVersamentoVO> list ) throws IOException;

    void esportaCodiciVersamentoCollegatiXlsx ( HttpServletResponse response, CodiceVersamentoVO codicePadre,
        List<CodiceVersamentoVO> codiciVersamentoCollegati ) throws IOException;

    void esportaCodiciVersamentoCollegatiCsv ( HttpServletResponse response, CodiceVersamentoVO codicePadre,
        List<CodiceVersamentoVO> codiciVersamentoCollegati ) throws IOException;

    void esportaRiferimentiContabiliXlsx ( HttpServletResponse response, List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> list )
                    throws IOException;

    void esportaRiferimentiContabiliCsv ( HttpServletResponse response, List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> list )
                    throws IOException;

    void esportaUtentiXlsx ( HttpServletResponse response, List<RisultatoRicercaUtenteVO> list ) throws IOException;

    void esportaUtentiCsv ( HttpServletResponse response, List<RisultatoRicercaUtenteVO> list ) throws IOException;
    
    void esportaTassonomiaXlsx ( HttpServletResponse response, List<TassonomiaVO> list ) throws IOException;
    
    void esportaTassonomiaCsv ( HttpServletResponse response, List<TassonomiaVO> list ) throws IOException;
    
    

}
