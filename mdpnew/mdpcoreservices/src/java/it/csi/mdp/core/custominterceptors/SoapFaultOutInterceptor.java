/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.core.custominterceptors;

import it.csi.mdp.core.util.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.WebFault;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.databinding.DataWriter;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.FaultOutInterceptor;
import org.apache.cxf.message.FaultMode;
import org.apache.cxf.message.Message;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.FaultInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.OperationInfo;
import org.w3c.dom.Node;

public class SoapFaultOutInterceptor extends FaultOutInterceptor {

    private static final Logger LOG = LogUtils.getL7dLogger(SoapFaultOutInterceptor.class);

    public SoapFaultOutInterceptor() {
        super();
    }
    
    private WebFault getWebFaultAnnotation(Class<?> t) {
        WebFault fault = t.getAnnotation(WebFault.class);
        if (fault == null
            && t.getSuperclass() != null
            && Throwable.class.isAssignableFrom(t.getSuperclass())) {
            fault = getWebFaultAnnotation(t.getSuperclass());
        }
        return fault;
    }
    
    public void handleMessage(Message message) throws Fault {
    	Fault overriddenFault = new Fault(new RuntimeException(Constants.DEFAULT_ERROR_MESSAGE));
    	message.setContent(Exception.class, overriddenFault);
        Fault f = (Fault)message.getContent(Exception.class);
        f.setFaultCode(new QName("", Constants.DEFAULT_ERROR_CODE));
        f.setMessage(Constants.DEFAULT_ERROR_MESSAGE);
        
        
        LOG.log(Level.WARNING, "CUSTOM FAULT HANDLING");

        Throwable cause = f.getCause();
        
        WebFault fault = null;
        if (cause != null) {
            fault = getWebFaultAnnotation(cause.getClass());
        }
        if (cause instanceof Exception && fault != null) {
            Exception ex = (Exception)cause;
            Service service = message.getExchange().get(Service.class);

            try {
                DataWriter<Node> writer = service.getDataBinding().createWriter(Node.class);
    
                QName faultName = new QName("defaultError", "generic");
                OperationInfo op = message.getExchange().get(BindingOperationInfo.class).getOperationInfo();
                MessagePartInfo part = getFaultMessagePart(faultName, op);
                JAXBElement<String> jaxbElement =
                		  new JAXBElement<String>(new QName("root-element"), 
                		    String.class, Constants.DEFAULT_ERROR_MESSAGE);
                if (f.hasDetails()) {
                    writer.write(jaxbElement, part, f.getDetail());
                } else {
                    writer.write(jaxbElement, part, f.getOrCreateDetail());
                    if (!f.getDetail().hasChildNodes()) {
                        f.setDetail(null);
                    }
                }
    
                
            } catch (Exception nex) {
                //if exception occurs while writing a fault, we'll just let things continue
                //and let the rest of the chain try handling it as is.
                LOG.log(Level.WARNING, "EXCEPTION_WHILE_WRITING_FAULT", nex);
            }
        } else {
            FaultMode mode = message.get(FaultMode.class);
            if (mode == FaultMode.CHECKED_APPLICATION_FAULT) {
                //only convert checked exceptions with this
                //otherwise delegate down to the normal protocol specific stuff
                super.handleMessage(message);
            }
        }
    }


    private MessagePartInfo getFaultMessagePart(QName qname, OperationInfo op) {
        for (FaultInfo faultInfo : op.getFaults()) {
            for (MessagePartInfo mpi : faultInfo.getMessageParts()) {
                String ns = null;
                if (mpi.isElement()) {
                    ns = mpi.getElementQName().getNamespaceURI();
                } else {
                    ns = mpi.getTypeQName().getNamespaceURI();
                }
                if (qname.getLocalPart().equals(mpi.getConcreteName().getLocalPart()) 
                        && qname.getNamespaceURI().equals(ns)) {
                    return mpi;
                }
            }
            
        }
        return null;
    }

}
