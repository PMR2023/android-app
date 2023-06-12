package fr.ec.app.data

import com.google.gson.Gson
import fr.ec.app.data.api.Services
import fr.ec.app.data.api.response.PostResponse
import fr.ec.app.data.api.response.PostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors


object DataProvider {


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/PMR2023/android-app/main/data/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val services = retrofit.create(Services::class.java)


    suspend fun getPosts(): List<Post> = withContext(Dispatchers.Default) {
        val postsResponse = services.getPosts()
        val postList =
            postsResponse.posts.filter { it.name != null && it.tagline != null && it.thumbnail?.url != null }
                .map {
                    Post(
                        title = it.name.orEmpty(),
                        subTitle = it.tagline.orEmpty(),
                        imageUrl = it.thumbnail?.url ?: ""
                    )
                }

        postList

    }


}