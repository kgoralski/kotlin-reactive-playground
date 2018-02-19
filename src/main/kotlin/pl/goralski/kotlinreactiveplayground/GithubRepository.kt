package pl.goralski.kotlinreactiveplayground

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class GithubRepository(

        @Id val id: Int,
        val full_name: String,
        val description: String,
        val clone_url: String,
        val stargazers_count: Int,
        val created_at: String
)