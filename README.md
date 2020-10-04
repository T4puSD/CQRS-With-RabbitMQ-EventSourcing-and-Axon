# CQRS-With-RabbitMQ-EventSourcing-and-Axon
This is follow up project of (Event Sourcing with Axon)[https://github.com/T4puSD/EventSourcing-With-Axon/blob/master/README.md]and implemented with RabbitMQ to completely separate command and query site into different service.

## What is CQRS?
The full form of CQRS means Command Query Responsibility Segragation. It means that a business solution should have two separate service
one should only change the state of the domain and other is only responsible to provide the view of the current state of the data.
Commands are responsible for the state changes in a domain and Queries are responsible to provide view data to the end user.

