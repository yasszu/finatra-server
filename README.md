# Example of API Server with [Finatra](https://twitter.github.io/finatra/)
## Getting Started

1. Start a MySQL server  
    ```
    $ docker-compose up -d    
    ```

2. Start the API server

    ``` 
    $ sbt run
    ```
    
## Deploy application
* Create a JAR file

    ```
    $ sbt assembly
    
    ```

* Run process
    
    ```
    $ java -jar target/scala-2.12/finatra-server-assembly-1.0.jar &
    ```    

## Requests     

### POST /user

```
$ curl -i -X POST http://localhost:8888/user -d '{"name":"test","email":"sample@test.com","comment":"testtesttest"}'
```

### GET /users/:id

```
$ curl -i -X GET 'http://localhost:8888/users/1'
```

### GET /users?page=0&count=3

```
$ curl -i -X GET 'http://localhost:8888/users?page=1&limit=3'
```

### PUT /user/:id

```
$ curl -i -X PUT \
    http://localhost:8888/users/:id \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d 'name=abcd&email=abcd@sample.com&comment=test'
```

### DELETE /user/:id

```
$ curl -i -X DELETE http://localhost:8888/users/:id
```