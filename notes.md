# Project Notes

## Current development

Projects in this repository aim to build on Domain-driven Design, as described by [Martin Fowler](http://martinfowler.com/) and [Vaughn Vernon](http://vaughnvernon.co/), combined with an aim for the least powerful technique. That said, this is a tutorial on distributed computing, so some techniques may be more powerful that *strictly* warranted by the application, in order to explore things that are definitely appropriately-powered for real production systems.

Specifically, for the package tracking project, packages and locations are the main objects of the domain. Packages are divided into categories depending on their status, and locations are divided into categories by whether they are shipping facilities, origins, or destinations.

It is important to keep a record of the history of transactions, both to add an extra layer of insurance that history will remain intact for inspection and also to enable that history to be replayed through the system. The benefit of this: 

1. This makes it possible to reconstruct the current state (or any prior intermittent state) in case of a failure
1. It provides a data source free of leakage for testing ML systems that might be used to analyze in-coming events

This requirement suggests an architecture using a persistent pub-sub system (a.k.a. event-sourcing) for delivering events to the application that maintains the current state. There are a number of ways to institute this kind of system, such as persistent actors in Akka, Redis, etc. Kafka will be used for this project in particular, due to easy integration with other Hadoop-ecosystem tools that are likely to be well-suited to other planned tasks (Oozie for temporal job scheduling, triggered job execution, and DAG-based work flows; YARN for allocation of actual physical memory and computing resources; and Spark for computation on large data sets).

With a pub-sub system in place (referred to in Kafka as a "broker"), the stream of incoming valid events (a "topic") can be persisted and used. This system is also very easily expanded to provide topics for other kinds of activity, depending on what is known to be needed. As an aside, it is very difficult to predict what will and won't be relevant later, so one approach would be to persist as much as the system can handle (read: that you can afford), and defer tossing data until storage requirements become over-burgeoning. This way, you have a chance to decide that this should be used. For example, you know that valid incoming events are important, but it might also be useful to know exactly what type of invalid incoming requests are received and how frequently, in order to develop an ML-based monitor, as is more common in fraud-detection systems.

## Plans

### Package tracking

The main project will track the current location and status of packages. This covers only the history and current status of packages in the system.

### Delivery/pickup route optimization

This project will determine the optimal route and number of trucks for pickup and delivery of packages in a region, given the array of locations that must be visited that day.

### Delivery window projection

Based on the scheduled route and current status of a delivery truck, provides a continuously-updated arrival window for a given location.

## Misc. notes

Relatively strict compiler flags are set to encourage good code practices (though devs should remember that passing the linter is necessary but not sufficient).
