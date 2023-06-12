package fr.ec.app.data.api

import fr.ec.app.data.Post
import fr.ec.app.data.api.response.PostsResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface Services {
    @GET("posts/posts.json")
    suspend fun getPosts() : PostsResponse
}