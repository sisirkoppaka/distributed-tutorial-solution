# distributed-tutorial-solution

## Package Tracking

This project simulates the kind of system that might be used to provide advanced, ML-based package tracking services. Architecture broadly follows the DDD/CQRS/ES model

ToDo:

- set up coverage tool
- set up CI server
- place coverage and build status links in this document
- write more tests
- write code to pass the tests :p

Project goals:

- perform comparable services to existing tracking systems
    - history readout
    - estimated delivery day
- additional functionality
    - on day of delivery, dynamically narrowing estimated delivery window
    
Initially, just the backend functionality with a simulator module than injects "real-world" activity and generates events, such as package transitions, deliveries, etc. Later, expose this to a web UI (potentially separating the Command and Query systems into separate services?).