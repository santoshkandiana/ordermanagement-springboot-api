#Order Management ####Service Name: ordermanagement-springboot-api

GET Order Details
Description
Build an application that performs the following actions:

Given a DB having orders in different statuses. write a SpringBoot app by exposing two endpoints one to retrieve order details with CustomerPhone and other to retrieve orderlines in specific status.
Use Logging and Monitoring as of industry standard best practices

HTTP Method
GET



##GitHub URL https://github.com/santoshkandiana/springboot-poc

Notes to reviewer
Used TDD for building the application. Used lombok framework for builders and models. used gradle instead of maven as i have been using gradle for while Used Mongo Data JPA framework to talk to mongo database

##How to run the application on local Please check out master branch from github. Please Right click and run the main class of the application application. Please Use any tool like postman or ARC to run the GET a operation Also Additionally i have created a POST operation which would be useful to create records for GET  operations Please refer swagger documentation

##Code Quality checks Ran sonarlint for quality check, could not run veracode due to licesnse issues

##Workspace Setup

Please checkout master branch in your favorite editor
This code is tested with Java 8 and Intellij
set up graddle setting to graddle wrapper if local machiene does not have gradle
Right click on Main class and click on run to bootstrap the spring boot application
Prometheus is set up metric collection which could be set up simply setting a docker container for prometheus on port 9090 
