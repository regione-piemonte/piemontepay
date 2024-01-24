# Prodotto
PiemontePay - Intermediazione dei Pagamenti Elettronici con pagoPA

# Descrizione
PiemontePay è una piattaforma progettata per la gestione dei processi di riscossione, di pagamento di tributi e/o servizi per i quali l’ente risulta creditore o erogatore e fornisce servizi di supporto alla riconciliazione contabile automatizzata in linea con le specifiche pagoPA. La piattaforma si integra con sistemi esterni qualora l'Ente Creditore abbia gestionali dedicati su specifiche tipologie di pagamento.
E' rivolta ai cittadini e alle imprese che devono eseguire pagamenti verso la PA e al personale della PA che deve predisporre e monitorare i processi di riscossione e riconciliazione contabile.

Di seguito le principali funzionalità:

Sportello dei Pagamenti PiemontePay (rivolto a cittadini e imprese):
-  eseguire un pagamento spontaneo verso un Ente Creditore
-  eseguire il pagamento associato ad un avviso di pagamento inviato dall'Ente Creditore.
-  verificare l'esito di un pagamento 
-  eseguire il download della quietanza di pagamento
-  eseguire la stampa di un avviso di pagamento per eseguire il pagamento presso un Prestatore di Servizi di Pagamento aderente a pagoPA.

Portale Enti Creditori:
- upload di una lista di posizioni debitorie per il loro caricamento sullo Sportello dei Pagamenti PiemontePay
- generazione e stampa dell'avviso di pagamento associato ad una posizione debitoria
- download degli IUV associati alle posizioni debitorie caricate a sistema
- download delle liste dei pagamenti eseguiti 
- ricerca e visualizzazione dei pagamenti eseguiti
- upload della lista dei provvisori di entrata
- creazione e manutenzione del catalogo delle entrate contabili
- elaborazione dei flussi di rendicontazione e delle Ricevute Telematiche pagoPA ai fini della riconciliazione contabile

PiemontePay è costituito da due macro componenti, EPAY e MDPNEW.
EPAY implementa le funzionalità della piattaforma PiemontePay sopra descritte e coopera con MDPNEW che implementa l'integrazione con il Nodo dei pagamenti pagoPA.

Le principali funzionalità di MDPNEW sono le seguenti:
- interfaccia unica e normalizzata ai servizi di pagamento elettronico
- servizi di carrello elettronico generalizzato
- servizi di preparazione ed esecuzione di un pagamento elettronico
- servizi di recupero dell'esito di un pagamento anche in un momento successivo rispetto alla sua esecuzione
- web service per la generazione di Identificativi Univoci di Versamento (IUV)  nel formato ISO 11649:2009 
- web service per la generazione massiva di Identificativi Univoci di Versamento (IUV) e codici avviso.
- implementazione dell'integrazione con i servizi del Nodo dei Pagamenti SPC pagoPA

<b>Componenti della macro componente EPAY<b>

