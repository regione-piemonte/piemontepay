/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa;

import java.sql.Timestamp;
import java.util.List;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

import it.csi.coopdiag.api.InvocationNode;
import it.csi.coopdiag.engine.utils.DefaultSelfChecker;
import it.csi.csi.wrapper.CSIException;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ConfigDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.GatewayDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao.interfacce.MdpDao;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTErrore;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplication;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFields;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFieldsTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetail;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetailTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnte;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnteTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTTemplate;
import it.csi.mdpnew.mdpcoopapplicativasrv.interfacecsi.mdpcoopapplicativa.MdpcoopapplicativaSrv;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.MdpcoopapplicativaConstants;


public class MdpcoopapplicativaImpl implements MdpcoopapplicativaSrv {

    MdpDao mdpDao;

    /// dichiarazione del self checker (utilizzato in monitoraggio e diagnostica)
    String _codS = "mdp"; // e' corretto che sia il codice prodotto?

    String _codR = "mdpcoopapplicativa";

    String [] _suppS = new String [] {};

    String [] _suppR = new String [] {};

    DefaultSelfChecker schk = new DefaultSelfChecker ( _codS, _codR,
        getLogger ( null ).getName (), _suppS, _suppR, "/checked_resources_mdpcoopapplicativa.xml" );

    public boolean testResources () throws it.csi.csi.wrapper.CSIException {
        Logger logger = getLogger ( null );
        logger.debug ( "[MdpiuvImpl::testResources()] BEGIN" );
        InvocationNode in = new InvocationNode ();
        try {
            logger.debug ( "[MdpiuvImpl::testResources()] END" );
            return schk.testResources ();
        } catch ( CSIException ex ) {
            logger
                .error ( "[MdpiuvImpl::testResources()] : si e' verificato un errore  "
                    + ex );
            throw ex;
        }
    }

    public it.csi.coopdiag.api.InvocationNode selfCheck ( it.csi.coopdiag.api.CalledResource [] alreadyChecked ) throws it.csi.csi.wrapper.CSIException {
        Logger logger = getLogger ( null );
        logger.debug ( "[MdpiuvImpl::selfCheck] - BEGIN" );
        InvocationNode in = new InvocationNode ();
        try {
            return schk.selfCheck ( alreadyChecked );
        } catch ( CSIException ex ) {
            logger.error ( "[MdpiuvImpl::selfCheck()] si e' verificato un errore  "
                + ex );
        }
        logger.debug ( "[MdpiuvImpl::selfCheck] - END" );
        return in;
    }

    public boolean hasSelfCheck () throws it.csi.csi.wrapper.CSIException {
        return true;
    }

    public void init ( Object initOptions ) {
    }

    protected Logger getLogger ( String subsystem ) {
        if ( subsystem != null )
            return Logger.getLogger ( MdpcoopapplicativaConstants.LOGGER_PREFIX + "." + subsystem );
        else
            return Logger.getLogger ( MdpcoopapplicativaConstants.LOGGER_PREFIX );
    }

    public MdpDao getMdpDao () {
        return mdpDao;
    }

    public void setMdpDao ( MdpDao mdpDao ) {
        this.mdpDao = mdpDao;
    }

    //UPDATE

    //SERVE
    public void updateEnte ( MdpTEnte enteOld ) {
        mdpDao.updateEnte ( enteOld );
    }

    //SERVE
    public void removeEnteTemp ( String idOperazione) {
        mdpDao.removeEnteTemp ( idOperazione );
    }

    public void removeEnteTempByPiva ( String piva) {
        mdpDao.removeEnteTempByPiva ( piva );
    }

    //SERVE
    public void persistAppTemp ( MdpTApplicationTemp applicationTemp ) {
        mdpDao.persistAppTemp ( applicationTemp );
    }

    //SERVE
    public void persistApp ( MdpTApplication app, boolean present ) {
        mdpDao.persistApp ( app, present );
    }

    //SERVE
    public void persistAppDetailTemp ( List<MdpTApplicationDetailTemp> appDetailTemp ) {
        mdpDao.persistAppDetailTemp ( appDetailTemp );
    }

    //SERVE
    public void persistAppCustomTemp ( List<MdpTApplicationCustomFieldsTemp> appCustomTemp ) {
        mdpDao.persistAppCustomTemp ( appCustomTemp );
    }

    public void persistAppCustomTemp ( MdpTApplicationCustomFieldsTemp appCustomTemp ) {
        mdpDao.persistAppCustomTemp ( appCustomTemp );
    }

    //SERVE
    public void removeApplicationTemp ( String idOperazione ) {
        mdpDao.removeApplicationTemp ( idOperazione );
    }

    //SERVE
    public void removeApplicationCustomTemp ( String idOperazione ) {
        mdpDao.removeApplicationCustomTemp ( idOperazione );
    }

