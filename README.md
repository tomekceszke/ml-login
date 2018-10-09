# ml-login
 [![Build Status](https://travis-ci.org/tomekceszke/ml-login.svg?branch=master)](https://travis-ci.org/tomekceszke/ml-login)
### Proof of Concept: 
improve login security by learning and checking additional features like typing speed
 
![demo](doc/img/demo1.gif)

This distributed application is responsible for:
* collecting training data: `collector-service`
* [anomaly detection using the multivariate gaussian distribution](https://www.coursera.org/lecture/machine-learning/anomaly-detection-using-the-multivariate-gaussian-distribution-DnNr9): `learning-service`
* validating password basing on features like typing speed: `validator-service`
* authenticating user: `login-service`
  
## Preconditions
- distributed, based on micro-services application written in Spring Boot & Cloud - _goal: improve Spring's skills and writing multi-services applications_
- no external machine learning libraries, all algorithms written using only basic math formulas (`+`,`-`,`*`,`/`,`^`) - _goal: know how anomaly detection algorithms work_   
- use any Continuous Integration and Delivery systems together with tests - _goal: know how to configure CI/CD systems and keep code quality discipline_
- deploy as docker's containers on any cheap VPS and make the application **available for everyone** - _goal: know how docker deployment works from production point of view_  

## Live demo
http://ml-login.ceszke.com

## Tech Stack
- Spring Boot
- Spring Cloud (Config, Ribbon, Feign)
- Spring Security
- Spring Data JPA (Hibernate, H2)
- JUnit, Spring MVC Test
- Thymeleaf
- Twitter Bootstrap
- Maven
- Travis CI
- Docker

## TODOs
- prepare GUI:
    - login page ✔
    - integrate with Spring Security ✔
- screenshots ✔  
- `collector-service` api ✔
- basic use case... ✔
- implement algorithm for choosing `ε` in `learning-service` ✔
- implement algorithm for model's evaluation in `learning-service`  ✔  
- validation of the input (request) data (in controllers)
- introduce exception handling ✔
- commons - split, refactor, clean up    
- introduce `-client` modules
- add hystrix, zuul, eureka
- add config clients/server ✔
- extract configuration ✔
- extract paths
- clean up maven poms (introduce hierarchy)    
- refactor: service names ✔
- introduce wiremock and integrational tests (multi services)
- write more tests ✔
- replace Thymeleaf by any modern frontend framework (angular/react/vue?)
- prepare live demo ✔    
    
    