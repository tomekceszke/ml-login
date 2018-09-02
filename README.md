# ml-login
 [![Build Status](https://travis-ci.org/tomekceszke/ml-login.svg?branch=master)](https://travis-ci.org/tomekceszke/ml-login)
#### PoC: improve login security by learning and checking additional features like typing speed
 
![login](doc/img/login.png)

This distributed application is responsible for:
* collecting training data: `collector-service`
* [anomaly detection using the multivariate gaussian distribution](https://www.coursera.org/lecture/machine-learning/anomaly-detection-using-the-multivariate-gaussian-distribution-DnNr9) (bases only on basic mathematical operations): `learning-service`
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
- Thymeleaf
- Twitter Bootstrap
- Lombok
- Maven
- Travis CI

## TODO
- prepare GUI:
    - login page ✔
    - integrate with Spring Security ✔
- screenshots ✔  
- `collector-service` api ✔
- basic use case...
- implement algorithm for choosing `ε` in `learning-service` ✔
- implement algorithm for model's evaluation in `learning-service`    
- validation of the input (request) data (in controllers)
- introduce exception handling ✔
- commons - split, refactor, clean up    
- introduce `-client` modules
- introduce ribbon, zuul, eureka and config clients/server
- extract configuration
- extract paths
- clean up maven poms (introduce hierarchy)    
- refactor: service names
- introduce wiremock and integrational tests (multi services)
- write more tests
- replace Thymeleaf by any modern frontend framework (angular/react/vue?)
- prepare Live demo    
    
    