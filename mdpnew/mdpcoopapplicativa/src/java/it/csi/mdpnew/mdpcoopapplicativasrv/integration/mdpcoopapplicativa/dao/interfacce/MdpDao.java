/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao.interfacce;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.xml.soap.SOAPException;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ApplicationcustomfieldsDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ConfigDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.DescrizioneApplicativoDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.GatewayDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.IuvAttributeDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.ErrorParameterException;
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


public interface MdpDao {

    String dataOdierna ();

    boolean isPresentIDApplication ( String idApplication );

    DescrizioneApplicativoDTO isPresentIDEnte ( String idApplication );

    IuvAttributeDTO getIuvCorrente ( String idEnte );

    public String getAuxDigit ( String idApp ) throws ErrorParameterException, SOAPException;

    public List<ApplicationcustomfieldsDTO> getApplicationcustomfields ( String applicationid, String gatewayid, String sKey ) throws SOAPException;

    void insertNewDay ( String idEnte );

    void updateStoricizza ( String idEnte );

    void updateIncremento ( String idEnte, BigInteger progressivo, String data );
    
    void updateEnte(MdpTEnte ente);

    //PERSIST

    void persistAppDetail ( List<MdpTApplicationDetail> appDetail, boolean present );

    void persistAppCustom ( List<MdpTApplicationCustomFields> appCustom, boolean present, boolean toEncode );

    void persistApp ( MdpTApplication app, boolean present );

    void persistAppDetailTemp ( List<MdpTApplicationDetailTemp> appDetailTemp );

    void persistAppCustomTemp ( List<MdpTApplicationCustomFieldsTemp> appCustomTemp );

    void persistAppCustomTemp ( MdpTApplicationCustomFieldsTemp appCustomTemp );

    void persistAppTemp ( MdpTApplicationTemp applicationTemp );

    //REMOVE

    void removeApplicationTemp ( String idOperazione );

    void removeApplicationCustomTemp ( String idOperazione );

    void removeApplicationDetailTemp ( String idOperazione );

    void removeEnteTemp ( String idOperazione );

    // SELECT

    MdpTEnte findOneEnte ( String cfEnte );

    MdpTTemplate getGateway ();

    MdpTErrore findOneErroreByCodiceErrore ( String codiceErrore );

    List<MdpTTemplate> findAllTemplates ( String gateway, String progetto );

    List<MdpTEnteTemp> findEnteTempByIdOperazione ( String idOperazione );

    List<MdpTApplicationTemp> findAllApplicationTemp ( String idOperazione );

    List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomTemp ( String idOperazione );

    List<MdpTApplicationDetailTemp> findAllApplicationDetailTemp ( String idOperazione );

    MdpTApplication getApplication ( String idApplication );

    String encode ( String toEncode );

    void persistEnteTemp ( MdpTEnteTemp enteTemp);

    Integer getEnteMaxId ();

    void persistEnte ( MdpTEnte enteNew, boolean present );
    
    String richiediApplicationId(String codiceFiscaleEnte, String iban);

    String richiediApplicationIdTmp(String codiceFiscaleEnte, String iban);
    
    List<MdpTApplicationCustomFields> findAllApplicationCustomFieldsByAppidParziale ( String appIdParziale);
    
    List<MdpTApplicationCustomFields> findAllApplicationCustomFields ( String appIdEnteOld );

    List<ConfigDTO> findAllConfig () throws SOAPException;

    void persistRelation ( String ente_id, String appId );

    Boolean isPresentRelazEnteApp ( String ente_id, String appId );

    List<MdpTApplicationDetail> findAllApplicationDetail ( String appId_OLD );

    MdpTApplication findOneApplication ( String id );

    List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomFieldsTemp ( String idOperazione );

    List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomFieldsTempByAppId ( String appId);
    
    MdpTApplication findMaxApplication ( String string );

    void removeEnteTempByPiva ( String piva );
    
    //CSI_PAG-316
    String richiediApplicationIdNew(String codiceFiscaleEnte, String iban, String ibanAppoggio, String idOperazione);

}
