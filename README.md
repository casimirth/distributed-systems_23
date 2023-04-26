# distributed-systems_23
Repository for distributed systems course of University of Applied Sciences Esslingen

## Tech used:
- Vaadin Grid Frontend
- JPA H2 Backend

## Development

    mvn spring-boot:run

## Docker Deployment

    From Scratch/ After new dev cycle:
            mvn clean package -Pproduction
            docker build -t myimage:version .
            docker run -p 8080:8080 myimage:version
    port inside container--^     ^--outside-port

    Pulled from dockerhub:
        docker run http://insertLINKhere.com
## API

    GET /todos
    POST /todos
    PUT /todos

    GET /todos/{id}
    POST /todos/{id}
    PUT /todos/{id}


    