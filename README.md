# ml-login

#### PoC: improve login security by learning and checking additional features like typing speed - anomaly detection

![login](doc/img/login.png)

This distributed application is responsible for:
* collecting training data: `collector-service`
* anomaly detection based on _Gaussian Probability Density Function_: `learning-service`
* validating password basing on features like typing speed: `ml-validator-service`
* authenticating user: `login-service`

## Live demo
tbd

## Tech Stack
- Spring Boot
- Spring Cloud
- Spring Security
- Spring Data JPA
- Hibernate
- H2 (in memory database)
- Open Feign (rest client)
- JUnit, Spring MVC Test
- Twitter Bootstrap
- Lombok
- Maven
- Travis CI: [![Build Status](https://travis-ci.org/tomekceszke/ml-login.svg?branch=master)](https://travis-ci.org/tomekceszke/ml-login)

## TODO
- prepare GUI:
    - login page ✔
    - integrate with Spring Security ✔
- screenshots ✔  
- `collector-service` api ✔
- basic use case...
- implement algorithm for choosing `ε` in `learning-service`
- implement algorithm for model's evaluation in `learning-service`    
- validation of the input (request) data (in controllers)
- introduce exception handling ✔
- commons - split, refactor, clean up    
- introduce ribbon, zuul, eureka and config clients/server
- extract configuration
- clean up maven poms (introduce hierarchy)    
- refactor: service names
- introduce wiremock and integrational tests (multi services)
- write more tests
- prepare Live demo    
    
    