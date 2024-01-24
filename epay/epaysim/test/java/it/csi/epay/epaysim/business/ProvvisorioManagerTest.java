/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaysim.business.epaysim.EpaysimulatorDatawsBusiness;
import it.csi.epay.epaysim.business.epaysim.utils.Costanti;
import it.csi.epay.epaysim.dto.InserisciProvvisorioInputDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioOutputResponse;
import it.csi.epay.epaysim.dto.ProvvisorioDTO;
import it.csi.epay.epaysim.test.config.EpaysimulatorUnitTestConfigH2;
import it.csi.epay.epaysim.test.model.ParentUnitTest;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaysimulatorUnitTestConfigH2.class } )
@Transactional
public class ProvvisorioManagerTest extends ParentUnitTest {

    @Autowired
    private EpaysimulatorDatawsBusiness epaysimulatorDatawsBusiness;

    @Test
    public void verifyInserisciEmptyList () throws Exception {
        InserisciProvvisorioOutputResponse output = epaysimulatorDatawsBusiness.inserisciProvvisorio ( new InserisciProvvisorioInputDTO () );
        assertTrue ( Costanti.RESULT_CODE_FIELD_REQUIRED.equals ( output.getCodiceEsito () ) );
    }

    @Test
    public void verifyOperationKOWith0Saves () throws Exception {
        InserisciProvvisorioInputDTO input = new InserisciProvvisorioInputDTO ();
        input.setXmlFlusso ( " " );
        List<ProvvisorioDTO> list = new ArrayList<> ();
        list.add ( new ProvvisorioDTO () );
        input.setProvvisorioDTOList ( list );
        InserisciProvvisorioOutputResponse output = epaysimulatorDatawsBusiness.inserisciProvvisorio ( input );
        assertTrue ( Costanti.RESULT_CODE_OPERATION_KO.equals ( output.getCodiceEsito () ) );
    }

    @Test
    public void verifyOperationOKWithSave () throws Exception {
        InserisciProvvisorioInputDTO input = new InserisciProvvisorioInputDTO ();
        input.setXmlFlusso ( " " );
        List<ProvvisorioDTO> list = new ArrayList<> ();
        list.add ( buildProvvisorio ( "2018-05-27PASCITMM-0E0JHIC0WV60H" ) );
        input.setProvvisorioDTOList ( list );
        InserisciProvvisorioOutputResponse output = epaysimulatorDatawsBusiness.inserisciProvvisorio ( input );
        assertTrue ( Costanti.RESULT_CODE_OK.equals ( output.getCodiceEsito () ) );
    }

    @Test
    public void verifyOperationOKWithMultipleSaves () throws Exception {
        InserisciProvvisorioInputDTO input = new InserisciProvvisorioInputDTO ();
        input.setXmlFlusso ( " " );
        List<ProvvisorioDTO> list = new ArrayList<> ();
        list.add ( buildProvvisorio ( "2018-05-25PASCITMM-0E0JHIC0WV60H" ) );
        list.add ( buildProvvisorio ( "2018-05-26PASCITMM-0E0JHIC0WV60I" ) );
        input.setProvvisorioDTOList ( list );
        InserisciProvvisorioOutputResponse output = epaysimulatorDatawsBusiness.inserisciProvvisorio ( input );
        assertTrue ( Costanti.RESULT_CODE_OK.equals ( output.getCodiceEsito () ) );
    }

    @Test
    @Transactional ( propagation = Propagation.NOT_SUPPORTED )
    public void verifyOperationKOWith2Saves () throws Exception {
        InserisciProvvisorioInputDTO input = new InserisciProvvisorioInputDTO ();
        input.setXmlFlusso ( " " );
        List<ProvvisorioDTO> list = new ArrayList<> ();
        list.add ( buildProvvisorio ( "2018-05-23PASCITMM-0E0JHIC0WV60H" ) );
        list.add ( buildProvvisorio ( "2018-05-23PASCITMM-0E0JHIC0WV60H" ) );
        input.setProvvisorioDTOList ( list );
        InserisciProvvisorioOutputResponse output = epaysimulatorDatawsBusiness.inserisciProvvisorio ( input );
        assertTrue ( Costanti.RESULT_CODE_OPERATION_COMPLETED_WITH_ERROR.equals ( output.getCodiceEsito () ) );
    }

    private ProvvisorioDTO buildProvvisorio ( String identificativoFlusso ) {
        LocalDate date = LocalDate.now ();
        GregorianCalendar gcal = GregorianCalendar.from ( date.atStartOfDay ( ZoneId.systemDefault () ) );
        XMLGregorianCalendar xcal = null;
        try {
            xcal = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gcal );
        } catch ( DatatypeConfigurationException e ) {
        }
        ProvvisorioDTO provv = new ProvvisorioDTO ();
        provv.setCodiceFiscaleEnte ( "CF113VALIDO" );
        provv.setIdentificativoFlusso ( identificativoFlusso );
        provv.setCausale ( "/PUR/LGPE-RIVERSAMENTO/URI/2018-05-28PASCITMM-0E0JHIC0WV60G" );
        provv.setDataMovimento ( xcal );
        provv.setAnnoEsercizio ( 2099 );
        provv.setAnnoProvvisorio ( 2099 );
        provv.setNumeroProvvisorio ( 999 );
        provv.setImportoProvvisorio ( BigDecimal.TEN );
        provv.setStato ( "STATO" );
        provv.setDataInsVar ( xcal );
        return provv;
    }

}
