# Project Notes

## Current development

Projects in this repository aim to build on Domain-driven Design, as described by [Martin Fowler](http://martinfowler.com/) and [Vaughn Vernon](http://vaughnvernon.co/), combined with an aim for the least powerful technique. 

Specifically, for the package tracking project, packages and locations are the main objects of the domain. Packages are divided into categories depending on their status, and locations are divided into categories by whether they are shipping facilities, origins, or destinations.

## Plans

### Package tracking

The main project will track the current location and status of packages. This covers only the history and current status of packages in the system.

### Delivery/pickup route optimization

This project will determine the optimal route and number of trucks for pickup and delivery of packages in a region, given the array of locations that must be visited that day.

### Delivery window projection

Based on the scheduled route and current status of a delivery truck, provides a continuously-updated arrival window for a given location.
