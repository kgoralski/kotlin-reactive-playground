package pl.goralski.kotlinreactiveplayground

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ClientConfig {

    @Bean
    fun webClient(): WebClient {
        val httpConnector = ReactorClientHttpConnector()
        return WebClient.builder().clientConnector(httpConnector).build()
    }

    @Bean
    fun githubClient(webClient: WebClient): GithubClient {
        return GithubClient(webClient)
    }

}