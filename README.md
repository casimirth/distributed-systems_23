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

<h1 id="openapi-definition">OpenAPI definition v0</h1>

> Scroll down for example requests and responses.

Base URLs:

* <a href="http://localhost:8085">http://localhost:8085</a>

<h1 id="openapi-definition-controller">controller</h1>

## one

<a id="opIdone"></a>


`GET /todos/{id}`

*Shows a Todo Item with path variable name*

<h3 id="one-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|none|

> Example responses

> 200 Response

```json
{
  "id": 0,
  "text": "string",
  "priority": 0
}
```

<h3 id="one-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Found the item|[TodoItem](#schematodoitem)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Invalid itemId supplied|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Item not found|None|

<aside class="success">
This operation does not require authentication
</aside>

## replaceTodoItem

<a id="opIdreplaceTodoItem"></a>


`PUT /todos/{id}`

*Updates todoItem by its id. If id is not already given to todoItem will create new todoItem*

> Body parameter

```json
{
  "id": 0,
  "text": "string",
  "priority": 0
}
```

<h3 id="replacetodoitem-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|none|
|body|body|[TodoItem](#schematodoitem)|true|none|

> Example responses

> 200 Response

```json
{
  "id": 0,
  "text": "string",
  "priority": 0
}
```

<h3 id="replacetodoitem-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|successfully updated todoItem|[TodoItem](#schematodoitem)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Item could not be updated|None|

<aside class="success">
This operation does not require authentication
</aside>

## deleteTodoItem

<a id="opIddeleteTodoItem"></a>


`DELETE /todos/{id}`

*Deletes todoItem by its id*

<h3 id="deletetodoitem-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|none|

<h3 id="deletetodoitem-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|successfully deleted todoItem|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Item could not be updated|None|

<aside class="success">
This operation does not require authentication
</aside>

## all

<a id="opIdall"></a>


`GET /todos`

*Lists all TodoItems that are available*

> Example responses

> 200 Response

```json
{
  "id": 0,
  "text": "string",
  "priority": 0
}
```

<h3 id="all-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Found the list of items|[TodoItem](#schematodoitem)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|TodoItems not found|None|

<aside class="success">
This operation does not require authentication
</aside>

## newTodoItem

<a id="opIdnewTodoItem"></a>


`POST /todos`

*Creates a new TodoItem that are available*

> Body parameter

```json
{
  "id": 0,
  "text": "string",
  "priority": 0
}
```

<h3 id="newtodoitem-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[TodoItem](#schematodoitem)|true|none|

> Example responses

> 200 Response

<h3 id="newtodoitem-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|TodoItem was created|[TodoItem](#schematodoitem)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|TodoItem not created|None|

<aside class="success">
This operation does not require authentication
</aside>

## greeting

<a id="opIdgreeting"></a>

`GET /greeting`

*Shows greeting as mentioned in the introduction to REST*

<h3 id="greeting-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|name|query|string|false|none|

> Example responses

<h3 id="greeting-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Greeting sent back|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|/greeting not found|None|

<h3 id="greeting-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_TodoItem">TodoItem</h2>
<!-- backwards compatibility -->
<a id="schematodoitem"></a>
<a id="schema_TodoItem"></a>
<a id="tocStodoitem"></a>
<a id="tocstodoitem"></a>

```json
{
  "id": 0,
  "text": "string",
  "priority": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|text|string|false|none|none|
|priority|integer(int32)|false|none|none|



