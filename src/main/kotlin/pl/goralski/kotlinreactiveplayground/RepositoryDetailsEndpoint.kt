package pl.goralski.kotlinreactiveplayground

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
@RequestMapping("repositories")
class RepositoryDetailsEndpoint(
        private val githubClient: GithubClient,
        private val entityRepository: ReactiveEntityGithubRepository) {

    @GetMapping(value = ["/{owner}/{repositoryName}"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getRepositoryInfo(
            @PathVariable(OWNER) owner: String,
            @PathVariable(REPOSITORY_NAME) repositoryName: String): Flux<GithubRepository> {

        return githubClient.getRepository(owner, repositoryName).flatMap { it -> entityRepository.save(it) }
    }

    @GetMapping(value = ["/{owner}"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getRepositoriesByOwner(@PathVariable(OWNER) owner: String): Flux<GithubRepository> {
        return githubClient.getRepositories(owner).flatMap { it -> entityRepository.save(it) }
    }

    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getRepositories(): Flux<GithubRepository> {
        return entityRepository.findAll()
    }

    companion object {
        const val OWNER: String = "owner"
        const val REPOSITORY_NAME: String = "repositoryName"
    }
}
