/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.ejb.mock.epayservices;

import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.exception.NotificationPriceServiceException;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.NotificationPriceOutput;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaOutput;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoLight;
import it.csi.epay.epayservices.model.TransazioneMdp;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PagamentoFacadeMockImpl implements it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade {

	@Override
	public Pagamento ricerca(Long id) throws NoDataException, IllegalArgumentException {
		Pagamento pagamento = new Pagamento();
		pagamento.setIdPagamento(id);
		pagamento.setImporto(new BigDecimal(10));
		pagamento.setIuv("RF3816274000300TF00000006");
		pagamento.setPagamentoSpontaneo(Boolean.FALSE);
		pagamento.setFlagPagamentoAutenticato(Boolean.FALSE);
		return pagamento;
	}

	@Override
	public Pagamento ricerca(String iuv) throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagamento ricercaSoloAttivi(String iuv) throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroVersamenti ricercaUltimaTransazioneByIUV(String iuv)
					throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagamento> ricercaPagamentiAttiviPerCF(String codiceFiscale) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagamento> ricercaPagamentiAttiviNonSpontaneiPerCF(String codiceFiscale)
					throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagamento> ricercaPagamentiAllPerCF(String codiceFiscale) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long inserisci(Pagamento pagamento) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiornaNote(Long idPagamento, String note) throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaConsensoPrivacy(Long idPagamento, boolean consensoPrivacy)
					throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaCodiceAvviso(Long idPagamento, CodiceAvviso codiceAvviso)
					throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaPagatore(Long idPagamento, Anagrafica anagrafica)
					throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public TipoPagamento ricercaTipoPagamento(Long id) throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoPagamentoLight> elencoTipoPagamentoPerEnte(Ente ente) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransazioneMdp ricercaTransazioneMdp(String idTransazione) throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserisciTransazioneMdp(TransazioneMdp transazione) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaTransazioneMdp(TransazioneMdp transazione) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public RegistroVersamenti ricercaUltimaRegistrazioneVersamento(Long idPagamento, StatoPagamento stato)
					throws NoDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rt ricercaRt(Long idPagamento, StatoPagamento stato) throws NoDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long inserisciRegistroVersamentiEGestioneStato(RegistroVersamenti risultatoPagamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generaAvvisoPagamento(Pagamento pagamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagamento> elencoPagamentiEffettuatiPerCFeUltimi10(String codiceFiscale)
					throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagamento> elencoPagamentiEffettuatiPerCFeIntervallo(String codiceFiscale, Date dataDa, Date dataA)
					throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rt ricercaRtByIuv ( String arg0 ) throws NoDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagamento ricercaOttimizzata ( String arg0 ) throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagamento ricercaPagamentoByIuvAndIdentificativoDominio ( String iuvNumeroAvviso, String identificativoDominio ) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagamentoSecondario ricercaPagamentoSecondarioByIdPagamentoPrincipale ( Long idPagamentoPrincipale )
					throws IllegalArgumentException, MoreResultException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rt ricercaRtPerPagamentoEPagamentoSecondario ( Long idPagamento, Long idPagamentoSecondario ) throws NoDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rt ricercaRtPerPagamentoPrimario ( Long idPagamento ) throws NoDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroVersamenti ricercaUltimoByIdPagamentoAndIdPagamentoSecondario ( Long idPagamento, Long idPagamentoSecondario )
					throws IllegalArgumentException, NoDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificaOrigineChiamata ( Long idPagamento, String idTransazione, Integer idOrigineChiamata ) throws IllegalArgumentException {
		return false;
	}

	@Override
    public EsitiRicevuti ricercaEsitiRicevutiByIUV ( String iuv ) throws IllegalArgumentException, NoDataException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput arg0 ) throws TassonomiaServiceException {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public StatoPosizioneDebitoriaOutput getStatoPosizioneDebitoria ( StatoPosizioneDebitoriaInput arg0 ) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public void attualizzaImporto(Long arg0, BigDecimal arg1, Boolean arg2)
			throws NoDataException, IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NotificationPriceOutput chiamaNotificationPrice(String arg0, String arg1, String arg2, byte [] arg3)
			throws NotificationPriceServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoPagamentoLight> elencoTipoPagamentoVisibiliPerEnte(Ente arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
