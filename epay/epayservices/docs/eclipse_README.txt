Dopo aver scaricato i sorgenti dal repository di subversion:
   - eseguire il target load-local-dependencies di build.xml e fare il refresh del progetto
   - in Project Properties -> Java Build Path -> Libraries aggiungere 
     tutti i jar delle sottocartelle di target/lib alle librerie del progetto
   - in Project Properties -> Java Build Path -> Source, impostare come sorgenti le seguenti cartelle:
       * src/main/java
       * src/main/resources
       * src/test/java
       * src/test/resources
       
