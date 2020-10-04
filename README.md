# CQRS-With-RabbitMQ-EventSourcing-and-Axon
This is a follow up project of [Event Sourcing with Axon](https://github.com/T4puSD/EventSourcing-With-Axon/blob/master/README.md) and implemented with RabbitMQ to completely separate command and query site into different services. So, to understand this project it is suggested to visit that repository first as this readme will only include partial details.

## What is CQRS?
The full form of CQRS means Command Query Responsibility Segragation. It means that a business solution should have two separate service
one should only change the state of the domain and other is only responsible to provide the view of the current state of the data.
Commands are responsible for the state changes in a domain and Queries are responsible to provide view data to the end user.

## What is Eventually Consistence Data?
Embracing high scalability and ensuring high availability of the application is the main target of CQRS applications, but that produce the consistency issue in the data. It means that it is not guaranteed that we will always get the consistence account balance in our service everytime. If we try to read and write into the same account at the same time then the incostency might occure but in the end of the operation we will have the consistence balance in the account.

## What is RabbitMQ?
It is a message broker which provide message queuing service. It means that instead of direct service to service call we can publish our message into a queue and any consumer which is subcribed to that queue can get that message whenever the consumer is free. Spring Boot already support rabbitmq with it's AMQP(Adcance Message Queuing Protocol) dependecy. So, impmlementing rabbitmq in spring boot is relatively easy with pre existing annotations.

## Project Details
There are two differnet project in this repository. Both are handling differnet task:

### AccountCommandProject
* **Responsibility:** It is responsible for handling every command.
* **Database:** It uses axon-server's in built database to store every event that occure in the project. 
* **Port:** It will run on port **8080.**

### AccountQueryProject
* **Responsibility:** It is responsible for handling the queries.
* **Database:** It uses h2-database to store only the aggregated result after every transaction.
* **Port:** It will run on port **8081.**

They both communicate with each other with RabbitMQ. The rabbitmq implementation in this project is very simple. AccountCommandProject submits the state of the aggregated result after each event into rabbitmq and at other side of the queue AccountQueryProject consume those state and change the account balance and state of the user accordingly.

## Requirements to Run
* [Java JDK11+](https://www.java.com/en/download/)
* [Gradle](https://gradle.org/)
* [Axon-Server](https://axoniq.io/product-overview/axon-server)
* [RabbitMQ-Server](https://www.rabbitmq.com/download.html)

## How to Run the project
This project assumes that Axon and RabbitMQ server are running at their default port in the background. After you ensure that both the server are running and you have the requrements already installed in your system follow these setps:
1. Clone this repositoy
2. Go to the downloaded repository(you might need to unzip it if you download as zip)
3. Go to AccountCommandProject directory and open terminal then run `gradle bootRun`
4. Go to AccountQueryProject directory and open terminal then run `gradle bootRun`   

If you were able to ran both of the project successfully then you can visite `localhost:8080/swagger-ui/index.html` in a browser tab to execute accountcommands controller endpoint and `localhost:8081/swagger-ui/index.html` to execute accountquery controller endpoint.
