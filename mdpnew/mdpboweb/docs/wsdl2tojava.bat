d:
rem set AXIS_LIB=C:\axis-1_4\lib
set AXIS_LIB=D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib
rem set JAVA_HOME=D:\bea\jdk150_22\bin

set CLASSPATH=D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\mail-1.2.jar;D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\activation-1.0.2.jar;D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\axis-1.4.jar;D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\jaxrpc-1.1.jar;D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\saaj-1.2.jar;D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\commons-discovery-0.2.jar;D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\commons-logging-1.1.jar;D:\myworkspace\MDPNEW-WRK\MDPBOWEB\lib\wsdl4j-1.6.2.jar


rem C:\axis-1_4\lib\axis.jar;
rem C:\axis-1_4\lib\jaxrpc.jar;
rem C:\axis-1_4\lib\saaj.jar;
rem C:\axis-1_4\lib\commons-discovery-0.2.jar;
rem C:\axis-1_4\lib\commons-logging-1.0.4.jar;
rem C:\axis-1_4\lib\wsdl4j-1.5.1.jar




cd D:\myworkspace

pause

rem D:\Programmi\java\jdk1.5.0_12\bin\java org.apache.axis.wsdl.WSDL2Java   -W -p  it.csi.mdp.mdpboweb.business.ws  https://tst-secure.ruparpiemonte.it/mdpboservices/MdpBoServicesCxf/MdpBoServicesCxf?wsdl

rem C:\Program Files\Java\jdk1.5.0_12\bin\java org.apache.axis.wsdl.WSDL2Java   -W -p  it.csi.mdp.mdpboweb.business.ws.core       d:/MdpCoreCxf.wsdl

D:\Programmi\java\jdk1.5.0_12\bin\java org.apache.axis.wsdl.WSDL2Java   -W -p  it.csi.mdp.mdpboweb.business.ws       d:/MdpBoServicesCxf.wsdl

rem C:\Program Files\Java\jdk1.5.0_12\bin\java org.apache.axis.wsdl.WSDL2Java   -W -p  it.csi.mdp.mdpboweb.business.ws.core  http://tst-applogic.reteunitaria.piemonte.it/mdpcoreservices/MdpCoreCxf/MdpCoreCxf?wsdl


rem -t = genera una classe testcase junit per il servizio
rem -W = indica noWrapped

pause