# ms-camunda-demo
to demostrate the usage of docker by development 

the application runs on camunda bpm plattform

# Usage
run ./reload.sh<br/>
(./reload.sh is a script to load docker containers)<br/>

open http://localhost:8080/camunda/app/cockpit/default/#/login<br/>
name:xinhua<br/>
password:xinhua<br/>

to create an order:<br/>
POST  http://localhost:8080/webrest/webapi/order<br/>
application/json<br/>
{<br/>
 "number":"1",<br/>
 "name":"test"<br/>
}<br/>

to list orders:<br/>
GET http://localhost:8080/webrest/webapi/order<br/>

to get an order by number:<br/>
GET http://localhost:8080/webrest/webapi/order/{number}<br/>
