package pl.goralski.kotlinreactiveplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class ApplicationRunner

fun main(args: Array<String>) {
    runApplication<ApplicationRunner>(*args)
}
