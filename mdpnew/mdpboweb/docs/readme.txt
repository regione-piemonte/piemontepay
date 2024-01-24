PER GENERARE IL PROGETTO 
sul progetto generatore (mdpboweb.mdd) in cartella workflow selezionare mdpboweb.oaw tasto destro 
e cliccare oAV.Workflow



IMPORTANTE NON CANCELLARE i 2 file nella cartella BIN
beans.xml
defws_mdpbo.properties

mentre si devono cancellare le cartelle 
it  e  mdpboweb che si trovano sempre nella cartella bin  

Il generato ha il file ivy da toccare ricoprirlo con quello salvato in locale



// accessto al BO di test
https://tst-secure.ruparpiemonte.it/mdpboweb
CSI.demo 23
PIEMONTE

// deploy in test 
tst-jboss43cp09-01.csi.it
user/pwd: austa/austa
e in (nodo 2)
tst-jboss43cp09-02.csi.it
user/pwd: austa/austa


jbossctl part254 0 clean
jbossctl part254 0 start
jbossctl part254 0 stop
jbossctl kill, 
tail -f log/STDOUT_part254node01.log
view log/STDOUT_part254node01.log

l'ear di test si trova in 
/usr/appserv/jboss/ajb43_09/jboss-eap-4.3/jboss-as/server/part254node01/farm
/usr/appserv/jboss/ajb43_09/jboss-eap-4.3/jboss-as/server/part254node02/farm

// servizzi esposti
https://tst-secure.ruparpiemonte.it/mdpboservices/MdpBoServicesCxf/MdpBoServicesCxf?wsdl



jbossctl part254 0 start
jbossctl part254 0 stop
tail -f log/STDOUT_part254node02.log

Per la generazione del client utuilizzare il wsdl2tojava.bat

// servizzi esposti
https://tst-secure.ruparpiemonte.it/mdpboservices/MdpBoServicesCxf/MdpBoServicesCxf?wsdl


se fai i test con soapUi 
usa questa pezzo di xml per l'autenticazione

<auth>
    <!--Optional:-->
    <codfisc>AAAAAA00A11D000L</codfisc>
    <group>6</group>
    <!--Optional:-->
    <pwdAuth>mypass</pwdAuth>
    <role>0</role>
    <userAuth>0</userAuth>
</auth>