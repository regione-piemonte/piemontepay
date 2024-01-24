/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpnodospcservices.util;

/**
 * <p>Classe delle costanti applicative.</p>
 *
 */
public final class Constants
{
    /**
     * identificativo dell'applicativo.
     */
    public static final String APPLICATION_CODE = "mdpnodospcservices";
    public static final String SERVER_SMTP = "mail.smtp.host";
    public static final String SERVER_SMTP_PORT = "mail.smtp.port";
    public static final String SERVER_SMTP_AUTH = "mail.smtp.useauth";

    public static final String APP_CODE_PERF = "mdpnodospcservicessrv_perf";
    
    // [MP] - path jndi dell EJB PaymentManager presente nel pacchetto ear della componente mdpcoreservices
    public static final String PAYMENT_MANAGER_JNDI_PATH = "java:global/mdpcoreservicessrv2/mdpcorecontext-business/PaymentManager!it.csi.mdp.core.business.paymentmanager.PaymentHome";
}