| Componente | Descrizione | Tecnologia |
| ---------- | ----------- | ---------- |
| [epayapi]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epayapi ) | Componente a servizi REST per la gestione delle posizioni debitorie da parte di un gestionale esterno su voci di pagamento configurate dall'Ente Creditore | JDK ver. 1.8 |
| [epaybeapi]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaybeapi ) | Componente a servizi REST di backend | JDK ver. 1.8 |
| [epayfeapi]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epayfeapi ) | Componente a servizi REST per la personalizzazione di un frontend rivolto al cittadino | openjdk LTS java11 |
| [epayjob]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epayjob ) | Componente job batch schedulata per le funzionalità off line del sistema| JDK ver. 1.8 |
| [epaymdpservices]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaymdpservices ) | Componente di backend che espone servizi SOAP in ascolto verso la componente MDPNEW | JDK ver. 1.8 |
| [epaymodric]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaymodric ) | Componente di backend per la gestione dei flussi di rendicontazione  | JDK ver. 1.8 |
| [epaymodricweb]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaymodricweb ) | Componente di frontend web per la gestione delle elaborazioni dei flussi di rendicontazione | JDK ver. 1.8 |
| [epaypacatalogsrv]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaypacatalogsrv ) | Componente di backend per la gestione del catalogo entrate degli Enti Creditori | JDK ver. 1.8|
| [epaypacatalogweb]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaypacatalogweb ) | Componente di frontend web per la gestione del catalogo entrate degli Enti Creditori | JDK ver. 1.8|
| [epaypaweb]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaypaweb ) | Componente di frontend web - Portale Enti Creditori | JDK ver. 1.8|
| [epayservices]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epayservices ) | Componente di backend a servizi per gestione Sportello dei Pagamenti | JDK ver. 1.8|
| [epaysim]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaysim ) | Componente di backend a servizi per il caricamento dei provvisori di entrata | JDK ver. 1.8|
| [epaysimweb]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaysimweb ) | Componente web per il caricamento manuale della lista dei provvisori di entrata| JDK ver. 1.8|
| [epayweb]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epayweb ) | Componente web Sportello dei Pagamenti PiemontePay | JDK ver. 1.8|
| [epaywso2services]( https://github.com/regione-piemonte/piemontepay/tree/main/epay/epaywso2services ) | Componente servizi di business ed integrazione per WSO2 | JDK ver. 1.8|

<b>Componenti della macro componente MDPNEW<b>

| Componente | Descrizione | Tecnologia |
| ---------- | ----------- | ---------- |
| [mdpboservices]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpboservices ) | Componente servizi di back office | JDK ver. 1.5 |
| [mdpboweb]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpboweb ) | Componente web di back office | JDK ver. 1.5 |
| [mdpcoopapplicativa]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpcoopapplicativa ) | Componente a servizi per generazione configurazione applicazioni (integrata con componente Catalogo delle Entrate di PiemontePay) | JDK ver. 1.5 |
| [mdpcoreservices]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mmdpcoreservices ) | Componente servizi core di pagamento | JDK ver. 1.5 |
| [mdpcoreservices2]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpcoreservices2 ) | Componente a servizi core di pagamento di supporto al modello 3 di pagamento pagoPA | JDK ver. 1.8 |
| [mdpetl]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpetl ) | Componente job batch per acquisizione dati del Nodo dei Pagamenti SPC pagoPA | JDK ver. 1.6 |
| [mdpintnodospc2]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpintnodospc2 ) | Componente adattatore per integrazione con Nodo dei Pagamenti SPC pagoPA | JDK ver. 1.5 |
| [mdpiuvsrv]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpiuvsrv ) | Componente web Service soap per generazione Identificativi Univoci Versamento (IUV) formato ISO 11649:2009 | JDK ver. 1.5 |
| [mdpmultiiuvsrv]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpmultiiuvsrv ) | Componente web Service soap per generazione massiva IUV e codici avviso| JDK ver. 1.5 |
| [mdpnodospcservices2]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdpnodospcservices2 ) | Componente servizi SOAP esposto verso il Nodo dei Pagamenti SPC pagoPA | JDK ver. 1.8 |
| [mdppagopaapi]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdppagopaapi ) | Componente a servizi SOAP che implementa la nuova interfaccia pagoPA per Nuovo Modello 3 pagamento multibeneficiario | JDK ver. 1.8 |
| [mdppagopacheckout]( https://github.com/regione-piemonte/piemontepay/tree/main/mdpnew/mdppagopacheckout ) | Componente servizi REST per implementazione nuovo modello unico pagoPA | openjdk LTS java11 |


# Configurazioni iniziali
Per allestire un development environment occorre installare i system software elencati nei prerequisiti di sistema e
configurare opportunamente i file di properties denominati dev-rp-01.propreties presenti sotto la cartella buildfiles delle componenti.\
Le librerie necessarie alla compilazione sono elencate nei file SBOM delle singole componenti e sono pubblicate su http://repart.csi.it
da dove il processo di compilazione le scarica automaticamente.\
Per avere una migliore affidabilità e scalabilità complessiva del sistema configurare istanze/partizioni dedicate per le componenti di front end e dei servizi. 

# Getting Started
Per compilare le componenti che usano la `openjdk LTS java11` usare il tool `maven` con il seguente comando:

`mvn clean package assembly:assembly -DskipTests`

L'esecuzione crea nella cartella 'target' del workspace un file .tar contenente il pacchetto da installare.

Per le componenti che usano la `jdk 1.8, 1.6 e 1.5` usare il tool `ant` con il seguente comando:
 
`ant -Dtarget=dev-rp-01 -lib apache-ivy-2.0.0`

L'esecuzione crea nella cartella 'dist/dev-rp-01' del workspace un file .tar contenente il pacchetto da installare.

# Prerequisiti di sistema
- Maven ver. 3.8.1
- Ant ver. 1.8.4
- Apache-ivy-2.0.0
- Java Development Kit (jdk) ver. 1.5, 1.6, 1.8
- Openjdk LTS java ver.11
- Quarkus 2.10.1.Final, 2.15.0.Final
- Spring 3.1.1
- Web 2.0 - Struts 2.3.35, JQuery 2.2.4, Boostrap 2.2.2
- WSO2 ver. 4.9.0, 6.1.1
- Apache Web Server ver. 2.4
- Application Server JBoss 4.3 EAP CP09, JBoss 6.4 EAP CP07
- PostgreSQL ver. 12, ver. 9.6`

# Installazione
I pacchetti generati dalla compilazione delle componenti che usano la `jdk 1.8 e la openjdk LTS java11` devono essere deployati su `JBoss 6.4` mentre i pacchetti che usano le `jdk 1.5 e 1.6` devono essere deployati su `JBoss 4.3`, in entrambi i casi copiare i pacchetti nella directory standalone/deployments degli application server per avviare il deploy.

# Versioning
Per il versionamento del software si usa la tecnica [Semantic Versioning](https://semver.org/).

# Copyrights
© Copyright Regione Piemonte 2024

# License
SPDX-License-Identifier: EUPL-1.2\
Vedere il file [EUPL-v1_2-IT-LICENSE.txt](./EUPL-v1_2-IT-LICENSE.txt) per i dettagli.


