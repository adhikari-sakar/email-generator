## Email Provider Service

### Introduction

This is an application is designed for adding capabilities to integrate with different email providers. Basic DDD and SOLID principles are applied in the design so that email providers can be easily added and removed.

### Implementation
- Spring boot 2.7.15 
- H2 as InMemory DB. 

### Requirements
- Java 11
- Maven
- Lombok Support
- Annotation Processor

### Properties:

| property                     |    sample value     | description      |
|------------------------------|:-------------------:|------------------|
| `server.port`                |        8080         | server http port |
| `spring.datasource.url`      | jdbc:h2:mem:emaildb | db url           |
| `spring.datasource.username` |         sa          | db user name     |
| `spring.datasource.password` |                     | db password      |



### Run Instruction
- mvn clean install
- `./mvnw spring-boot:run` or `mvn spring-boot:run` or `java -jar target/email-generator-0.0.1-SNAPSHOT.jar`
- Data can be viewed/queried through h2 console http://localhost:8080/h2-console/


### API:
api/v1/email/generate/{type} - GET
- http://localhost:8080/api/v1/email/generate/{type}
api/v1/report/generate - POST
- http://localhost:8080/api/v1/report/generate

### Supported Email Providers:

- SENDGRID
- SPARK
- MAILGUN
- DEFAULT 

### Features:
- Based on the provider type, a template will be built and an email will be generated.
- Currently, integration is implemented with SendGrid provider.
- Remaining providers act as a mock email provider but the generated email is distinguished based on the type.
- A fallback mechanism is implemented if supplied provider is not found or not up and running.
- Fallback will be based on Priority value, the highest up and running provider will be picked to generate an email.
- Priority will be swapped if from failing provider to the new provider.


### Postman
The documentation for all sever endpoints can be found in `/src/main/resources/API_COLLECTION.json` file.
- Import the json collection file in Postman client.
- Invoke Register request for registering new batteries.
- Invoke Battery Info request for querying batteries within supplied postcode range.``

