/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business.dto.decoder;

import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoPagamentoAgID;

public class EsitoPagamentoAgIDDecoder {
	
	public boolean isPagamentoEseguito(Integer esito){
		
		boolean result = false;		
		if(esito.equals(EsitoPagamentoAgID.PAGAMENTO_ESEGUITO.getEsitoPagamento().intValue())){
			return true;
		}		
		return result;
	}
	
	public boolean isPagamentoNonEseguito(Integer esito){
		
		boolean result = false;		
		if(esito.equals(EsitoPagamentoAgID.PAGAMENTO_NON_ESEGUITO.getEsitoPagamento().intValue())){
			return true;
		}		
		return result;
	}	
	
	public boolean isPagamentoParzEseguito(Integer esito){
		
		boolean result = false;		
		if(esito.equals(EsitoPagamentoAgID.PAGAMENTO_PARZIALMENTE_ESEGUITO.getEsitoPagamento().intValue())){
			return true;
		}		
		return result;
	}	
	
	public boolean isDecorrenzaTermini(Integer esito){
		
		boolean result = false;		
		if(esito.equals(EsitoPagamentoAgID.DECORRENZA_TERMINI.getEsitoPagamento().intValue())){
			return true;
		}		
		return result;
	}	
	
	public boolean isDecorrenzaTerminiParziale(Integer esito){
		
		boolean result = false;		
		if(esito.equals(EsitoPagamentoAgID.DECORRENZA_TERMINI_PARZIALE.getEsitoPagamento().intValue())){
			return true;
		}		
		return result;
	}	

}