    //SERVE
    public void removeApplicationDetailTemp ( String idOperazione ) {
        mdpDao.removeApplicationDetailTemp ( idOperazione );
    }

    //SERVE
    public void persistAppDetail ( List<MdpTApplicationDetail> appDetail, boolean present ) {
        mdpDao.persistAppDetail ( appDetail, present );
    }

    //SERVE
    public void persistAppCustom ( List<MdpTApplicationCustomFields> appCustom, boolean present, boolean toEncode ) {
        mdpDao.persistAppCustom ( appCustom, present, toEncode );
    }

    //SERVE
    public List<ConfigDTO> findAllConfig () throws SOAPException {
        return mdpDao.findAllConfig ();
    }
    //SELECT

    //SERVE
    public MdpTErrore findOneErroreByCodiceErrore ( String codiceErrore ) {
        return mdpDao.findOneErroreByCodiceErrore ( codiceErrore );
    }

    //SERVE
    public List<MdpTEnteTemp> findEnteTempByIdOperazione ( String idOperazione ) {
        return mdpDao.findEnteTempByIdOperazione ( idOperazione );
    }

    //SERVE
    public List<MdpTApplicationTemp> findAllApplicationTemp ( String idOperazione ) {
        return mdpDao.findAllApplicationTemp ( idOperazione );
    }

    //SERVE
    public List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomTemp ( String idOperazione ) {
        return mdpDao.findAllApplicationCustomTemp ( idOperazione );
    }

    //SERVE
    public List<MdpTApplicationDetailTemp> findAllApplicationDetailTemp ( String idOperazione ) {
        return mdpDao.findAllApplicationDetailTemp ( idOperazione );
    }

    //SERVE
    public List<MdpTTemplate> findAllTemplates ( String gateway, String progetto ) {
        return mdpDao.findAllTemplates ( gateway, progetto );
    }

    //SERVE
    public MdpTApplication getApplication ( String idApplication ) {
        return mdpDao.getApplication ( idApplication );
    }

    public List<MdpTApplicationCustomFields> findAllApplicationCustomFields(String appIdEnteOld) {
        return mdpDao.findAllApplicationCustomFields(appIdEnteOld);
    }
    
    public List<MdpTApplicationCustomFields> findAllApplicationCustomFieldsByAppidParziale(String appIdParziale) {
        return mdpDao.findAllApplicationCustomFieldsByAppidParziale(appIdParziale);
    }
    
    public MdpTEnte findOneEnte ( String cfEnte ) {
        return mdpDao.findOneEnte ( cfEnte );
    }

    public MdpTTemplate getGateway () {
        return mdpDao.getGateway();
    }

    public String encode ( String toEncode) {
        return mdpDao.encode(toEncode);
    }

    public void persistEnteTemp ( MdpTEnteTemp enteTemp ) {
        mdpDao.persistEnteTemp(enteTemp);
    }

    public Integer getEnteMaxId () {
        return mdpDao.getEnteMaxId();
    }

    public void persistEnte ( MdpTEnte enteNew, boolean present ) {
        mdpDao.persistEnte ( enteNew, present );
    }
    
    public String richiediApplicationId(String codiceFiscaleEnte, String iban) {
    	return mdpDao.richiediApplicationId(codiceFiscaleEnte, iban);
    }

    public String richiediApplicationIdTmp(String codiceFiscaleEnte, String iban) {
        return mdpDao.richiediApplicationIdTmp(codiceFiscaleEnte, iban);
    }

    public void persistRelation ( String ente_id, String appId ) {
        mdpDao.persistRelation(ente_id, appId);
    }

    public Boolean isPresentRelazEnteApp ( String ente_id, String appId ) {
        return mdpDao.isPresentRelazEnteApp(ente_id, appId);
    }

    public List<MdpTApplicationDetail> findAllApplicationDetail ( String appId_OLD ) {
        return mdpDao.findAllApplicationDetail(appId_OLD);
    }

    public MdpTApplication findOneApplication ( String id ) {
        return mdpDao.findOneApplication(id);
    }

    public List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomFieldsTemp ( String idOperazione ) {
        return mdpDao.findAllApplicationCustomFieldsTemp(idOperazione);
    }

    public List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomFieldsTempByAppId ( String appId) {
        return mdpDao.findAllApplicationCustomFieldsTempByAppId(appId);
    }

    public MdpTApplication findMaxApplication ( String cf ) {
        return mdpDao.findMaxApplication("EPAY_" + cf + "_");
    }
    
    //CSI_PAG-316
    public String richiediApplicationIdNew(String codiceFiscaleEnte, String iban, String ibanAppoggio, String idOperazione) {
    	return mdpDao.richiediApplicationIdNew(codiceFiscaleEnte, iban, ibanAppoggio, idOperazione);
    }

}
