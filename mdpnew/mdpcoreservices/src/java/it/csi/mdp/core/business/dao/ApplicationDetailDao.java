/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.ApplicationDetailDao;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.ApplicationDetailPk;
import it.csi.mdp.core.business.exceptions.ApplicationDetailDaoException;
import java.util.List;

public interface ApplicationDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ApplicationDetail
	 */
	public ApplicationDetailPk insert(ApplicationDetail dto);

	/** 
	 * Updates a single row in the applicationdetail table.
	 */
	public void update(ApplicationDetailPk pk, ApplicationDetail dto) throws ApplicationDetailDaoException;

	/** 
	 * Deletes a single row in the applicationdetail table.
	 */
	public void delete(ApplicationDetailPk pk) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'applicationid = :applicationid AND gatewayid = :gatewayid AND paymentmodeid = :paymentmodeid'.
	 */
	public ApplicationDetail findByPrimaryKey(String applicationid, String gatewayid, String paymentmodeid, String idgroup) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria ''.
	 */
	public List<ApplicationDetail> findAll(String idgroup) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'applicationid = :applicationid'.
	 */
	public List<ApplicationDetail> findWhereApplicationidEquals(String applicationid, String groupid) throws ApplicationDetailDaoException;
	
	public List<ApplicationDetail> findWhereApplicationidEqualsAndNotNodo(String applicationid, String groupid) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'gatewayid = :gatewayid'.
	 */
	public List<ApplicationDetail> findWhereGatewayidEquals(String gatewayid) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'paymentmodeid = :paymentmodeid'.
	 */
	public List<ApplicationDetail> findWherePaymentmodeidEquals(String paymentmodeid) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'merchantid = :merchantid'.
	 */
	public List<ApplicationDetail> findWhereMerchantidEquals(String merchantid) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'merchantidpassword = :merchantidpassword'.
	 */
	public List<ApplicationDetail> findWhereMerchantidpasswordEquals(String merchantidpassword) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'pgactioncode = :pgactioncode'.
	 */
	public List<ApplicationDetail> findWherePgactioncodeEquals(long pgactioncode) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'flagreturlmdp = :flagreturlmdp'.
	 */
	public List<ApplicationDetail> findWhereFlagreturlmdpEquals(String flagreturlmdp) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'applicationurlresponseok = :applicationurlresponseok'.
	 */
	public List<ApplicationDetail> findWhereApplicationurlresponseokEquals(String applicationurlresponseok) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'applicationurlresponseko = :applicationurlresponseko'.
	 */
	public List<ApplicationDetail> findWhereApplicationurlresponsekoEquals(String applicationurlresponseko) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'tipocommissione = :tipocommissione'.
	 */
	public List<ApplicationDetail> findWhereTipocommissioneEquals(String tipocommissione) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'valorecommissionemin = :valorecommissionemin'.
	 */
	public List<ApplicationDetail> findWhereValorecommissioneminEquals(float valorecommissionemin) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'sogliada = :sogliada'.
	 */
	public List<ApplicationDetail> findWhereSogliadaEquals(float sogliada) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'sogliaa = :sogliaa'.
	 */
	public List<ApplicationDetail> findWhereSogliaaEquals(float sogliaa) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'valorecommissionemax = :valorecommissionemax'.
	 */
	public List<ApplicationDetail> findWhereValorecommissionemaxEquals(float valorecommissionemax) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'enabled = :enabled'.
	 */
	public List<ApplicationDetail> findWhereEnabledEquals(String enabled) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'pgcontabcode = :pgcontabcode'.
	 */
	public List<ApplicationDetail> findWherePgcontabcodeEquals(long pgcontabcode) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'applicationurlback = :applicationurlback'.
	 */
	public List<ApplicationDetail> findWhereApplicationurlbackEquals(String applicationurlback) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mac_avvio = :macAvvio'.
	 */
	public List<ApplicationDetail> findWhereMacAvvioEquals(String macAvvio) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mac_esito = :macEsito'.
	 */
	public List<ApplicationDetail> findWhereMacEsitoEquals(String macEsito) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'codiceistat = :codiceistat'.
	 */
	public List<ApplicationDetail> findWhereCodiceistatEquals(String codiceistat) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'tipobollettinoposte = :tipobollettinoposte'.
	 */
	public List<ApplicationDetail> findWhereTipobollettinoposteEquals(String tipobollettinoposte) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'contocorrenteposte = :contocorrenteposte'.
	 */
	public List<ApplicationDetail> findWhereContocorrenteposteEquals(String contocorrenteposte) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'tipodocumentoposte = :tipodocumentoposte'.
	 */
	public List<ApplicationDetail> findWhereTipodocumentoposteEquals(String tipodocumentoposte) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mail2buyerok = :mail2buyerok'.
	 */
	public List<ApplicationDetail> findWhereMail2buyerokEquals(String mail2buyerok) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mail2buyerko = :mail2buyerko'.
	 */
	public List<ApplicationDetail> findWhereMail2buyerkoEquals(String mail2buyerko) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mail2esercko = :mail2esercko'.
	 */
	public List<ApplicationDetail> findWhereMail2eserckoEquals(String mail2esercko) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mail2esercok = :mail2esercok'.
	 */
	public List<ApplicationDetail> findWhereMail2esercokEquals(String mail2esercok) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mail2sysko = :mail2sysko'.
	 */
	public List<ApplicationDetail> findWhereMail2syskoEquals(String mail2sysko) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'mail2sysok = :mail2sysok'.
	 */
	public List<ApplicationDetail> findWhereMail2sysokEquals(String mail2sysok) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'applicationurlcancel = :applicationurlcancel'.
	 */
	public List<ApplicationDetail> findWhereApplicationurlcancelEquals(String applicationurlcancel) throws ApplicationDetailDaoException;

	/** 
	 * Returns all rows from the applicationdetail table that match the criteria 'applicationurlerror = :applicationurlerror'.
	 */
	public List<ApplicationDetail> findWhereApplicationurlerrorEquals(String applicationurlerror) throws ApplicationDetailDaoException;

	/** 
	 * Returns the rows from the applicationdetail table that matches the specified primary-key value.
	 */
	public ApplicationDetail findByPrimaryKey(ApplicationDetailPk pk, String idgroup) throws ApplicationDetailDaoException;
	
	public ApplicationDetail findGatewayForNodo(String applicationid) throws ApplicationDetailDaoException;

	
	public void setsKey(String sKey);
	public String getsKey();
}
