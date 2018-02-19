## Kotlin & Spring Reactive Playground

Just quick example app. Nothing special here. 

- Kotlin
- Spring WebFlux & WebClient
- Spring Boot 2.0 RC1 with Netty
- MongoDb & Reactive Repositories

```docker
docker run -d -p 27017:27017 --name mongodb -d mongo
```

Requests:
1. will fetch repository from github and save to mongodb
2. will fetch all github repositories and save them to mongodb
3. will fetch saved repositories from mongodb

```bash
curl -v http://localhost:8080/repositories/kgoralski/go-crud-template
curl -v http://localhost:8080/repositories/kgoralski
curl -v http://localhost:8080/repositories/

```

PS. MongoDb used because it was easier to start playing with it.