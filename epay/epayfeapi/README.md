# epayfeapi

Progetto di API rest per cittafacile sviluppato con il framework Quarkus

## Infos

+ contract first: edit the yaml, never the generated classes!
+ openjdk LTS java11
+ indents: tabs
+ use eng.formatter ```docs/formatter_format_eclipse.xml```!
+ ~~use the ```changelog.txt```!~~ no1 use it :( deleted

## Localhost Setup

1. git clone this repo
2. add this if missing to maven setting file (```settings.xml```):
   ```xml
      <mirrors>
          <mirror>
              <id>csi-central</id>
              <name>CSI Repart</name>
              <url>http://repart.csi.it/maven2</url>
              <mirrorOf>*</mirrorOf>
          </mirror>
      </mirrors>
   ```
3. run ```mvn clean compile install```
4. run the application in debug mode ```./mvnw quarkus:dev```

## How to create a new endpoints/dto

1. edit the [yaml file](src/main/resources/epayfeapi_v1.yaml) inside ```resources``` folder
2. run ```mvn clean compile```
3. **never edit generated classes!**

## Usefull commands

+ ~~./mvnw quarkus:list-endpoints~~

## Eclipse

+ Enable debug from maven: ```compile quarkus:dev -Ddebug```

## Maven

+ if error on java memory heap: add this to VM Options: ```-Xms512m -Xmx1024m``` (or tuning it)
+ if stucks on importing: VM options for importer: ```-Xms1024m -Xmx2048m```
  or if you are using intellij, delete under ```.idea``` folder all repos but repart

## Health's checks

To check that the smallrye-health extension is working as expected:

1. run the app ```./mvnw quarkus:dev```
2. access the http://localhost:8080/q/health/live endpoint using your browser or curl http://localhost:8080/q/health/live

All **health** REST endpoints return a simple JSON object with two fields:

+ **status** &rarr; the overall result of all the health check procedures
+ **checks** &rarr; an array of individual checks

The general **status** of the health check is computed as a logical AND of all the declared health check procedures.

## Soap clients

When needed to call a soap server with @CXFClient, use the project under ```docs/soap``` to generate it from wsdl; or clone and use
this: https://gitlab.csi.it/73863/soapclient

Then import the class here, adjusting the package name and the ```className = "package"``` code

## Notes

+ do not use spring, ie: RequestBody is not needed, use just the plain object with quarkus-resteasy-jackson
+ do not use lombok if possible
+ avoid to use ```com.thoughtworks.xstream```

## CI/CD

Jenkis uses this command: ```clean package assembly:assembly -Dskip-unit-test=true```. Always double check if it
works! ```java -jar epayfeapi-1.0-SNAPSHOT-runner.jar```

Always tag!

+ ```git for-each-ref --sort=creatordate --format '%(refname) %(creatordate)' refs/tags```

### localhost environment

~~Add this args in the run configuration (runner VM options):~~

```
"-Dquarkus.security.users.embedded.users.epayapi1=mypass"
"-Dquarkus.security.users.embedded.roles.epayapi1=writer,reader,user"
"-DepaypacatalogService.authusr=epaycatalogapi_sportello"
```

~~or use ```epayfeapi.run.xml```~~

Just use this argument: ```-Dquarkus.profile=local```

### DEV environment

+ RELEASE_DEV_GIT &rarr; https://jenkins-cd.toolchain.csi.it/job/EPAY/job/RELEASE_DEV_GIT/job/EPAY_DEV-RP-01_epayfeapi/
+ epay-dev-rp-01
+ dv-jb%-svfe-epay.site01.nivolapiemonte.it

### test environment

+ RELEASE_TEST_GIT &rarr; https://jenkins-cd.toolchain.csi.it/job/EPAY/job/RELEASE_TEST_GIT/job/EPAY_TEST-RP-01_epayfeapi/
+ ts-qk%-apife-epay.site03.nivolapiemonte.it
+ http://tst-srv-pay.bilancio.csi.it/epayfe/
+ ultime ricette: 3.3.0 !

### test utente environment

+ https://jenkins-cd.toolchain.csi.it/job/EPAY/job/RELEASE_TU1_GIT/job/EPAY_TU1-RP-01_epayfeapi/
+ http://tu-exp-srv-pay.bilancio.csi.it/epayfe
+ epayapi_001, epayapi_002, 96wVeDM8
+ epayapi_022 epayapi_023
+ tu-qk%-apife-epay.site03.nivolapiemonte.it

winscp + putty example: ```beehive ssh nodes connect tu-qk1-apife-epay.site03.nivolapiemonte.it```

## Common Errors:

+ ```java.lang.NullPointerException: Cannot invoke "java.net.URL.toExternalForm()" because "resource" is null at org.mapstruct```, solution: In your intellij
  Idea: ```File | Settings | Build, Execution, Deployment | Compiler | user-local build process vm options``` set this
  value: ```-Djps.track.ap.dependencies=false```
+ ```processing.FilerException```, solution: unmark generated source mapper package under target/generated-sources/annotations/it/csi/epay/epayfeapi/mapper

## TODOs and questions

- [X] contract first mechanism
- [X] tutti i controllers che ritornano niente
- [X] health check
- [X] auth su properties
- [X] login, come epayapi, utente d_autorizzazione profilazione
- [X] pulizia application.properties
- [X] fix controller con requestbody
- [X] pulizia test chiamanti esterno (le classi java)
- [X] ~~com.fasterxml.jackson.core~~ c'è qualcosa di meglio? &rarr; rimossa
- [X] logs? default quarkus? &rarr; usati nel formato json default csi
- [ ] tests, esempi a livello controller, service, ecc...
- [ ] quarkus.http.root-path=/api/v1 -> si potrebbe fare di meglio prendendelo dallo yaml
- [ ] elasticsearch
- [ ] esporre ui swagger https://quarkus.io/guides/openapi-swaggerui
- [ ] https://quarkus.io/guides/security-basic-authentication-tutorial
- [ ] https://quarkus.io/guides/continuous-testing
- [ ] https://quarkus.io/guides/openapi-swaggerui
- [ ] https://quarkus.io/guides/transaction)

## Usefull links

+ https://editor.swagger.io/
+ https://gitlab.csi.it/technical-components/stack/STK_spa-angular-jaxrs/lg_jaxrs_quarkus/blob/master/docs/linee-guida/DSG-STD-GUI-jaxrsquarkus.md#configurazioni-environment-dependent-della-componente
+ http://kbt.csi.it/csi/extwebres/frameworksvil/drilldown/topics/devframework/topic_Single_Page_Applications_Angular2_Quarkus_Resteasy_V1.0.0.html
+ https://quarkus.io/
+ https://quarkus.io/guides/
+ https://quarkus.io/guides/resteasy
+ https://quarkus.io/guides/hibernate-orm-panache &rarr; paging example
+ https://evrentan.medium.com/using-mapstruct-within-quarkus-8636af17a2ea &rarr; mapping
+ https://openapi-generator.tech/docs/generators/jaxrs-spec/ &rarr; code generation options
+ https://github.com/MahaswamiSoftware/quarkus-jasper-report
+ https://engit.sharepoint.com/sites/CSI-Pagamenti &rarr; analisi
+ https://openapi-generator.tech/docs/generators/java/ &rarr; opzioni generazioni classi da yaml
