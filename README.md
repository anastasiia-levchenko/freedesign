## This is a test README file, to be modified

JDK version 17.0.11
mySQL server

================================
## Instructions
Used tool: mySQL Workbench

Actions:
1) DB schema 'freedesign' should be created
2) DB User (creds in properties files) should be created as well and privileges given.
3) After pulling the project, please run it and import all the sql data placed in src/main/resources/sql/testartworkdata
4) Move the images from 'public/images backup' folder into 'public/images' manually
5) After launching the project, go to http://localhost:8080/. 

Use these test user creds to log in:
username: testUser
password: SecurePassword
================================

##Technologies used:
- Spring framework
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Lombok
- Thymeleaf
- mySQL JDBC Driver
- Maven
- Spring Boot DevTools
- Bootstrap
  Tools:
  IDE: IntelliJ Idea
  SQL: MySQL Workbench

Design Patterns used:
Factory, Strategy

Project Architecture:
Controller -> Service -> DAO (Repository) -> DB