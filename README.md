Run project directly from CMD: </br>
1. <!-- By default port 8080--></br>
mvn tomcat:run</br>
2. <!-- change server port --></br>
mvn -Dmaven.tomcat.port=8181 tomcat:run</br>

Hit url : http://localhost:8080/payment-service/transaction/Ashish</br>

Dependencies : </br>

1. Your payment-service project should be running on below path</br>
		http://localhost:8080/payment-service</br>
		
2.EndPoint are as defined in the controller for domain objects.
i.e For Get and POST :
 e.g.http://localhost:<port Number>/selenisum-suite/projects/

Swagger frame work has been implemented .To access all the services use the follwoing link 

http://localhost:8080/selenisum-suite/api-docs.json
 and for UI use below
http://localhost:8080/selenisum-suite/swagger-ui.html


