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
  
<dateDa>2013-01-01T00:00:00</dateDa>
 
// servizzi esposti
https://tst-secure.ruparpiemonte.it/mdpboservices/MdpBoServicesCxf/MdpBoServicesCxf?wsdl

// deploy in test 
tst-jboss43cp09-01.csi.it
user/pwd: austa/austa

jbossctl part254 0 start
jbossctl part254 0 stop
jbossctl part254 0 clean
jbossctl kill
tail -f log/STDOUT_part254node01.log
view log/STDOUT_part254node01.log

l'ear di test si trova in 
/usr/appserv/jboss/ajb43_09/jboss-eap-4.3/jboss-as/server/part254node01/farm


// servizzi esposti
https://tst-secure.ruparpiemonte.it/mdpboservices/MdpBoServicesCxf/MdpBoServicesCxf?wsdl


