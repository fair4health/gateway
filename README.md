# FAIR4Health Gateway

[![Build Status](https://travis-ci.org/fair4health/gateway.svg?branch=master)](https://travis-ci.org/fair4health/gateway) 
[![codecov.io](https://codecov.io/gh/fair4health/gateway/branch/master/graphs/badge.svg)](http://codecov.io/gh/fair4health/gateway)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=eu.fair4health:gateway&metric=alert_status)](https://sonarcloud.io/dashboard/index/eu.fair4health:gateway)
[![Docker Build](https://img.shields.io/docker/cloud/build/fair4health/gateway)](https://cloud.docker.com/u/fair4health/repository/docker/fair4health/gateway)
[![Docker Pulls](https://img.shields.io/docker/pulls/fair4health/gateway)](https://cloud.docker.com/u/fair4health/repository/docker/fair4health/gateway)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)

## Description

FAIR4Health Gateway to provide authentication, authorization and intelligent routing. It provides the following routing:

- [POST] /oauth/login
- [GET]  /oauth/user

## Technology

- Java 8+
- Maven for Java dependency management
- Spring Boot 
- Spring Cloud Gateway
- Lombok for the models

## Functionalities

- OAuth 2.0 Authentication and Authorization
- Intelligent routing

## How to deploy

Compile and package the project with

```
mvn clean package
```

and execute

```
java -jar target/gateway.war
```

It can also be run as:

```
mvn spring-boot:run
```

Deploy the [Keycloak auth repo](https://github.com/AriHealth/keycloak-auth). Execute the following curl:
```
curl -X POST "http://localhost:8083/oauth/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"string\", \"username\": \"string\"}"
```

It should redirect the post to `http://localhost:8081/login` and provide the following response:
```
{
  "timestamp" : "2020-07-19T18:06:19.810+0000",
  "status" : 401,
  "error" : "Unauthorized",
  "message" : "Unauthorised access to protected resource",
  "path" : "/login"
}
```

## Environment variables

    OAUTH_EASY_URL=
    LOGGING_FOLDER=
    LOGGING_MODE=

## Docker deployment

Build the image:

```
docker build -t fair4health/gateway .
```

Simple deployment:

```
docker run --name gateway -d fair4health/gateway
```

Logging can be also configured using `LOGGING_FOLDER` and sharing a volume (this is useful for example for [ELK](https://www.elastic.co/elk-stack) processing). The level of the logging can be configured with `LOGGING_MODE` (dev|prod):

```
docker run --name gateway -d -v /tmp/log/gateway:/log/calculator -e LOGGING_FOLDER=/log/test -e LOGGING_MODE=dev fair4health/gateway
```

## License

Apache 2.0

By downloading this software, the downloader agrees with the specified terms and conditions of the License Agreement and the particularities of the license provided.
