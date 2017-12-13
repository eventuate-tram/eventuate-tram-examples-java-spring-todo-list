# Todo List example application

## The problem: atomically updating data and publishing events/messages

It's challenging to atomically update a data (e.g. a Domain-Driven design aggregate) and publish a message, such as a domain event.
The traditional approach of using 2PC/JTA isn't a good fit for modern applications.

The [Eventuate&trade; Tram framework](https://github.com/eventuate-tram/eventuate-tram-core) implements an alternative mechanism based on the [Application Events](http://microservices.io/patterns/data/application-events.html) pattern.
When an application creates or updates data, as part of that ACID transaction, it inserts an event into an `EVENTS` or `MESSAGES` table.
A separate process publishes those events to a message broker, such as Apache Kafka.


## About the Todo list application

The Todo List application, which lets users maintain a todo list, is the hello world application for the [Eventuate&trade; Tram framework](https://github.com/eventuate-tram/eventuate-tram-core).
It shows how use Eventuate Tram to

* reliably publish domain events as part of a database transaction that updates an aggregate.
* consume domain events to update a [CQRS view](http://microservices.io/patterns/data/cqrs.html) view

When a user creates or updates a todo, the application publishes a domain event.
An event handler, subscribes to those events and updates an ElasticSearch-based CQRS view.

## Todo list architecture

The Todo List application is built using

* Java
* JPA
* Eventuate Tram
* Spring Boot
* MySQL
* ElasticSearch

The application persists the Todo JPA entity in MySQL.
It also maintains a materialized view of the data in ElasticSearch.

There are two versions of the application:

* `single-module` - a single module Gradle project for a monolithic version of the application.
It is the easiest to get started with.
* `multi-module` - a multi-module Gradle project for the microservices-based version of the application.
It consists of a `todo-service`, which creates and updates Todos, and `todo-view-service`, which maintains a [CQRS view](http://microservices.io/patterns/data/cqrs.html view in ElasticSearch

Note: you do not need to install Gradle since it will be downloaded automatically.
You just need to have Java 8 installed.


# Got questions?

Don't hesitate to create an issue or see

* [Mailing list](https://groups.google.com/d/forum/eventuate-users)
* [Slack](https://eventuate-users.slack.com). [Get invite](https://eventuateusersslack.herokuapp.com/)
* [Contact us](http://eventuate.io/contact.html).


Don't forget to take a look at the other [Eventuate Tram examples](http://eventuate.io/exampleapps.html):

* [Customers and Orders](https://github.com/eventuate-tram/eventuate-tram-sagas-examples-customers-and-orders)
* [FTGO Example application for Microservice Patterns book](https://github.com/microservice-patterns/ftgo-application)


# Building and running

First, build the application

```
./gradlew assemble
```

Next, launch the services using [Docker Compose](https://docs.docker.com/compose/):

```
export DOCKER_HOST_IP=...
docker-compose -f docker-compose-eventuate-mysql.yml build
docker-compose -f docker-compose-eventuate-mysql.yml up -d
```

Note:

1. You can also run the Postgres version using `docker-compose-eventuate-postgres.yml`
2. You need to set `DOCKER_HOST_IP` before running Docker Compose.
This must be an IP address or resolvable hostname.
It cannot be `localhost`.
See this [guide to setting `DOCKER_HOST_IP`](http://eventuate.io/docs/usingdocker.html) for more information.

# Using the application

Once the application has started, you can use the application via the Swagger UI.

If you are running the `multi-module` version:

* `http://${DOCKER_HOST_IP}:8081/swagger-ui.html` - the command-side service
* `http://${DOCKER_HOST_IP}:8082/swagger-ui.html` - the query-side service

If you are running the `single-module` version:

* `http://${DOCKER_HOST_IP}:8080/swagger-ui.html` - the monolithic application

# Got questions?

Don't hesitate to create an issue or see

* [Mailing list](https://groups.google.com/d/forum/eventuate-users)
* [Slack](https://eventuate-users.slack.com). [Get invite](https://eventuateusersslack.herokuapp.com/)
* [Contact us](http://eventuate.io/contact.html).
