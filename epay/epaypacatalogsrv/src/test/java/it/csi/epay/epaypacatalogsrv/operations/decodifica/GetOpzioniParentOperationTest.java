/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.decodifica;

import java.lang.reflect.Method;

import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniInput;
import it.csi.epay.epaypacatalogsrv.interfacews.Epaypacatalogsrv;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;


public abstract class GetOpzioniParentOperationTest<OutputType> extends ParentOperationTest {

    protected GetOpzioniInput getInput () {
        GetOpzioniInput input = new GetOpzioniInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        return input;
    }

    protected OutputType call () {
        return call ( getInput () );
    }

    @SuppressWarnings ( "unchecked" )
    protected OutputType call ( GetOpzioniInput input ) {

        String operationBeanName = this.getClass ().getSimpleName ().replace ( "OperationTest", "Operation" );
        String portMethodName = operationBeanName.replace ( "Operation", "" );
        portMethodName = portMethodName.substring ( 0, 1 ).toLowerCase () + portMethodName.substring ( 1 );

        try {
            Epaypacatalogsrv port = getPort ();
            Method method = port.getClass ().getMethod ( portMethodName, GetOpzioniInput.class );
            Object methodResult = method.invoke ( port, input );

            return (OutputType) methodResult;
        } catch ( Exception e ) {
            throw new RuntimeException ( "Can't autoload port test method", e );
        }
    }

}
