/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import it.csi.mdp.core.business.dao.ApplicationDao;
import it.csi.mdp.core.business.dao.ApplicationDetailDao;
import it.csi.mdp.core.business.dao.ApplicationcustomfieldsDao;
import it.csi.mdp.core.business.dao.AuditactionsDao;
import it.csi.mdp.core.business.dao.AuthorizationsDao;
import it.csi.mdp.core.business.dao.CodiciEsitoPagamentoDao;
import it.csi.mdp.core.business.dao.CommissionDao;
import it.csi.mdp.core.business.dao.ConfigDao;
import it.csi.mdp.core.business.dao.CsiLogAuditDao;
import it.csi.mdp.core.business.dao.ERDao;
import it.csi.mdp.core.business.dao.ElementoMultiVersamentoDao;
import it.csi.mdp.core.business.dao.EntiDao;
import it.csi.mdp.core.business.dao.FlussoRiversamentoDao;
import it.csi.mdp.core.business.dao.FlussoSingoloPagamentoDao;
import it.csi.mdp.core.business.dao.GatewayDao;
import it.csi.mdp.core.business.dao.GatewaycustomfieldsDao;
import it.csi.mdp.core.business.dao.GatewaydetailDao;
import it.csi.mdp.core.business.dao.GiornaleEventoDao;
import it.csi.mdp.core.business.dao.IbanEnteApplicationDao;
import it.csi.mdp.core.business.dao.IcicodiciimmDao;
import it.csi.mdp.core.business.dao.InformativePSPDao;
import it.csi.mdp.core.business.dao.ItemDao;
import it.csi.mdp.core.business.dao.ItemExtraAttributeDao;
import it.csi.mdp.core.business.dao.IuvOtticiDao;
import it.csi.mdp.core.business.dao.LanguageDao;
import it.csi.mdp.core.business.dao.MdpBckofficegroupappmappingDao;
import it.csi.mdp.core.business.dao.MdpBckofficegroupsDao;
import it.csi.mdp.core.business.dao.MdpBckofficeuserappmappingDao;
import it.csi.mdp.core.business.dao.MdpBckrolesDao;
import it.csi.mdp.core.business.dao.MdpBckrolesgroupmapDao;
import it.csi.mdp.core.business.dao.MdpBckusersDao;
import it.csi.mdp.core.business.dao.MdpBckusersgroupDao;
import it.csi.mdp.core.business.dao.MdpCurrencyDao;
import it.csi.mdp.core.business.dao.MdpErroriDao;
import it.csi.mdp.core.business.dao.MdpReceiptDao;
import it.csi.mdp.core.business.dao.NodoErroriDao;
import it.csi.mdp.core.business.dao.OptAttrDao;
import it.csi.mdp.core.business.dao.OptAttrExtraAttributeDao;
import it.csi.mdp.core.business.dao.PartAnComuneDao;
import it.csi.mdp.core.business.dao.PaymentGatewayDao;
import it.csi.mdp.core.business.dao.PaymentmodeDao;
import it.csi.mdp.core.business.dao.PdpstatiesteriDao;
import it.csi.mdp.core.business.dao.PeasTableIdDao;
import it.csi.mdp.core.business.dao.RPTDao;
import it.csi.mdp.core.business.dao.RRDao;
import it.csi.mdp.core.business.dao.RTDao;
import it.csi.mdp.core.business.dao.StatiRptDao;
import it.csi.mdp.core.business.dao.StatisticaApplicazioneTransazioneDao;
import it.csi.mdp.core.business.dao.StatoTransazioneDao;
import it.csi.mdp.core.business.dao.TransazioneDao;
import it.csi.mdp.core.business.dao.TransazioneExtraAttributeDao;
import it.csi.mdp.core.business.dao.VappgroupDao;
import it.csi.mdp.core.business.dao.VapplicationcomuniDao;
import it.csi.mdp.core.business.dao.VerroriDao;
import it.csi.mdp.core.business.dao.VtransazioneDao;
import it.csi.mdp.core.business.dao.WispParamsDao;

