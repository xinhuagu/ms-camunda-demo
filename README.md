# ms-camunda-demo
this project aims to demostrate the usage of docker by development 

the application runs on camunda bpm plattform

# Usage
run ./reload.sh
(./reload.sh is a script to load docker containers)

open http://localhost:8080/camunda/app/cockpit/default/#/login
name:xinhua
password:xinhua

to create an order:
POST  http://192.168.99.100:8080/webrest/webapi/order
application/json
{
 "number":"1",
 "name":"test"
}

to list orders:
GET http://192.168.99.100:8080/webrest/webapi/order

to get an order by number:
GET http://192.168.99.100:8080/webrest/webapi/order/{number}
