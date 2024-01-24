/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Catalogo;
import it.csi.epay.epaymodric.business.epaymodric.manager.CatalogoManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogo;
import it.csi.epay.epaymodric.business.epaymodric.repository.CatalogoRepository;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.RiconciliazioneException;


/**
 *
 */

@Service
public class CatalogoManagerImpl implements CatalogoManager {
    
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Autowired
    CatalogoRepository catalogoRepository;

    @Override
    public Catalogo recuperaDatiCatalogo ( String idEnte, String codiceVersamento, String datiSpecificiRiscossione, Integer annoEsercizio,
        Date dataPagamento ) {

        Catalogo catalogoAccertamento = new Catalogo ();
        CblTCatalogo entity = catalogoRepository.findOneAccertamento ( idEnte, codiceVersamento, datiSpecificiRiscossione, annoEsercizio, dataPagamento );

        if ( null != entity ) {
            catalogoAccertamento.setAccertamentoAnno ( entity.getAccertamentoAnno () );
            catalogoAccertamento.setAccertamentoDesc ( entity.getAccertamentoDesc () );
            catalogoAccertamento.setAccertamentoNro ( entity.getAccertamentoNro () );
            catalogoAccertamento.setAnnoEsercizio ( entity.getAnnoEsercizio () );
            catalogoAccertamento.setArticolo ( entity.getArticolo () );
            catalogoAccertamento.setCapitolo ( entity.getCapitolo () );
            catalogoAccertamento.setCategoria ( entity.getCategoria () );
            catalogoAccertamento.setCodiceFiscaleEnte ( entity.getCodiceFiscaleEnte () );
            catalogoAccertamento.setCodiceVersamento ( entity.getCodiceVersamento () );
            catalogoAccertamento.setDataFineValidita ( entity.getDataFineValidita () );
            catalogoAccertamento.setDataInizioValidita ( entity.getDataInizioValidita () );
            catalogoAccertamento.setDatiSpecificiRiscossione ( entity.getDatiSpecificiRiscossione () );
            catalogoAccertamento.setDescrizioneVersamento ( entity.getDescrizioneVersamento () );
            catalogoAccertamento.setIdEnte ( entity.getIdEnte () );
            catalogoAccertamento.setPianoDeiConti ( entity.getPianoDeiConti () );
            catalogoAccertamento.setSottovoce ( entity.getSottovoce () );
            catalogoAccertamento.setTipologia ( entity.getTipologia () );
            catalogoAccertamento.setTitolo ( entity.getTitolo () );
        } else {
            catalogoAccertamento = null;
        }
        return catalogoAccertamento;
    }

    
    @Override
    public Catalogo recuperaRiferimentoContabilePerAnnoCorrenteAttivo ( String idEnte, String codiceVersamento, Integer annoEsercizio, Date dataValidita, String datiSpecificiRiscossione, Date dataPagamento) throws RiconciliazioneException {

        Catalogo catalogoAccertamento = new Catalogo ();
        List<CblTCatalogo> entities = catalogoRepository.recuperaRiferimentoContabilePerAnnoCorrenteAttivo ( idEnte, codiceVersamento, annoEsercizio, dataValidita );

        if(!CollectionUtils.isEmpty(entities)) {
        	if(entities.size()>1) {
        	    LOGGER.info ( "Trovati piu' riferimenti contabili per i seguenti parametri di ricerca." );
        	    LOGGER.info ( "Ente: " +  idEnte);
        	    LOGGER.info ( "Codice versamento:" + codiceVersamento);
        	    LOGGER.info ( "Anno esercizio:" + annoEsercizio);
        	    LOGGER.info ( "Data validita:" + dataValidita);
                Catalogo catalogo = recuperaDatiCatalogo ( idEnte, codiceVersamento, datiSpecificiRiscossione, annoEsercizio, dataPagamento );
                if ( null == catalogo ) {
                    LOGGER.info ( "datiSpecificiRiscossione: " + datiSpecificiRiscossione );
                    LOGGER.info ( "dataPagamento: " + dataPagamento );
                    String message = String.format ( "Per l'ente [%s] e il codice versamento [%s], per l'anno corrente sono attivi piu' riferimenti contabili",
                        idEnte, codiceVersamento );
                    throw new RiconciliazioneException ( message );
                }
        	} else {
                catalogoAccertamento.setAccertamentoAnno ( entities.get(0).getAccertamentoAnno () );
                catalogoAccertamento.setAccertamentoDesc ( entities.get(0).getAccertamentoDesc () );
                catalogoAccertamento.setAccertamentoNro ( entities.get(0).getAccertamentoNro () );
                catalogoAccertamento.setAnnoEsercizio ( entities.get(0).getAnnoEsercizio () );
                catalogoAccertamento.setArticolo ( entities.get(0).getArticolo () );
                catalogoAccertamento.setCapitolo ( entities.get(0).getCapitolo () );
                catalogoAccertamento.setCategoria ( entities.get(0).getCategoria () );
                catalogoAccertamento.setCodiceFiscaleEnte ( entities.get(0).getCodiceFiscaleEnte () );
                catalogoAccertamento.setCodiceVersamento ( entities.get(0).getCodiceVersamento () );
                catalogoAccertamento.setDataFineValidita ( entities.get(0).getDataFineValidita () );
                catalogoAccertamento.setDataInizioValidita ( entities.get(0).getDataInizioValidita () );
                catalogoAccertamento.setDatiSpecificiRiscossione ( entities.get(0).getDatiSpecificiRiscossione () );
                catalogoAccertamento.setDescrizioneVersamento ( entities.get(0).getDescrizioneVersamento () );
                catalogoAccertamento.setIdEnte ( entities.get(0).getIdEnte () );
                catalogoAccertamento.setPianoDeiConti ( entities.get(0).getPianoDeiConti () );
                catalogoAccertamento.setSottovoce ( entities.get(0).getSottovoce () );
                catalogoAccertamento.setTipologia ( entities.get(0).getTipologia () );
                catalogoAccertamento.setTitolo ( entities.get(0).getTitolo () );
        	}
        } else {
        	catalogoAccertamento = null;
        }
        return catalogoAccertamento;
    }
}
