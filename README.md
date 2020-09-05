# spring-boot-rest-api-starter

This project aims to be used as an initial project to make web applications with spring boot.
The project contains a basic module structure, a database config, security configuration and docker support. 

# How to run the application

To run the application, you need to have a PostgreSQL database installed and configured properly (or you can jump to the Docker support section):
 - the database is running on localhost, accessible on port 5432
 - has a database called api-db
 - the database is accessible with the username: *rest-api* and password: *admin*

To run the application, use the below commands:

> mvn clean install
> mvn spring-boot:run -pl web -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev"

It builds the project and run it with the dev profile. To access the application, open

http://localhost:8080

# Supported profiles

Currently two profiles are supported, *dev* and *prod*. the *dev* profile is to be used locally, and the prod profile contains configuration for the production environment.

# Docker support

To build the docker image of the application, first package the application with

> mvn clean package

and then run

> docker-compose up

it should set up the necessary containers. The application container can be reached at

http://localhost:8080

The pgAdmin container can be reached at

http://localhost:5050

and you can log in with 
 - the email address: *admin@admin.com*
 - password: *admin*

and set up the connection to the database by right clicking the Servers tab on the left, choose create and in the popup on the connection tab
 - set the "Host name / address" field to *spring-boot-rest-api-starter-db*
 - set the "Username" to *rest-api*
 - set the "Password" to *admin*