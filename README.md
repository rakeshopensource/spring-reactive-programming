# spring-reactive-programming

## Prerequisite
#### MongoDB

mkdir ~/data

sudo docker run -d -p 27017:27017 -v ~/data:/data/db mongo

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

