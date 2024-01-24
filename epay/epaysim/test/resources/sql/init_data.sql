--INSERT INTO epaysim_t_provvisorio
--(id, codice_fiscale_ente, identificativo_flusso, causale, data_movimento, anno_esercizio, anno_provvisorio, numero_provvisorio, importo_provvisorio, importo_disponibilita, stato, descrizione, tipo_movimento, data_fine)
--VALUES(999, 'CF113VALIDO', '2018-05-23PASCITMM-0E0JHIC0WV60G', '/PUR/LGPE-RIVERSAMENTO/URI/2018-05-23PASCITMM-0E0JHIC0WV60G', '2010-05-16 15:36:38.000', 2018, 2018, 113, 1064.00, 1064.00, 'VALIDO', 'Provvisorio per incasso n.113/2018', 'SOSPESO DI ENTRATA', NULL);
INSERT INTO sim_t_giornale_di_cassa
(id,data_inserimento,xml)
VALUES(-1,TIMESTAMP '2018-09-03 17:52:22.000','esempio per la creazione della costrain');

INSERT INTO sim_t_flusso_origine_pagopa
(
  id,
  identificativo_flusso,
  identificativo_psp,
  numero_totale_pagamenti,
  importo_totale_pagamenti,
  iban_riversamento_flusso,
  dataora_flusso,
  data_inserimento,
  xml_flusso,
  contatore_tentativi,
  id_elaborazione,
  id_stato_flusso,
  importo_totale_pagamenti_intermediati,
  numero_totale_pagamenti_intermediati,
  id_messaggio,
  data_regolamento,
  identificativo_univoco_regolamento,
  stato_elaborazione_flusso
)
VALUES
(
  50,
  'FLUSSO_TEST',
  NULL,
  10,
  547.00,
  NULL,
  TIMESTAMP '2018-09-10 17:34:04.597',
  TIMESTAMP '2018-09-10 17:47:56.667',
  '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><ns2:TrasmettiFlussiPagoPARequest xmlns="http://www.csi.it/epay/epaywso/types" xmlns:ns2="http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types"><ns2:TestataFlusso><ns2:IdMessaggio>2018-08-31-15:14:47.275-00000001003</ns2:IdMessaggio><ns2:DataOraMessaggio>2018-09-10T17:34:04.597+02:00</ns2:DataOraMessaggio><ns2:CFEnteCreditore>00429440068</ns2:CFEnteCreditore><ns2:IdentificativoUnivocoRegolamento>TODO</ns2:IdentificativoUnivocoRegolamento><ns2:DataRegolamento>2018-09-10+02:00</ns2:DataRegolamento><ns2:NumeroTotalePagamentiFlusso>10</ns2:NumeroTotalePagamentiFlusso><ns2:NumeroTotalePagamentiIntermediati>14</ns2:NumeroTotalePagamentiIntermediati><ns2:ImportoTotalePagamentiFlusso>547</ns2:ImportoTotalePagamentiFlusso><ns2:ImportoTotalePagamentiIntermediati>154</ns2:ImportoTotalePagamentiIntermediati><ns2:ProvvisorioAnno>0</ns2:ProvvisorioAnno><ns2:ProvvisorioNumero>0</ns2:ProvvisorioNumero></ns2:TestataFlusso><ns2:RigheSintesi><ns2:SingolaRigaSintesi><ns2:ImportoQuotaAggregazione>10</ns2:ImportoQuotaAggregazione><ns2:NroPagamentiAggregazione>1</ns2:NroPagamentiAggregazione><ns2:ProgressivoFlussoSintetico xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/><ns2:AccertamentoAnno xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/><ns2:AccertamentoNro xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/><ns2:RigheDettaglio><ns2:SingolaRigaDettaglio><ns2:AnagraficaPagatore><PersonaFisica><Cognome>Lollo</Cognome><Nome>Lollino</Nome></PersonaFisica><IdentificativoUnivocoFiscale>LOLLOLOLLINICF</IdentificativoUnivocoFiscale><Indirizzo>Via dei lolli 1</Indirizzo><CAP>10100</CAP><Localita>Lollonia</Localita><Provincia>LO</Provincia><Nazione>Lollosa</Nazione><EMail>lollo@mail.it</EMail></ns2:AnagraficaPagatore><ns2:Causale>Riscatto lollo</ns2:Causale><ns2:DataPagamento>2018-09-10T17:34:04.597+02:00</ns2:DataPagamento><ns2:ImportoSingoloVersamento>10</ns2:ImportoSingoloVersamento></ns2:SingolaRigaDettaglio><ns2:SingolaRigaDettaglio><ns2:AnagraficaPagatore><PersonaFisica><Cognome>Lollo</Cognome><Nome>Lollino</Nome></PersonaFisica><PersonaGiuridica><RagioneSociale>GLOLLO SPA</RagioneSociale></PersonaGiuridica><IdentificativoUnivocoFiscale>GLOLLOLOLLINICF</IdentificativoUnivocoFiscale><Indirizzo>Via dei Glolli 1</Indirizzo><CAP>121212</CAP><Localita>GLollonia</Localita><Provincia>GLO</Provincia><Nazione>GLollosa</Nazione><EMail>Glollo@mail.it</EMail></ns2:AnagraficaPagatore><ns2:Causale>Riscatto Glollo</ns2:Causale><ns2:DataPagamento>2018-09-10T17:34:04.597+02:00</ns2:DataPagamento><ns2:ImportoSingoloVersamento>10</ns2:ImportoSingoloVersamento></ns2:SingolaRigaDettaglio></ns2:RigheDettaglio></ns2:SingolaRigaSintesi></ns2:RigheSintesi></ns2:TrasmettiFlussiPagoPARequest>',
  NULL,
  NULL,
  NULL,
  154.00,
  14,
  '2018-08-31-15:14:47.275-00000001003',
  DATE '2018-09-10',
  'TODO',
  NULL
);




