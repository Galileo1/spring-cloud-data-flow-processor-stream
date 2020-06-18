# Spring cloud data flow processor stream application

Processes/transforms the data stream received from the [source stream](https://github.com/Galileo1/spring-cloud-data-flow-source-stream)
The micro service uses the reactive stream java DSL library to specify the nature of the data stream(Processor).

The core purpose of the application is to convert the unix millisecond timestamp received from Finnhub.io quotes [endpoint](https://fi
into readable DateTime stamp.


## Overall Design about the application and platform design 

More info about the design of the overall application can be found [here](
https://github.com/Galileo1/weird.io.case-study/blob/master/design/Design.md).

## Setup the platform 

More information about setting the platform can be found [here](
https://github.com/Galileo1/weird.io.case-study/blob/master/README.md).

# BUILD THE PROJECT

## Prerequisites

 * Apache Maven 3.3.x
 * Java 1.8.x

## Build Locally

```bash
mvn clean install
```

## Publish docker image

```bash
mvn clean package jib:build
```

# Stream Deployments 

We can either use the server console or the spring data flow shell cli :

```bash 
java -jar spring-cloud-dataflow-shell-1.7.4.RELEASE.jar
```
## configuring the data flow server

Note the how to get the server url is documented in [here](https://github.com/Galileo1/weird.io.case-study/blob/master/README.md)

```bash
server-unknown:>dataflow config server http://server-address
```
 
# Registering application

If the Data Flow Server and shell are not running on the same host, point the shell to the Data Flow server URL:

```bash
  app register --name transform --type processor 
    --uri docker://gaurva/spring-cloud-data-flow-processor-stream:rev2test5    
```

# For the application to work you need to register the source and the sink application as well

```bash

  app register --name http --type source 
    --uri docker://gaurva/spring-cloud-data-flow-source-stream:rev2test
  
  app register --name log --type sink 
    --uri docker:springcloudstream/log-sink-rabbit:1.2.0.RELEASE
```

# Creating Streams 

```bash
    stream create --definition "http | transform | log" --name quotes

```

# Deploying the streams 

```bash
 stream deploy --name quotes

```

