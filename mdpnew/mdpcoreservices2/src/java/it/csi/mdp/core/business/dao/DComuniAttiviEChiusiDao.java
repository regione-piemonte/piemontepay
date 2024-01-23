/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.DComuniAttiviEChiusiDao;
import it.csi.mdp.core.business.dto.DComuniAttiviEChiusi;
import it.csi.mdp.core.business.exceptions.DComuniAttiviEChiusiDaoException;
import java.util.Date;
import java.util.List;

public interface DComuniAttiviEChiusiDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(DComuniAttiviEChiusi dto);

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria ''.
	 */
	public List<DComuniAttiviEChiusi> findAll() throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ID_COMUNE = :idComune'.
	 */
	public List<DComuniAttiviEChiusi> findWhereIdComuneEquals(long idComune) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'COD_CATASTO = :codCatasto'.
	 */
	public List<DComuniAttiviEChiusi> findWhereCodCatastoEquals(String codCatasto) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_COMUNE = :istatComune'.
	 */
	public List<DComuniAttiviEChiusi> findWhereIstatComuneEquals(String istatComune) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DESC_COMUNE = :descComune'.
	 */
	public List<DComuniAttiviEChiusi> findWhereDescComuneEquals(String descComune) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'CAP = :cap'.
	 */
	public List<DComuniAttiviEChiusi> findWhereCapEquals(String cap) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ALTITUDINE = :altitudine'.
	 */
	public List<DComuniAttiviEChiusi> findWhereAltitudineEquals(long altitudine) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'SUPERFICIE_HM2 = :superficieHm2'.
	 */
	public List<DComuniAttiviEChiusi> findWhereSuperficieHm2Equals(long superficieHm2) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_PROVINCIA = :istatProvincia'.
	 */
	public List<DComuniAttiviEChiusi> findWhereIstatProvinciaEquals(String istatProvincia) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_ZONA_ALTIMETRICA = :istatZonaAltimetrica'.
	 */
	public List<DComuniAttiviEChiusi> findWhereIstatZonaAltimetricaEquals(String istatZonaAltimetrica) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DESC_ZONA_ALTIMETRICA = :descZonaAltimetrica'.
	 */
	public List<DComuniAttiviEChiusi> findWhereDescZonaAltimetricaEquals(String descZonaAltimetrica) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_REGIONE = :istatRegione'.
	 */
	public List<DComuniAttiviEChiusi> findWhereIstatRegioneEquals(String istatRegione) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DATA_FINE_VALIDITA = :dataFineValidita'.
	 */
	public List<DComuniAttiviEChiusi> findWhereDataFineValiditaEquals(Date dataFineValidita) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ATTIVA = :attiva'.
	 */
	public List<DComuniAttiviEChiusi> findWhereAttivaEquals(String attiva) throws DComuniAttiviEChiusiDaoException;

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DATA_UPD = :dataUpd'.
	 */
	public List<DComuniAttiviEChiusi> findWhereDataUpdEquals(Date dataUpd) throws DComuniAttiviEChiusiDaoException;

}
