# note

Log di bug-hunting-search&destroy per far funzionare i servizi soap

1. partito dal pom dell'altro progetto (```myprodsrvqrk```)
2. rename project name & package (```pom.xml```, script vari, ```application.properties```)
3. commentato plugin swagger generator nel pom
4. copiati file del client soap, tutta la cartella ```/iride2pep```

   -[X] **FINO A QUI BUILDA**
   -[ ] ```clean package assembly:assembly -Dskip-unit-test=true```

5. copiato file inutile ```gen_swagger.bat```
6. copiata intera cartella di test

   -[X] **FINO A QUI BUILDA**
   -[X] ```clean package -Dskip-unit-test=true```

7. aggiunto wsdl ```epayfeapi\src\main\cxf-codegen-resources\wsdl\MdpiuvsrvServiceWS.wsdl```
8. aggiunto classe controller vuota
9. aggiunta classe service con annotation ```@CXFClient``` injectata
10. aggiunte classi client soap generate altrove

    -[ ] NON COMPILA PIU'

    Errori dovuti al fatto che si è usato CXF 4.0.0 e non CXF 3.3.2 ?

    **SOLUZIONE**

11. creato progetto a parte ```soapclient``` (vedesi ```docs/soap/soapclientszip```)
12. impostare wsdl e generato le classi
13. copiate le classi dentro il nostro progetto
14. rimosse le classi del client soap myprod

    -[X] **FINO A QUI BUILDA**
    -[X] ```clean package -Dskip-unit-test=true```

15. rimossa cartella test
16. semplificazione ed eliminazione package ```mdpiuv```

    -[X] **FINO A QUI BUILDA**
    -[X] ```clean package -Dskip-unit-test=true```

17. aggiunto sul pom generazione classi dal nostro yaml di cittafacile
18. aggiunto yaml

    -[X] **FINO A QUI BUILDA**
    -[X] ```clean package -Dskip-unit-test=true```

19. rinominato test controller
20. introdotto controller end point principale dal branch develop che estende le classi generate dallo yaml
21. aggiunto test chiamata "vera" controller
22. aggiunto test http ed env.json
23. cambiato quarkus.http.root-path a quello di prod
24. profilato utente "reale" chiamate rest nell'application.properties
25. rimossa cartella gen
26. rimosso file gen_swagger.bat
27. aggiornato .gitignore per i log files
28. rimosso run-java.sh

    -[X] **FINO A QUI BUILDA**
    -[X] ```clean package -Dskip-unit-test=true```
    -[X] **RISPOSTE VUOTE DALLO YAML OK** 

29. aggiunto ```tar_config.xml```
30. aggiunto assembly plugin nel pom
31. aggiunto maven-resources-plugin nel pom

    -[X] **BUILDA**
    -[X] **RISPOSTE VUOTE DALLO YAML OK**
    -[X] ```clean package assembly:assembly -Dskip-unit-test=true```

32. refactoring path package clients
