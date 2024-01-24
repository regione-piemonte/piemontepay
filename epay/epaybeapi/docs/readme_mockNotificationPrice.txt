mockNotificationPrice e' un servizio interno, serve a generare 

Il servizio è richiamabile all'indirizzo :

http://{server:port}/epaybeapi/api/mockNotificationPrice
Esempio per TU-EXP
http://tu-exp-srv-pay.bilancio.csi.it/epaybeapi/api/mockNotificationPrice
Le credenziali criptate da inseire direttamente su db sono:0wzs29ZXjRh/j7yaAMwU0rF128krFZzkm0HjT0nUBhI=
 

metodo: get

autenticazione: basic

epayabepi_34/trS8L6Y4

Restituisce un oggetto NotificationPriceOutput, che contiene la proprietà importo, 
che viene valorizzato  con un bigdecimal scale 2, generato  random, compreso tra 1 e 100.

 


