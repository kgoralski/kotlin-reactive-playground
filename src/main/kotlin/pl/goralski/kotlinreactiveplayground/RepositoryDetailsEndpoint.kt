package pl.goralski.kotlinreactiveplayground

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class RepositoryDetailsEndpoint(
        private val githubClient: GithubClient,
        private val entityRepository: ReactiveEntityGithubRepository) {

    @GetMapping(value = ["/repositories/{owner}/{repositoryName}"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getRepositoryInfo(
            @PathVariable("owner") owner: String,
            @PathVariable("repositoryName") repositoryName: String): Flux<GithubRepository> {

        return githubClient.getRepository(owner, repositoryName).flatMap { it -> entityRepository.save(it) }
    }

    @GetMapping(value = ["/repositories/{owner}"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getRepositoriesByOwner(@PathVariable("owner") owner: String): Flux<GithubRepository> {
        return githubClient.getRepositories(owner).flatMap { it -> entityRepository.save(it) }
    }

    @GetMapping(value = ["/repositories/"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getRepositories(): Flux<GithubRepository> {
        return entityRepository.findAll()
    }

}