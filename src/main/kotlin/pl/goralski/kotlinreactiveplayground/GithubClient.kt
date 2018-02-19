package pl.goralski.kotlinreactiveplayground

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux

class GithubClient(private val webClient: WebClient) {

    fun getRepository(owner: String, repo: String): Flux<GithubRepository> {
        return webClient.get()
                .uri("https://api.github.com/repos/$owner/$repo")
                .accept(APPLICATION_JSON).retrieve().bodyToFlux()
    }

    fun getRepositories(owner: String): Flux<GithubRepository> {
        return webClient.get()
                .uri("https://api.github.com/users/$owner/repos")
                .accept(APPLICATION_JSON).retrieve().bodyToFlux()
    }

}
