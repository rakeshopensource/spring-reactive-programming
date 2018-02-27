# spring-reactive-programming

## Prerequisite
#### MongoDB

###### docker-compose.yml
```
version: '3'
services:
  mongodb:
    image: mongo
    ports:
      - "27017:27017"
    volumes:
      - "mongodata:/data/db"
    networks:
      - network1

volumes:
   mongodata:

networks:
   network1:
```
```shell
$ docker-compose up
```

## How to run
#### Curl

```shell
$ curl http://localhost:8080/products | json_pp

[
   {
      "id" : "5a955820190497091665c4eb",
      "name" : "iPhone"
   },
   {
      "id" : "5a955820190497091665c4ec",
      "name" : "Mi"
   },
   {
      "id" : "5a955820190497091665c4ed",
      "name" : "Samsung"
   }
]

```

```shell
$ curl http://localhost:8080/products/5a955820190497091665c4eb/events 


data:{"productId":"5a955c9f19049709e765557c","date":"2018-02-27T13:28:16.634+0000"}

data:{"productId":"5a955c9f19049709e765557c","date":"2018-02-27T13:28:17.639+0000"}

data:{"productId":"5a955c9f19049709e765557c","date":"2018-02-27T13:28:18.640+0000"}

data:{"productId":"5a955c9f19049709e765557c","date":"2018-02-27T13:28:19.645+0000"}
.
.
. 
continue



```

#### Spring Boot (WebClient)
```text
Run spring boot client application.

```

