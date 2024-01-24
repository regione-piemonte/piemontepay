Percorso JAVA_HOME TEST tst-sked1.bilancio.csi.it:
/opt/jdk1.8.0_71

Percorso installazione job java epayjob:
/skedul/java/epay-rp-01

Percorso log:

/skedul/java/epay-rp-01/log/epayjob.log


*/10 8-22 * * * /skedul/procsked/procscript/epayjob.sh >> /skedul/progetti/epay-rp-01/dati/log/epayjob.sh_`date +\%Y\%m\%d`.log
