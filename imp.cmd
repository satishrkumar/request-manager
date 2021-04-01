
curl --insecure --cert-type P12 --cert /Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12:2payuback.p12 https://localhost:8083/users/47853b6d-45e2-4201-92e0-e814cba0156f


java -Djavax.net.debug=ssl -Djavax.net.ssl.trustStore=/Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -Djavax.net.ssl.trustStorePassword=2payuback -jar target/request-manager-0.0.1-SNAPSHOT.jar

 keytool -v -importkeystore -srckeystore /Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -srcstoretype PKCS12 -destkeystore changedit -deststoretype JKS
 
 java -jar target/request-manager-0.0.1-SNAPSHOT.jar
   98  java -Djavax.net.debug=ssl -Djavax.net.ssl.trustStore=/Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -Djavax.net.ssl.trustStorePassword=2payuback -jar target/request-manager-0.0.1-SNAPSHOT.jar
   99  mvn clean package
  100  java -Djavax.net.debug=ssl -Djavax.net.ssl.trustStore=/Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -Djavax.net.ssl.trustStorePassword=2payuback -jar target/request-manager-0.0.1-SNAPSHOT.jar
  101  mvn clean package
  102  java -Djavax.net.debug=ssl -Djavax.net.ssl.trustStore=/Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -Djavax.net.ssl.trustStorePassword=2payuback -jar target/request-manager-0.0.1-SNAPSHOT.jar
  103  keytool -v -list -keystore 
  104  keytool -v -list -keystore changedit
  105  keytool -v -list -keystore .keystore
  106  keytool -v -list -keystore changedit
  107   keytool -v -importkeystore -srckeystore /Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -srcstoretype PKCS12 -destkeystore changedit -deststoretype JKS
  108  keytool -importkeystore -srckeystore changedit -destkeystore changedit -deststoretype pkcs12
  109  java -Djavax.net.debug=ssl -Djavax.net.ssl.trustStore=/Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -Djavax.net.ssl.trustStorePassword=2payuback -jar target/request-manager-0.0.1-SNAPSHOT.jar
  110  keytool -v -importkeystore -srckeystore /Users/satishkumar/2payyouback/request-manager/src/main/resources/keystore/2payuback.p12 -srcstoretype PKCS12 -destkeystore changedit -deststoretype pkcs12
  111  mvn clean package
