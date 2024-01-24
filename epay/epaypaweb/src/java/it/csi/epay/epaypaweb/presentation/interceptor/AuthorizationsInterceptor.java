/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.presentation.Constants;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.annotation.Authorizations;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class AuthorizationsInterceptor implements Interceptor {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = AuthorizationsInterceptor.class.getSimpleName ();

    private static final Logger log = LogManager.getLogger ( APPLICATION_CODE + ".presentation" );

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String methodName = actionInvocation.getProxy().getMethod();
//        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
//        lw.start();
		log.info ( CLASSNAME + " " + methodName + " - START" );

        boolean authorized = true;

        Object action = actionInvocation.getAction();
        Method method = action.getClass().getDeclaredMethod(methodName);

        List<String> cduList = new ArrayList<String>();

        if (method.isAnnotationPresent(Authorization.class)) {
            Authorization authorization = method.getAnnotation(Authorization.class);
            cduList.add(authorization.cdu());
        }
        if (method.isAnnotationPresent(Authorizations.class)) {
            Authorization[] authorizations = method.getAnnotationsByType(Authorization.class);
            for (Authorization auth : authorizations) {
                cduList.add(auth.cdu());
            }
        }

        if (cduList.size() > 0) {
            authorized = false;

            SessionContext sessionContext = (SessionContext) ActionContext.getContext().getSession().get(Constants.SESSION_CONTEXT_SESSION_KEY);
            if (sessionContext.getListaCdu() != null) {
                outerloop:
                    for (CduDto cdu : sessionContext.getListaCdu()) {
                        for (String cduI : cduList) {
                            if (cdu.getCod().equals(cduI)) {
                                authorized = true;
                                break outerloop;
                            }
                        }
                    }
            }
        }

        return authorized ? actionInvocation.invoke() : "unauthorized-error";
    }

}
