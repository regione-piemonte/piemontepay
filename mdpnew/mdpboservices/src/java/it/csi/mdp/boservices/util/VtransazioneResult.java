/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.util;
import java.util.List;

import it.csi.mdp.core.business.dto.*;

public class VtransazioneResult
{
	private int totTransactionsFound = 0;
	private int totTransactions = 0;
	private List<Vtransazione> listTransazioni = null;
	public int getTotTransactionsFound()
	{
		return totTransactionsFound;
	}
	public void setTotTransactionsFound(int totTransactionsFound)
	{
		this.totTransactionsFound = totTransactionsFound;
	}
	public List<Vtransazione> getListTransazioni()
	{
		return listTransazioni;
	}
	public void setListTransazioni(List<Vtransazione> listTransazioni)
	{
		this.listTransazioni = listTransazioni;
	}
	public int getTotTransactions()
	{
		return totTransactions;
	}
	public void setTotTransactions(int totTransactions)
	{
		this.totTransactions = totTransactions;
	}
	
}
