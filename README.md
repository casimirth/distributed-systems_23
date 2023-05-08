# distributed-systems_23
Repository for distributed systems course of University of Applied Sciences Esslingen

## Tech used:
- Vaadin Grid Frontend
- Spring Boot JPA H2 Backend

## Development Installation
    
    git clone https://github.com/casimirth/distributed-systems_23
    mvn clean install 
    mvn spring-boot:run

## Docker Installation
### From CLI / After new dev cycle (Dockerfile):
    mvn clean package -Pproduction
            docker build -t myimage_name:version .
            docker run -p 8080:8080 myimage:version
    port inside container--^     ^--outside-port

### Pulled from dockerhub:
    docker run http://insertLINKhere.com
### With docker compose (docker-compose.yaml):
    docker-compose up --build

### !Important Environment Variables
The Vaadin Servlet has to leave room for for Swagger and the Springdocs. Therefore in development the setup for Vaadin needs to exclude those URLs.
## API
### CRUD
# OpenAPI definition
## Version: v0

### /todos/{id}

#### GET
##### Summary:

Creates a Todo Item with path variable name and default priority of 2

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Found the item |
| 400 | Invalid itemId supplied |
| 404 | Item not found |

#### PUT
##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

#### DELETE
##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### /todos

#### GET
##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

#### POST
##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### /greeting

#### GET
##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| name | query |  | No | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |


### Documentation
    Open API Spec: 
        GET /api-docs

    SwaggerUI:    
        GET /swagger-ui.html


    