# CQRS-With-RabbitMQ-EventSourcing-and-Axon
This is a follow up project of [Event Sourcing with Axon](https://github.com/T4puSD/EventSourcing-With-Axon/blob/master/README.md) and implemented with RabbitMQ to completely separate command and query site into different services. So, to understand this project it is suggested to visit that repository first as this readme will only include partial details.

## What is CQRS?
The full form of CQRS means Command Query Responsibility Segragation. It means that a business solution should have two separate service
one should only change the state of the domain and other is only responsible to provide the view of the current state of the data.
Commands are responsible for the state changes in a domain and Queries are responsible to provide view data to the end user.

## What is RabbitMQ?
It is a message broker which provide message queuing service. It means that instead of direct service to service call we can publish our message into a queue and any consumer which is subcribed to that queue can get that message whenever the consumer is free. Spring Boot already support rabbitmq with it's AMQP(Adcance Message Queuing Protocol) dependecy. So, impmlementing rabbitmq in spring boot is relatively easy with pre existing annotations.

## Project Details
* **AccountCommandProject:** It is responsible for handling every command.
* **AccountQueryProject:** It is responsible for handling the queries.   
They both communicate with each other with RabbitMQ. The rabbitmq implementation in this project is very simple. AccountCommandProject submits the state of the aggregated result after each event into rabbitmq and at other side of the queue AccountQueryProject consume those state and change the account balance of the user accordingly.
