# Yet another movie administrator

### Starting the application
1. docker-compose up (starts the database)
2. mvn clean install (to convert XML Objects to POJOs)
3. Start YamaApplication

### SoapClient
* use the testExample file as argument (resources folder)
* java -jar [yamaSoapCliSpring-0.0.0.jar] -f [absolutePathToFile]

### RestClient
* use the json files in the resources folders
* java -jar [yamaRestCliSpring-0.0.0.jar] -f [absolutePathToFile]

### Website 
localhost:8080

### REST Interface
#### Swagger 
http://localhost:8080/swagger-ui.html

### SOAP Interface
http://localhost:8080/ws/movies.wsdl