public class DaoFactory
{
    /**
     * Method 'createApplicationDetailDao'
     *
     * @return ApplicationDetailDao
     */
    public static ApplicationDetailDao createApplicationDetailDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ApplicationDetailDao) bf.getBean( "ApplicationDetailDao" );
    }

    /**
     * Method 'createApplicationcustomfieldsDao'
     *
     * @return ApplicationcustomfieldsDao
     */
    public static ApplicationcustomfieldsDao createApplicationcustomfieldsDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ApplicationcustomfieldsDao) bf.getBean( "ApplicationcustomfieldsDao" );
    }

    /**
     * Method 'createGatewayDao'
     *
     * @return GatewayDao
     */
    public static GatewayDao createGatewayDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (GatewayDao) bf.getBean( "GatewayDao" );
    }

    /**
     * Method 'createGatewaydetailDao'
     *
     * @return GatewaydetailDao
     */
    public static GatewaydetailDao createGatewaydetailDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (GatewaydetailDao) bf.getBean( "GatewaydetailDao" );
    }

    /**
     * Method 'createItemDao'
     *
     * @return ItemDao
     */
    public static ItemDao createItemDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ItemDao) bf.getBean( "ItemDao" );
    }

    /**
     * Method 'createItemExtraAttributeDao'
     *
     * @return ItemExtraAttributeDao
     */
    public static ItemExtraAttributeDao createItemExtraAttributeDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ItemExtraAttributeDao) bf.getBean( "ItemExtraAttributeDao" );
    }

    /**
     * Method 'createOptAttrDao'
     *
     * @return OptAttrDao
     */
    public static OptAttrDao createOptAttrDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (OptAttrDao) bf.getBean( "OptAttrDao" );
    }

    /**
     * Method 'createOptAttrExtraAttributeDao'
     *
     * @return OptAttrExtraAttributeDao
     */
    public static OptAttrExtraAttributeDao createOptAttrExtraAttributeDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (OptAttrExtraAttributeDao) bf.getBean( "OptAttrExtraAttributeDao" );
    }

    /**
     * Method 'createPartAnComuneDao'
     *
     * @return PartAnComuneDao
     */
    public static PartAnComuneDao createPartAnComuneDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (PartAnComuneDao) bf.getBean( "PartAnComuneDao" );
    }

    /**
     * Method 'createPaymentGatewayDao'
     *
     * @return PaymentGatewayDao
     */
    public static PaymentGatewayDao createPaymentGatewayDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (PaymentGatewayDao) bf.getBean( "PaymentGatewayDao" );
    }

    /**
     * Method 'createPaymentmodeDao'
     *
     * @return PaymentmodeDao
     */
    public static PaymentmodeDao createPaymentmodeDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (PaymentmodeDao) bf.getBean( "PaymentmodeDao" );
    }

    /**
     * Method 'createPdpstatiesteriDao'
     *
     * @return PdpstatiesteriDao
     */
    public static PdpstatiesteriDao createPdpstatiesteriDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (PdpstatiesteriDao) bf.getBean( "PdpstatiesteriDao" );
    }

    /**
     * Method 'createApplicationDao'
     *
     * @return ApplicationDao
     */
    public static ApplicationDao createApplicationDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ApplicationDao) bf.getBean( "ApplicationDao" );
    }

    /**
     * Method 'createIcicodiciimmDao'
     *
     * @return IcicodiciimmDao
     */
    public static IcicodiciimmDao createIcicodiciimmDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (IcicodiciimmDao) bf.getBean( "IcicodiciimmDao" );
    }

    /**
     * Method 'createLanguageDao'
     *
     * @return LanguageDao
     */
    public static LanguageDao createLanguageDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (LanguageDao) bf.getBean( "LanguageDao" );
    }

    /**
     * Method 'createMdpBckofficeuserappmappingDao'
     *
     * @return MdpBckofficeuserappmappingDao
     */
    public static MdpBckofficeuserappmappingDao createMdpBckofficeuserappmappingDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpBckofficeuserappmappingDao) bf.getBean( "MdpBckofficeuserappmappingDao" );
    }

    /**
     * Method 'createMdpCurrencyDao'
     *
     * @return MdpCurrencyDao
     */
    public static MdpCurrencyDao createMdpCurrencyDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpCurrencyDao) bf.getBean( "MdpCurrencyDao" );
    }

    /**
     * Method 'createTransazioneDao'
     *
     * @return TransazioneDao
     */
    public static TransazioneDao createTransazioneDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (TransazioneDao) bf.getBean( "TransazioneDao" );
    }

    public static NodoErroriDao createNodoErroriDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (NodoErroriDao) bf.getBean( "NodoErroriDao" );
    }

    /**
     * Method 'createPeasTableIdDao'
     *
     * @return LanguageDao
     */
    public static PeasTableIdDao createPeasTableIdDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (PeasTableIdDao) bf.getBean( "PeasTableIdDao" );
    }

    /**
     * Method 'createTransazioneExtraAttributeDao'
     *
     * @return TransazioneExtraAttributeDao
     */
    public static TransazioneExtraAttributeDao createTransazioneExtraAttributeDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (TransazioneExtraAttributeDao) bf.getBean( "TransazioneExtraAttributeDao" );
    }

    /**
     * Method 'createTransazioneExtraAttributeDao'
     *
     * @return TransazioneExtraAttributeDao
     */
    public static VapplicationcomuniDao createVapplicationcomuniDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (VapplicationcomuniDao) bf.getBean( "VapplicationcomuniDao" );
    }

    /**
     * Method 'createMdpBckofficegroupappmappingDao'
     *
     * @return MdpBckofficegroupappmappingDao
     */
    public static MdpBckofficegroupappmappingDao createMdpBckofficegroupappmappingDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpBckofficegroupappmappingDao) bf.getBean( "MdpBckofficegroupappmappingDao" );
    }

    /**
     * Method 'createMdpBckofficegroupsDao'
     *
     * @return MdpBckofficegroupsDao
     */
    public static MdpBckofficegroupsDao createMdpBckofficegroupsDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpBckofficegroupsDao) bf.getBean( "MdpBckofficegroupsDao" );
    }

    /**
     * Method 'createMdpBckrolesDao'
     *
     * @return MdpBckrolesDao
     */
    public static MdpBckrolesDao createMdpBckrolesDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpBckrolesDao) bf.getBean( "MdpBckrolesDao" );
    }

    /**
     * Method 'createMdpBckrolesgroupmapDao'
     *
     * @return MdpBckrolesgroupmapDao
     */
    public static MdpBckrolesgroupmapDao createMdpBckrolesgroupmapDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpBckrolesgroupmapDao) bf.getBean( "MdpBckrolesgroupmapDao" );
    }

    /**
     * Method 'createMdpBckusersDao'
     *
     * @return MdpBckusersDao
     */
    public static MdpBckusersDao createMdpBckusersDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpBckusersDao) bf.getBean( "MdpBckusersDao" );
    }

    /**
     * Method 'createMdpBckusersgroupDao'
     *
     * @return MdpBckusersgroupDao
     */
    public static MdpBckusersgroupDao createMdpBckusersgroupDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpBckusersgroupDao) bf.getBean( "MdpBckusersgroupDao" );
    }

    /**
     * Method 'createAuditingDao'
     *
     * @return AuditingDao
     */
    public static CsiLogAuditDao createCsiLogAuditDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (CsiLogAuditDao) bf.getBean( "CsiLogAuditDao" );
    }

    /**
     * Method 'createAuthorizationsDao'
     *
     * @return AuthorizationsDao
     */
    public static AuthorizationsDao createAuthorizationsDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (AuthorizationsDao) bf.getBean( "AuthorizationsDao" );
    }

    /**
     * Method 'createVtransazioneDao'
     *
     * @return VtransazioneDao
     */
    public static VtransazioneDao createVtransazioneDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (VtransazioneDao) bf.getBean( "VtransazioneDao" );
    }

    /**
     * Method 'createVappgroupDao'
     *
     * @return VappgroupDao
     */
    public static VappgroupDao createVappgroupDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (VappgroupDao) bf.getBean( "VappgroupDao" );
    }

    /**
     * Method 'createStatoTransazioneDao'
     *
     * @return StatoTransazioneDao
     */
    public static StatoTransazioneDao createStatoTransazioneDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (StatoTransazioneDao) bf.getBean( "StatoTransazioneDao" );
    }

    public static MdpErroriDao createMdpErroriDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (MdpErroriDao) bf.getBean( "MdpErroriDao" );
    }

    public static VerroriDao createVErroriDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (VerroriDao) bf.getBean( "VerroriDao" );
    }

    public static AuditactionsDao createAuditactionsDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (AuditactionsDao) bf.getBean( "AuditactionsDao" );
    }

    public static ConfigDao createConfigDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ConfigDao) bf.getBean( "ConfigDao" );
    }
    public static CommissionDao createCommissionDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (CommissionDao) bf.getBean( "CommissionDao" );
    }

    public static GatewaycustomfieldsDao createGatewaycustomfieldsDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (GatewaycustomfieldsDao) bf.getBean( "GatewaycustomfieldsDao" );
    }

    public static GiornaleEventoDao createGiornaleEventoDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (GiornaleEventoDao) bf.getBean("GiornaleEventoDao");
    }

    public static RTDao createRTDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (RTDao) bf.getBean("RTDao");
    }

    public static MdpReceiptDao createMdpReceiptDao () {
        BeanFactory bf = new XmlBeanFactory ( new ClassPathResource ( "dao-beans.xml" ) );
        return (MdpReceiptDao) bf.getBean ( "MdpReceiptDao" );
    }

    public static RPTDao createRPTDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (RPTDao) bf.getBean("RPTDao");
    }

    public static RRDao createRRDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (RRDao) bf.getBean("RRDao");
    }

    public static ERDao createERDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ERDao) bf.getBean("ERDao");
    }

    public static InformativePSPDao createInformativePSPDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (InformativePSPDao) bf.getBean("InformativePSPDao");
    }

    public static CodiciEsitoPagamentoDao createCodiciEsitoPagamentoDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (CodiciEsitoPagamentoDao) bf.getBean("CodiciEsitoPagamentoDao");
    }

    public static StatiRptDao createStatiRptDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (StatiRptDao) bf.getBean("StatiRptDao");
    }

    public static EntiDao createEntiDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (EntiDao) bf.getBean("EntiDao");
    }

    public static FlussoRiversamentoDao createFlussoRiversamentoDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (FlussoRiversamentoDao) bf.getBean("FlussoRiversamentoDao");
    }

    public static FlussoSingoloPagamentoDao createFlussoSingoloPagamentoDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (FlussoSingoloPagamentoDao) bf.getBean("FlussoSingoloPagamentoDao");
    }

    public static StatisticaApplicazioneTransazioneDao createStatisticaApplicazioneTransazioneDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (StatisticaApplicazioneTransazioneDao) bf.getBean("StatisticaApplicazioneTransazioneDao");
    }

    public static IbanEnteApplicationDao createIbanEnteApplicationDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (IbanEnteApplicationDao) bf.getBean("IbanEnteApplicationDao");
    }


    public static IuvOtticiDao createIuvOtticiDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (IuvOtticiDao) bf.getBean("IuvOtticiDao");
    }


    public static WispParamsDao createWispParamsDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (WispParamsDao) bf.getBean("WispParamsDao");
    }

    public static ElementoMultiVersamentoDao createElementoMultiversamentoDao()
    {
        BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
        return (ElementoMultiVersamentoDao) bf.getBean("ElementoMultiversamentoDao");
    }

}
