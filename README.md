# TMC LAB JEPPENSEN GRUPA 1

#### How to run server

1) Just import project to NetBeans IDE, build it and run
2) U can change port if you need to (ServerCore.java:24)

#### Server usage

1) To set approach gate (for example 1st gate)
POST: http://localhost:8080/setGate?id=1
2) To get approach gate (if was set)
GET: http://localhost:8080/getGate
3) To get route to gate (if gate was set, MOCKED works only for gates 1,2 and 3)
GET: http://localhost:8080/getRouteToGate
4) To get current plane position (MOCKED, srtight line from Gdansk Wzgorze Mickiewicza to Airport)
GET: http://localhost:8080/getCurrentPostion

#### To run tests in NetBeans
Click right button on test class(Test Packages) then click Test File.

#### Documentaion

1) Documentation is located in DOCS dir
2) Schemats was made in Umlet tool (.uxf files)

#### Contibutors
Michalina Kukiełko
Przemysław Studziński
Piotr Grzejszczyk
