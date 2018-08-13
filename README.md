# ml-login (PoC)

#### PoC: improve login security by learning and checking additional features like typing speed - anomaly detection

This distributed application is responsible for:
* collecting training data: `collector-service`
* learning process - algorithm: anomaly detection based on _Gaussian Probability Density Function_:  `learning-service`
* validating password basing on features like typing speed: `login-validator-service`
* authenticating user: `login-service`

## Live demo
tbd

## Tech Stack
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Hibernate
- H2 in memory database
- JUnit, Spring MVC Test
- Twitter Bootstrap
- Lombok
- Maven
- Travis CI: [![Build Status](https://travis-ci.org/tomekceszke/ml-login.svg?branch=master)](https://travis-ci.org/tomekceszke/ml-login)

## TODO
- prepare GUI:
    - login page https://getbootstrap.com/docs/4.1/examples/floating-labels/
    - integrate with Spring Security
- screenshots    
- basic use case...    
- validation of the input (request) data (in controllers)
- exception handling
- commons    
- clean up maven poms    
- prepare Live demo    
    
    