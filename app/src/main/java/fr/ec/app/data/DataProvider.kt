package fr.ec.app.data

import com.google.gson.Gson
import fr.ec.app.data.api.response.PostResponse
import fr.ec.app.data.api.response.PostsResponse
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors


object DataProvider {

    private val BACKGOURND = Executors.newFixedThreadPool(2)

    private val POST_API_URL =
        "https://raw.githubusercontent.com/PMR2023/android-app/main/data/posts/posts.json"


    private val gson = Gson()
    fun getData(onSuccess :(List<Post>)->Unit,onError : (Throwable)->Unit)  {
        BACKGOURND.submit {
            try {
                val json :String? = makeCall()
                val postsResponse = gson.fromJson<PostsResponse>(json, PostsResponse::class.java)
                val postList = postsResponse.posts.filter { it.name !=  null && it.tagline != null && it.thumbnail?.url != null }.map {
                    Post(
                        title = it.name.orEmpty(),
                        subTitle = it.tagline.orEmpty(),
                        imageUrl = it.thumbnail?.url ?: ""
                    )
                }

                onSuccess(postList)

            }catch (e :Exception) {
                onError(e)
            }
        }
    }

    private fun makeCall(): String? {
        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        try {
            urlConnection = URL(POST_API_URL).openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            reader = urlConnection.inputStream?.bufferedReader()
            return reader?.readText()

        } finally {
            urlConnection?.disconnect()
            reader?.close()
        }
    }
}