/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.business.StampaAvvisoPagamentoService;
import it.csi.epay.epaybeapi.dto.request.RichiestaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.response.StampaAvvisoPagamentoResponse;
import it.csi.epay.epaybeapi.integration.domain.EpayTPagamento;
import it.csi.epay.epaybeapi.integration.repository.EpayTPagamentoRepository;
import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class StampaAvvisoPagamentoServiceImplTest {

    @Autowired
    private StampaAvvisoPagamentoService stampaAvvisoPagamentoService;

    @Autowired
    private EpayTPagamentoRepository repository;

    @Test
    public void getAvvisoPagamentoShouldWork () {

        Page<EpayTPagamento> records = repository.findByStatoPagamento ( new Short ( (short) 0 ),
            new PageRequest ( 0, 1, new Sort ( Sort.Direction.DESC, "idPagamento" ) ) );
        assertNotNull ( records );
        assertTrue ( records.getSize () > 0 );

        EpayTPagamento pagamento = (EpayTPagamento) IteratorUtils.toList ( records.iterator () ).get ( 0 );

        RichiestaAvvisoPagamentoRequest request = new RichiestaAvvisoPagamentoRequest ();

        request.setIuv ( pagamento.getIuvNumeroAvviso () );

        request.setCodAvviso ( pagamento.getAuxDigit () + pagamento.getIuvNumeroAvviso () );

        StampaAvvisoPagamentoResponse response = stampaAvvisoPagamentoService.getAvvisoPagamento ( request );

        assertNotNull ( response );
        assertNotNull ( response.getAvvisoPagamentoData () );
    }
}
