/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.IOException;
import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTInvioMailDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTInvioMailDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoInvioMail;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTInvioMail;

public class EpaypaTInvioMailDaoTest extends DaoBaseTest {

    @Before
    public void setup() throws IOException {
    	super.setup();
    }

    @Test
    public void testInsert() {
    	EpaypaTInvioMailDao epaypaTInvioMail = new EpaypaTInvioMailDaoImpl();
    	try {
			setEntityManager(epaypaTInvioMail);

        	EpaypaDTipoInvioMail epaypaDTipoInvioMail1 = new EpaypaDTipoInvioMail();
        	epaypaDTipoInvioMail1.setIdTipoInvioMail(1);

        	transaction.begin();
        	EpaypaTInvioMail invioMail1 = new EpaypaTInvioMail();
        	invioMail1.setDtInserimento(new Timestamp(System.currentTimeMillis()));
        	invioMail1.setDestinatarioTo("test1@csi.it");
        	invioMail1.setEpaypaDTipoInvioMail(epaypaDTipoInvioMail1);
        	invioMail1.setIdFlusso(43L);
			epaypaTInvioMail.persist(invioMail1);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    public void testCompleteCycleCaso1() {
        try {
        	EpaypaTInvioMailDao epaypaTInvioMail = new EpaypaTInvioMailDaoImpl();
        	setEntityManager(epaypaTInvioMail);

        	EpaypaDTipoInvioMail epaypaDTipoInvioMail1 = new EpaypaDTipoInvioMail();
        	epaypaDTipoInvioMail1.setIdTipoInvioMail(1);

        	transaction.begin();
        	EpaypaTInvioMail invioMail1 = new EpaypaTInvioMail();
        	invioMail1.setDtInserimento(new Timestamp(System.currentTimeMillis()));
        	invioMail1.setDestinatarioTo("test1@csi.it");
        	invioMail1.setEpaypaDTipoInvioMail(epaypaDTipoInvioMail1);
			epaypaTInvioMail.persist(invioMail1);
			transaction.commit();

			transaction.begin();
			EpaypaTInvioMail invioMail2 = new EpaypaTInvioMail();
			invioMail2.setDtInserimento(new Timestamp(System.currentTimeMillis()-60000L*10));
			invioMail2.setDtUltimoTentativo(new Timestamp(System.currentTimeMillis()-60000L)); // 60 secondi dall'ultimo tentativo
			invioMail2.setDestinatarioTo("test2@csi.it");
			invioMail2.setEpaypaDTipoInvioMail(epaypaDTipoInvioMail1);
			epaypaTInvioMail.persist(invioMail2);
			transaction.commit();

        	transaction.begin();
            EpaypaTInvioMail result = epaypaTInvioMail.findNextMailToSend(30); // 30 secondi dall'ultimo invio
			transaction.commit();

        	transaction.begin();
        	epaypaTInvioMail.remove(invioMail1);
        	epaypaTInvioMail.remove(invioMail2);
			transaction.commit();

            Assert.assertEquals("test2@csi.it", result.getDestinatarioTo());

        } catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void testCompleteCycleCaso2() {
    	try {
    		EpaypaTInvioMailDao epaypaTInvioMail = new EpaypaTInvioMailDaoImpl();
    		setEntityManager(epaypaTInvioMail);

        	EpaypaDTipoInvioMail epaypaDTipoInvioMail1 = new EpaypaDTipoInvioMail();
        	epaypaDTipoInvioMail1.setIdTipoInvioMail(1);

        	transaction.begin();
    		EpaypaTInvioMail invioMail1 = new EpaypaTInvioMail();
    		invioMail1.setDtInserimento(new Timestamp(System.currentTimeMillis()));
    		invioMail1.setDestinatarioTo("test1@csi.it");
    		invioMail1.setEpaypaDTipoInvioMail(epaypaDTipoInvioMail1);
    		epaypaTInvioMail.persist(invioMail1);
    		transaction.commit();

    		transaction.begin();
    		EpaypaTInvioMail invioMail2 = new EpaypaTInvioMail();
    		invioMail2.setDtInserimento(new Timestamp(System.currentTimeMillis()-60000L*10));
    		invioMail2.setDtUltimoTentativo(new Timestamp(System.currentTimeMillis()-20000L)); // 20 secondi dall'ultimo tentativo
    		invioMail2.setDestinatarioTo("test2@csi.it");
    		invioMail2.setEpaypaDTipoInvioMail(epaypaDTipoInvioMail1);
    		epaypaTInvioMail.persist(invioMail2);
    		transaction.commit();

    		transaction.begin();
    		EpaypaTInvioMail result = epaypaTInvioMail.findNextMailToSend(30); // 30 secondi dall'ultimo invio
    		transaction.commit();

    		transaction.begin();
    		epaypaTInvioMail.remove(invioMail1);
    		epaypaTInvioMail.remove(invioMail2);
    		transaction.commit();

    		Assert.assertEquals("test1@csi.it", result.getDestinatarioTo());

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public void testCompleteCycleCaso3() {
    	try {
    		EpaypaTInvioMailDao epaypaTInvioMail = new EpaypaTInvioMailDaoImpl();
    		setEntityManager(epaypaTInvioMail);

        	EpaypaDTipoInvioMail epaypaDTipoInvioMail1 = new EpaypaDTipoInvioMail();
        	epaypaDTipoInvioMail1.setIdTipoInvioMail(1);

        	transaction.begin();
    		EpaypaTInvioMail invioMail1 = new EpaypaTInvioMail();
    		invioMail1.setDtInserimento(new Timestamp(System.currentTimeMillis()));
    		invioMail1.setDestinatarioTo("test1@csi.it");
    		invioMail1.setEpaypaDTipoInvioMail(epaypaDTipoInvioMail1);
    		epaypaTInvioMail.persist(invioMail1);
    		transaction.commit();

    		transaction.begin();
    		EpaypaTInvioMail result = epaypaTInvioMail.findNextMailToSend(30); // 30 secondi dall'ultimo invio
    		transaction.commit();

    		transaction.begin();
    		epaypaTInvioMail.remove(invioMail1);
    		transaction.commit();

    		Assert.assertEquals("test1@csi.it", result.getDestinatarioTo());

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
