REM La directory dove sono presenti i jar del generatore csi-swagger 
 
java -cp swagger-codegen-cli.jar;csi-java-swagger-codegen-1.0.0.jar io.swagger.codegen.SwaggerCodegen generate -i "..\..\src\resources\swagger.json" -l jaxrs-resteasy-eap-csi -o "..\.." --config swagger-config.json
